## Topicify API
multi-module Spring Boot project
> modules prefix: tpf-*

### Docker
#### Create docker network:
> docker network create -d bridge topicify-network

### MongoDB
#### MongoDB console:
> docker exec -it #mongo_container_name mongosh

#### Init Mongo replica(simple 1 node, tx support):
> mongosh: $ rs.initiate()

#### Mongo replica status:
> mongosh: $ rs.status()

#### Spring boot property:
> spring.data.mongodb.auto-index-creation=true

### Redis
##### Redis console:
> docker exec -it #redis_container_name bash

> redis-cli 

#### Enable Redis TTL:
> redis-cli: $ CONFIG SET notify-keyspace-events Ex

### Flush records:
> redis-cli: $ FLUSHALL

#### Spring Boot config:
> @EnableRedisRepositories(basePackages = "***", enableKeyspaceEvents = ON_STARTUP)