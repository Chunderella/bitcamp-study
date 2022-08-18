// 목록 조회: for(변수:Iterable 객체)
package com.eomcs.basic.ex03;

import java.util.Arrays;
import java.util.Iterator;

public class Exam0314 {

  // 해당 규칙을 준수하는(따라서) 만든 MyList
  //주석으로 일반 클래스로 만들었을때 for문에 list가 오류 발생

  static class MyList<E> implements Iterable<E> {
    Object[] list = new Object[5];
    int size;

    @Override
    public Iterator<E> iterator() {
      class MyListIterator implements Iterator<E> {
        MyList<E> myList;
        int cursor;

        public MyListIterator(MyList<E> myList) {
          this.myList = myList;
        }
        //생성자에서 myList를 받음


        @Override
        public boolean hasNext() {
          System.out.println("hasNext()");
          return cursor < size;
        }
        @Override
        public E next() {
          System.out.println("next()");
          return myList.get(cursor++);
        }
      }
      return new MyListIterator(this);
    }

    public void add(E value) {
      list[size++] = value;
    }

    @SuppressWarnings("unchecked")
    public E get(int i) {
      return (E) list[i];
    }

    public int size() {
      return size;
    }

    public Object[] toArray() {
      Object[] arr = new Object[size];
      for (int i = 0; i < size; i++) {
        arr[i] = list[i];
      }
      return arr;
    }

    @SuppressWarnings("unchecked")
    public E[] toArray(E[] arr) {
      if (arr.length >= size) {
        for (int i = 0; i < size; i++) {
          arr[i] = (E) list[i];
        }
        return arr;

      } else {
        return (E[]) Arrays.copyOf(list, size, arr.getClass());
      }
    }
  }

  static class Member {
    String name;
    int age;

    public Member(String name, int age) {
      this.name = name;
      this.age = age;
    }
  }

  public static void main(String[] args) {

    Member m1 = new Member("홍길동", 20);
    Member m2 = new Member("임꺽정", 30);
    Member m3 = new Member("유관순", 17);

    MyList<Member> list = new MyList<>();
    list.add(m1);
    list.add(m2);
    list.add(m3);


    //list 자리에 올 수 있는 것은 배열 or 규칙에 따라 만든 객체 라면 올 수 있음.

    for (Member m : list) {
      System.out.printf("이름: %s, 나이: %d\n", m.name, m.age);


      // 위 반복문은 컴파일 될 때 다음 문장으로 변환된다.(자바컴파일러가 읽을때)
      //    Iterator<Member> iterator = list.iterator();
      //    while (list)
      //    Member.m = iterator.next();
      //    System.out.printf("이름: %s, 나이: %d\n", m.name, m.age);

    }
  }
}





