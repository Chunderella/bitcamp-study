<%@page import="com.bitcamp.board.domain.Board"%>
<%@page import="com.bitcamp.board.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
    <html>
    <head>
    <meta charset=\"UTF-8\">
    <title>bitcamp</title>
    </head>
    <body>
    <h1>게시글 상세 정보</h1>
<% 
    int boardNo = Integer.parseInt(request.getParameter("no");
%>
<%     try {
      Board board = boardDao.findByNo(boardNo);

      if (board == null) {
        %>
        <p>해당 번호의 게시글이 없습니다.</p>
<% 
      } else {
      %>
        <form action='update'>
        <table border='1'>
          <tr>
            <th>번호</th><td><input name='no' type='number' value='%d' readonly></td>", board.no
          </tr>
          <tr>
            <th>제목</th><td><input name='title' type='text' value='%s' size='60'></td>", board.title
          </tr>
          <tr>
            <th>내용</th><td><textarea name='content' rows='10' cols='60'>%s</textarea></td>", board.content
          </tr>
          <tr>
            <th>조회수</th><td>%d</td>", board.viewCount
          </tr>
          <tr>
            <th>작성자</th><td>%d</td>", board.memberNo
          </tr>
          <tr>
            <th>등록일</th><td>%s</td>", board.createdDate
          </tr>
        </table>
        <p>
          <button type='submit'>변경</button>
          <a href='delete?no=%d'>삭제</a>", board.no
        </p>
        </form>
      <% 
      }
      %>  
<%
} catch (Exception e) {
  %>
  <p>실행 중 오류 발생!</p>
   <%
    }
%>

    </body>
    </html>

<%!BoardDao boardDao;%>
    <% 
    boardDao = (BoardDao)this.getServletContext().getAttribute("boardDao");
%>