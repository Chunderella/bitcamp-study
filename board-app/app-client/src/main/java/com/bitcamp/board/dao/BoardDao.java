package com.bitcamp.board.dao;

import java.util.List;
import com.bitcamp.board.domain.Board;

public interface BoardDao {

  int insert(Board board) throws Exception ;

  Board findByNo(int no) throws Exception ;  

  int update(Board board) throws Exception ;

  int delete(int no) throws Exception ;

  List<Board> findAll() throws Exception ;

  List<Board> findAll2() throws Exception ;
}


//메서드 시그너처만 남긴다.
//추상 메서드로 만든다 => 인터페이스는 규칙을 정의하는 것이기 때문이다.













