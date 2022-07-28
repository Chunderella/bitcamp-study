package com.bitcamp.board.dao;

import com.bitcamp.board.domain.Member;
import com.bitcamp.util.ObjectList;

// 회원 목록을 관리하는 역할
//
public class MemberList extends ObjectList {


  //수퍼 클래스의 get() 메서드를 호출했을 때 예외가 발생하면,
  // 서브 클래스의 get() 메서드에서 처리할 상황이 아니다.
  // 서브 클래스의 get()을 호출한 쪽에 보고하는 것이 더 낫다.
  // 이럴 경우 try ~ catch를 쓰지말고 메서드 선언부에 발생되는 예외를표시하라!


  //<<==========================get================================>>
  public Member get(String email) throws Throwable { //예외를 위임한다. "이 메서드를 호출한 쪽에 예외를 던진다." 
    for (int i = 0; i < size(); i++) {
      Member member = (Member) get(i);
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

  public boolean remove(String email) throws Throwable  {
    for (int i = 0; i < size(); i++) {
      Member member = (Member) get(i);
      if (member.email.equals(email)) {
        return remove(i);
      }
    }
    return false;
  }
}















