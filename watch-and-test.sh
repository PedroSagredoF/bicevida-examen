fswatch -o src/main/java src/test/java | while read change; do
    mvn test
done