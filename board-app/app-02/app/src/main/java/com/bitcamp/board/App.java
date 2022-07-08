/*
 * 게시판 관리 애플리케이션
 * 비트캠프-20200704 */
package com.bitcamp.board;

public class App {
  
    public static void main(String[] args) {
        System.out.println("[게시판 애플리케이션]");
        System.out.println();
        System.out.println("환영합니다!");
        //System.out.println("\n환영합니다!\n");
        System.out.println();

        System.out.println("메뉴:");
        System.out.println("  1:게시글 목록");
        System.out.println("  2:게시글 상세보기");
        System.out.println();
        System.out.println("메뉴를 선택하세요[1..2] ");     
        
        java.util.Scanner keyboardInput = new java.util.Scanner(System.in);

        //java.lang 패키지 (println)
        //java.util 패키지 (Scanner)
    }
}
