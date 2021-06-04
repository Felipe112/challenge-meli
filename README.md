## challenge-meli
Prueba presentada para evaluar el conocimiento de un desarrollador backend, por parte del equipo de Mercadolibre.

##Descripción
Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Men.

Te ha contratado a ti para que desarrolles un proyecto que detecte si un humano es mutante basándose en su secuencia de
 ADN.
 
Para eso te ha pedido crear un programa con un método o función con la siguiente firma  
##### boolean isMutant(String[] dna);
En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla
de (NxN) con la secuencia del ADN.

Las letras de los Strings solo pueden ser: (A,T,C,G), las cuales representa cada base nitrogenada del ADN.

Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras, de forma oblicua, horizontal o vertical.

### Desafio

#### Nivel 1
Programa (en cualquier lenguaje de programación) que cumpla con el método pedido por
Magneto.
#### Nivel 2
Crear una API REST, hostear esa API en un cloud computing libre (Google App Engine,
Amazon AWS, etc), crear el servicio “/mutant/” en donde se pueda detectar si un humano es
mutante enviando la secuencia de ADN mediante un HTTP POST con un Json el cual tenga el
siguiente formato:

POST => /mutant/

{

“dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]

}

En caso de verificar un mutante, debería devolver un HTTP 200-OK, en caso contrario un
403-Forbidden
#### Nivel 3
Anexar una base de datos, la cual guarde los ADN’s verificados con la API.
Solo 1 registro por ADN.
Exponer un servicio extra “/stats” que devuelva un Json con las estadísticas de las
verificaciones de ADN: 

{

“count_mutant_dna”:40,

 “count_human_dna”:100:,
 
  “ratio”:0.4
  
 }
 
Tener en cuenta que la API puede recibir fluctuaciones agresivas de tráfico(Entre 100 y 1 millón de peticiones por segundo).

#Solución concurrencia.
Para lograr soportar el volumen y magnitud de peticiones, se propone implementar una arquitectura con un enfoque de escalabilidad
horizontal en el cual se logre desplegar las funcionalidades en un ambiente Cloud.

Al realizar un despliegue en la nube se puede contar con la elasticidad de las tecnologias, lo que facilita 
proponer soluciones de arquitectura que se ajusten a una alta demanda de recursos. 

Existen otro factores que tambien es bueno conciderar como los canales y anchos de banda, para que al momento de implementar
la arquitectura, no se posean cuellos de botella, de igual forma en un sistema con alto flujo de peticiones es bueno identicar
el tipo de peticiones para que los recursos propuestos esten completamente optimizados y saber si la carga de escritura es mayor que 
la de lectura o viseversa.

![img](https://docs.aws.amazon.com/autoscaling/ec2/userguide/images/elb-tutorial-architecture-diagram.png)



## Entorno
* Java 11
* IDE: Intellij IDEA
* Gradle 

## Dependencias
* [JUnit](https://junit.org/junit5/) 
* [Lombok](https://projectlombok.org)
* [Swagger](https://swagger.io)
* [JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [H2](https://www.baeldung.com/spring-boot-h2-database)
* [Sprint Boot](https://spring.io/projects/spring-boot)




