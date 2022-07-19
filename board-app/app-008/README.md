# 008. 클래스 사용법 II


## 작업 내용

### 1단계 - 게시글 데이터를 저장할 메모리를 설계한다.
- com.bitcamp.board.Board 클래스 생성

### 2단계 - 게시글 처리에 배열을 사용한다.
- com.bitcamp.board.BoardHandler 클래스 변경
  - 기존의 게시글 관련 변수 배열을 Board 레퍼런스 배열로 대체
  - 게시글 입력, 목록, 상세보기 메서드를 변경한다.

### 3단계 - 게시글 삭제 메뉴를 추가한다.
- com.bitcamp.board.App 클래스 변경
- com.bitcamp.board.BoardHandler 클래스 변경

```
=========================================
[게시글 목록]
번호 제목 조회수 작성자 등록일
1	a	0	aaa	2022-07-15
2	b	0	bbb	2022-07-15
3	c	0	ccc	2022-07-15

메뉴:
  1: 게시글 목록
  2: 게시글 상세보기
  3: 게시글 등록
  4: 게시글 삭제
  
메뉴를 선택하세요[1..4](0: 종료) 4
=========================================
[게시글 삭제]
삭제할 게시글 번호? 2
삭제하였습니다.

메뉴:
  1: 게시글 목록
  2: 게시글 상세보기
  3: 게시글 등록
  4: 게시글 삭제

메뉴를 선택하세요[1..3](0: 종료) 1
=========================================
[게시글 목록]
번호 제목 조회수 작성자 등록일
1	a	0	aaa	2022-07-15
3	c	0	ccc	2022-07-15
=========================================
[게시글 삭제]
삭제할 게시글 번호? 5
해당 번호의 게시글이 없습니다!
```

### 4단계 - 게시글 변경 메뉴를 추가한다.
- com.bitcamp.board.App 클래스 변경
- com.bitcamp.board.BoardHandler 클래스 변경

```
메뉴를 선택하세요[1..4](0: 종료) 5
=========================================
[게시글 변경]
변경할 게시글 번호? 5
해당 번호의 게시글이 없습니다!
=========================================
[게시글 변경]
변경할 게시글 번호? 2
제목?(b) xx
내용?(bb) xbxb
변경하시겠습니까?(y/n) y
변경했습니다.
변경하시겠습니까?(y/n) n
변경 취소했습니다.
```