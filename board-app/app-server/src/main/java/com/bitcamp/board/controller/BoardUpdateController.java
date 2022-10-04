package com.bitcamp.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.AttachedFile;
import com.bitcamp.board.domain.Board;
import com.bitcamp.board.domain.Member;

@WebServlet("/board/update")
public class BoardUpdateController extends HttpServlet {
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

      DiskFileItemFactory factory = new DiskFileItemFactory(); //임시 폴더에 저장한다.
      ServletFileUpload upload = new ServletFileUpload(factory);
      List<FileItem> items = upload.parseRequest(request);

      Board board = new Board(); //가방을 준비해서
      List<AttachedFile> attachedFiles= new ArrayList<>();

      //첨부파일을 저장할 OS의 파일시스템 경로를 알아낸다.
      String dirPath = this.getServletContext().getRealPath("/board/files");



      for(FileItem item : items) { 
        if(item.isFormField()) {
          String paramName = item.getFieldName(); //파일아이템의 파라미터 이름과
          String paranValue = item.getString("UTF-8"); //파라미터 아이템의 값을 꺼낼때 GetString을 호출해서 utf-8로 꺼냄
          switch(paramName) {
            case "no": board.setNo(Integer.parseInt(paranValue));
            case "title": board.setTitle(paranValue);
            case "content": board.setContent(paranValue);

          }


        }else {

          //첨부파일을 저장할 때 사용할 파일명을 생성한다.
          String filename = UUID.randomUUID().toString();

          //지정한 위치에 생성한 이름으로 첨부파일을 저장한다.
          item.write(new File(dirPath + "/" + filename));

          //첨부파일의 이름을 DB에 저장할 수 있도록 List에 보관한다.
          attachedFiles.add(new AttachedFile(filename));

        }
      }

      board.setAttachedFiles(attachedFiles);
      //게시글 작성자 인지 검사한다.
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