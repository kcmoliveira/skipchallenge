# Skipchallenge
This provide API microservices for a product delivery. It contains:

  - Docker for MySQL and RabbitMQ (the last one is to be done);
  - One microservice for authentiation;
  - One microservice for creating users accounts;
  - One microservice for creating restaurants;
  - One microservice for creating products for the restaurants;
  - One microservice for order products;

The project was developed using TDD, which means there are unit tests for all microservices.
# Testing the microservices
In order to test the microservices, first it's necessary to start the docker container. Do as follow to start.
```sh
$ cd docker
$ docker docker-compose up
```