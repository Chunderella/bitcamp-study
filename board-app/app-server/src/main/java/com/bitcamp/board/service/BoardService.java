package com.bitcamp.board.service;

import java.util.List;
import com.bitcamp.board.domain.AttachedFile;
import com.bitcamp.board.domain.Board;

// 비즈니스 로직을 수행하는 객체의 사용규칙(호출규칙)
//
public interface BoardService {

  void add(Board board) throws Exception;

  boolean update(Board board) throws Exception;

  Board get(int no) throws Exception;

  boolean delete(int no) throws Exception;

<<<<<<< HEAD
  List<Board> list(String keyword, String titleSort, int pageNo, int pageSize) throws Exception;

  int size(String keyword, String titleSort) throws Exception;
=======
  List<Board> list() throws Exception;
>>>>>>> 3a7fbaafedd7aa50c6e32bd47bc1a143920b15bd

  AttachedFile getAttachedFile(int fileNo) throws Exception;

  boolean deleteAttachedFile(int fileNo) throws Exception;

}








