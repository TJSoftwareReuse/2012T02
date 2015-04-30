package com.manager.failure;

/**
 * Created by momo on 4/23/15.
 */

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.InputStream;
import java.util.Properties;

public class FailureManager {

    public synchronized static void logInfo(String log){
        logger.info(log);
    }

    public synchronized static void logDebug(String log){
        logger.debug(log);
    }

    public synchronized static void logWarn(String log){
        logger.warn(log);
    }

    public synchronized static void logError(String log){
        logger.error(log);
    }

    public synchronized static void logFatal(String log){
        logger.fatal(log);
    }

    public synchronized static boolean resetOutputFile(String file){
        Properties props = new Properties();
        InputStream in = FailureManager.class.getResourceAsStream("log4j.properties");

        try{
            props.load(in);
            in.close();
        }catch (Exception e){
            System.out.println("Exception: resetOutputFile " + e);
            return false;
        }

        props.setProperty("log4j.appender.FILE.File", file);
        LogManager.resetConfiguration();
        PropertyConfigurator.configure(props);

        return true;
    }

    private static Logger logger = Logger.getLogger(FailureManager.class.getName());
}
