FROM maven:3.8.2-openjdk-17

WORKDIR /restapi
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run