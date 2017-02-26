package com.company;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class Logger {


    // As there will be only one logger at a time,
    // we are implementing the singleton pattern
    // hence declaring the Logger object as static

    static Logger logObj;

    Queue<String> q = new LinkedList();
    private int level;
    private boolean consoleFlag, fileFlag;

    private Logger() {

        // Calling the parameterized constructor using this()
        this("Debug", "both");

    }

    // Parameterized constructor
    private Logger(String logLevel, String appender) {

        // Set the log level in the instance
        setLevel(logLevel);

        // Set the appender
        setAppender(appender);
    }

    // Implementing the singleton design pattern
    public static Logger getInstance() {

        // null is written in small in java
        if (logObj == null) {
            logObj = new Logger();
            return logObj;
        } else {
            return logObj;
        }
    }

    // Set the appender: Where the logs will be printed
    public void setAppender(String appender) {

        if (appender.equalsIgnoreCase("Console")) {
            consoleFlag = true;
            fileFlag = false;
        } else if (appender.equalsIgnoreCase("File")) {
            consoleFlag = false;
            fileFlag = true;
        } else if (appender.equalsIgnoreCase("Both")) {
            consoleFlag = true;
            fileFlag = true;
        }

    }

    // Set the log level
    public void setLevel(String level) {

        if (level.equalsIgnoreCase("error"))
            this.level = 1;
        else if (level.equalsIgnoreCase("warning"))
            this.level = 2;
        else if (level.equalsIgnoreCase("info"))
            this.level = 3;
        else if (level.equalsIgnoreCase("debug"))
            this.level = 4;

    }

    // Method to log debug messages
    public void debug(String msg) {

        if (this.level >= 4) {

            msg = generateMsg('D', msg);

            handleMsg(msg);
        }
    }

    // Method to log info messages
    public void info(String msg) {

        if (this.level >= 3) {

            msg = generateMsg('I', msg);

            handleMsg(msg);
        }
    }

    // Method to log Warnings
    public void warning(String msg) {

        if (this.level >= 2) {

            msg = generateMsg('W', msg);

            handleMsg(msg);

        }
    }

    // Method to log errors
    public void error(String msg) {

        if (this.level >= 1) {

            msg = generateMsg('E', msg);
            handleMsg(msg);
        }
    }

    // Method to generate the message
    private String generateMsg(char msgType, String msg) {
        return (msgType + ": " + getDate() + ": " + msg);
    }

    // Finish method is called when the program finishes
    public void finish() {

        System.out.println(q);
        printToFile();
    }

    // Method to handle the message: Store in file or print to console
    private void handleMsg(String msg) {

        if (fileFlag == true) {

            q.add(msg);

            if (q.size() == 100) {
                printToFile();
            }
        }

        if (consoleFlag == true)
            System.out.println(msg);

    }

    // Method to print to the file
    private void printToFile() {
        LogToFile lfObj = new LogToFile(q);
        q.clear();
    }

    // Method to return the date
    private String getDate() {

        Date dObj = new Date();
        return (dObj.toString());
    }

}
