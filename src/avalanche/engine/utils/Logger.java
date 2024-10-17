package avalanche.engine.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	
	public static Logger instance = null;
	private static final String LOG_FILE_NAME = "AvalancheEngine.log";
	
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[1;32m";
    public static final String ANSI_CYAN = "\u001B[36m";
	
	public static enum logLevel{
		INFO,WARNING,ERROR,DEBUG
	}
	
	public static Logger get() {
		if (instance == null) {
			instance = new Logger();
		}
		
		return instance;
	}
	
	public Logger() {
		if (instance == null) {
			instance = this;
		}
	}
	
	public static void toLogFile(logLevel level,String text) {
		try {
			String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		    String formattedMessage = String.format("[%s] [%s] %s ", timestamp, level.name(), text);
		    
			BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_NAME,true));
			writer.write(formattedMessage);
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void toConsole(logLevel level, String message,boolean hideLogInfo) {
		String consoleColour = "";
	    switch (level) {
	        case INFO:
	            consoleColour += ANSI_CYAN;
	            break; 
	        case WARNING:
	            consoleColour += ANSI_YELLOW;
	            break;
	        case ERROR:
	            consoleColour += ANSI_RED;
	            break;
	        case DEBUG:
	            consoleColour += ANSI_GREEN;
	            break;
	        default:
	            consoleColour += ANSI_RESET;
	            break;
	    }
		
		if (!hideLogInfo) {
		    String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		    String formattedMessage = String.format("%s [%s] %s", timestamp, level.name(), message);
		    System.out.println(consoleColour + formattedMessage + ANSI_RESET);
		}else {
		    String formattedMessage = String.format("%s",message);
		    System.out.println(consoleColour + formattedMessage + ANSI_RESET);
		}

	    
	}
	
	public static void toConsole(logLevel level, String message) {
	    String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	    String formattedMessage = String.format("%s [%s] %s", timestamp, level.name(), message);
	    String consoleColour = "";

	    switch (level) {
	        case INFO:
	            consoleColour += ANSI_CYAN;
	            break; 
	        case WARNING:
	            consoleColour += ANSI_YELLOW;
	            break;
	        case ERROR:
	            consoleColour += ANSI_RED;
	            break;
	        case DEBUG:
	            consoleColour += ANSI_GREEN;
	            break;
	        default:
	            consoleColour += ANSI_RESET;
	            break;
	    }

	    System.out.println(consoleColour + formattedMessage + ANSI_RESET);
	}
}
