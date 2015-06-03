package com.manager.failure;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;


public class FailureManager {
	
	private static Logger logger = Logger.getLogger(FailureManager.class.getName());
//	private static int counter = 0;
//	private static String fakenow = "0000-00-00_00-00-00";
	private static String filepath = ".";
	private static String lastlog = null;
	private static Boolean repetition = false;
	
	
	public static void logInfo(String log){
		log(log, "INFO");
    }

    public static void logDebug(String log){
    	log(log, "DEBUG");
    }

    public static void logWarn(String log){
    	log(log, "WARN");
    }

    public static void logError(String log){
    	log(log, "ERROR");
    }

    public static void logFatal(String log){
    	log(log, "FATAL");
    }
    
    private static void log(String log, String level){
//    	String filename = generateUniqueName();
    	System.out.println("log		"+log);
    	System.out.println("last	" + lastlog);
    	if(!FailureManager.repetition && log.equals(FailureManager.lastlog)){
    		System.out.println("no output");
    		logger.warn("Repetation message" + log);
    	}else{
        	writeToFile(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " " + level+" "+log);
        	lastlog = log;
    	}
    	
    }

    public synchronized static boolean resetOutputFile(String filepath){
    	try{
    		File file = new File(filepath);
    		File parent = file.getParentFile();
    		
    		if(parent != null && !parent.exists()){
    			file.getParentFile().mkdirs();
    		}
    		
        	FailureManager.filepath = filepath;
        	logger.info("Done: reset log directory to "+filepath);
        	return true;
    	}catch(Exception e){
    		e.printStackTrace();
    		logger.error("Failed reset log directory to "+filepath);
    		return false;
    	}
    }
    
    public synchronized static void setRepetation(Boolean setting){
    	FailureManager.repetition = setting;
    	if(FailureManager.repetition)
    		logger.info("Repetation setting now is TRUE");
    	else 
    		logger.info("Repetation setting now is FALSE");
    }
    
//    private synchronized static String generateUniqueName(){
//    	String filename = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
//		if(!FailureManager.fakenow.equals(filename)){
//			FailureManager.counter = 0;
//			FailureManager.fakenow = filename;
//		}
//		filename += "-"+Integer.toString(FailureManager.counter++)+".log";
//		return filename;
//    }
    
    private synchronized static void writeToFile(String log){
		try {
			PrintWriter printwriter = new PrintWriter(new FileOutputStream(new File(FailureManager.filepath), true));
			printwriter.println(log);
			printwriter.close();
			logger.info("Done: write to file "+FailureManager.filepath);
//			logger.debug("Log: "+log); 
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Failed: write to file "+FailureManager.filepath);
		}
    }
}
