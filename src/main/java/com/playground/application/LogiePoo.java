package com.playground.application;

import java.util.logging.Logger;

public class LogiePoo {


    private LogiePoo() {

    }

    private static final Logger logger = Logger.getLogger(LogiePoo.class.getName());


   public static void log(String string){
        logger.warning(string);
    }

}
