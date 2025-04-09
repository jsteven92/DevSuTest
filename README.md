Como instalar:

1) descargar los proyectos Client-Person y bank, estos son los 2 microservicios
2) compilar el proyecto Client-Person con : mvn clean install
3) ejecutar el Dockerfile para crear la image del proyecto client-person, debe estar situado en la raiz del proyecto : docker build -t "image-client-app" .
4) compilar el proyecto bank con : mvn clean install
3) ejecutar el Dockerfile para crear la image del proyecto bank, debe estar situado en la raiz del proyecto : docker build -t "image-bank-app" .
4) validar la creacion de las imagenes : -> docker images
5) descargar el archivo docker-compose.yml
6) ejecutar en la ruta donde este el docker-compose.yml -> docker-compose up -d

   
Nota: esto subira el apache kafka, Mysql y creara 2 esquemas (service1 y service 2), zookeeper y los dos microservicios llamados account-movement y client-person

Postman
en la ruta DevSu/Postman estan las collections y el environment, importarlos al Postman

Para crear cuentas el type debe ser: SAVING,CHECKING. El status permitido son : ACTIVE,INACTIVE,SUSPEND (cualquier otro sera rechazado por validación @valid).

para crear movimientos a una cuenta se debe ingresar en el campo type TRANSFER_SAME_BANK o TRANSFER_OTHER_BANK si se desea debitar (restar al saldo) y DEPOSIT si se desea acreditar (sumar al saldo) (cualquier otro sera rechazado por validación @valid).

para crear un cliente el parametro gender soporta MASCULINO Y FEMENINO (cualquier otro sera rechazado por validación @valid).

BD
en la ruta DevSu/DB esta un archivo llamda BaseDatos.sql esta contiene entidades y esquema datos de los dos proyectos
Nota: no se requiere porque el proyecto esta con JPA que se encarga de crear las tablas y los esquemas los crea docker por defecto


PRACTICAS IMPLEMENTADAS

1) ARQUITECTURA HEXAGONAL: 3 capas infraestructure, usecase y domain con una capa anticorrupcion entre domain y infraestructure (Patron ADAPTER e implementacion de port). entre el dominio y la infraestructura.
2) PATRON REPOSITORY : por medio de port para tener la independencia entre capas y no permitir una asociacion rigida
3) PORTABILIDAD: al separar la logica de BD, para poder migrar facilmente a otra BD sin necesidad de afectar las otras capas (src/main/java/com/devsu/infraestructure/database/mysql)
4) MICROSERVICIOS: 2 servicios que se comunican con el componente spring-cloud-starter-openfeign. Ruta bank/src/main/java/com/devsu/bank/infraestructure/client/DataClient.java componente encargado la comunicacion  bank->client
5) COMUNICACIÓN ASINCRONICA: por medio de docker se levanta Kafka. en el proyecto   bank/src/main/java/com/devsu/bank/infraestructure/config se tiene las configuraciones para producir mensajes y crear topic (tunel para la comunicacion)
   y en la ruta bank/src/main/java/com/devsu/bank/infraestructure/events esta la estrategia para el envio de mensajes al bus y el manejo de un Event con la data para facil manejo en los Topic NOTA: debido a que presento un error al momento de escribir en kafka se comento esta funcionalidad
   bank/src/main/java/com/devsu/bank/usecase/AccountInteractorImpl.java -> linea 39 encargada de crear el mensaje
6) MANEJO DE EXEPCIONES: bank/src/main/java/com/devsu/bank/infraestructure/exception
7) BUENAS PRACTICAS:
   * el no uso de @Autowrite , se le da prioridad a la inyeccion por contructor
   * apoyo de lomnbok
   * se tiene en cuenta principios de DRY (bank/src/main/java/com/devsu/bank/usecase/MovementInteractorImpl.java linea 65) se es conciente de evitar la duplicidad de codigo al crear metodo validAccount
   * se respeta la inversion de control, responsabilidad unica y sustitucion de Liskov
8) test unitarios Client-Person/src/test/java/com/devsu/usecase/ClientInteractorImplTest.java
9) test de integración: Client-Person/src/test/java/com/devsu/infraestructure/rest/controller/ClientRestControllerTest.java
10) despliegue con Docker

Funciones desarrolladas

   F1, F2, F3, F5, F6 y F7
   F4 -> no se realiza el reporte solicitado, pero se tiene API REST para listar listar movimientos de una cuenta


TEMAS POR MEJORAR

1) implementacion de un service discovery y un api-gw que se encargara del acceso y poder subir instancias para aplicar balanceo (Eureka)
2) Auth2 para validar la comunicacion entre los microservicios
3) no se realiza pruebas con KARATE por no conocer mucho de esta herramienta

