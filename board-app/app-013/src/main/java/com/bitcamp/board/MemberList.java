package com.bitcamp.board;

//게시글 목록을 관리하는 역할
//
public class MemberList {
  static final int DEFAULT_SIZE = 3;

  int boardCount;
  Member[] member;
  int no = 0;

  //생성자
  MemberList() {
    this.member = new Member[DEFAULT_SIZE];
  }

  MemberList(int initCapacity) {
    this.member = new Member[initCapacity];
  }

  //목록에 저장된 인스턴스를 꺼내서 리턴한다.
  Member[] toArray() {
    Member[] arr = new Member[this.boardCount];
    for (int i = 0; i < arr.length; i ++) {
      arr[i] = this.member[i];
    }
    return arr;
  }


  //게시글 번호에 해당하는 Board 인스턴스를 찾아 리턴한다.
  Member get(int boardNo) {
    for (int i = 0; i < this.boardCount; i++) {
      if (this.member[i].no == boardNo) {
        return this.member[i];
      }
    }
    return null;
  }
  void add(Member member) {
    if (this.boardCount == this.member.length) {
      grow();
    }
    member.no = nextNo();
    this.member[this.boardCount++] = member;
  }

  boolean remove(int boardNo) {
    int boardIndex = -1;
    for (int i = 0; i < this.boardCount; i++) {
      if (this.member[i].no == boardNo) {
        boardIndex = i;
        break;
      }
    }

    if (boardIndex == -1) {
      return false;
    }
    //삭제할 항목의 다음 항목을 앞으로 당긴다.
    for (int i = boardIndex + 1; i < this.boardCount; i++) {
      this.member[i - 1] = this.member[i];
    }

    //게시글 개수를 한 개 줄인 후
    //맨 뒤의 있던 항목의 주소를 0으로 설정한다.
    this.member[--this.boardCount] = null;

    return true;
  }



  void grow() {
    int newSize = this.member.length + (this.member.length >> 1);
    Member[] newArray = new Member[newSize];
    for (int i = 0; i <this.member.length; i ++) {
      newArray[i] = this.member[i];
    }
    this.member = newArray;
  }

  int nextNo() {
    return ++no;
  }
}

