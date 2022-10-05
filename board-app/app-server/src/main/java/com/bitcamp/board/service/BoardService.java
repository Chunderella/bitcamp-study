package com.bitcamp.board.service;

import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.Board;

//비지니스 로직을 수행하는 객체
// - 메서드의 이름은 업무와 관련된 이름을 사용한다.
// - 
public class BoardService {
  BoardDao boardDao;

  public BoardService(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  //게시글을 추가하는 기능
  public void add(Board board) throws Exception {
    // 1)게시글 등록 
    if (boardDao.insert(board) == 0) {
      throw new Exception("게시글 등록 실패!");
    }

    // 2)첨부파일 등록
    boardDao.insertFiles(board);
  }

}

