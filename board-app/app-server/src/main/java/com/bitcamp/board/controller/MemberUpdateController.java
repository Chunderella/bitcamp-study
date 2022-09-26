package com.bitcamp.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bitcamp.board.dao.MemberDao;
import com.bitcamp.board.domain.Member;


@WebServlet("/member/update")
public class MemberUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  //얘가 BoardList

  MemberDao memberDao;


  @Override
  public void init() throws ServletException {
    // TODO Auto-generated method stub
    memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
  }
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException { //자기가 받은 것을

    try {
      Member member = new Member();
      member.no = Integer.parseInt(request.getParameter("no"));
      member.name = request.getParameter("name");
      member.email = request.getParameter("email");
      member.password = request.getParameter("password");

      if (memberDao.update(member) == 0) {
        throw new Exception("게시글 변경 실패!");
      }

      //      Refresh
      // - 응답 프로토콜
      //      Refreash : 30


      //        자바 코드:
      //      response.setHeader("Refresh","1;url=list"); //응답 헤더에 refresh 삽입
      //      response.setContentType("text/html;charset=UTF-8"); 
      //      request.getRequestDispatcher("/member/update.jsp").include(request, response);

      response.sendRedirect("list");

    } catch (Exception e) { 
      request.setAttribute("exception",e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}
