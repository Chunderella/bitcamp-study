package com.eomcs.quiz.ex01.sol;

// 출처: codefights.com
//
// 주어진 숫자에 짝수가 몇 개인지 세어라.
// 예) 
// 1010 => 2
// 123 => 1
//
public class Test0041
{

  public static void main(String[] args) {
    System.out.println(countEvenNumber(1238694636)==6);
    System.out.println(countEvenNumber(2567884)==5);
  }

  static int countEvenNumber(int value) {
    int result = 0;
    int n = value; //파라미터에 들어온 값을 담을 변수를 선언. n으로 value 값이 들어옴.

    while(n != 0) {
      if ((n % 2) == 0) {
        result++;
      }
      n /= 10;
    }
    return result;
  }
}