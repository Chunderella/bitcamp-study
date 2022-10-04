package com.bitcamp.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.AttachedFile;
import com.bitcamp.board.domain.Board;
import com.bitcamp.board.domain.Member;


// Servlet API에서 제공하는 multipart/form-data 처리기를 사용하려면
// 서블릿에 다음 애노테이션으로 설정해야 한다.

@MultipartConfig(maxFileSize = 1024 * 1024 * 10) //최대 10MB까지 업로드 허용
@WebServlet("/board/add")
public class BoardAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  BoardDao boardDao;

  @Override
  public void init() {
    boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      request.setCharacterEncoding("UTF-8");

      // 클라이언트가 멀티파트로 보낸 데이터를 저장할 도메인 객체를 준비한다.
      Board board = new Board();
      board.setTitle(request.getParameter("title"));
      board.setContent(request.getParameter("content"));
      //값을 꺼내는 get~

      // 첨부파일명을 저장할 컬렉션 객체 준비
      List<AttachedFile> attachedFiles = new ArrayList<>();

      // 임시 폴더에 저장된 첨부 파일을 옮길 폴더 경로 알아내기
      String dirPath = this.getServletContext().getRealPath("/board/files");

      //여러개일 경우 다 꺼내야함.
      Collection<Part> parts = request.getParts();

      //첨부파일을 제외하고 걸러내야함(files)
      for(Part part : parts) {
        if(!part.getName().equals("files")) { 
          continue;
          //getpart하면 첨부파일만 아니라 기존의 파라미터로 꺼낸 파트 전부를 컬렉션에 담아 리턴한다.
        }

        //첨부파일이 아닌 나머지 파트
        String filename = UUID.randomUUID().toString();
        part.write(dirPath + "/" + filename); // 바로 스트링으로 전달
        attachedFiles.add(new AttachedFile(filename)); //새로 저장된 이름을 담는다.

      }
      //Board객체에서 파일명 목록을 담고 있는 컬렉션 객체를 저장한다.
      board.setAttachedFiles(attachedFiles);

      // Board 객체에 로그인 사용자 정보를 저장한다.
      Member loginMember = (Member) request.getSession().getAttribute("loginMember");
      board.setWriter(loginMember);

      if (boardDao.insert(board) == 0) {
        throw new Exception("게시글 등록 실패!");
      }

      response.sendRedirect("list");
    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}






