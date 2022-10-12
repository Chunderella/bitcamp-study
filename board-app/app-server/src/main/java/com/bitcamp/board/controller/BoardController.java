package com.bitcamp.board.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

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

    @PostMapping("/board/add") 
    public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        Board board = new Board();
        board.setTitle(request.getParameter("title")); 
        board.setContent(request.getParameter("content"));

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
        board.setAttachedFiles(attachedFiles);

        Member loginMember = (Member) request.getSession().getAttribute("loginMember");
        board.setWriter(loginMember);

        // 서비스 객체에 업무를 맡긴다.
        boardService.add(board);
        return "redirect:list";
    }

    @GetMapping("/board/list")
    public String list(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        req.setAttribute("boards", boardService.list());
        return "/board/list.jsp";
    }
}






