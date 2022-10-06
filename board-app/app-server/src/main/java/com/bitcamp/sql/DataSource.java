package com.bitcamp.sql;

import java.sql.Connection;
import java.sql.DriverManager;

// 스레드 전용 DB 커넥션을 제공하는 일을 한다.
// 
public class DataSource {

    String driver;
    String jdbcurl;
    String username;
    String password;

    //스레드 전용 DB 보관소 준비
    ThreadLocal<Connection> conStore = new ThreadLocal<>();

    public DataSource(String driver, String jdbcurl, String username, String password) throws Exception {

        //JDBC Driver 클래스 로딩하기
        Class.forName(driver);

        this.jdbcurl =jdbcurl;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws Exception {
        //현재 스레드의 보관소에서 DB커넥션 객체를 꺼낸다.
        Thread currThread = Thread.currentThread();
        System.out.printf("%s==>getConnection() 호출\n", currThread.getName());

        Connection con = conStore.get(); //스레드별로 값을 관리
        if(con == null) { //현재 스레드 보관소에 커넥션 객체가 없다면,
            con =  DriverManager.getConnection(jdbcurl,username,password);
            conStore.set(con); //새로 만든 DB컬렉션 객체를 다음에 다시 사용할 수 있도록 보관한다. 
            //현재 스레드의 이름으로 저장한다. 
            //없으면 새로 만들어서 킵
            System.out.printf("%s=>Connection 객체 생성\n", currThread.getName());
        }
        return con;
    }


}
