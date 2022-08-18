// 목록 조회: 중첩 클래스의 이점
package com.eomcs.basic.ex03;

import java.util.Arrays;
import java.util.Iterator;

public class Exam0315 {

  static class MyList<E> implements Iterable<E> {
    Object[] list = new Object[5];
    int size;

    @Override
    //Iterator을 만든다.
    public Iterator<E> iterator() {
      class MyListIterator implements Iterator<E> {
        //로컬클래스
        //        MyList<E> $0this; (바깥 클래스의 생성자가 자동 생성)
        int cursor;

        //public MyListIterator(MyList<E> $0this) //자동으로생성된다.
        //로컬클래스 문법 아무것도 안주면 this 값이 들어가고
        //필드와 생성자가 자동으로 생성된다.


        @Override
        public boolean hasNext() {
          return cursor < size;
        }
        @Override
        public E next() {
          return MyList.this.get(cursor++);
        }
      }
      return new MyListIterator();
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

    for (Member m : list) {
      System.out.printf("이름: %s, 나이: %d\n", m.name, m.age);
    }
  }
}





