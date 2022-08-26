// 스레드의 생명주기(lifecycle) - 죽은 스레드는 다시 살릴 수 없다.
package com.eomcs.concurrent.ex4;

import java.util.Scanner;

public class Exam0111 {
  public static void main(String[] args) {
    System.out.println("스레드 시작시킴.");


    //람다문법의 런메소드
    Thread t = new Thread(() -> { // Runnable 구현체를 정의하고 생성한다.
      for (int i = 0; i < 1000; i++) {
        System.out.println("===> " + i);
      }
      System.out.println("스레드의 run() 실행 종료!"); //스레드의 런메소드가 호출이 끝남
    });


    //스레드를 만들고

    t.start();


    //스레드를 시작


    Scanner keyboard = new Scanner(System.in);
    keyboard.nextLine(); // 스레드가 종료될 때까지 시간을 벌기 위함.
    //메인스레드는 여기서 멈춤

    keyboard.close();

    // 죽은 스레드 객체를 또 실행할 수 없다.
    t.start(); // 예외 발생! ==> IllegalThreadStateException

    System.out.println("main() 종료!");
  }

}
