package com.bitcamp.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.bitcamp.board.domain.Board;
import com.bitcamp.board.domain.Member;

public class MariaDBBoardDao implements BoardDao {

  Connection con;

  //DAO가 사용할 의존 객체 Connection을 생성자의 파라미터로 받는다.
  public MariaDBBoardDao(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(Board board) throws Exception {
    try (PreparedStatement pstmt = con.prepareStatement(
        "insert into app_board(title,cont,mno) values(?,?,?)")) {
      pstmt.setString(1, board.getTitle());
      pstmt.setString(2, board.getContent());
      pstmt.setInt(3, board.getWriter().getNo());
      return pstmt.executeUpdate();
    }
  }

  @Override
  public Board findByNo(int no) throws Exception {
    try (PreparedStatement pstmt = con.prepareStatement(
        "select "
            + "    b.bno,"
            + "    b.title,"
            + "    b.cont,"
            + "    b.cdt,"
            + "    b.vw_cnt,"
            + "    m.mno,"
            + "    m.name"
            + "    from app_board b"
            + "  join app_member m on b.mno = m.mno" 
            + "    where b.bno=" + no);
        ResultSet rs = pstmt.executeQuery()) {

      if (!rs.next()) {
        return null;
      }

      Board board = new Board();
      board.setNo(rs.getInt("bno"));
      board.setTitle(rs.getString("title"));
      board.setContent(rs.getString("cont"));
      board.setCreatedDate(rs.getDate("cdt"));
      board.setViewCount(rs.getInt("vw_cnt"));

      Member writer = new Member();
      writer.no = rs.getInt("mno");
      writer.name = rs.getString("name");

      board.writer = writer;

      return board;
    }
  }

  @Override
  public int update(Board board) throws Exception {
    try (PreparedStatement pstmt = con.prepareStatement(
        "update app_board set title=?, cont=? where bno=?")) {

      pstmt.setString(1, board.title);
      pstmt.setString(2, board.content);
      pstmt.setInt(3, board.no);

      return pstmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (PreparedStatement pstmt = con.prepareStatement("delete from app_board where bno=?")) {

      pstmt.setInt(1, no);
      return pstmt.executeUpdate();
    }
  }

  @Override
  public List<Board> findAll() throws Exception {
    try (PreparedStatement pstmt = con.prepareStatement(
        "select "
            + "    b.bno,"
            + "    b.title,"
            + "    b.cdt,"
            + "    b.vw_cnt,"
            + "    m.mno,"
            + "    m.name"
            + "    from app_board b"
            + "  join app_member m on b.mno = m.mno");
        ResultSet rs = pstmt.executeQuery()) {

      /*
      +-----+---------+---------------------+--------+-----+-------+
      | bno | title   | cdt                 | vw_cnt | mno | name  |
      +-----+---------+---------------------+--------+-----+-------+
      |  16 | 제목6   | 2022-09-07 14:38:18 |      0 |   4 | user4 |
      |  17 | okok    | 2022-09-07 16:49:46 |      0 |   3 | user3 |
      |  21 | okok    | 2022-09-08 10:53:35 |      0 |   3 | user3 |
      |  24 | 44      | 2022-09-19 11:37:31 |      0 |   4 | user4 |
      |  29 | ㅇ      | 2022-09-26 12:43:45 |      0 |   4 | user4 |
      |  30 | 333     | 2022-09-26 12:45:06 |      0 |   4 | user4 |
      |  31 | xxxxxx  | 2022-09-26 14:21:21 |      0 |   4 | user4 |
      |  32 | zzz     | 2022-09-26 14:23:56 |      0 |   4 | user4 |
      |  35 | 111     | 2022-09-28 12:26:35 |      0 |   3 | user3 |
      |  36 | 2222    | 2022-09-28 12:26:58 |      0 |   2 | user2 |
      +-----+---------+---------------------+--------+-----+-------+
       */
      ArrayList<Board> list = new ArrayList<>();

      while (rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("bno"));
        board.setTitle(rs.getString("title"));
        board.setCreatedDate(rs.getDate("cdt"));
        board.setViewCount(rs.getInt("vw_cnt"));

        Member writer = new Member();
        writer.no = rs.getInt("mno");
        writer.name = rs.getString("name");

        board.writer = writer;

        list.add(board);
      }

      return list;
    }
  }
}














