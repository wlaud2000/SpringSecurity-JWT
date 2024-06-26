# SpringSecurity-JWT

### feat: 기본적인 SecurityConfig 설정
- JWT 방식에서는 세션을 STATELESS 상태로 관리한다!

---
### feat:DB연결 및 Entity 작성
![img.png](images/img.png)
- DB연결 완료

---
### feat: 회원가입 로직 구현
![img_1.png](images/img_1.png)
- POSTMAN으로 JSON데이터를 보내주면
![img_2.png](images/img_2.png)
- OK 문구가 뜨면서
![img_3.png](images/img_3.png)
- DB에 들어가게 된다.

컨트롤러에 @RequestBody를 붙여줘야지 JSON형태로 data를 보낼 수 있다.
Controller 메서드 인자에 @RequestBody 어노테이션 설정을 하지 않으면
Form-Data 형식으로 받도록 설정되어있다.

---
### feat: 로그인 필터 구현

- POSTMAN에 
![img_4.png](images/img_4.png)
- JSON형식으로 보내면
![img_5.png](images/img_5.png)
- 에러가 뜨게된다.

- 이유는 우리의 LoginFilter에서 MIME 타입을 form-data 형식으로 받도록 구현이 되어 있어서 그렇다.

- 그래서 form-data형식으로 보내면
![img_6.png](images/img_6.png)
![img_7.png](images/img_7.png)
- 이렇게 admin이 뜨게된다.

---
### feat: DB기반 로그인 검증

- POSTMAN에
![img_8.png](images/img_8.png)
- 이렇게  Body내부에 POST방식과 함께 username과 password값을 넘기면
![img_9.png](images/img_9.png)
- 응답 200 OK가 뜨고
![img_10.png](images/img_10.png)
- 로그인이 성공했을 때 출력되는 log도 잘 찍힌다.

---
### feat: JWT 발급 및 검증 클래스 작성

---
### feat: 로그인 성공 시 JWT 발급
![img.png](images/img_11.png)
- 응답코드 200과 함께 응답헤더에 Authorization이라는 키값에 대한 JWT값을 확인할 수 있다.

---
### feat: JWT 검증 필터 구현
![img.png](images/img11.png)
- 발급 된 Authorization 키를 Header에 넣어준다.

![img_1.png](images/img12.png)
- 그 후 admin 권한이 있어야지 접근할 수 있는 경로에 접근해보면

![img_2.png](images/img13.png)
- 이렇게 정상적으로 접근 가능한것을 볼 수 있다.

![img_3.png](images/img14.png)
- 헤더에 토큰을 해제하고 요청을 보내보면

![img_4.png](images/img15.png)
- 403이 뜨면서 접근이 되지 않는다.

![img_6.png](images/img17.png)
- 유저 권한을 가진 회원의 경우

![img_7.png](images/img18.png)
- main 페이지에는 접근이 가능하지만

![img_5.png](images/img16.png)
- admin 페이지 접근 시 오류가 뜬다.

---
### feat: refresh 토큰 발급 구현/JWTFilter 수정
![img.png](images/19.png)
- JSON으로 로그인 가능하게 코드 변경

![img_1.png](images/20.png)
- 로그인을 하면 Cookie에 refresh에 토큰이 담겨져 온다.

![img_2.png](images/21.png)
- 또한 Header에 access 키에 토큰이 담겨져 온다.

---
### feat: reissue 경로 구현(Refresh로 Access 재발급)
![img.png](images/22.png)
- 로그인 시 발급받은 Access 토큰

![img_1.png](images/23.png)
- refresh 토큰은 cookie에 저장되어있으므로 reissue 경로로 요청을 보내면

![img_2.png](images/24.png)
- 200 OK 응답과 함께 새로운 Access 토큰이 발급된걸 볼 수있다.

---
