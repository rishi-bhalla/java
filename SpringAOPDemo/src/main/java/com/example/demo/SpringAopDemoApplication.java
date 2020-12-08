package com.example.demo;

import com.customer.dao.CustomerDAO;
import com.customer.dao.impl.CustomerDAOImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"spring-customer.xml"})
public class SpringAopDemoApplication {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(SpringAopDemoApplication.class, args);

        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerBo");
        customerDAO.addCustomer();
        customerDAO.addCustomerReturnValue();
        customerDAO.addCustomerAround("Rishi Bhalla");
        customerDAO.addCustomerThrowException();
    }

}
