mvn clean install -DskipTests

cd cache-local  
docker build -t cache-local . 

cd ../cache-standalone
docker build -t cache-standalone .

cd ../cache-clustered
docker build -t cache-clustered .

cd ../cache-read-write-through
docker build -t cache-read-write-through .

cd ../spring-cache-native
docker build -t spring-cache-native .

cd ../spring-cache-jsr
docker build -t spring-cache-jsr .