// String - 다형적 변수와 형변환
package com.eomcs.basic.ex02;

public class Exam0142 {
  public static void main(String[] args) {


    Object obj = new String("Hello");

    //<=스트링 레퍼런스에 담아서 호출하기====================>
    String s = (String) obj;
    String x = s.toLowerCase();


    //<============형변환해서 담기===========================>


    // obj가 String 객체를 가리키더라도 
    // obj의 타입이 Object이기 때문에 Object에 선언한 멤버만 사용할 수 있다.
    // obj가 가리키는 원래 클래스의 메서드를 호출하고 싶다면
    // 다음과 같이 원래 타입으로 형변환하라.
    String str = ((String) obj).toLowerCase(); //괄호안에 스트링 인스턴스 주소 = 스트링 클래스 타입
    System.out.println(str);

    // 또는 다음과 같이 원래 타입의 레퍼런스에 저장한 다음 사용하라.
    String x1 = (String) obj;
    str = x1.toLowerCase();
    System.out.println(str);
  }
}


