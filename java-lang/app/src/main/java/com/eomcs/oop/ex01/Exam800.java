package com.eomcs.oop.ex01;

// # 클래스 사용 : 1) 일반 변수 사용
//
public class Exam800 {
  public static void main(String[] args) {

    class Score {
      String name;
      int kor;
      int eng;
      int math;
      int sum;
      float aver;
    }

    Score s; 

    s = new Score();

    s.name = "홍길동"; 
    s.kor = 100; 
    s.eng = 90;
    s.math = 80;
    s.sum = s.kor + s.eng + s.math;
    s.aver = s.sum / 3;

    System.out.printf("%s: %d, %d, %d, %d, %.1f\n",
        s.name, s.kor, s.eng, s.math, s.sum, s.aver);
  }
}
