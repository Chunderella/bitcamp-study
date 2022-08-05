package com.eomcs.design_pattern.iterator.after1;

public class Queue<E> extends LinkedList<E> {

  public void offer(E value) {
    this.add(value);
  }

  public E poll() {
    return this.remove(0);
  }

  public boolean empty() {
    return this.size == 0;
  }
  //Iterator 구현체를 제공한다.
  @Override
  public Iterator<E> iterator() {
    return new QueueIterator<E>(this); //this는 ArrayList의 객체
  }

  // 자신이 보유한 데이터를 대신 꺼내주는 일을 하는 객체를 리턴한다.

}



