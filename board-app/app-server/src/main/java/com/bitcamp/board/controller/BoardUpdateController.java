package com.bitcamp.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.Board;


@WebServlet("/board/update")
public class BoardUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  //얘가 BoardList

  BoardDao boardDao;


  @Override
  public void init() throws ServletException {
    // TODO Auto-generated method stub
    boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
  }
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException { //자기가 받은 것을

    try {
      Board board = new Board();
      board.no = Integer.parseInt(request.getParameter("no"));
      board.title = request.getParameter("title");
      board.content = request.getParameter("content");

      if (boardDao.update(board) == 0) {//인서트가 안됐다는 의미
        throw new Exception("게시글 변경 실패!");
      }

      //      Refresh
      // - 응답 프로토콜
      //      Refreash : 30


      //        자바 코드:
      response.setHeader("Refresh","1;url=list"); //응답 헤더에 refresh 삽입
      response.setContentType("text/html;charset=UTF-8"); 
      request.getRequestDispatcher("/board/update.jsp").include(request, response);


    } catch (Exception e) { 
      request.setAttribute("exception",e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}
