package com.eomcs.quiz.ex01;

// [문제] 
// 음이 아닌 두 정수의 곱셈을 수행하는 프로그램을 작성하라.
// 조건:
// => 2진수로 변환하여 비트 연산자만 이용하여 곱셈을 수행한다.
//
// [훈련 목표]
// - 비트 연산자를 이용한 2진수의 곱셈 수행 방법
//
// [시간 복잡도]
// - ?
//
public class Test08 {
  public static void main(String[] args) {
    //    System.out.println(multiply(5, 3) == 15);
    //    System.out.println(multiply(17, 13) == 221);
  }

  int a = 5; // 0101 ==> 010 ==> 01 ==> 맨끝 1비트 추출 ==> 오른쪽 이동 0
  int b = 3; // 0011 ==> 0110 ==> 1100 ==> 오른쪽 이동 1000
  int result = 0; // 0011 + 1100
  while (a > 0) {
    int x = a & 1;
    if (x != 0) {
      result = result + b;
      a = a >>> 1;
      b = b << 1;
    }
    System.out.println(result);
    return
  }
}
}

