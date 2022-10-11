package com.bitcamp.board.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.bitcamp.board.domain.Member;
import com.bitcamp.board.service.MemberService;
import com.bitcamp.servlet.Controller;
@Component("/auth/login")//페이지 컨트롤러 찾을 때 사용할 이름을 지정하라!
//- 애노테이션을 붙일 때 객체 이름을 명시하면 그 이름으로 저장한다.
//- 프론트 컨트롤러는 페이지 컨트롤러를 찾을 때 이 이름으로 찾을 것이다.
public class LoginController implements Controller{


    MemberService memberService;

    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
}
