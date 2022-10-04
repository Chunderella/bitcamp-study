package com.bitcamp.board.domain;

public class AttachedFile {
    private int no;
    private String filepath;
    private int boardNo;

    public AttachedFile() {} //기본생성자도 있고


    //파일이름을 받아서 초기화시키는 생성자도 있다
    public AttachedFile(String filepath) {
        this.filepath = filepath;
    }

    //첨부파일에서 게시글 번호를 알아낼 필요가 있음.
    //테이블에 있는 컬럼값을 담을 수 있도록 필드를 추가했다.



    @Override
    public String toString() {
        return "AttachedFile [no=" + no + ", filepath=" + filepath + ", boardNo=" + boardNo + "]";
    }


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }


    public String getFilepath() {
        return filepath;
    }


    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }



    public int getBoardNo() {
        return boardNo;
    }


    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }






}
