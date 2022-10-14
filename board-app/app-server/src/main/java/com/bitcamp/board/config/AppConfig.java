package com.bitcamp.board.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

//  스프링 IoC 컨테이너의 설정을 수행하는 클래스
//  1) DB 커넥션 객체 관리자 준비 : DataSource
//  2) 트랜젝션 관리자 준비 : PlatformTransactionManager
//  3) 어떤 패키지의 있는 객체를 자동으로 생성할 것인지 지정한다.
//

@ComponentScan(value="com.bitcamp.board")
//  - com.bitcamp.board 패키지 및 그 하위 패키지에 소속된 클래스 중에서 
//  @Component, @Controller, @Service, @Repository 등의 애노테이션이 붙은 클래스를 찾아
//  객체를 생성한다.
public class AppConfig {

    public AppConfig() {
        System.out.println("AppConfig() 생성자 호출됨!");
    }


    //    @Bean 애노테이션을 붙일 때 객체 이름을 지장하면
    //   그 이름으로 리턴 값을 컨테이너에 보관한다.
    //   이름을 지정하지 않으면 메서드 이름으로 보관한다.
    //    @Bean("transactionManager") //애노테이션을 붙일때 이름이없으면 메서드 이름을 객체이름으로 사용한다.


    @Bean
    public PlatformTransactionManager transactionManager(DataSource ds) { //동사구
        return new DataSourceTransactionManager(ds);
    }

    @Bean
    public DataSource dataSource() { //저장할 때 키값으로 사용한다.
        System.out.println("createDataSource() 호출됨!");

        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.mariadb.jdbc.Driver");
        ds.setUrl("jdbc:mariadb://localhost:3306/studydb");
        ds.setUsername("study");
        ds.setPassword("1111");
        return ds;
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class); //주어진 URL을 처리할 객체 => JSP를 실행시켜주는 객체
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
