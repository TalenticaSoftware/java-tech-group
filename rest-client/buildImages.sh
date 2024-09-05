mvn clean install -DskipTests

cd basic-http-code
docker build -t basic-http-code .

cd ../demo-service
docker build -t demo-service .

cd ../feign-client
docker build -t feign-client .

cd ../rest-client
docker build -t rest-client .

cd ../rest-template
docker build -t rest-template .

cd ../web-client
docker build -t web-client .