package com.bitcamp.board.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitcamp.board.domain.Member;
import com.bitcamp.board.service.MemberService;

@Controller
@RequestMapping("/auth/")
public class AuthController {

    MemberService memberService;

    public AuthController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("form")
    public String form() throws Exception {
        return "/auth/form.jsp";
    }

    // 'value' 나 'path' 나 같다.
    @PostMapping("login")
    public String login(
            String email, 
            String password, 
            String saveEmail, 
            HttpServletResponse response,
            HttpServletRequest request,
            HttpSession session) throws Exception {

        Member member = memberService.get(email, password);

        if (member != null) {
            session.setAttribute("loginMember", member); // 로그인한 멤버 정보를 세션 보관소에 저장
        }

        // 클라이언트에게 쿠키 보내기
        // - 쿠키 데이터는 문자열만 가능 (숫자 boolean 값은 문자열로 바꿔야한다.)
        Cookie cookie = new Cookie("email", email); // 클라이언트 쪽에 저장할 쿠키 생성
        if (saveEmail == null) {
            cookie.setMaxAge(0); // 클라이언트에게 해당 이름의 쿠키를 지우라고 명령한다.
        } else {
            cookie.setMaxAge(60 * 60 * 24 * 7); // 7일 
        }
        response.addCookie(cookie);

        request.setAttribute("member", member);
        return "/auth/loginResult.jsp";

    }

    @GetMapping("logout")
    public String logout(HttpSession session) throws Exception {
        session.invalidate(); // 현재 세션을 무효화시킨다.
        return "redirect:../../"; // 로그아웃 한 후 메인 페이지를 요청하라고 응답한다.
    }
}






