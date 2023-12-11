```shell
sbt run
```
```shell
sbt test
```
```shell
sbt integration/test
```
```shell
sbt features/test
```
```shell
sbt performance/Gatling/test
```
```shell
sbt docker:publishLocal
```
```shell
docker stop play3-exploration
docker run --rm --name play3-exploration -p 9000:9000 -d play3-exploration:1.0-SNAPSHOT
```

```shell
 docker run -d -p 5000:5000 --name registry registry
```
```shell
docker image tag play3-exploration:1.0-SNAPSHOT localhost:5000/play3-exploration
docker push localhost:5000/play3-exploration:latest
```
```shell
docker stop play3-exploration
docker run --rm --name play3-exploration -p 9000:9000 -d localhost:5000/play3-exploration:latest
```
