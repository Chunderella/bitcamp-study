package com.bitcamp.board.domain;

import java.sql.Date;
import java.util.List;

public class Party {

  private int no;
  private String title;
  private String content;
  private String password;
  private Date createdDate;
  private Member writer;
  private String meal;
  private String food;
  private String nick;
  private String gender;
  private int max;
  private Date time;
  private int age;
  private int limit;
  private String location;
  private String post;
  private String address;
  private int viewCnt;
  private String image;
  private String pub;
  private Date createDate;
  private List<AttachedFile> attachedFiles;
  @Override
  public String toString() {
    return "Party [no=" + no + ", title=" + title + ", content=" + content + ", password="
        + password + ", createdDate=" + createdDate + ", writer=" + writer + ", meal=" + meal
        + ", food=" + food + ", nick=" + nick + ", gender=" + gender + ", max=" + max + ", time="
        + time + ", age=" + age + ", limit=" + limit + ", location=" + location + ", post=" + post
        + ", address=" + address + ", viewCnt=" + viewCnt + ", image=" + image + ", pub=" + pub
        + ", createDate=" + createDate + ", attachedFiles=" + attachedFiles + "]";
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
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
  public Member getWriter() {
    return writer;
  }
  public void setWriter(Member writer) {
    this.writer = writer;
  }
  public String getMeal() {
    return meal;
  }
  public void setMeal(String meal) {
    this.meal = meal;
  }
  public String getFood() {
    return food;
  }
  public void setFood(String food) {
    this.food = food;
  }
  public String getNick() {
    return nick;
  }
  public void setNick(String nick) {
    this.nick = nick;
  }
  public String getGender() {
    return gender;
  }
  public void setGender(String gender) {
    this.gender = gender;
  }
  public int getMax() {
    return max;
  }
  public void setMax(int max) {
    this.max = max;
  }
  public Date getTime() {
    return time;
  }
  public void setTime(Date time) {
    this.time = time;
  }
  public int getAge() {
    return age;
  }
  public void setAge(int age) {
    this.age = age;
  }
  public int getLimit() {
    return limit;
  }
  public void setLimit(int limit) {
    this.limit = limit;
  }
  public String getLocation() {
    return location;
  }
  public void setLocation(String location) {
    this.location = location;
  }
  public String getPost() {
    return post;
  }
  public void setPost(String post) {
    this.post = post;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public int getViewCnt() {
    return viewCnt;
  }
  public void setViewCnt(int viewCnt) {
    this.viewCnt = viewCnt;
  }
  public String getImage() {
    return image;
  }
  public void setImage(String image) {
    this.image = image;
  }
  public String getPub() {
    return pub;
  }
  public void setPub(String pub) {
    this.pub = pub;
  }
  public Date getCreateDate() {
    return createDate;
  }
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
  public List<AttachedFile> getAttachedFiles() {
    return attachedFiles;
  }
  public void setAttachedFiles(List<AttachedFile> attachedFiles) {
    this.attachedFiles = attachedFiles;
  }

}







