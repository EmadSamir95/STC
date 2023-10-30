FROM maven:3.6.3-openjdk-17 AS build

WORKDIR /app
COPY SystemDesign/src /app/src
COPY SystemDesign/pom.xml /app

RUN mvn dependency:go-offline && \
    mvn clean package && \
    mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/app.jar .
ARG DEPENDENCY=/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
CMD java -jar -Doracle.jdbc.timezoneAsRegion=false app.jar