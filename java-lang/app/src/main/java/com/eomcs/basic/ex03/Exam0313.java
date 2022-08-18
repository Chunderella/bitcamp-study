// 목록 조회: toArray(E[])
package com.eomcs.basic.ex03;

import java.util.Arrays;

public class Exam0313 {

  static class MyList<E> {
    Object[] list = new Object[5];
    int size;

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


    Member[] arr0 =new Member[list.size()];
    list.toArray(arr0);
    //배열을 만들때 리스트의 수와, 호출하기 전에 수가 다를 수 있기때문에 새로만든 배열에 담는다.

    Member[] arr = list.toArray(new Member[list.size()]); //리턴하는 주소나 넘기는 주소나 같은 배열이다.
    //toArray를 쓸때는 가능하면 toArray의 리턴값을 받아서 써라

    for (Member m : arr) {
      System.out.printf("이름: %s, 나이: %d\n", m.name, m.age);
    }
  }
}
