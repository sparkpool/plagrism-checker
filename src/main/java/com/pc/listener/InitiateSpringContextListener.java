package com.pc.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitiateSpringContextListener implements ServletContextListener{

	private static Logger logger = Logger.getLogger(InitiateSpringContextListener.class);
	
	private static ClassPathXmlApplicationContext context;
	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("Spring Context initialization starts");
        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        logger.info("Spring Context initialization ends");		
	}
	
	public static ApplicationContext getContext(){
       return context;		
	}
}
