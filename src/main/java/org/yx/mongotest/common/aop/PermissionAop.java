package org.yx.mongotest.common.aop;

import com.fasterxml.jackson.databind.json.JsonMapper;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.yx.mongotest.common.AesEncryptUtils;
import org.yx.mongotest.common.Result;
import org.yx.mongotest.common.annotation.Permission;
import org.yx.mongotest.oauth2Server.authorization.dto.UserAuthorizationDto;
import org.yx.mongotest.security.jwt.JwtProperties;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author yangxin
 */
@Component
@Aspect
@Slf4j
public class PermissionAop {
    @Resource
    private JwtProperties jwtProperties;

    @Resource
    private JsonMapper jsonMapper;

    @Around("@annotation(org.yx.mongotest.common.annotation.Permission)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();

        final List<String> userPermission = Optional.ofNullable(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())).map(m -> {
            String aesCode = Jwts.parser().setSigningKey(jwtProperties.getPrivateKey())
                    .parseClaimsJws(m.getRequest().getHeader("token"))
                    .getBody()
                    .get("authorization", String.class);

            try {
                String s = AesEncryptUtils.decrypt(aesCode, jwtProperties.getPrivateKey());
                log.info("code: {}", s);
                return jsonMapper.readValue(s, UserAuthorizationDto.class).getPermissions();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }).orElseThrow(() -> new RuntimeException("Failed to get ServletRequestAttributes object"));

        Permission annotation = signature.getMethod().getAnnotation(Permission.class);
        Function<Permission.Type, Boolean> function = t -> {
            Stream<String> stream = Stream.of(annotation.value());
            Predicate<String> predicate = all -> userPermission.stream().anyMatch(all::equals);
            if (t == Permission.Type.ALL) {
                return stream.allMatch(predicate);
            }
            return stream.anyMatch(predicate);
        };

        boolean b = function.apply(annotation.type());
        if (!b) {
            return Result.failed("无访问权限");
        }

        // 执行原方法
        return proceedingJoinPoint.proceed();
    }
}
