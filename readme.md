# board
> AxonFramework 및 향후 표준 프로젝트 구성을 위한 프로토타입 및 샘플

## 실행방법
### mariadb 환경
- profile = mariadb

### h2 database 환경
- profile = h2db

---

## H2 Database Engine
> Java로 작성된 관계형 데이터베이스 관리 시스템  
> Java SQL 데이터베이스
- 매우 빠른 오픈 소스 JDBC API
- Embedded 및 server 모드; in-memory database
- 브라우저 기반 콘솔 애플리케이션
- 작은 설치 공간 : 약 2MB의 jar 파일 크기

## Axon Table 관련 정보 위치
- `src/main/resources/META-INF/orm.xml`

## Axon Table 자동 생성 방법
`spring-boot-starter-data-jpa` 의존성을 추가해 줘야 함. 

~~~xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
~~~


## Axon Serializers
> 기본적으로 Axon은 XStream을 사용하여 XML로 직렬화하는 XStreamSerializer 사용  
> JSON으로 직렬화하는 JacksonSerializer도 제공. 대신 클래스가 Jackson이 요구하는 규칙(구성)을 고수해야함.

- `XStreamSerializer`
- `JacksonSerializer`
- `JavaSerializer`


## SQL log

### hibernate 로그
~~~yaml
spring:
  jpa:
    show-sql: true
    properties:
      hibernate:
        format_sql: true  # hibernate 쿼리 상세 출력
~~~

- `show-sql`은 한줄로 출력 됨.
- `format_sql`은 상세히 출력 됨. 파라미터값은 나오지 않음.

### hibernate 로그(로거로 출력됨)
~~~yaml
logging:
  level:
    org:
      hibernate:
        SQL: DEBUG
        type:
          descriptor:
            sql:
              BasicBinder: TRACE
~~~
- `SQL` 은 바로 위에 설명한 `show-sql` 과 동일. `logger`로 출력 됨.  
  바로 위에 설명한 `format_sql` 설정을 해주지 않으면 `logger`도 한줄로 출력 됨.
- `BasicBinder`는 파라미터 타입과 값을 출력함. 



### data.sql, schema.sql 로그
~~~yaml
logging:
  level:
    org:
      springframework:
        jdbc:
          datasource:
            init:
              ScriptUtils: DEBUG  # data.sql, schema.sql 쿼리 로그 레벨
~~~


### Interceptor 처리시 코딩 방법

> Interceptor의 경우 각 Command, Event를 제외한 별도 Interceptor 클래스를 생성해서 사용  
> 개발자에게 Interceptor의 자유를 주면 좋지 않을 듯..  
> 그런데 여기서 생각해봐야 할 것이 모든 서비스에 Interceptor를 별도 구현한다. 말이 안될 수도..  
> 모든 서비스의 공통부분은 API Gateway를 통해서 구현되거나 하나의 Library를 생성해서 그 Library의 종속성을 포함시킴.  
> 갑자기 든 생각으로는 각 서비스에 Interceptor가 존재해야할 이유는 Domain별 서비스 로직이 다르기 때문일 수도 있을 듯.  

> 인사에만 있을 수 있는 공통 로직에 대한 Interceptor  
> 회계에만 있을 수 있는 공통 로직에 대한 Interceptor  
> 구매 및 자산에만 있을 수 있는 Interceptor  

#### Conclusion
> - 모든 서비스에 대한 공통로직은 API Gateway 또는 공통 Library 생성  
> - 각 업무에 특화된 공통로직은 각 서비스별 Interceptor 구현

