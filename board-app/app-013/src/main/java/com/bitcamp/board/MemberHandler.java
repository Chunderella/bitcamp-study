/*
 * 게시글 메뉴 처리 클래스
 */
package com.bitcamp.board;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MemberHandler {

  String member ;
  MemberList memberList = new MemberList();

  public MemberHandler() {
    this.member = "회원";
  }

  MemberHandler(String member) {
    this.member = member;
  }
  void execute() {
    //App 클래스에서 이 메서드를 호출할 때 BoardHandler의 인스턴스 주소를 줄 것이다.
    // 그 주소는 this 라는 내장 변수에 보관된다.

    while (true) {
      System.out.printf("%s:\n", this.member);
      System.out.println("  1: 목록");
      System.out.println("  2: 상세보기");
      System.out.println("  3: 등록");
      System.out.println("  4: 삭제");
      System.out.println("  5: 변경");
      System.out.println();

      int menuNo = Prompt.inputInt("메뉴를 선택하세요[1..5](0: 이전) ");
      displayHeadLine();

      // 다른 인스턴스 메서드를 호출할 때 this에 보관된 인스턴스 주소를 사용한다.
      switch (menuNo) { 
        case 0: return;
        case 1: this.onList(); break; 
        case 2: this.onDetail(); break;
        case 3: this.onInput(); break;
        case 4: this.onDelete(); break;
        case 5: this.onUpdate(); break;
        default: System.out.println("메뉴 번호가 옳지 않습니다!");
      }

      displayBlankLine();
    } //게시판 while
  }

  static void displayHeadLine() {
    System.out.println("=========================================");
  }
  //인스턴스 변수를 사용하지 않기 때문에 굳이 인스턴스 맴버로 만들 필요가 없음
  //그냥 스태틱메서드로 사용하면 된다.

  static void displayBlankLine() {
    System.out.println(); // 메뉴를 처리한 후 빈 줄 출력
  }

  void onList() {

    SimpleDateFormat formatter = 
        new SimpleDateFormat("yyyy-MM-dd");

    System.out.printf("[%s 목록]\n", this.member);
    System.out.println("번호 이름 이메일");


    //boardList 인스턴스에 들어 있는 데이터 목록을 가져온다.
    Member[] list = this.memberList.toArray();

    for (Member member : list) {
      Date date = new Date(member.createdDate);
      String dateStr = formatter.format(date); 
      System.out.printf("%d\t%s\t%d\t%s\t%s\n",
          member.no, member.title, member.viewCount, member.writer, dateStr);
    }

  }

  void onDetail() {
    System.out.printf("[%s 회원 상세보기]\n", this.member);

    int boardNo = Prompt.inputInt("조회할 회원 번호? ");

    // 해당 번호의 게시글이 몇 번 배열에 들어 있는지 알아내기

    Member member = this.memberList.get(boardNo);

    // 사용자가 입력한 번호에 해당하는 게시글을 못 찾았다면
    if (member == null) {
      System.out.println("해당 번호의 게시글이 없습니다!");
      return;
    }

    System.out.printf("번호: %d\n", member.no);
    System.out.printf("이름: %s\n", member.title);
    System.out.printf("이메일: %s\n", member.email);
    Date date = new Date(member.createdDate);
    System.out.printf("등록일: %tY-%1$tm-%1$td %1$tH:%1$tM\n", date);

  }

  void onInput() {
    System.out.printf("[%s 회원 등록]\n", this.member);

    Member board = new Member();

    board.title = Prompt.inputString("이름? ");
    board.email = Prompt.inputString("이메일? ");
    board.password = Prompt.inputString("암호? ");
    board.viewCount = 0;
    board.createdDate = System.currentTimeMillis();

    this.memberList.add(board); //보드리스트가 가르키는 배열을 저장하라

    System.out.println("게시글을 등록했습니다.");
  }

  void onDelete() {
    System.out.printf("[%s 삭제]\n", this.member);

    int boardNo = Prompt.inputInt("삭제할 게시글 번호? ");

    if (memberList.remove(boardNo)) {
      System.out.println("삭제하였습니다.");
    }else {
      System.out.println("해당 번호의 게시글이 없습니다!");
    }

  }

  void onUpdate() {
    System.out.printf("[%s 회원 변경]\n", this.member);

    int memberNo = Prompt.inputInt("변경할 회원 번호? ");

    // 해당 번호의 게시글이 몇 번 배열에 들어 있는지 알아내기
    Member member = this.memberList.get(memberNo);

    // 사용자가 입력한 번호에 해당하는 게시글을 못 찾았다면
    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    String newMember = Prompt.inputString("이름?(" + member.title + ") ");
    String newEmail = Prompt.inputString(String.format("이메일?(%s) ", member.email));

    String input = Prompt.inputString("변경하시겠습니까?(y/n) ");
    if (input.equals("y")) {
      member.title = newMember;
      member.email = newEmail;
      System.out.println("변경했습니다.");
    } else {
      System.out.println("변경 취소했습니다.");
    }
  }
}




