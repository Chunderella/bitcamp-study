package com.bitcamp.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import com.bitcamp.board.domain.AttachedFile;
import com.bitcamp.board.domain.Member;
import com.bitcamp.board.domain.Party;
import com.bitcamp.board.service.PartyService;

@Controller
@RequestMapping("/party/")
public class PartyController {

  ServletContext sc;
  PartyService partyService;

  public PartyController(PartyService partyService, ServletContext sc) {
    System.out.println("BoardController() 호출됨!");
    this.partyService = partyService;
    this.sc = sc;
  }

  // InternalResourceViewResolver 사용 전:
  //
  //  @GetMapping("form")
  //  public String form() throws Exception {
  //    return "board/form";
  //  }

  // InternalResourceViewResolver 사용 후:
  @GetMapping("form")
  public void form() throws Exception {
  }

  @PostMapping("add") 
  public String add(
      Party party,
      MultipartFile[] files,
      HttpSession session) throws Exception {

    party.setAttachedFiles(saveAttachedFiles(files));
    party.setWriter((Member) session.getAttribute("loginMember"));

    partyService.add(party);
    return "redirect:list";
  }

  private List<AttachedFile> saveAttachedFiles(Part[] files)
      throws IOException, ServletException {
    List<AttachedFile> attachedFiles = new ArrayList<>();
    String dirPath = sc.getRealPath("/party/files");

    for (Part part : files) {
      if (part.getSize() == 0) {
        continue;
      }

      String filename = UUID.randomUUID().toString();
      part.write(dirPath + "/" + filename);
      attachedFiles.add(new AttachedFile(filename));
    }
    return attachedFiles;
  }

  private List<AttachedFile> saveAttachedFiles(MultipartFile[] files)
      throws IOException, ServletException {
    List<AttachedFile> attachedFiles = new ArrayList<>();
    String dirPath = sc.getRealPath("/party/files");

    for (MultipartFile part : files) {
      if (part.isEmpty()) {
        continue;
      }

      String filename = UUID.randomUUID().toString();
      part.transferTo(new File(dirPath + "/" + filename));
      attachedFiles.add(new AttachedFile(filename));
    }
    return attachedFiles;
  }

  @GetMapping("list")
  public void list(Model model) throws Exception {
    model.addAttribute("boards", partyService.list());
  }

  @GetMapping("detail")
  public Map detail(int no) throws Exception {
    Party party = partyService.get(no);
    if (party == null) {
      throw new Exception("해당 번호의 게시글이 없습니다!");
    }

    Map map = new HashMap();
    map.put("party", party);
    return map;
  }

  @PostMapping("update")    
  public String update(
      Party party,
      Part[] files,
      HttpSession session) 
          throws Exception {

    party.setAttachedFiles(saveAttachedFiles(files));

    checkOwner(party.getNo(), session);

    if (!partyService.update(party)) {
      throw new Exception("게시글을 변경할 수 없습니다!");
    }

    return "redirect:list";
  }

  private void checkOwner(int partyNo, HttpSession session) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    if (partyService.get(partyNo).getWriter().getNo() != loginMember.getNo()) {
      throw new Exception("게시글 작성자가 아닙니다.");
    }
  }

  @GetMapping("delete")
  public String delete(
      int no, 
      HttpSession session) 
          throws Exception {

    checkOwner(no, session);
    if (!partyService.delete(no)) {
      throw new Exception("게시글을 삭제할 수 없습니다.");
    }

    return "redirect:list";
  }

  @GetMapping("fileDelete")
  public String fileDelete(
      int no,
      HttpSession session) 
          throws Exception {

    AttachedFile attachedFile = partyService.getAttachedFile(no); 

    Member loginMember = (Member) session.getAttribute("loginMember");
    Party party = partyService.get(attachedFile.getBoardNo()); 

    if (party.getWriter().getNo() != loginMember.getNo()) {
      throw new Exception("게시글 작성자가 아닙니다.");
    }

    if (!partyService.deleteAttachedFile(no)) {
      throw new Exception("게시글 첨부파일을 삭제할 수 없습니다.");
    }

    return "redirect:detail?no=" + party.getNo();
  }
}






