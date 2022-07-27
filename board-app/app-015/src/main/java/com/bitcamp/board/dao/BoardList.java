package com.bitcamp.board.dao;

import com.bitcamp.board.domain.Board;
import com.bitcamp.util.ObjectList;

// 게시글 목록을 관리하는 역할
//
public class BoardList extends ObjectList{
  private int boardNo = 0;

  @Override
  public void add(Object e) {

    Board board = (Board) e;
    board.no = nextNo();
    super.add(e);
  }

  @Override
  public Board get(int boardNo) {
    for (int i = 0; i < size(); i++) { //this 현재 클래스에서 찾음
      Board board = (Board) super.get(i); //현재 get이 아닌 수퍼클래스의 get을 찾는다.
      if (board.no == boardNo) {
        return board;
      }
    }
    return null;
  }

  @Override
  public boolean remove(int boardNo) {
    for (int i = 0; i < size(); i++) {
      Board board = (Board) super.get(i);
      if (board.no == boardNo) {
        return super.remove(i);

      }
    }

    return false;
  }



  private int nextNo() {
    return ++boardNo;
  }
}














