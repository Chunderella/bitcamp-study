package com.bitcamp.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bitcamp.board.domain.AttachedFile;
import com.bitcamp.board.domain.Board;
import com.bitcamp.board.domain.Member;
import com.bitcamp.board.service.BoardService;


//CRUD 요청을 처리하는 페이지 컨트롤러들을 한 개의 클래스로 합친다.
@Controller 
public class BoardController {
    BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    /*================================form==========================================================*/

    @GetMapping("/board/form")
    public String form(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/board/form.jsp";
    }

    /*================================add==========================================================*/

    @PostMapping("/board/add") 
    public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        Board board = new Board();
        board.setTitle(request.getParameter("title")); 
        board.setContent(request.getParameter("content"));
        board.setAttachedFiles(saveAttachedFiles(request)); //저장된 첨부파일을 담고(setAttachedFiles)
        board.setWriter((Member) request.getSession().getAttribute("loginMember"));

        // 서비스 객체에 업무를 맡긴다.
        boardService.add(board);
        return "redirect:list";
    }
    private List<AttachedFile> saveAttachedFiles(HttpServletRequest request) throws IOException, ServletException {
        List<AttachedFile> attachedFiles = new ArrayList<>();
        String dirPath = request.getServletContext().getRealPath("/board/files");
        Collection<Part> parts = request.getParts();

        for (Part part : parts) {
            if (!part.getName().equals("files") || part.getSize() == 0) {
                continue;
            }

            String filename = UUID.randomUUID().toString();
            part.write(dirPath + "/" + filename);
            attachedFiles.add(new AttachedFile(filename));
        }
        return attachedFiles;
    }
    /*==================================list========================================================*/

    @GetMapping("/board/list")
    public String list(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        req.setAttribute("boards", boardService.list());
        return "/board/list.jsp";
    }
    /*======================================detail====================================================*/

    @GetMapping("/board/detail") // 요청이 들어 왔을 때 호출될 메서드에 붙이는 애노테이션
    public String detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int boardNo = Integer.parseInt(request.getParameter("no"));

        Board board = boardService.get(boardNo);
        if (board == null) {
            throw new Exception("해당 번호의 게시글이 없습니다!");
        }

        request.setAttribute("board", board);

        return "/board/detail.jsp";
    }

    /*====================================update======================================================*/
    @PostMapping("/board/update")
    public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        Board board = new Board();
        board.setNo(Integer.parseInt(request.getParameter("no")));
        board.setTitle(request.getParameter("title"));
        board.setContent(request.getParameter("content"));
        board.setAttachedFiles(saveAttachedFiles(request));

        // 게시글 작성자인지 검사한다.
        Member loginMember = (Member) request.getSession().getAttribute("loginMember");
        if (boardService.get(board.getNo()).getWriter().getNo() != loginMember.getNo()) {
            throw new Exception("게시글 작성자가 아닙니다.");
        }

        if (!boardService.update(board)) {
            throw new Exception("게시글을 변경할 수 없습니다!");
        }
        return "redirect:list";
    }
    /*======================================delete====================================================*/

    @GetMapping("/board/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int no = Integer.parseInt(request.getParameter("no"));

        Member loginMember = (Member) request.getSession().getAttribute("loginMember");
        if (boardService.get(no).getWriter().getNo() != loginMember.getNo()) {
            throw new Exception("게시글 작성자가 아닙니다.");
        }

        if (!boardService.delete(no)) {
            throw new Exception("게시글을 삭제할 수 없습니다.");
        }

        return "redirect:list";
    }
    /*=======================================fileDelete===================================================*/

    @GetMapping("/board/fileDelete")
    public String fileDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int no = Integer.parseInt(request.getParameter("no"));

        AttachedFile attachedFile = boardService.getAttachedFile(no); 

        Member loginMember = (Member) request.getSession().getAttribute("loginMember");
        Board board = boardService.get(attachedFile.getBoardNo()); 

        if (board.getWriter().getNo() != loginMember.getNo()) {
            throw new Exception("게시글 작성자가 아닙니다.");
        }

        if (!boardService.deleteAttachedFile(no)) {
            throw new Exception("게시글 첨부파일을 삭제할 수 없습니다.");
        }

        return"redirect:detail?no=" + board.getNo();


    }

}






