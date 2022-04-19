package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// levels for Apache Logger
// FATAl < ERROR < WARN < INFO < DEBUG < TRACE

public class AppLog4J {
    public static final Logger logger = LogManager.getLogger(AppLog4J.class);

    public static void main(String[] args) {
        int a = 101;
        logger.trace("trace {}", a); // to show value of 'a' in logs
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal");

    }
}
