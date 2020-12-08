https://github.com/in28minutes/spring-microservices
http://localhost:8080/limits
http://localhost:8888/limits-service/default
http://localhost:8000/currency-exchange/from/USD/to/INR
http://localhost:8100/currency-converter/from/USD/to/INR/quantity/1000
http://localhost:8100/currency-converter-feign/from/USD/to/INR/quantity/1000

http://localhost:8761/


http://localhost:8765/currency-exchange-service/currency-exchange/from/USD/to/INR
http://localhost:8765/currency-conversion-service/currency-converter-feign/from/EUR/to/INR/quantity/1000

feign
ribbon
eureka server, client
zuul proxy
spring cloud sleuth
zipkin distributed tracing server


set RABBIT_URI=amqp://localhost
java -jar zipkin-server-2.7.0-exec.jar

https://search.maven.org/remote_content?g=io.zipkin&a=zipkin-server&v=LATEST&c=exec
http://localhost:9411/zipkin/

spring-cloud-config-server: change value of the property spring.cloud.config.server.git.uri=E:/Rishi/workspace/java/spring-microservices/demo/git-localconfig-repo **


POST http://localhost:8080/application/refresh
POST http://localhost:8080/bus/refresh

spring.datasource.url=jdbc:h2:mem:testdb