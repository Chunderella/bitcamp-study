package com.eomcs.quiz.ex02;

// 출처: codefights.com
// 분수 배열에서 가장 큰 분수의 인덱스를 찾아라!
// 예) 분자: 5, 2, 5
//     분모: 6, 3, 4
// 위 예에서 가장 큰 분모는 5/4를 가리키는 인덱스 2이다.
// 

/* 원문
Given a list of fractions find the largest one.

Example

for numerators=[5, 2, 5], denominators=[6, 3, 4] output 
should be 2 since 5/4 is the largest fraction

[input] array.integer numerators

array of integers representing numerators of the fractions
[input] array.integer denominators

array of integers of the same length as numerators representing denominators 
where the i-th fraction equals to numerators[i]/denominators[i]
[output] integer

index of the largest fraction assuming that none of the fractions are equal
 */
//
// [시간 복잡도]
// - ?
//
public class Test05 {
  public static void main(String[] args) {
    System.out.println(maxFraction(
        new int[]{5, 2, 5}, 
        new int[]{6, 3, 4}) == 2);

    System.out.println(maxFraction(
        new int[]{2, 4, 5, 16, 56}, 
        new int[]{3, 5, 6, 22, 99}) == 2);

  }

  static int maxFraction(int[] numerators, int[] denominators) {
    int maxFractionIndex = 0; //가장 큰 분수가 0번째라고 가정하고 시작한다.

    //양쪽에 상대편의 분모를 곱했을때 결과가 같다. 라는 전제를 이해하고 시작해야한다.

    // 0번째 다음 항목인 1번째 부터 비교를 시작한다.
    for (int i = 1; i < numerators.length; i++) {
      //maxFractionIndex 가 가르키는 현재의 분수보다 i번째 분수가 더 큰 수라면
      if (numerators[i] *denominators[maxFractionIndex] >
      numerators[maxFractionIndex] * denominators[i]) {
        maxFractionIndex = i;//maxfractionIndex의 값을 i로 바꾼다.
      }
    }

    // 이 메서드를 완성하시오!
    return maxFractionIndex;
  }
}
