package com.bitcamp.board.domain;

import java.io.Serializable;

public class Board2 implements Serializable {

  private static final long serialVersionUID = 1L;

  public int no;
  private String title;
  private String content;
  private String writer;
  private String password;
  private int viewCount;
  private long createdDate;

  //외부에서 접근하지못하게 막음

  static int count = 0;

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getWriter() {
    return writer;
  }

  public void setWriter(String writer) {
    this.writer = writer;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public long getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(long createdDate) {
    this.createdDate = createdDate;
  }

  public Board2(String title) {
    if(title == null) 
      throw new RuntimeException("제목이 비어있습니다.");
    //생성자의 파라미터는 반드시 초기화시켜야할 필드를 파라미터를 통해 받음.
    this.no = ++count;
    this.viewCount = 0;
    this.createdDate = System.currentTimeMillis();
  }
}






