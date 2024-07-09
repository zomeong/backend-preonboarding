![spartacodingclub](https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1719643111/noticon/yeqwdeuiybor5m4hh7zj.png)
# Hanghae99 Preonboarding Backend Course

**취업시장에 침투하기 전에, 실전과 같은 훈련으로 코딩의 감(떫음)을 찾아서 세상에 스파르타st를 보여주자.<br />
어렵다고 느끼는 제군들도 있겠지만, 힌트를 보면서 잘 따라 와주기를 바란다.**



### 🎖️ 훈련 메뉴

---
- [ ]  Junit를 이용한 테스트 코드 작성법 이해
- [ ]  Spring Security를 이용한 Filter에 대한 이해
- [ ]  JWT와 구체적인 알고리즘의 이해
- [ ]  PR 날려보기
- [ ]  리뷰 바탕으로 개선하기
- [ ]  EC2에 배포해보기

### Day 1 - 시나리오 설계 및 코딩 시작!

---
**Spring Security 기본 이해**

- [ ]  Filter란 무엇인가?(with Interceptor, AOP)
- 필터(Filter)란 웹 애플리케이션의 요청과 응답을 가로채서 전처리 또는 후처리를 수행한다. 주로 HTTP요청과 응답을 처리하기 전, 응답을 클라이언트로 보내기 전에 특정한 작업을 수행할 때 사용된다.
- 인터셉터(Interceptor)는 스프링에서 제공하는 기능으로 컨트롤러에 도달하기 전과 후에 특정 로직을 수행할 수 있도록 한다. 필터와 비슷하지만 구체적으로 헨들러(컨트롤러) 메서드의 전처리, 후처리, 완료 후 작업을 수행할 수 있다.
- AOP(Aspect Oriented Programming)는 흩어진 공통 관심사를 모듈화하여 재사용성을 높이는 프로그래밍 기법이다. 메서드의 동작 전후에 특정 로직을 삽입할 수 있다. 주로 비지니스 로직을 처리할 때 사용한다.
- [ ]  Spring Security란?
- 스프링 시큐리티는 스프링 프레임워크의 보안 모듈로 인증, 권한 부여, 인가 등의 기능을 제공한다. 주로 웹 애플리케이션의 보안을 담당하며 인증과 인가를 신속하고 효과적으로 처리할 수 있는 다양한 기능과 유연한 설정을 제공한다.
- 클라이언트가 서버로 요청을 보내면 디스패쳐 서블릿에 도달하기 전 여러 필터를 거친다. 스프링 시큐리티에서는 필터가 요청을 가장 먼저 처리하고ㅡ 인터셉트가 컨트롤러의 호출 전후에는 동작하여 추가 작업을 수행할 수 하며,메서드 호출 전후에 AOP가 동작하여 코드를 관리한다.

**JWT 기본 이해**

- [ ]  JWT란 무엇인가요?
- JWT(Json Web Token)은 JSON 형태로 데이터를 주고받기 위해 표준규약에 따라 생성된 암화화된 토큰이다. 주로 권한 인증과 정보 교환을 위해 사용된다. JWT는 헤더, 페이로드, 서명의 세 부분으로 구성되며 URL, 헤더 또는 HTTP 요청의 일부로 전송할 수 있다. 간결하고 확장성이 높지만 탈취될 경우 보안에 취약하다.
- 웹 애플리케이션에서 사용되는 인증 방식에는 세션과 토큰 기반 방식이 있다. 세션 기반 인증은 서버 측에서 상태를 유지하여 보안성이 높지만 서버에 부담을 주며 확장성에 제한이 있다는 단점이 있고, 토큰 기반 인증은 클라이언트 측에서 직접 토큰을 관리하여 확장성이 우수하지만 보안상의 위험이 있다. 토큰 기반 인증 방식이 주로 사용되는 이유는 RESTful API와 같은 상태를 유지하지 않는 서비스에서 부담이 적고 적합하기 때문이다. 

**토큰 발행과 유효성 확인**

- [ ]  Access / Refresh Token 발행과 검증에 관한 **테스트 시나리오** 작성하기

**유닛 테스트 작성**

- [ ]  JUnit를 이용한 JWT Unit 테스트 코드 작성해보기

  - https://preasim.github.io/39

  - [https://velog.io/@da_na/Spring-Security-JWT-Spring-Security-Controller-Unit-Test하기](https://velog.io/@da_na/Spring-Security-JWT-Spring-Security-Controller-Unit-Test%ED%95%98%EA%B8%B0)


### Day 2 - 백엔드 배포하기

---
**테스트 완성**

- [ ]  백엔드 유닛 테스트 완성하기

**로직 작성**

- [ ]  백엔드 로직을 Spring Boot로
    - [ ]  회원가입 - /signup
        - [ ]  Request Message

           ```json
           {
               "username": "JIN HO",
               "password": "12341234",
               "nickname": "Mentos"
           }
           ```

        - [ ]  Response Message

           ```json
           {
               "username": "JIN HO",
               "nickname": "Mentos",
               "authorities": [
                       {
                               "authorityName": "ROLE_USER"
                       }
               ]		
           }
           ```

    - [ ]  로그인 - /sign
        - [ ]  Request Message

           ```json
           {
               "username": "JIN HO",
               "password": "12341234"
           }
           ```

        - [ ]  Response Message

           ```json
           {
               "token": "eKDIkdfjoakIdkfjpekdkcjdkoIOdjOKJDFOlLDKFJKL",
           }
           ```


**배포해보기**

- [ ]  AWS EC2 에 배포하기

**API 접근과 검증**

- [ ]  Swagger UI 로 접속 가능하게 하기

### Day 3 - 백엔드 개선하기

---
[Git 커밋 메시지 잘 쓰는 법 | GeekNews](https://news.hada.io/topic?id=9178&utm_source=slack&utm_medium=bot&utm_campaign=TQ595477U)

**AI-assisted programming**

- [ ]  AI 에게 코드리뷰 받아보기

**Refactoring**

- [ ]  피드백 받아서 코드 개선하기

**마무리**

- [ ]  AWS EC2 재배포하기
