# 삼쩜삼 과제
> REST API 구현
> 회원가입, 로그인, 스크래핑, 결정세액 조회 API
>
> 개발 환경 및 참고사항
>   - Java version 17
>   - Springboot 3.2.3
>   - JPA
>   - H2 Embedded In-memory mode
>   - Gradle
>   - Swagger

# **프로젝트**

### 패키지 구조

```

```
### ERD
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

