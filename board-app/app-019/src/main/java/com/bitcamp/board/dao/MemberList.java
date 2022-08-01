package com.bitcamp.board.dao;

import com.bitcamp.board.domain.Member;
import com.bitcamp.util.LinkedList;

// 회원 목록을 관리하는 역할
//
public class MemberList extends LinkedList {

  //objectList의 get()에서 던지는 예외를 이 메서드에서 처리하지 않고
  //호출자에게 처리를 위임한다.
  // ListException은 RuntimeException 계열이기 때문에
  // 메서드 선언부에 표시하지않아도 된다.
  // Exception 계열의 예외를 다루는 것보다 덜 번거롭다.

  //메서드의 이름을 일관성을 위해 수퍼클래스의 메서드 이름에 맞춰 변경한다.
  //<<==========================get================================>>
  public Member retrieve(String email)  { //예외를 위임한다. "이 메서드를 호출한 쪽에 예외를 던진다." 
    for (int i = 0; i < length(); i++) {
      Member member = (Member) retrieve(i);
      if (member.email.equals(email)) {
        return member;
      }
    }
    return null;
  }


  //@Override ==>overloading이기 때문에 적을 필요 없음 
  //컴파일러에게 "이 메서드는 수퍼클래스의 메서드를 재정의하기 위해 다음 메서드를 만들었는데,
  //내가 제대로 재정의 했는지 확인해줄래?"
  //
  //<<==========================remove================================>>
  //인덱스 대신 이메일로 회원 데이터를 찾아 삭제하는 메서드.
  //슈퍼 클래스로부터 상속 받은 메소드와 같은 일을 하며, 
  //메소드 이름도 같다.
  //overloading = 오버로딩

  //수퍼 클래스 교체에 따라 메서드의 이름도 일관성 있게 
  //수퍼 클래스의 메서드 이름과 같게 한다. 오버로딩 규칙을 준수한다.

  public Object delete(String email)  {
    for (int i = 0; i < length(); i++) {
      Member member = (Member) retrieve(i); //예외가 발생하는데 컴파일러가 검사를 안한다. "ListException"에 대한 에러를
      if (member.email.equals(email)) {
        return delete(i);
      }
    }
    return false;
  }
}















