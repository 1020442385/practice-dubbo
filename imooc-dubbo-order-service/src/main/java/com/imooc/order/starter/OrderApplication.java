package com.imooc.order.starter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OrderApplication {

	//线程启动方式
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"classpath:spring/spring-context.xml"});
        context.start();
        // press any key to exit
        System.in.read();
	}

}
