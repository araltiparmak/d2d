# d2d - fullStack

**Used Tech Stack**
|Tech|
|------|
| Kotlin      | 
| Spring Boot |
| MongoDB     |
| React       |
| Reactive Web Sockets  |
| Docker, Docker Compose|

**Running the application**<br/>

**Docker**
```
$ docker-compose up  
```

**Manual** 

**Api**
```
$ cd d2d-api
$ gradle build
```


**Web**
```
$ cd d2d-web
$ yarn build
$ yarn start
```

**Api:** http://localhost:8080 

**Web:** http://localhost:3000

**Simulator**: https://github.com/araltiparmak/d2d-code-challenges/tree/master/resources/driver-simulator
<br/>
<br/>

**Endpoints**

| Method | Endpoint | Body | Response | Description |
| -----------| --------------|----|----|------------ |
| POST | /vehicles	| ```{"id":"some-uuid"}``` |  204 - no body  | Vehicle Registration |
| DELETE | /vehicles	|```{"id":"some-uuid"}``` |  204 - no body  | Vehicle De-Registration |
| POST | /vehicles/:id/locations	|```{"lat":52.54, "lng":13.413, "at":"2020-07-02T10:10:17+0200", "id":"some-uuid"}```| 204 - no body  |  Vehicle Location Update |
