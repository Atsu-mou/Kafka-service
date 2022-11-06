# Docker Kafka

## Requirements

For building and running the application you need:

- [Docker](https://docs.docker.com/desktop/install/mac-install/)

## Running the application locally

First to run the app locally, you need to create the required componet. For this app you need to execute the command bellow.
```shell
docker-compose up -d
```

## Looking inside Kafka
You can have a look inside the Kafka with the command bellow
```shell
# Check if docker container exists
docker ps 
docker exec --interactive --tty broker kafka-console-consumer --bootstrap-server broker:9092   -topic topic1 --from-beginning

# If you want to create a topic
docker exec broker \
kafka-topics --bootstrap-server broker:9092 \
             --create \
             --topic quickstart
```