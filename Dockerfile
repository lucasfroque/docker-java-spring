FROM openjdk:17

ENV APP_PROFILE=${PROFILE}

WORKDIR /project/spring-docker

COPY /target/springdocker-0.0.1-SNAPSHOT.jar springdocker-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","springdocker-0.0.1-SNAPSHOT.jar"]