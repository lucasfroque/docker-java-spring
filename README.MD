<h1 align="center" id="title">Docker + Springboot</h1>

<p align="center"><img src="https://socialify.git.ci/lucasfroque/docker-java-spring/image?description=1&amp;descriptionEditable=Running%20a%20java%2Bspring%20application%20using%20docker&amp;font=Inter&amp;language=1&amp;owner=1&amp;pattern=Plus&amp;theme=Light" alt="project-image"></p>

<p id="description">This repository demonstrates how to run Springboot applications using docker</p>

<h2>💻 Built with</h2>

Technologies used in the project:

*   Java
*   Springboot
*   PostgreSQL
*   Docker

<h2>🛠️ Build steps:</h2>

<h4>Build API using Springboot</h4>
<p>As an example of an application that we can dockerize, we will create a simple Spring Boot application, Student API, which exposes the POST endpoint to create a new student and the GET endpoint to return all students:</p>

```java
      @GetMapping
public ResponseEntity<List<Student>> findAll(){
        List<Student> list = service.findAll();
        return ResponseEntity.ok().body(list);
        }


@PostMapping
    ResponseEntity<Student> insert(@RequestBody Student student){
        Student response = service.insert(student);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
        }
```
<p>With a correctly configured Maven file, we can then create an executable jar file:</p>

```bash
mvn clean install
```
<p>
Now we have a working Spring Boot application that we can access at localhost:8080/students.
</p>

<h3>Create Dockerfile</h3>
```yaml
FROM openjdk:17

ARG PROFILE

ENV APP_PROFILE=${PROFILE}

WORKDIR /project/spring-docker

COPY /target/springdocker-0.0.1-SNAPSHOT.jar springdocker-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","springdocker-0.0.1-SNAPSHOT.jar"]
```
**This file contains the following information:**

* **FROM**: As the base for our image, we will use openjdk-17.
* **ENV**: We use ENV values to set profiles that we will use in src/main/resources (Dev or Test).
* **COPY**: We let Docker copy our jar file into the image.
*  **ENTRYPOINT**: This will be the executable to start when the container is booting. We must define them as JSON-Array because we'll use an ENTRYPOINT in combination with a CMD for some application arguments.


<h3>Build and run Docker image</h3>

Build docker image
```bash
docker build --tag=springdocker:latest .
```
Run the container from our image
```bash
docker run springdocker:latest -p 8080:8080
```
This will start our application in Docker, and we can access it from the host machine at localhost:8080/students.

<h3>Dockerize Applications in a Composite</h3>

This tool comes with its own build-file in YAML format, and is better suited for managing multiple containers. It's able to start or stop a composite of services in one command.

**The Docker Compose File**
We can combine the configuration for PostgreSQL and Springboot in one file:

```yaml
version: '3'
volumes:
  data:
services:
  db:
    image: postgres
    ports:
      - "5432:5432"
    environment:
      - POSTGRES_PASSWORD=1234
      - POSTGRES_USER=postgres
      - POSTGRES_DB=springdb
      - PGDATA=/var/lib/postgresql/data/pgdata
    volumes:
      - data:/var/lib/postgresql/data
  app:
    container_name: app-springdocker
    image: app-springdocker
    build:
      context: ./
      dockerfile: ./Dockerfile
    ports:
      - "8080:8080"
    environment:
      APP_PROFILE: dev
    depends_on:
      - db
```

<h3>Run Docker compose</h3>

```shell
docker-compose up
```

And then the application and database will be started:

```shell
Starting docker-java-spring_db_1 ...
Starting docker-java-spring_db_1 ... done
Starting  app-springdocker ...
Starting  app-springdocker ... done
```

## API Documentation


#### Returns all students

```http
  GET /api/items
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

#### Save a student in the database

```http
  POST /students
```

| Parâmetro   | Tipo       |
| :---------- | :--------- | 
| `name`      | `string` | 
| `age`      | `byte` | 
| `gender`      | `String` | 