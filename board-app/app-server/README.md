###멀티파트 형식으로 업러드된 데이터를 처리한다.

appahe의 commons-fileupload 라이브러리를 프로젝트 추가

com.bitcamp.board.domain.AttachedFile 클래스 생성
>app_board_file 테이블의 값을 담는 객체


### 4단계 - 게시글을 조회할 때 첨부파일 목록을 출력한다.

-com.bitcamp.board.dao.MariaDBBoardDao 클래스 변경
 - findByNo() 변경
 - /webapp/board/detail.jsp 변경
### 5단계 - 첨부파일 삭제 기능을 추가한다. 

 - /webapp/board/detail/jsp 변경
 - com.bitcamp.board.controller.BoardFileDeleteContreller 클래스 생성
 - com.bitcamp.board.dao.BoardDao 인터페이스 변경
 - findFileByNo() , deleteFile() 추가
 - com.bitcamp.board.dao.MariaDBBoardDao 클래스 변경
 - findFileByNo() , deleteFile() 구현

 ### 6단계 - 게시글을 변경할 대 첨부파일을 추가할 수 있게 만든다.
 
 - com.bitcamp.board.dao.BoardDao 인터페이스 변경
 - findFileByNo() , deleteFile() 추가