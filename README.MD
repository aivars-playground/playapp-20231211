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
docker stop playapp
docker run --rm --name playapp -p 9000:9000 -d playapp:1.0-SNAPSHOT
```

```shell
 docker run -d -p 5000:5000 --name registry registry
```
```shell
docker image tag playapp:1.0-SNAPSHOT localhost:5000/playapp
docker push localhost:5000/playapp:latest
```
```shell
docker stop play3-exploration
docker run --rm --name playapp -p 9000:9000 -d localhost:5000/playapp:latest
```
