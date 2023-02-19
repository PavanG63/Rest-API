package test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Logs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PropertyConfigurator.configure("log4j.properties");
		Logger logger = Logger.getLogger(Logs.class.getName());
		
		logger.debug("Debugging");
		logger.info("Info for run");

	}

}
