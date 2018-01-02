package com.xmlangel.selenium.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
  public static Logger logger = LoggerFactory.getLogger(Log.class);

  public static void main(String[] args) {
    logger.trace("trace");
    logger.debug("debug");
    logger.info("info");
    logger.warn("warn");
    logger.error("error");
  }
}
