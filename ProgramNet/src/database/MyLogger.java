package database;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
	public static final MyLogger singleton = new MyLogger("log_file.txt");
	private FileHandler fh;
	private LogManager lm;
public MyLogger(String file) {
		lm = LogManager.getLogManager();
		try {
			fh = new FileHandler(file);
			fh.setFormatter(new SimpleFormatter());
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
public void log(String className, String msg) {
		try {
			Logger logger = lm.getLogger(className);
			if (logger == null) {
				logger = Logger.getLogger(className);
				logger.setLevel(Level.SEVERE);
				logger.addHandler(fh);
				lm.addLogger(logger);
			}
			logger.severe(msg);
		} catch (Exception e) {
			System.out.println("Exception thrown: " + e);
			e.printStackTrace();
		}}}
