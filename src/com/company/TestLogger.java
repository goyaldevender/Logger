package com.company;

/**
 * Created by temp on 07/02/17.
 */
public class TestLogger {

    public static void main(String[] args) {

        // Here the Logger class has the private constructors,
        // hence we can't create object using new keyword.
        Logger log;

        // Getting the instance of logger
        log = Logger.getInstance();

        log.debug("This is a debug message");
        log.info("This is an info message");
        log.warning("This is a warning message");
        log.error("This is an error message");

        log.finish();
    }
}
