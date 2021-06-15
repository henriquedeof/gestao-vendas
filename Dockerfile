FROM openjdk:8
EXPOSE 8080
RUN apt-get update && \
 apt-get install -y netcat;
COPY /target/gestao-vendas-0.0.1-SNAPSHOT.jar /app/gestao_vendas.jar
COPY /wait-for-mysql.sh /app/wait-for-mysql.sh
WORKDIR /app
# ENTRYPOINT ["java", "-jar", "gestao_vendas.jar"] #The initialization now is on docker-compose.yml
