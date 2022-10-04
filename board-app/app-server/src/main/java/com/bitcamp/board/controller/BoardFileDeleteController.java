package com.bitcamp.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.AttachedFile;
import com.bitcamp.board.domain.Board;
import com.bitcamp.board.domain.Member;

@WebServlet("/board/fileDelete")
public class BoardFileDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    BoardDao boardDao;

    @Override
    public void init() {
        boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int no = Integer.parseInt(request.getParameter("no")); //삭제할 첨부파일 번호

            // 첨부파일 정보를 가져온다.
            AttachedFile attachedFile = boardDao.findFileByNo(no); //첨부파일 정보를 알아내서

            // 게시글의 작성자가 로그인 사용자인지 검사한다.
            Member loginMember = (Member) request.getSession().getAttribute("loginMember"); //세션에서 로그인 사용자 정보를 꺼낸다.
            Board board = boardDao.findByNo(attachedFile.getBoardNo()); //첨부파일이 들어가있는 게시물 번호를 통해 게시물 정보를 가져옴

            if(board.getWriter().getNo() != loginMember.getNo()) { //작성자번호와 로그인한 사람이 같은 지를 판단
                throw new Exception("게시글 작성자가 아닙니다.");
            }

            // 첨부파일을 삭제한다.
            if (boardDao.deleteFile(no) == 0) {
                throw new Exception("게시글 첨부파일 삭제 실패!");
            }

            response.sendRedirect("detail?no=" +board.getNo()); //게시글번호

        } catch (Exception e) {
            request.setAttribute("exception", e);
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}






