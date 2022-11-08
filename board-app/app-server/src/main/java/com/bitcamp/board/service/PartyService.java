package com.bitcamp.board.service;

import java.util.List;
import com.bitcamp.board.domain.AttachedFile;
import com.bitcamp.board.domain.Party;

// 비즈니스 로직을 수행하는 객체의 사용규칙(호출규칙)
//
public interface PartyService {

  void add(Party party) throws Exception;

  boolean update(Party party) throws Exception;

  Party get(int no) throws Exception;

  boolean delete(int no) throws Exception;

  List<Party> list() throws Exception;

  AttachedFile getAttachedFile(int fileNo) throws Exception;

  boolean deleteAttachedFile(int fileNo) throws Exception;

}








