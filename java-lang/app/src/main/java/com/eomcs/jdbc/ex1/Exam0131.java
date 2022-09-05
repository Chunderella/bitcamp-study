// JDBC 드라이버 준비 - 드라이버 클래스 로딩과 등록
package com.eomcs.jdbc.ex1;

import java.io.FileReader;
import java.sql.DriverManager;
import java.util.Properties;

public class Exam0131 {

  public static void main(String[] args) {
    try {
      Properties props = new Properties();  //이름을 이렇게 등록해놓고
      props.load(new FileReader("./jdbc-driver.properties")); //어느 프라퍼티 파일을 읽을것인지 지정하고
      System.out.println(props.getProperty("jdbc.driverClassName")); // 이 이름으로 들어있는값
      System.out.println(props.getProperty("jdbc.url")); // 이이름으로 들어있는 주소

      // 위의 방식을 사용하면 다음과 같이, 
      // Driver 구현체를 소스 파일에 직접 명시할 필요가 없다.
      Class.forName(props.getProperty("jdbc.driverClassName")); //저장된 이름을 꺼내서 로딩한다.
      java.sql.Driver driver = DriverManager.getDriver(props.getProperty("jdbc.url"));
      System.out.println(driver);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}


//향후 바꿀 가능성을 대비해 파일을 만들어야하고, 코드를 추가적으로 짜야하기 때문에 
//exam0130보다 코드가 길어짐.
