# API
### 사용기술
* Spring Boot 2.6
* JPA
* MySQL 5.6
* Swagger UI
* Spring Security (개발진행중)


### 실행순서
1. DB 저장소 설정
   * 프로젝트 루트 디렉토리에서 `$ docker-compose up` 로 mysql 구동
   * mysql 설치되어있는 경우 `application.yml`에서 datasource 설정 
     * `${hostname}` 
     * `${port}`
     * `${dbname}`
     * `${username}`
     * `${password}`
   ```
    spring:
      datasource:
        url: jdbc:mysql://${hostname}:${post}/${dbname}?useSSL=false&autoReconnect=true&useUnicode=true&characterEncoding=UTF-8
        username: ${username}
        password: ${password}
        ....
    ```
2. 프로젝트 실행
   `$ java -jar build/libs/account-0.0.1-SNAPSHOT.jar`


### 호출 API 테스트
<http://localhost:8080/api/swagger-ui/index.html>

* 기본 입력된 사용자 정보
```
{
    "id": 1,
    "email": "dodo001@mail.com",
    "tel": "01012341234",
    "name": "도로시",
    "nickname": "dodo001",
    "password": "$2a$10$2WzlIUEqzzqygiex2XJiu.o7NABB9UFrTLdznTYYknY2X0u.OXKju", // plain : pwd1234
    "signUpAt": null,
    "lastUpdateAt": null,
    "role": null
}

{
    "id": 2,
    "email": "oz78@mail.com",
    "tel": "01056785678",
    "name": "오즈",
    "nickname": "오즈의마법사",
    "password": "$2a$10$9VMJY6DX2RTQBy2ojetWdOvR7lMLJRgfMfq/LA47x5wOyyLgGBSK6", // plain : abc1234
    "signUpAt": null,
    "lastUpdateAt": null,
    "role": null
}
```

* 회원가입 API 샘플데이터
```
{
    "email": "testuser@email.com",
    "nickname": "nickuser",
    "password": "abc1234",
    "name": "테스트유저",
    "tel": "01055550000"
}
```







