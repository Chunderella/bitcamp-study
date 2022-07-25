package com.eomcs.quiz.ex02;

// 출처: codefights.com
//
// 숫자 배열이 있다.
// 나누어 0이 떨어지는 수가 몇 쌍이 있는지 구하라!
//
// [시간 복잡도]
// - O(n) : n은 배열의 개수이다.
//
public class Test03x {

  public static void main(String[] args) {
    int[] values = {2, 4, 8};
    System.out.println(divisorsPairs(values) == 3);
  }

  public static int divisorsPairs(int[] sequence) {
    int result = 0;

    for (int i = 0; i < sequence.length; i++) {
      for (int j = i + 1; j < sequence.length; j++) {
        if (sequence[i] % sequence[j] == 0 || sequence[j] % sequence[i] == 0) {
          result++;
        }
      }
    }

    return result;
  }
}
/* 배열은 0번 항목부터 반복한다.
//나눌 값은 i번 다음 항목과 나누기 계산한다.
for(int i = 0, i < sequence.length; i++) {
for(int j = i + 1; i < sequence.length; j++) {
System.out.println(sequence[i] + "<--->" + sequence[j]);

i번째 항목의 값을 꺼내고,int iValue = sequence[i];

i번 다음 항목에 값을 꺼낸다.int jValue = sequence[j];

if((iValue % jValue == 0) || ((jValue % iValue) == 0 )) {
result++;
두 항목을 바꿔가면서 나눗셈을 하여 0 으로 떨어지는지 조사한다.

}*/