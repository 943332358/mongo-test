package org.yx.mongotest.requestwrapper;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author A
 */
@RestController
@RequestMapping("wrapper")
public class TestWrapperController {

    @RequestMapping("aa")
    public String aa(String pageSize) {
        return "返回：" + pageSize;
    }

    @RequestMapping("bb")
    public String bb(@RequestBody TestWrapper testWrapper) {
        return "返回：" + testWrapper.getPageSize() + " size:" + testWrapper.getSize();
    }

}
