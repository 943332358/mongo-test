package org.yx.mongotest.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.yx.mongotest.yx.dao.MongoDao;
import org.yx.mongotest.yx.entity.User;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author yangxin
 */
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private MongoDao mongoDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = mongoDao.findByName(username).orElseThrow(() -> new UsernameNotFoundException(String.format("not found:%s", username)));
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ADMIN"), new SimpleGrantedAuthority("USER1"), new SimpleGrantedAuthority("USER2"));
    }

}
