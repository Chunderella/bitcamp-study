package com.bitcamp.board.dao;

import java.util.LinkedList;
import java.util.List;
import com.bitcamp.board.domain.Board;

// 게시글 목록을 관리하는 역할
//
public class BoardDao {

  // List 인터페이스의 레퍼런스인 list 변수는
  // List 규격에 따라 만든 객체 주소를 담을 수 있다.
  //
  List<Board> list = new LinkedList<>();

  private int boardNo = 0;

  public void insert(Board board) {

    list.add(board); //Linkedlist의 메서드
  }

  public Board findByNo(int boardNo) {

    // 의존 객체 BoardList를 이용하여 기존에 저장된 게시글 목록 중에 
    // 해당 번호의 게시글을 찾는다.
    for (int i = 0; i < list.size(); i++) {
      Board board = list.get(i);
      if (board.no == boardNo) {
        return board;
      }
    }

    return null;
  }

  public boolean delete(int boardNo) {

    // 의존 객체 ObjectList을 이용하여 목록에 저장된 게시글을 찾아 삭제한다.
    for (int i = 0; i < list.size(); i++) {
      Board board = list.get(i);
      if (board.no == boardNo) {
        return list.remove(i) != null;
      }
    }

    return false;
  }

  public Board[] findAll() {

    // 의존 객체 ObjectList를 이용하여 목록에 저장된 게시글을 가져온다.
    return list.toArray(new Board[0]);
  }
  private int nextNo() {
    return ++boardNo;
  }
}



//의존 객체를 가리킬 때는 클래스명 대신 인터페이스 이름을 사용한다.










