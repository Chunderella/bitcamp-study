// String - String.equals() vs Object.equals()
package com.eomcs.basic.ex02;

public class Exam0122 {//extends Object{


  static class Member {
    String name;
    int age;

    public Member(String name, int age) {
      this.name = name;
      this.age = age;
    }
  }

  public static void main(String[] args) {

    Member m1 = new Member("홍길동", 20);
    Member m2 = new Member("홍길동", 20);

    //Member 인스턴스를 통해 호출하는 메서드는 모두
    //Object 클래스의 메서드다.

    //<---------------------------etc-------------->
    m1.toString();
    m1.hashCode();
    m1.equals(null);
    //object 클래스에 정의된 메서드

    //<---------------------------etc-------------->

    // 비록 m1과 m2는 같은 값을 갖고 있지만 인스턴스가 다르다.
    System.out.println(m1 == m2); // false

    // Member는 Object에서 상속 받은 equals()를 오버라이딩 하지 않았다.
    // 따라서 단순히 인스턴스가 같은지를 비교할 것이다.
    System.out.println(m1.equals(m2)); // false
    //object 의 equals 이기때문에 m1/m2가 전달하는 주소를 비교하는 것



    //<----------------------------------------->
    System.out.println(m1.toString());
    System.out.println(m2.toString());
    //<----------------------------------------->
    //toString ==> 클래스명 + @ hashcode (식별코드) 
  }
}


