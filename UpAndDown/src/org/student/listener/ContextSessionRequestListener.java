package org.student.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
//12345
//实现监听器接口
public class ContextSessionRequestListener implements ServletRequestListener,HttpSessionListener,ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("监听ServletContext并销毁"+sce);
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("监听ServletContext并创建"+sce);

	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("监听HttpSession并创建"+se);

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("监听ServletSession并销毁"+se);

	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("监听ServletRequest并销毁"+sre);

	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("监听ServletRequest并创建"+sre);

	}

}
