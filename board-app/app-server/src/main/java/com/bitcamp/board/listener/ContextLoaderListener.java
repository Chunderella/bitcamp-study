package com.bitcamp.board.listener;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebListener;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.bitcamp.board.config.AppConfig;
import com.bitcamp.board.filter.AdminCheckFilter;
import com.bitcamp.board.filter.LoginCheckFilter;

// 웹애플리케이션이 시작되었을 때 공유할 자원을 준비시키거나 해제하는 일을 한다.
//
@MultipartConfig(maxFileSize = 1024 * 1024 * 10) 
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("공유 자원을 준비 중!!");
        try {

            //<-------------스프링IoC컨테이너------------->

            //  웹 기능이 포함된 스프링 IoC 컨테이너 준비
            AnnotationConfigWebApplicationContext iocContainer =
                    new AnnotationConfigWebApplicationContext();

            iocContainer.register(AppConfig.class);

            ServletContext ctx = sce.getServletContext();


            //<-------------iocContainer가 주입된 페이지 컨트롤러를 사용한다.------------->
            // 자바 코드로 서블릿 객체를 직접 생성하여 서버에 등록하기
            DispatcherServlet servlet = new DispatcherServlet(iocContainer);
            Dynamic config = ctx.addServlet("DispatcherServlet", servlet);
            config.addMapping("/service/*");
            config.setMultipartConfig(new MultipartConfigElement(
                    this.getClass().getAnnotation(MultipartConfig.class)));
            config.setLoadOnStartup(1); // 웹 애플리케이션을 시작할 때 프론트 컨트롤러를 자동으로 생성한다.            


            //  필터 등록
            /*======================CharacterEncodingFilter===========================================*/
            CharacterEncodingFilter filter = new CharacterEncodingFilter("UTF-8"); //Request.setEncoding~실행
            FilterRegistration.Dynamic filterConfig =  ctx.addFilter("CharacterEncodingFilter", filter); //중첩인터페이스
            filterConfig.addMappingForServletNames( //필터를 설정하는 것
                    EnumSet.of(DispatcherType.REQUEST,DispatcherType.FORWARD, DispatcherType.INCLUDE), 
                    //이 이름을 가진 서블릿을 실행할때 이 필터를 실행하겠다.
                    //요청이 들어왔을때 실행할꺼면 이 필터를 먼저 사용하라.                    
                    //필터 이름으로 저장했기때문에 다른 필터에는 꽂을 수 없다.
                    false,
                    "DispatcherServlet");

            /*======================AdminCheckFilter===========================================*/
            AdminCheckFilter adminFilter = new AdminCheckFilter();
            FilterRegistration.Dynamic adminFilterConfig =  ctx.addFilter("AdminCheckFilter", adminFilter); //중첩인터페이스
            adminFilterConfig.addMappingForUrlPatterns( 
                    EnumSet.of(DispatcherType.REQUEST,DispatcherType.FORWARD, DispatcherType.INCLUDE), 
                    false,
                    "/service/member/*");

            /*======================LoginCheckFilter===========================================*/
            LoginCheckFilter loginFilter = new LoginCheckFilter();
            FilterRegistration.Dynamic loginCheckFilterConfig =  ctx.addFilter("LoginCheckFilter", loginFilter); //중첩인터페이스
            loginCheckFilterConfig.addMappingForUrlPatterns( 
                    EnumSet.of(DispatcherType.REQUEST,DispatcherType.FORWARD, DispatcherType.INCLUDE), 
                    false,
                    "/service/*");


            //남이 만든 필터를 설정하고 배치 정보를 넣을때 자바코드로 설정한다.


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
