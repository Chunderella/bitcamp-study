package com.eomcs.quiz.ex02;

// 출처: codefights.com
//
// 숫자 배열에서 각 이웃하는 숫자간의 차가 가장 큰 것을 알아내기
// 예)
// [2, 4, 1, 0] => 3
// 
// [시간 복잡도]
// - 0(n-1)
// n은 배열의 갯수 : 시간 복잡도는 실행 횟수


public class Test01x {

  public static void main(String[] args) {
    System.out.println(maxDiff(new int[]{2, 4, 1, 0}) == 3);
    System.out.println(maxDiff(new int[]{3, 1, 4, 3, 8, 7}) == 5);
  }

  static int maxDiff(int[] values) {
    int answer = 1;

    for(int i = 1; i < values.length; i++) {
      int diff = Math.abs(values[i] - values[i-1]);
      if (diff > answer) {
        answer = diff;

      }
    }
    // 이 메서드를 완성하시오!
    return answer;
  }
}


/* 1. 배열에서 두개의 수를 꺼내 차를 구한다.
 2. 이때 배열은 마지막 전 항목까지 반복한다.
 3. for(int i = 0; i < values.length - 1; i++) 
    or >>for(int i = 1; i < values.lenght; i ++)
 i는 배열의 순서이기때문에 순서1번 [i]와 [i-1]의 차를 구한다.
 2, 4, 1, 0 ==> 배열 끝까지 반복하면 0과 계산할 값이 없기때문에
 4가 아닌 3까지만 반복한다.
 숫자간의 차는 양수가 되어야하기때문에 Math.abs를 통해 절대값으로 변환한다.
 4.diff 값이 answer 보다 크다면 계속 반복한다.
 */
