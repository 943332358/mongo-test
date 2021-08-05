package org.yx.mongotest.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yangxin
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping(value = "test",
            produces = "text/plain",
            consumes = "application/json",
            headers = "testHeaders",
            params = "paramsTest!=1"
    )
    public String test(@RequestBody String test, HttpServletRequest request) {
        System.out.println(request.getContentType());
        System.out.println(test);
        return "test";
    }

}
