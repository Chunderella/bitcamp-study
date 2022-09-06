// 데이터를 처리하는 코드를 별도의 클래스로 캡슐화시킨다.
// => data 영속성(지속성)을 관리하는 클래스를 DAO(Data Access Object)라 부른다.
// => data 영속성(지속성)
//    - 데이터를 저장하고 유지하는 것.
//    - "데이터 퍼시스턴스(persistence)"라 부른다.
package com.eomcs.jdbc.ex2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {
  public int delete(int no) throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb?user=study&password=1111");
        Statement stmt = con.createStatement()) {

      // 첨부파일 삭제
      stmt.executeUpdate("delete from x_board_file where board_id = " + no);

      // 게시글 삭제
      return stmt.executeUpdate("delete from x_board where board_id=" + no);
    }
  }

  public List<Board> findAll() throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb?user=study&password=1111");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from x_board order by board_id desc")) {

      //받은 다음에 보드 객체에 담고 리스트에 담는다.

      ArrayList<Board> list = new ArrayList<>();
      while (rs.next()) {
        Board board = new Board();
        //새로운 보드객체를 만들어서 담아야함. *덮어쓰기 방지
        board.setNo(rs.getInt("board_id"));
        board.setTitle(rs.getString("title"));
        board.setContent(rs.getString("contents"));
        board.setRegisteredDate(rs.getDate("created_date"));
        board.setViewCount(rs.getInt("view_count"));
        list.add(board);
      }
      return list;
    }
  }

  public int insert(Board board) throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb?user=study&password=1111");
        Statement stmt = con.createStatement();) {

      String sql = String.format(
          "insert into x_board(title,contents) values('%s','%s')", 
          board.getTitle(),
          board.getContent());

      return stmt.executeUpdate(sql); //서버에 보내 리턴
    }
  }
  //보드객체를 파라미터로 받고
  public int update(Board board) throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb?user=study&password=1111");
        Statement stmt = con.createStatement()) {

      String sql = String.format(//업데이트에 해당되는 sql문을 준비
          "update x_board set title='%s', contents='%s' where board_id=%d", 
          board.getTitle(),
          board.getContent(),
          board.getNo());

      return stmt.executeUpdate(sql);
    }
  }

  public Board findBy(String no) throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb?user=study&password=1111");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from x_board where board_id = " + no)) { //특정 게시글 번호의 데이터를 서버에 질의하고 리절트 셋을 리턴받음
      //이를 통해 서버에 생성된 결과를 가져온다.
      //가져오면 보드객체에 담아서 리턴하고 가져오지 않으면 null 리턴

      if (rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_id"));
        board.setTitle(rs.getString("title"));
        board.setContent(rs.getString("contents"));
        board.setRegisteredDate(rs.getDate("created_date"));
        board.setViewCount(rs.getInt("view_count"));
        return board;

      } else {
        return null;
      }
    }
  }
}


