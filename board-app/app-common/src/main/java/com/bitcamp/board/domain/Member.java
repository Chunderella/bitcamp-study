package com.bitcamp.board.domain;

import java.sql.Date;

public class Member {

  public int no;
  public String name;
  public String email;
  public String password;
  public Date createdDate;
  public String pwd;

  @Override
  public String toString() {
    return "Member [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password
        + ", createdDate=" + createdDate + "]";
  }

  //GoF의 Factory Method 패턴
  // - 객체 생성 과정이 복잡할 때 별도의 메서드로 캡슐화 한다.
  // 

}
