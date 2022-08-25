// Thread를 상속 받기 - 익명 클래스로 구현하기
package com.eomcs.concurrent.ex3;

public class Exam0120 {

  public static void main(String[] args) {




    new Thread() {
      //스레드를 상속받은 익명클래스를 정의하고
      @Override
      //정의하자마자 
      public void run() {
        for (int i = 0; i < 1000; i++) {
          System.out.println("===> " + i);
        }
      }
    }.start();
    //주소를 가지고 스타트


    for (int i = 0; i < 1000; i++) {
      System.out.println(">>>> " + i);
    }

  }

}
