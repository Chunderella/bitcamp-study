package com.bitcamp.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.bitcamp.servlet.Controller;
@Component("/member/form")//페이지 컨트롤러 찾을 때 사용할 이름을 지정하라!
//- 애노테이션을 붙일 때 객체 이름을 명시하면 그 이름으로 저장한다.
//- 프론트 컨트롤러는 페이지 컨트롤러를 찾을 때 이 이름으로 찾을 것이다.
public class MemberFormController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/member/form.jsp";
    }
}






