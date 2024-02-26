#### Especificaciones técnicas: 

 - Spring boot 2.7.14
 - Acceso a la base de datos MariaDB por medio JPA
 - Test
 - Java 11
 - RabbitMQ

### Repositorio
https://github.com/PedroLuisPereira/recargas_sprint_boot


### RabbitMQ
https://www.javaguides.net/2018/12/how-rabbitmq-works-and-rabbitmq-core-concepts.html
https://www.javaguides.net/2022/07/spring-boot-rabbitmq-producer-and-consumer-example.html

### Instalación
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management

### Resilience4j
https://javatechonline.com/how-to-implement-fault-tolerance-in-microservices-using-resilience4j/