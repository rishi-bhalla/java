package com.springframework.sfgdi;

import com.springframework.sfgdi.controllers.*;
import com.springframework.sfgdi.service.PrimaryGreetingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SfgDiApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SfgDiApplication.class, args);

		I18nController i18nController = (I18nController) ctx.getBean("i18nController");
		System.out.println(i18nController.sayHello());

		PrimaryGreetingService primaryGreetingService1 = (PrimaryGreetingService) ctx.getBean("primaryGreetingService");
		primaryGreetingService1.setName("Rishi");
		System.out.println(primaryGreetingService1.getName());

		PrimaryGreetingService primaryGreetingService2 = (PrimaryGreetingService) ctx.getBean("primaryGreetingService");
		System.out.println(primaryGreetingService2.getName());
		primaryGreetingService2.setName("Bhalla");


		System.out.println(primaryGreetingService1.getName() + " : " + primaryGreetingService2.getName());

		MyController myController = (MyController) ctx.getBean("myController");
		System.out.println("-------- Primary Bean");
		System.out.println(myController.sayHello());

		System.out.println("------ Property");
		PropertyInjectedController propertyInjectedController = (PropertyInjectedController) ctx.getBean("propertyInjectedController");
		System.out.println(propertyInjectedController.getGreeting());

		System.out.println("--------- Setter");
		SetterInjectedController setterInjectedController = (SetterInjectedController) ctx.getBean("setterInjectedController");
		System.out.println(setterInjectedController.getGreeting());

		System.out.println("-------- Constructor" );
		ConstructorInjectedController constructorInjectedController = (ConstructorInjectedController) ctx.getBean("constructorInjectedController");
		System.out.println(constructorInjectedController.getGreeting());
	}

}
