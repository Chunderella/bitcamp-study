// Iterator 디자인 패턴 : 1) 패키지 멤버로 Iterator 클래스 정의하기

package com.eomcs.design_pattern.iterator.after1;

import java.util.HashSet;

public class Test01 {

  public static void main(String[] args) {
    ArrayList<String> list1 = new ArrayList<>();
    list1.add("aaa");
    list1.add("bbb");
    list1.add("ccc");
    list1.add("ddd");

    LinkedList<String> list2 = new LinkedList<>();
    list2.add("aaa2");
    list2.add("bbb2");
    list2.add("ccc2");
    list2.add("ddd2");

    Stack<String> list3 = new Stack<>();
    list3.push("aaa3");
    list3.push("bbb3");
    list3.push("ccc3");
    list3.push("ddd3");

    Queue<String> list4 = new Queue<>();
    list4.offer("aaa4");
    list4.offer("bbb4");
    list4.offer("ccc4");
    list4.offer("ddd4");


    HashSet<String> list5 = new HashSet<String>();
    list5.add("aaa5");
    list5.add("bbb5");
    list5.add("ccc5");
    list5.add("ddd5");
    //배열을 여러개를 만들어 놓고 수학계산식(해쉬값)으로 값을 뽑아서 지정된 위치에 넣음 




    // 목록에서 값 꺼내기
    //1) ArrayList에서 값 꺼내기
    // => ArrayList
    //1) linkedList에서 값 꺼내기
    Iterator <String> iterator1 = list1.iterator();
    while (iterator1.hasNext()) {
      System.out.println(iterator1.next());
    }
    for (int i = 0 ; i < list1.size(); i++) {
      System.out.println(list1.get(i));
    }
    System.out.println("================ArrayList=================");


    Iterator <String> iterator2 = list2.iterator();
    while (iterator2.hasNext()) {
      System.out.println(iterator2.next());
    }
    for (int i = 0 ; i < list2.size(); i++) {
      System.out.println(list2.get(i));
    }
    System.out.println("==============linkedList===================");

    //stack 에서 값 꺼내기
    Iterator <String> iterator3 = list3.iterator();
    while (iterator3.hasNext()) {
      System.out.println(iterator3.next());
    }
    while(!list3.empty()) {
      System.out.println(list3.pop());
    }
    System.out.println("==============stack===================");

    //queue 에서 값 꺼내기
    Iterator <String> iterator4 = list4.iterator();
    while (iterator4.hasNext()) {
      System.out.println(iterator4.next());
    }
    while(!list4.empty()) {
      System.out.println(list4.poll());
    }
    System.out.println("=================queue================");

    //HashSet 에서 값 꺼내기
    //=>java.util.HashSet의 iterator()가 리턴하는 객체는
    //우리가 만든 Iterator가 아니라,
    //=>java.util.Iterator 구현체를 리턴한다.
    // 비록 우리가 만든 Iterator가 아닐지라도 사용법( 메서드명)은 같다.
    //=>해시셋은 입력된 순서가 아니라 해시값의 오름차순으로 꺼낸다.

    java.util.Iterator <String> iterator5 = list5.iterator();
    while (iterator5.hasNext()) {
      System.out.println(iterator5.next());
    }
    System.out.println("=================HashSet================");

    //Iterator 설계 패턴의 특징
    //-자료 구조에 상관없이 꺼내는 방식이 같다.
    //- 프로그래밍의 일관성을 제공한다.

    // 문제점
    //-ArrayListIterator는 오직 ArrayList 클래스에서만 생성한다.
    //-즉 ArrayList 가 아닌 클래스에서 생성할 일이 없다.
    //-그럼에도 불구하고 패키지 멤버이기 때문에 전체 패키지에 공개되어 있다.


    //해결책
    //-각 Iterator 클래스를 Iterator를 생성하는 클래스 안으로 넣어서 쓸데없는 노출을 막는다.
    //-외부의 객체는 Iterator 인터페이스 규칙에 따라 사용할 수 있어 중첩 클래스로 만들어도 괜찮다.

  }
}












