package t2.server2.fm;

import com.manager.failure.FailureManager;

public class MyFailureManager {
	
	public static String lastInfoLog = null;
	public static String lastDebugLog = null;
	public static String lastWarnLog = null;
	public static String lastErrorLog = null;
	public static String lastFatalLog = null;
	
	private static boolean isSameLog(String currentLog, String lastLog){
		if(currentLog == null){
			lastLog = null;
			return false;
		}
		if(currentLog.equals(lastLog)){
			return true;
		}
		return false;
	}
	
	public static void logInfo(String log){
		if(!isSameLog(log, lastInfoLog)){
			FailureManager.logInfo(log);
			lastInfoLog = log;
		}
    }

    public static void logDebug(String log){
    	if(!isSameLog(log, lastDebugLog)){
    		FailureManager.logDebug(log);
    		lastDebugLog = log;
    	}
    }

    public static void logWarn(String log){
    	if(!isSameLog(log, lastWarnLog)){
    		FailureManager.logWarn(log);
    		lastWarnLog = log;
    	}
    }

    public static void logError(String log){
    	if(!isSameLog(log, lastErrorLog)){
    		FailureManager.logError(log);
    		lastErrorLog = log;
    	}
    	
    }

    public static void logFatal(String log){
    	if(!isSameLog(log, lastFatalLog)){
    		FailureManager.logFatal(log);
    		lastFatalLog = log;
    	}
    }
    
    public synchronized static boolean resetOutputFile(String filepath){
    	return FailureManager.resetOutputFile(filepath);
    }

}
