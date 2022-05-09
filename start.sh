#!/bin/bash
export KAFKA_HOST="localhost:9092"
export KAFKA_TOPIC="TOPICO_B"
export KAFKA_GROUP_ID_READER="consumer_1"

export className=IlabExercicioSemana2AwsSqsApplication

java -jar "target/ilab-exercicio-semana2-aws-sqs-0.0.1-SNAPSHOT.jar"