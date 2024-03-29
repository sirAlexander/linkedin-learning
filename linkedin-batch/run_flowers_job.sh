#!/bin/bash

CURRENT_DATE=$(date '+%Y/%m/%d')
LESSON=$(basename "$PWD")
mvn clean package -Dmaven.test.skip=true;
java -jar -Dspring.batch.job.names=prepareFlowersJob ./target/linkedin-batch-*.jar "run.date(date)=$CURRENT_DATE" "lesson=$LESSON" type=$1;