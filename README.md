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

## Assumptions

- Metric should only have value and timestamp as fields.
- There can be multiple operations in a request.
- There can be multiple filters applied in the same request.
- There can be only ONE aggregation and ONE grouping transformation in the same request.

## Out of scope

- Object return type is used to keep the application simple and save time.
- Very limited Unit tests: Due to lack of time.
- Integration tests: Due to lack of time.
- Exception handling: Due to lack of time.
- Multiple aggregators and groupers.
- No DB integration.
