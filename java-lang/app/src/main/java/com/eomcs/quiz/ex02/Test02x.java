package com.eomcs.quiz.ex02;

// 출처: codefights.com
//
// 배열의 전체 길이를 L이라고 하자.
// 배열을 절반(L/2)으로 나눌 때, 앞쪽 부분과 뒤쪽 부분의 위치를 바꿔라.
// 예)
// [2, 4, 5, 6, 4, 3, 7, 8] => [4, 3, 7, 8, 2, 4, 5, 6]
//
// [시간 복잡도]
// - O(n) : n은 배열의 개수이다.
// 비례하느냐, 아니면 제곱으로 증가하냐, log로 증가하냐 
//
public class Test02x {

  public static void main(String[] args) {
    int[] values = {2, 4, 5, 6, 4, 3, 7, 8};
    changeValuePosition(values);

    int[] results = {4, 3, 7, 8, 2, 4, 5, 6};

    for (int i = 0; i < results.length; i++) {
      if (values[i] != results[i]) {
        System.out.println(false);
        return;
      }
    }
    System.out.println(true);
  }

  static void changeValuePosition(int[] values) {
    int tmp;
    for (int i = 0; i < values.length / 2; i++) {
      tmp = values[i + values.length / 2];
      values[i + values.length / 2] = values[i];
      values[i] = tmp;
    }
  }
}


//int halfIndex = values.length /2;
//for(int i = 0; i < values.length /2; i++ ) {
/* 배열의 절반을 맞바꿀 것이기 때문에 
i는 values값의 절반만 반복한다.
int target = i + values.length;

[두 항목의 값을 바꾸기 전에 i 번째 항목의 값을 임시 변수에 저장해 둔다]
int temp = values[i]; 

temp에 i값을 저장하고, target 인덱스의 값을 i번째 항목에 넣는다.
values[i] = values[target];
target번째 항목에 temp에 임시보관된 값을 저장한다.
values[target] = temp;

 */