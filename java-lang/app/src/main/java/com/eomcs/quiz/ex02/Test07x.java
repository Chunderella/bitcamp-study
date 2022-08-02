package com.eomcs.quiz.ex02;
// 출처: codefights.com
// 
// 주어진 양의 정수 배열에서 가장 큰 짝수를 찾아라!
// 최소 한 개의 짝수가 존재한다.
// 예) 
//   maximalEven([4, 3, 6, 8, 2, 4]) = 8
//
/*
Pick-out the largest even number from an array of integers

Example

maximalEven([4, 3, 6, 8, 2, 4]) = 8

[input] array.integer inputArray
an array of positive integers, it's guaranteed 
that inputArray contains at least one even integer

[output] integer
maximal even element of inputArray
 */
//
// [시간 복잡도]
// - ?
//
public class Test07x {

  public static void main(String[] args) {
    System.out.println(maximalEven(new int[]{4, 3, 6, 8, 2, 4}) == 8);
  }

  static int maximalEven(int[] inputArray) {
    int answer = inputArray[0];
    // 이 메서드를 완성하시오!
    // int answer = 0; //시작값을 양의 정수만 고려하기에 시작 값을 0으로 설정한다.
    for (int i = 0 ; i <inputArray.length; i++) { //*배열에 저장된 값을 모두 확인한다 (반복문)
      if (inputArray[i] %2 == 0 && inputArray[i] > answer) {
        answer = inputArray[i];
      }
    }
    return answer;
  }
}


