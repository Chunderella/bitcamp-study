package com.bitcamp.board.domain;

import java.io.Serializable;

//java.io.Serializable 인터페이스
// - 객체(인스턴스)를 통째로 입출력 할 수 있도록 표시하는 용도이다. 
public class Board implements Serializable {
  // 인스턴스를 생성할 때 준비되는 메모리를 선언
  public int no;
  public String title;
  public String content;
  public String writer;
  public String password;
  public int viewCount;
  public long createdDate;

  @Override
  public String toString() {
    return "Board [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer
        + ", password=" + password + ", viewCount=" + viewCount 
        + ", createdDate=" + new java.sql.Date(createdDate) 
        + "]";
  }
}
