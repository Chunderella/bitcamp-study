package com.bitcamp.board.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bitcamp.board.domain.Member;
import com.bitcamp.board.service.MemberService;


@Controller //페이지 컨트롤러에 붙이는 애노테이션
public class AuthController {

    MemberService memberService;
    public AuthController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/auth/form")
    public String form(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/auth/form.jsp";
    }

    @PostMapping("/auth/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Member member = memberService.get(email, password);

        if(member != null) {
            HttpSession session = request.getSession(); // 요청한 클라이언트의 전용 HttpSession 보관소를 얻는다.
            session.setAttribute("loginMember", member); // 로그인한 멤버 정보를 세션 보관소에 저장한다.
        }
        Cookie cookie = new Cookie("email", email); // 클라이언트 쪽에 저장할 쿠키 생성 

        if(request.getParameter("saveEmail") == null) {
            cookie.setMaxAge(0); // 클라이언트에게 해당 이름의 쿠키를 지우라고 명령한다.
        } else {
            cookie.setMaxAge(60 * 60 * 24 * 7); // 7일
        }
        response.addCookie(cookie);

        request.setAttribute("member", member);
        return "/auth/loginResult.jsp";
    }

    @GetMapping("/auth/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        session.invalidate(); // 현재 세션을 무효화시킨다.
        return "redirect:../../"; // 로그아웃 한 후 메인 페이지를 요청하라고 응답한다.
    }

}






