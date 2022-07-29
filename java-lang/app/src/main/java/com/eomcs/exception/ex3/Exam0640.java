// 예외 처리 후 마무리 작업 - try-with-resources
package com.eomcs.exception.ex3;

public class Exam0640 {

  static class A {
  }

  static class B {
    public void close() throws Exception {
      System.out.println("B 클래스의 자원을 해제하였습니다.");
    }
  }

  static class C implements AutoCloseable { //이행하다(implements) , (닫기)AutoCloseable
    //AutoCloseable라는 규칙을 시행한다. (요구 조건을 채운다.)

    @Override
    public void close() throws Exception { //클래스에서 AutoCloseable의 요구조건을 채움 = close
      System.out.println("C 클래스의 자원을 해제하였습니다.");
    }//이메서드가 호출됨  = try 블럭을 나가면 자동으로 호출되도록 함.
  }

  public static void main(String[] args) throws Exception {

    try (
        // A 클래스는 AutoCloseable 구현체가 아니기 때문에 여기에 선언할 수 없다.
        //        A obj = new A(); // 컴파일 오류!

        // B 클래스에 close() 메서드가 있어도 
        // AutoCloseable 구현체가 아니기 때문에 여기에 선언할 수 없다. 
        //        B obj2 = new B(); // 컴파일 오류!

        C obj3 = new C(); // OK
        ) {
      System.out.println("try 블록 실행...");
    }
    // finally 블록에서 C의 close()를 호출하지 않아도,
    // 자동으로 호출될 것이다. 
    // 실행하여 확인하라!
  }
}
