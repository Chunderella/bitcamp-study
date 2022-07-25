package com.eomcs.quiz.ex01;



//[문제] 
//파라미터로 주어진 정수 값을 2진수로 표현했을 때 1로 설정된 비트의 개수를 구하라!
//[훈련 목표]
//- 관계 연산자 및 비트 연산자, 비트 이동 연산자 활용
//- 반복문 활용
//- 메서드 파라미터 및 리턴 값 다루기

public class Test011 {

  public static void main(String[] args) {
    int c = countBits(0b01100011);
    System.out.println(c == 4); // true

    c = countBits(0b01010111_01100011);
    System.out.println(c == 9); // true
  }

  static int countBits(int value) {
    int r = 0;

    while (value !=0) {
      //!=0 = 피연산자들의 값이 같지 않다.
      // 피연산자들의 값이 같지 않을 경우 반복한다.

      r += (value & 1);
      //왼쪽 변수에 더하면서 대입한다.
      // & 주어진 조건들이 모두 true일때만 true를 나타낸다.

      value >>>= 1;

      //비트값을 오른쪽으로 이동 (빈자리는 0으로 대입한다.)
    }
    return r;
  }
}


