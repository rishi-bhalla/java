This is a spring mvc application using spring framework 5.

A simple application that creates a mapping between authors, books and publishers and loads the .html pages using the spring thymeleaf template engine.

The templates directory contains html pages for books and authors respectively.

The tutorials followed for this application is John Thompson's spring framework 5 guru. 

https://github.com/springframeworkguru
https://github.com/springframeworkguru/spring5-jokes-app/

To run the application, run the following commands:
1. mvn clean install
2. mvn spring-boot:run (alternative you can also run the main application class from the IDE you are using)

Once the application has started, the output can be seen on the following URL's:

http://localhost:8080/authors
http://localhost:8080/books