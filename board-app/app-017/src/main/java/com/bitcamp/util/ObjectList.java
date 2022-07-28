package com.bitcamp.util;

public class ObjectList {

  private static final int DEFAULT_CAPACITY = 10; 
  private int size;
  private Object[] elementData;
  //<<==========================list================================>>
  public ObjectList() {
    elementData = new Object[DEFAULT_CAPACITY];
  }

  public ObjectList(int initialCapacity) {
    elementData = new Object[initialCapacity];
  }
  //<<==========================add================================>>
  public void add(Object e) {
    if (size == elementData.length) {
      grow();
    }
    elementData[size++] = e;
    //    현재 사이즈 값을 이자리에 넣고 후에 사이즈를 증가 시킨다.
  }
  //<<==========================get================================>>
  public Object[] toArray() {
    Object[] arr = new Object[size]; //새 배열을 만들어서 새 레퍼런스 배열에 옮겨 리턴한다.
    for (int i = 0; i < arr.length; i++) {
      arr[i] = this.elementData[i];
    }
    return arr;
  }
  //<<==========================get================================>>
  public Object get(int index) {
    if(index < 0 || index >= size) {
      return null;
    }
    return elementData[index];
  }
  //<<==========================remove================================>>
  public boolean remove(int index) {

    if (index < 0 || index >= size) {
      return false;
    }

    for (int i = index + 1; i < size; i++) {
      elementData[i-1] = elementData[i]; 
    }

    this.elementData[--size] = null;
    return true;
  }

  //<<==========================size================================>>
  public int size() {
    return size;
  }
  //<<==========================grow================================>>
  private void grow() {
    int newCapacity = elementData.length + (elementData.length >> 1);
    Object[] newArray = new Object[newCapacity];
    for (int i = 0; i < elementData.length; i++) {
      newArray[i] = elementData[i];
    }//BoardList의 grow를 복사해와서, this.~ 를 elementDate로 변경
    elementData = newArray;
  }


}

