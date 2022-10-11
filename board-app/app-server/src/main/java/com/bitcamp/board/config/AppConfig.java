package com.bitcamp.board.config;


//  스프링 IoC 컨테이너의 설정을 수행하는 클래스
//  1) DB 커넥션 객체 관리자 준비 : DataSource
//  2) 트랜젝션 관리자 준비
public class AppConfig {

    public AppConfig() {
        System.out.println("AppConfig() 생성자 호출됨!");
    }

    //
    //    public DataSource createDataSource() {
    //        System.out.println("createDataSource() 호출됨!");
    //        DriverManagerDataSource ds = new DriverManagerDataSource();
    //        ds.setDriverClassName("org.mariadb.jdbc.Driver");
    //        ds.setUrl("jdbc:mariadb://localhost:3306/studydb");
    //        ds.setUsername("study");
    //        ds.setPassword("1111");
    //        return ds;
    //    }
    //
    //
    //    public TransactionManager createTransactionManager(DataSource ds) {
    //        System.out.println("DataSourceTransactionManager() 호출됨!");
    //
    //        return new DataSourceTransactionManager(ds);
    //
    //    }
}
