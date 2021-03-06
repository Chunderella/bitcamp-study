package com.eomcs.quiz02.ex01;

// 출처: codefights.com
//
// 주어진 숫자에 짝수가 몇 개인지 세어라.
// 예) 
// 1010 => 2
// 123 => 1
//
public class Test04 {

  public static void main(String[] args) {
    System.out.println(countEvenNumber(1238694636) == 6);
    System.out.println(countEvenNumber(2567884) == 5);
  }

  static int countEvenNumber(int value) {
    int result = 0;

    int n = value;
    while(n != 0) {
      if ((n % 2)== 0) {
        result++;
      }
      n /=10;
    }
    System.out.println(n);

    // 이 메서드를 완성하시오!
    return result;
  }
}
