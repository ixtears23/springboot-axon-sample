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
