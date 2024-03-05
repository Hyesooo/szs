# 삼쩜삼 과제
> 회원가입, 로그인, 스크래핑, 결정세액 조회 API 구현
> 

## 개발환경 및 참고사항
  - Java version 17
  - Springboot 3.2.3
  - H2 Embedded In-memory mode
  - JPA
  - Gradle
  - Swagger
  - JWT Token
  - Bcrypt(비밀번호)/TextEncryptor(주민번호)
  - OpenFeign
    
회원가입/로그인 API 구현을 위해 SpringSecurity + JWT를 사용했으며 비밀번호는 단방향 암호화, 주민번호는 양방향 암호화하여 저장했습니다.  
스크래핑은 FeignClient이용하여 외부 통신했으며 스크래핑 api요청이 선행되어야 결정세액이 계산될 수 있는 구조입니다.



# **프로젝트**

### 패키지 구조
이 아키텍처는 `만들면서 배우는 클린 아키텍처`에서 제안된 표현력 있는 패키지 구조를 기반으로 합니다. 그러나 애플리케이션의 규모를 고려할 때, 여러 인커밍 어댑터가 필요할 가능성이 낮습니다.  
따라서, 불필요한 추상화 계층을 제거하기 위해 인커밍 포트를 사용하지 않기로 결정했습니다.

<img width="632" alt="스크린샷 2023-12-28 14 00 01" src="https://github.com/2zikk/twitter/assets/25236852/bd8211c3-7df9-4a9b-9319-03ee03b6fb4f">

`adapter` : 애플리케이션 계층의 인커밍 포트를 호출하는 인커밍 어댑터와 애플리케이션 계층의 아웃고잉 포트에 대한 구현을 제공하는 아웃고잉 어댑터를 포함합니다.  
`domain` : 도메인 모델이 속한 패키지입니다.  
`application` : 도메인 모델을 둘러싼 서비스 계층을 포함합니다. 인커밍 포트, 아웃고잉 포트 인터페이스를 포함합니다.  

### **매핑전략**

![Untitled](https://github.com/2zikk/twitter/assets/25236852/8665f3df-82cb-443f-9e4f-8c7a2ef00cd2)

이 매핑 전략은 `만들면서 배우는 클린 아키텍처`에서 제안된 방식을 기반으로 하되, 매핑 오버헤드를 최소화하기 위해 과제 요구사항에 맞게 일부 수정하였습니다.  
우선, **영속성 모델과 도메인 모델을 통합하는 접근법**을 채택했습니다. 이 방식은 도메인 모델이 JPA에 의존하게 되지만, 이러한 의존성이 매핑 오버헤드보다 더 큰 부담을 주지 않을 것으로 예상됩니다.  

변경과 관련된 유스케이스에서는 `완전 매핑 전략`과 유사한 방식을 사용하되, 특정 상황을 제외하고는 **웹 모델이 command를 대체합니다.**  
요구사항을 고려할때 복잡한 Request 모델이 있을 것으로 예상되지 않기 때문에 매핑에 대한 오버헤드를 줄이기 위함입니다.  
application 계층에서 adapter로의 의존성을 피하기 위해 모델은 **application 계층**에서 생성됩니다.
`Command`의 추가 기준은 모델의 복잡도에 따라 달라집니다. 복잡한 모델이 필요한 경우 `command`를 추가하여 사용할 예정이었으나 본 과제에서는 사용하지 않았습니다.
마지막으로 읽기전용모델(쿼리의 결과 또는 외부 API통신의 결과 모델)은 `result`로 명명합니다.

```
.
├── common // 공통 관련 패키지
│   ├── annotation
│   ├── api
│   │   └── response
│   ├── config
│   ├── converter
│   ├── dto
│   ├── entity
│   ├── exception
│   └── util
├── refund //결정세액 계산 관련 패키지
│   ├── adapter
│   │   ├── in
│   │   └── out
│   │       └── persistence
│   ├── application
│   │   ├── dto
│   │   ├── port
│   │   │   └── persistence
│   │   └── service
│   └── domain
│       └── enums
├── scrap // 스크래핑 관련 패키지
│   ├── adapter
│   │   ├── in
│   │   └── out
│   │       └── client
│   │           ├── config
│   │           └── model
│   └── application
│       ├── dto
│       │   ├── request
│       │   └── result
│       ├── port
│       │   └── client
│       └── service
└── user //사용자 관련 패키지
    ├── adapter
    │   ├── in
    │   └── out
    │       └── persistence
    ├── application
    │   ├── dto
    │   ├── port
    │   │   └── out
    │   └── service
    └── domain

```
### ERD
`Deducts` : 소득공제(국민연금,신용카드) 년/월별 내역 테이블    
`TaxCredits` : 세액공제 년도별 내역 테이블   
`RefundResults` : 결정세액 계산을 위한 집계 테이블     
`Users` : 사용자 테이블    


<img width="525" alt="스크린샷 2024-03-05 22 29 20" src="https://github.com/Hyesooo/szs/assets/25236852/7daac716-24ea-4a96-b544-fec9f3c09f14">


### 명명규칙
- **모델**
    - 요청 모델: **`request`**
    - 응답 모델: **`response`**
    - 읽기전용 모델 **`result`**
- **서비스** **`usecase`**
- **포트 `port`**
- **어댑터**
    - 인커밍 어댑터 : **`controller`**
    - 아웃고잉 어댑터 : **`persistenceAdapter`**, **`clientAdapter`**
- **JPA 레포지토리 `repository`**


### API 명세
- http://localhost:8080/3o3/swagger-ui/index.html#/
<img width="1456" alt="스크린샷 2024-03-05 22 12 07" src="https://github.com/Hyesooo/szs/assets/25236852/92d7dd3a-a9f7-4de0-b101-c86d7a376408">

