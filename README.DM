
mvn clean package -DskipTests

docker build --no-cache --build-arg JAR_FILE=target/mongodb-0.0.1-SNAPSHOT.jar -t mongo-service .

docker run --name spring-boot-app --network hom e-work-project_mongo-net -e SPRING_PROFILES_ACTIVE=docker -p 8180:8180 mongo-service


------------------
consul

# Или если нужен именно server режим, создайте папку в temp, и подгрузить конфиг
mkdir -p /tmp/consul-data
./consul agent -server -bind=192.168.0.103 -client=0.0.0.0 -ui -data-dir=/tmp/consul-data -bootstrap





консул к grpcService
мониторинг и трейсинг прекрутить
кейклок
кафка
и расскрутить монго дб и grpc еще!