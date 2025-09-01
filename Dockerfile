
# Build stage
FROM gradle:8.7-jdk17 AS build
WORKDIR /home/gradle/project
COPY . .
RUN gradle clean bootJar --no-daemon

# Run stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /home/gradle/project/build/libs/optima360-activemq-*.jar app.jar
ENV JAVA_OPTS=""
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
