package com.bitcamp.board.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bitcamp.board.domain.AttachedFile;
import com.bitcamp.board.domain.Party;

@Mapper
public interface PartyDao {

  int insert(Party party);

  Party findByNo(int no);

  int update(Party party);

  int delete(int no);

  int deleteByMember(int memberNo);

  List<Party> findAll();

  int insertFiles(Party party);

  AttachedFile findFileByNo(int fileNo);

  List<AttachedFile> findFilesByParty(int partyNo);

  int deleteFile(int fileNo);

  int deleteFiles(int partyNo);

  int deleteFilesByMemberParties(int memberNo);
}














