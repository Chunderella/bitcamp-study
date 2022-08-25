// "system" 스레드 그룹의 자식 그룹들
package com.eomcs.concurrent.ex2;

public class Exam0160 {

  public static void main(String[] args) {
    Thread main = Thread.currentThread(); //현재 쓰레드 메인
    ThreadGroup mainGroup = main.getThreadGroup(); // 쓰레드 그룹은 메인
    ThreadGroup systemGroup = mainGroup.getParent(); //메인 쓰레드 그룹의 부모는 시스템

    ThreadGroup[] groups = new ThreadGroup[100];
    //스레드 그룹을 담을 배열을 준비하기
    int count = systemGroup.enumerate(groups, false);  
    //어떤 스레드 그룹의 하위 스레드를 알고 싶으면 enumerate라는 메서드를 호출한다.

    System.out.println("system 스레드 그룹의 자식 그룹들:");
    for (int i = 0; i < count; i++) {
      System.out.println("   =>" + groups[i].getName());
    }
  }
}

// JVM의 스레드 계층도:
// system(TG)
// => main(TG)
// ...=> main(T) : main() 메서드를 호출한다.
// => InnocuousThreadGroup(TG)
