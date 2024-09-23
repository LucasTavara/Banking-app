FROM maven:3.9.8-amazoncorretto-21 AS build
WORKDIR /app
COPY . .

ARG SPRING_DATASOURCE_URL
ARG SPRING_DATASOURCE_USERNAME
ARG SPRING_DATASOURCE_PASSWORD

# Set the environment variables for the build process
ENV SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}
ENV SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME}
ENV SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD}

# Build the application
RUN mvn clean package -DskipTests

FROM openjdk:21-bullseye
WORKDIR /app

# Expose the port
EXPOSE 8080

# Copy the jar from the build stage
COPY --from=build /app/target/backing-app-0.0.1-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
