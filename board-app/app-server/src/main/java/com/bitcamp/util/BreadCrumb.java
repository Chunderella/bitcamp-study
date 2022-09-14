package com.bitcamp.util;

import java.util.Stack;

public class BreadCrumb {

  // BreadCrumb 메뉴를 저장할 스택을 준비
  public Stack<String> menuStack = new Stack<>();

  //Thread 마다 BreadCrumb 객체를 따로 관리해주는 통합 관리자를 준비한다.
  static ThreadLocal<BreadCrumb> localManager = new ThreadLocal<>();

  public static BreadCrumb getBreadCrumbOfCurrentThread() { 
    // 스레드 로컬 관리자를 통해 현재 스레드 보관소에 저장되어 있는 
    // Breadcrumb 객체를 달라고 요청한다.
    return localManager.get(); 
  }

  //[현재 스레드 이름]으로 브래드크럼 객체를 꺼내줘(객체 주소) = 로컬매니저
  //각 스레드 별로 브래드크럼을 따로따로 관리할 수 있다.

  //생성자 준비하기
  public BreadCrumb() {
    //스레드 로컬 관리자에게 "현재 스레드 전용 보관소"에
    //BreadCrumb 객체를 보관해 달라고 요청한다.
    localManager.set(this);
  }


  public void put(String menu) {
    menuStack.push(menu);
  }

  public void pickUp() {
    menuStack.pop();
  }
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (String title : menuStack) {
      if (!builder.isEmpty()) {
        builder.append(" > ");
      }
      builder.append(title);
    }
    return builder.toString();
  }
}
