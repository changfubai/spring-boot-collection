package com.changfubai.springboot.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by changfubai on 2018/3/17
 */
@RestController
public class TestController {

    private Logger logger = LogManager.getLogger(TestController.class.getName());
    @RequestMapping("/")
    public String index() {

        logger.info("test");
        return "testLog4j2";
    }
}
