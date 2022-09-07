package com.bitcamp.board.dao;

import java.util.List;
import com.bitcamp.board.domain.Member;

public class Test {

  public static void main(String[] args) throws Exception{
    // TODO Auto-generated method stub
    MariaDBMemberDao dao = new MariaDBMemberDao();

    // list Test
    List<Member> list = dao.findAll();
    for(Member m : list) {
      System.out.println(m);
    }

    // [insert]
    //    Member member = new Member();
    //    member.name = "홍길동" ;
    //    member.email = "hong@test.com";
    //    member.password ="1111";
    //    dao.insert(member);

    Member member = new Member();
    member.no = 1 ;
    member.name ="xxxx";
    member.email = "xxxx@test.com";
    member.password ="2222";
    dao.update(member);



    Member member2 = dao.findByNo(1);
    System.out.println(member2);

    //
    // [delete]

    // dao.delete(6);
    //
    //    Member mamver = dao.findByNo(1);
    //    System.out.println(member);

    System.out.println("----------------");


    //    list = dao.findAll();
    //    for(Member m : list) {
    //      System.out.println(m);
    //    }
    //    System.out.println("----------------");

  }
}
//JUIT 단위 테스트
