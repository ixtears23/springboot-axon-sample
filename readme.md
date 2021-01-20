# board




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
- 'SQL' 은 바로 위에 설명한 `show-sql` 과 `format_sql` 을 합한 것과 같고 `logger`로 출력 됨.
- `BasicBinder` 는 파라미터 타입과 값을 출력함. 



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