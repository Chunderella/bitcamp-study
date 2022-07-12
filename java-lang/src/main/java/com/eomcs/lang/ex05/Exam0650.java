package com.eomcs.lang.ex05;

//# 증감 연산자 : 전위(pre-fix) 증가 연산자
//
public class Exam0650 {
  public static void main(String[] args) {
    int i = 2;

    ++i; //3
    // i 메모리의 값을 먼저 증가시킨다.
    // 그리고 i 메모리의 값을 그 자리에 놓는다.

    ++i; //4

    System.out.println(i); // 4

    System.out.println(++i); 
    // i = i + 1
    // System.out.println(5)

    System.out.println(i); // 5

  }
}

//++1 먼저 증가시킨 뒤에 증가된 값을 놓는다.
//1++ 값을 놓은 후에 증가시킨다.