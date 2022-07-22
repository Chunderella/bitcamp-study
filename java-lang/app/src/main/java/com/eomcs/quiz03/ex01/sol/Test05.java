package com.eomcs.quiz.ex01.sol;

// 출처: codefights.com
//
// 한번에 한 자리의 숫자만 바꿀 때 이전 값과 다르게 만들 수 있는 경우는 몇가지인가?
// 단 맨 앞의 숫자는 0이 될 수 없고, 바꾸는 숫자는 현재의 숫자보다 커야한다. (의 경우에 - value % 10)
// 예)
// 10 => 17 번 
//
// [시간 복잡도]
// - O(k) : k는 10진수의 자릿수이다.
//
public class Test05 {

  public static void main(String[] args) {
    System.out.println(countWaysToChangeDigit(10) == 17);
    System.out.println(countWaysToChangeDigit(2345) == 22);
  }

  static int countWaysToChangeDigit(int value) {
    int answer = 0;  



    while (value > 0) {    //주어진 숫자가 0이 될때까지 계속 반복(while)
      answer += 9 - value % 10; //answer += 9; (자리수 하나 당 9 번 바꿀 수 있음) ==> 맨 뒷자리를 9번 바꿀 수 있다.
      //% 10 맨 뒤에 숫자를 뽑아냄.
      value /= 10; // 나누기 10
    }
    return answer; // return answer -1 ;
  }
}


/*
public class Test05 {

  public static void main(String[] args) {
    System.out.println(countWaysToChangeDigit(2345) == 35);
  }

  static int countWaysToChangeDigit(int value) {
    int answer = 0;  
 while (value > 0) {    //주어진 숫자가 0이 될때까지 계속 반복(while)
      answer += 9 - value % 10; //answer += 9; (자리수 하나 당 9 번 바꿀 수 있음) ==> 맨 뒷자리를 9번 바꿀 수 있다.
      value /= 10; 
    }
    return answer -1 ;
  }
}
