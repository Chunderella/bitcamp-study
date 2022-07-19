package com.eomcs;

// 다른  패키지에서도 사용할 수 있도록 public 공개한다.
public class Score {

  //다른 패키지에서 이 설계도에 따라 만든 변수에 접근할 수 있도록 접근 범위를 넓힌다.
  String name; // 변수 또는 필드
  int kor;
  int eng;
  int math;
  int sum;
  float aver;

  //


  // static method ==> instance method
  public void compute() {
    //인스턴스 메서드를 호출할때 넘겨준 인스턴스 주소는
    //this 라는 내장 변수(built-in)에 보관된다.
    this.sum = this.kor + this.eng + this.math;
    this.aver = (float) this.sum / 3; 
  }
}
