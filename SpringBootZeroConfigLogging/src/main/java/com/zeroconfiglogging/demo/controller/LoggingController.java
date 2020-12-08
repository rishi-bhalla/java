package com.zeroconfiglogging.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {

    private static final Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @RequestMapping("/")
    public String getMessage() {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("A INFO Message");
        logger.warn("A WARN Message");
        logger.error("A ERROR Message");

        return "Hello! Check out the Logs to see the output...";
    }
}
