# Metric Streaming App

## Order of Operations

1. Filtering
2. Grouping
3. Aggregation

## How to run the application

### Using Maven and Java

```sh
mvn clean install
```

```sh
java -jar metricStreaming-0.0.1-SNAPSHOT.jar
```

### Using Maven and Docker

```sh
mvn clean package
```

```sh
docker build -t metric-streaming-app .
```

```sh
docker run --name metric-streaming-app-container --rm -p 8080:8080 metric-streaming-app
```
