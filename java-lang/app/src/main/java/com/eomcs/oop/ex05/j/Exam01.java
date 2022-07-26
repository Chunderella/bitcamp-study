// 상속 : specialization
package com.eomcs.oop.ex05.j;
//car 클래스를 만든 이후에 승용차나 트럭에 대해
// 좀더 섬세하게 제어하기 위해서 클래스를 추가로 정의하게 되었다.
// Car 클래스를 상속 받아 Sedan, Truck 클래스를 추가로 정의하게 되었다.
// 이렇게 기존 클래스에 기능을 덧붙여 특별한 클래스를 만드는 것
// - 수퍼 클래스를 상속 받아 서브 클래스를 만드는 것을 "전문화(specialization)"라 부른다.
//
public class Exam01 {

  public static void main(String[] args) {
    //이렇게 sedan과 truck 클래스를 만들어 쓰다가
    //    두클래스 사이에 공통코드가 발견되었다.
    //    유지보수를 쉽게하기 위해 공통 코드를 추출하여 중복 코드를 없앨 필요가 있었다.
    // 다음 예제를 보라

    Sedan s = new Sedan();
    Truck t = new Truck();

    s.doSunroof(true);
    t.dump();
  }

}
