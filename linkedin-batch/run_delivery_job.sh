CURRENT_DATE=$(date '+%Y/%m/%d')
LESSON=$(basename "$PWD")
mvn clean package -Dmaven.test.skip=true;
java -jar ./target/linkedin-batch-*.jar "item=shoes" "run.date(date)=$CURRENT_DATE" "lesson=$LESSON";
read -r;