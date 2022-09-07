package com.bitcamp.board.domain;

import java.sql.Date;

// java.io.Serializable 인터페이스
// - 인스턴스를 통째로 입출력 할 수 있도록 표시하는 용도이다.
// - 인터페이스에 추상 메서드가 선언되어 있지 않기 때문에 따로 메서드를 구현할 필요는 없다.
// 
public class Board {

  public int no;
  public String title;
  public String content;
  public int memberNo;
  public String password;
  public int viewCount;
  public Date createdDate;

  @Override
  public String toString() {
    return "Board [no=" + no + ", title=" + title + ", content=" + content + ", memberNo="
        + memberNo + ", password=" + password + ", viewCount=" + viewCount + ", createdDate="
        + createdDate + "]";
  }
}



//벨류 오브젝트 = VO 
//값객체 
//도메인객체 = DTO





