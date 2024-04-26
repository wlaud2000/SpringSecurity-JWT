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