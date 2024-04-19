FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

COPY ./pom.xml /app

RUN mvn dependency:go-offline -Dmaven.includeTransitiveDependencies=true

COPY ./src /app/src

RUN mvn package -Dmaven.test.skip=true

FROM openjdk:17-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8084

CMD ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
