package com.eomcs.lang.ex07;

import java.util.Scanner;

// 1단계: 공백 출력 코드를 메서드로 추출하기
// 2단계: 별을 출력하는 코드를 메서드로 추출하기
// 3단계: while 대신 for 문 사용하기
// 4단계: 공백 계산 식을 메서드로 추출하기
public class Exam0114 {

  static void printSpaces(int len) {
    for (int i = 0; i < len; i++) {
      System.out.print(" ");
    }
  }

  static void printStars(int len) {
    for (int i = 0; i < len; i++) {
      System.out.print("*");
    }
  }

  static int getSpaceLength(int totalStar, int displayStar) {
    return (totalStar - displayStar) / 2;
    //0113. 1)을 더욱 가시적으로 볼 수 있도록 코드화

  }

  public static void main(String[] args) {
    Scanner keyScan = new Scanner(System.in);
    System.out.print("밑변의 길이? ");
    int len = keyScan.nextInt();
    keyScan.close();

    for (int starLen = 1; starLen <= len; starLen += 2) {
      printSpaces(getSpaceLength(len, starLen)); //출력할 공백 계산
      //expression 메서드를 호출하는 문장
      printStars(starLen);
      System.out.println();
    }
  }
}
//리펙터링




