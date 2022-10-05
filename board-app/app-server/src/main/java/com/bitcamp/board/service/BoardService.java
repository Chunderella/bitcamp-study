package com.bitcamp.board.service;

import java.util.List;
import com.bitcamp.board.domain.AttachedFile;
import com.bitcamp.board.domain.Board;

// 비즈니스 로직을 수행하는 객체의 사용규칙(호출규칙)
// - 메서드 이름은 업무와 관련된 이름을 사용한다.
//
public interface BoardService {

  void add(Board board) throws Exception ;

  boolean update(Board board) throws Exception ;

  Board get(int no) throws Exception ;

  boolean delete(int no) throws Exception ;

  List<Board> list() throws Exception ;

  AttachedFile getAttachedFile(int fileNo) throws Exception ;

  boolean deleteAttachedFile(int fileNo) throws Exception ;
}
//교체하기 쉽게 만든다.








