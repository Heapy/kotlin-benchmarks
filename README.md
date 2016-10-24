# kotlin-benchmarks
Number of Benchmarks written for Kotlin code using JMH.

## Build

```bash
mvn clean package
```

## Help

```bash
java -jar target/benchmarks.jar -h
```

## Run

```bash
java -jar target/benchmarks.jar -f 10 -wi 10 ModifyingImmutableList
```
