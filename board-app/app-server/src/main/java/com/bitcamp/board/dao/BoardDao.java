package com.bitcamp.board.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
<<<<<<< HEAD
import org.apache.ibatis.annotations.Param;
=======
>>>>>>> 3a7fbaafedd7aa50c6e32bd47bc1a143920b15bd
import com.bitcamp.board.domain.AttachedFile;
import com.bitcamp.board.domain.Board;

@Mapper
public interface BoardDao {

  int insert(Board board);

  Board findByNo(int no);

  int update(Board board);

  int delete(int no);

  int deleteByMember(int memberNo);

<<<<<<< HEAD
  List<Board> findAll(
      @Param("keyword") String keyword, 
      @Param("titleSort") String titleSort,
      @Param("startIndex") int startIndex,
      @Param("size") int size);

  int count(@Param("keyword") String keyword, @Param("titleSort") String titleSort);
=======
  List<Board> findAll();
>>>>>>> 3a7fbaafedd7aa50c6e32bd47bc1a143920b15bd

  int insertFiles(Board board);

  AttachedFile findFileByNo(int fileNo);

  List<AttachedFile> findFilesByBoard(int boardNo);

  int deleteFile(int fileNo);

  int deleteFiles(int boardNo);

  int deleteFilesByMemberBoards(int memberNo);
}














