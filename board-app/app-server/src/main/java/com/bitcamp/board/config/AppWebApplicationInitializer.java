package com.bitcamp.board.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.bitcamp.board.filter.AdminCheckFilter;
import com.bitcamp.board.filter.LoginCheckFilter;


// 서블릿 컨테이너에서 웹 애플리케이션을 시작할 때:
// ===> SpringServletContainerInitializer.onStartup() 호출
//      ===> WebApplicationInitializer 구현체의 onStart() 호출

//@MultipartConfig(maxFileSize = 1024 * 1024 * 10) 
public class AppWebApplicationInitializer extends AbstractContextLoaderInitializer { //스프링에 있는 인터페이스

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        //당장 Root IoC 컨테이너를 생성하지 않을 것이다.
        //따라서 null을 리턴한다.
        //null을 리턴하면 ContextLoaderListener 객체도 생성되지 않을 것이다.
        //당연히 해당 리스터가 서블릿 컨테이너에 등록되지 않는다.
        return null;
    }


    //수퍼클래스의 onStartup()을 재정의한다.
    // => super.onStartup() : ContextLoaderListener를 준비한다.
    // => + 프론트 컨트롤러와 프론트 컨트롤러에서 사용할 IoC 컨테이너를 등록한다.

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //수퍼클래스가 ContextLoaderListener를 준비하는 작업은 그대로 수생하게 한다.
        super.onStartup(servletContext); // 수퍼 클래스의 메서느는 그대로 실행!
        System.out.println("AppWebApplicationInitializer.onStartup()");



        // 웹관련 컴포넌트를 다룰 수 있는 기능이 포함된 스프링 IoC컨테이너 준비
        AnnotationConfigWebApplicationContext iocContainer =
                new AnnotationConfigWebApplicationContext();
        iocContainer.register(AppConfig.class);

        //웹 애플리케이션의 루트 경로를 ServletContext 보관소에 저장해 둔다.
        servletContext.setAttribute("contextPath", servletContext.getContextPath());

        //<-------------iocContainer가 주입된 페이지 컨트롤러를 사용한다.------------->
        // 자바 코드로 서블릿 객체를 직접 생성하여 서버에 등록하기
        DispatcherServlet servlet = new DispatcherServlet(iocContainer); //서블릿 클래스
        Dynamic config = servletContext.addServlet("app", servlet); //서블릿의 별명
        config.setLoadOnStartup(1); // 웹 애플리케이션을 시작할 때 프론트 컨트롤러를 자동으로 생성한다.            
        config.addMapping("/app/*"); //일관성 있게 맞춤

        //1) 멀티파트 설정 정보를 애노테이션에서 가져오기
        //@MultipartConfig(maxFileSize = 1024 * 1024 * 10) 
        //        config.setMultipartConfig(new MultipartConfigElement(
        //                this.getClass().getAnnotation(MultipartConfig.class)));


        //        2) 멀티파트 설정 정보를 직접 지정하기
        //        System.out.println(System.getProperty("java.io.tmpdir"));
        config.setMultipartConfig(new MultipartConfigElement(
                System.getProperty("java.io.tmpdir"),//클라이언트가 보낸 파일을 임시 저장할 디렉토리)));
                1024 * 1024 * 5, // 한 파일의 최대 크기
                1024 * 1024 * 10, //첨부 파일을 포함한 전체 요청 데이터의 최대 크기(파일 전체 크기)
                1024 * 1024 // 첨부 파일 데이터를 일시적으로 보관할 버퍼 크기
                ));


        //  "app" 이름의 프론트 컨트롤러에 필터를 붙인다.
        /*======================CharacterEncodingFilter===========================================*/
        CharacterEncodingFilter filter = new CharacterEncodingFilter("UTF-8"); //Request.setEncoding~실행
        FilterRegistration.Dynamic filterConfig =  servletContext.addFilter("CharacterEncodingFilter", filter); //중첩인터페이스
        filterConfig.addMappingForServletNames( //필터를 설정하는 것
                EnumSet.of(DispatcherType.REQUEST,DispatcherType.FORWARD, DispatcherType.INCLUDE), 
                //이 이름을 가진 서블릿을 실행할때 이 필터를 실행하겠다.
                //요청이 들어왔을때 실행할꺼면 이 필터를 먼저 사용하라.                    
                //필터 이름으로 저장했기때문에 다른 필터에는 꽂을 수 없다.
                false,
                "app");

        // 특정 URL에 필터를 붙인다.
        /*======================AdminCheckFilter===========================================*/
        AdminCheckFilter adminFilter = new AdminCheckFilter();
        FilterRegistration.Dynamic adminFilterConfig =  servletContext.addFilter("AdminCheckFilter", adminFilter); //중첩인터페이스
        adminFilterConfig.addMappingForUrlPatterns( 
                EnumSet.of(DispatcherType.REQUEST,DispatcherType.FORWARD, DispatcherType.INCLUDE), 
                false,
                "/app/member/*");

        /*======================LoginCheckFilter===========================================*/
        LoginCheckFilter loginFilter = new LoginCheckFilter();
        FilterRegistration.Dynamic loginCheckFilterConfig =  servletContext.addFilter("LoginCheckFilter", loginFilter); //중첩인터페이스
        loginCheckFilterConfig.addMappingForUrlPatterns( 
                EnumSet.of(DispatcherType.REQUEST,DispatcherType.FORWARD, DispatcherType.INCLUDE), 
                false,
                "/app/*");
    }
}
