package com.bitcamp.board.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bitcamp.board.dao.MemberDao;
import com.bitcamp.board.domain.Member;

// 비즈니스 로직을 수행하는 객체
// - 메서드 이름은 업무와 관련된 이름을 사용한다.
//

@Component
//- 이 애노테이션을 붙이면 Spring IoC 컨테이너가 객체를 자동 생성한다.
//- 객체의 이름을 명시하지 않으면 
//- 클래스 이름을 사용하여 저장한다.
//(첫 알파켓은 소문자. 예: "DefaultMemberService")을 사용하여 저장한다.
//- 물론 생성자의 파라미터 값을 자동으로 주입한다.
//- 파라미터에 해당하는 객체가 없다면 생성 오류가 발생한다.


//-이 애노테이션을 붙이면 Spring IoC 컨테이너가 객체를 자동으로 생성할 것이다.
//- 생성자에 파라미터가 있다면 해당 타입의 객체를 찾아 생성자를 호출할 때 주입할 것이다.
//- 만약 생성자가 원하는 파라미터 값이 없다면 생성 예외가 발생한다.
public class DefaultMemberService implements MemberService{
    MemberDao memberDao;

    public DefaultMemberService(MemberDao memberDao) {
        System.out.println("DefaultMemberService() 호출됨!");
        this.memberDao = memberDao;
    }

    @Override
    public void add(Member member) throws Exception {
        memberDao.insert(member);
    }


    @Override
    public boolean update(Member member) throws Exception {
        return memberDao.update(member)>0;
    }


    @Override
    public Member get(int no) throws Exception {
        return memberDao.findByNo(no);
    }
    @Override
    public Member get(String email, String password) throws Exception {
        return memberDao.findByEmailPassword(email, password);
        //파라미터가 다르더라도 같은 일을 하는 메소드에 대해서 같은 이름을 부여함으로써 프로그래밍의 일관성을 제공하는 문법(오버로딩)
    }



    @Override
    public boolean delete(int no) throws Exception {
        return memberDao.delete(no) > 0 ;
    }

    @Override
    public List<Member> list() throws Exception {
        return memberDao.findAll();
    }
}








