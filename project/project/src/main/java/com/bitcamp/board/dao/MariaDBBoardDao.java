package com.bitcamp.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.bitcamp.board.domain.AttachedFile;
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
    try (
        PreparedStatement pstmt = con.prepareStatement(
            "insert into app_board(title,cont,mno) values(?,?,?)", 
            Statement.RETURN_GENERATED_KEYS);

        PreparedStatement pstmt2 = con.prepareStatement(
            "insert into app_board_file(filepath,bno) values(?,?)")) {

      //게시글 제목과 내용을 app_board 테이블에 저장한다.
      pstmt.setString(1, board.getTitle());
      pstmt.setString(2, board.getContent());
      pstmt.setInt(3, board.getWriter().getNo());
      int count = pstmt.executeUpdate();

      //게시글을 app_board 테이블에 입력 한 후 자동 증가된 PK값을 꺼낸다.
      try (ResultSet rs = pstmt.getGeneratedKeys()) {
        rs.next();
        board.setNo(rs.getInt(1));
      }

      // 게시글의 첨부파일을 app_board_file 테이블에 저장한다.
      List<AttachedFile> attachedFiles = board.getAttachedFiles();
      for(AttachedFile attachedFile : attachedFiles) {
        pstmt2.setString(1,attachedFile.getFilepath()); //파일이름
        pstmt2.setInt(2, board.getNo()); //제목하고 내용
        pstmt2.executeUpdate();
      }
      return count; 
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
      writer.setNo(rs.getInt("mno"));
      writer.setName(rs.getString("name"));

      board.setWriter(writer);

      return board;
    }
  }

  @Override
  public int update(Board board) throws Exception {
    try (PreparedStatement pstmt = con.prepareStatement(
        "update app_board set title=?, cont=? where bno=?")) {

      pstmt.setString(1, board.getTitle());
      pstmt.setString(2, board.getContent());
      pstmt.setInt(3, board.getNo());

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
            + "  join app_member m on b.mno = m.mno"
            + " order by bno desc");
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
        writer.setNo(rs.getInt("mno"));
        writer.setName(rs.getString("name"));

        board.setWriter(writer);

        list.add(board);
      }

      return list;
    }
  }
}














