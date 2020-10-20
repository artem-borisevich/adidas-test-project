FROM maven:3.6.3-jdk-11 as builder
WORKDIR /app
COPY ./ ./
RUN mvn clean -DskipTests package

FROM amazoncorretto:11
COPY --from=builder /app/target/subscription-service-*.jar /usr/src/app.jar
WORKDIR /usr/src
ENTRYPOINT ["sh","-c","java -jar app.jar"]