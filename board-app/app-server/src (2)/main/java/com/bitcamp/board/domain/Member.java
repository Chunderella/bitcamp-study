package com.bitcamp.board.domain;

import java.sql.Date;

public class Member {

  private int no;
  private String nick;
  private String id;
  private String password;
  private Date createdDate;
  @Override
  public String toString() {
    return "Member [no=" + no + ", nick=" + nick + ", id=" + id + ", password=" + password
        + ", createdDate=" + createdDate + "]";
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getNick() {
    return nick;
  }
  public void setNick(String nick) {
    this.nick = nick;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }



}