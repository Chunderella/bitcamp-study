package com.bitcamp.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bitcamp.board.dao.MemberDao;
import com.bitcamp.board.domain.Member;

// 비즈니스 로직을 수행하는 객체
// - 메서드 이름은 업무와 관련된 이름을 사용한다.
//

@Service // 서비스 역할을 수행하는 객체에 붙이는 애노테이션
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








