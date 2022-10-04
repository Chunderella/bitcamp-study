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
                        "insert into app_board(title,cont,mno) values(?,?,?)",            //게시글 번호는 자동으로 증가.  
                        Statement.RETURN_GENERATED_KEYS); //-> 자동으로 증가된 번호를 알기 위해 사용


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
                board.setNo(rs.getInt(1)); //받아온 첫번째 컬럼값(PK)
                //PK가 여러개일 경우/PK가 여러 컬럼을 가질 경우 등등

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

            board.setWriter(writer); //게시글 작성자 정보

            //게시글 첨부파일 가져오기
            try(PreparedStatement pstmt2 = con.prepareStatement( //특정 게시물의 저장된 특정 파일의 번호를 셀렉
                    "select bfno, filepath, bno from app_board_file where bno = "+ no);
                    ResultSet rs2 = pstmt2.executeQuery()) {

                ArrayList<AttachedFile> attachedFiles = new ArrayList<>();
                while (rs2.next()) { //첨부파일 갯수만큼 가져와서
                    AttachedFile file = new AttachedFile(); //첨부파일 번호와 경로를 담는다.
                    file.setNo(rs2.getInt("bfno"));
                    file.setFilepath(rs2.getString("filepath"));
                    attachedFiles.add(file); //리스트에 담기
                }
                board.setAttachedFiles(attachedFiles); //게시글에 저장하라
            }

            return board; //그 다음 보드에 리턴
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
    @Override
    public AttachedFile findFileByNo(int fileNo) throws Exception {
        //게시글 첨부파일 가져오기
        try(PreparedStatement pstmt = con.prepareStatement( //특정 게시물의 저장된 특정 파일의 번호를 셀렉
                "select bfno, filepath, bno from app_board_file where bfno = "+ fileNo); //bfno 첨부파일 넘버로
                ResultSet rs = pstmt.executeQuery()) {

            if (!rs.next()) { //가져오지 못했다면 리턴해라
                return null;
            }
            AttachedFile file = new AttachedFile(); //있으면 첨부파일 번호와 경로, 게시글번호 담는다.
            file.setNo(rs.getInt("bfno"));
            file.setFilepath(rs.getString("filepath"));
            file.setBoardNo(rs.getInt("bno"));

            return file; //첨부파일 객체를 리턴
        }
    }
    @Override
    public int deleteFile(int fileNo) throws Exception {
        try (PreparedStatement pstmt = con.prepareStatement(
                "delete from app_board_file where bfno=?")) {

            pstmt.setInt(1, fileNo);
            return pstmt.executeUpdate();
        }
    }
}














