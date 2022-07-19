package com.eomcs.oop.ex02.test;

/*public class ExamTest {
  public static void main(String[] args) {

    step 0110
    class Score {

      String name;
      int kor;
      int eng;
      int math;
      int sum;
      float aver;
    }

    Score s1 = new Score();

    s1.name = "홍길동";
    s1.kor = 100;
    s1.eng = 90;
    s1.math = 85;
    s1.sum = s1.kor + s1.eng + s1.math;
    s1.aver = (float) s1.sum / 3;

    System.out.printf("%s: %d, %d, %d, %d, %.1f\n", 
        s1.name, s1.kor, s1.eng, s1.math, s1.sum, s1.aver);


    Score s2 = new Score();

    s2.name = "임꺽정";
    s2.kor = 90;
    s2.eng = 80;
    s2.math = 75;
    s2.sum = s2.kor + s2.eng + s2.math;
    s2.aver = (float) s2.sum / 3;

    System.out.printf("%s: %d, %d, %d, %d, %.1f\n",
        s2.name, s2.kor, s2.eng, s2.math, s2.sum, s2.aver);

    Score s3 = new Score();
    s3.name = "유관순";
    s3.kor = 80;
    s3.eng = 70;
    s3.math = 65;
    s3.sum = s3.kor + s3.eng + s3.math;
    s3.aver = (float) s3.sum / 3;

    System.out.printf("%s: %d, %d, %d, %d, %.1f\n", 
        s3.name, s3.kor, s3.eng, s3.math, s3.sum, s3.aver);
  }
}
 */

//===============================================================
/*
public class ExamTest {


  static class Score {
    String name; // 변수 또는 필드
    int kor;
    int eng;
    int math;
    int sum;
    float aver;
  }

  public static void main(String[] args) {

    Score s1 = new Score();

    s1.name = "홍길동";
    s1.kor = 100;
    s1.eng = 90;
    s1.math = 85;

    printScore(s1);
    //s1.sum = s1.kor + s1.eng + s1.math;
    //s1.aver = (float) s1.sum / 3;

    Score s2 = new Score();
    s2.name = "임꺽정";
    s2.kor = 90;
    s2.eng = 80;
    s2.math = 75;
    printScore(s2);
    //s2.sum = s2.kor + s2.eng + s2.math;
    //s2.aver = (float) s2.sum / 3;

    Score s3 = new Score();
    s3.name = "유관순";
    s3.kor = 80;
    s3.eng = 70;
    s3.math = 65;
    printScore(s3);
    //s3.sum = s3.kor + s3.eng + s3.math;
    //s3.aver = (float) s3.sum / 3;
  }

  static void printScore(Score s) {
    s.sum = s.kor + s.eng + s.math;
    s.aver = (float) s.sum / 3;

    System.out.printf("%s: %d, %d, %d, %d, %.1f\n", 
        s.name, s.kor, s.eng, s.math, s.sum, s.aver);
  }
}

}
 */
//============================================================
/*
public class Exam0130 {

static class Score {
  String name; 
  int kor;
  int eng;
  int math;
  int sum;
  float aver;
}

public static void main(String[] args) {

  Score s1 = new Score();

  s1.name = "홍길동";
  s1.kor = 100;
  s1.eng = 90;
  s1.math = 85;
  compute(s1);
  printScore(s1);

  Score s2 = new Score();
  s2.name = "임꺽정";
  s2.kor = 90;
  s2.eng = 80;
  s2.math = 75;
  compute(s2);
  printScore(s2);

  Score s3 = new Score();
  s3.name = "유관순";
  s3.kor = 80;
  s3.eng = 70;
  s3.math = 65;
  compute(s3);
  printScore(s3);
}

static void printScore(Score s) {
  System.out.printf("%s: %d, %d, %d, %d, %.1f\n", 
      s.name, s.kor, s.eng, s.math, s.sum, s.aver);
}

static void compute(Score s) {
  s.sum = s.kor + s.eng + s.math;
  s.aver = (float) s.sum / 3;
}
}
 */