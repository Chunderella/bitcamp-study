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
  //예외를 보고하는 메서드인 경우
  //메서드 선언부에 어떤 예외를 보고하는지 표시해야 한다.
  //오류가 발생했을 때 예외 정보를 던지는 메서드인 경우
  //메서드 선언부에 던지는 예외 정보 타입을 표시해야 한다.


  /**
   * 목록에서 인덱스에 해당하는 항목을 찾아 리턴한다. 
   * @param index 목록에 저장된 항목에 인덱스
   * @return index에 저장된 항목
   * @throws ListException 인덱스가 무효함
   */

  //개발자가 예외 클래스 이름만 보고도
  //어떤 작업을 하다가 예외가 발생했는지 
  //직관적으로 알 수 있도록 사용자 정의 예외를 던진다!!
  //==>RuntimeException 계열의 예외는 메서드 선언부에 표시할 필요가 없다.
  public Object get(int index) /*throws ListException*/ {
    if(index < 0 || index >= size) {
      throw new ListException("인덱스가 무효함!");
    }
    return elementData[index];
  }
  //<<==========================remove================================>>
  //예외를 보고하는 메서드인 경우
  //메서드 선언부에 어떤 예외를 보고하는지 표시해야 한다.

  public boolean remove(int index) /*throws ListException*/ {


    if (index < 0 || index >= size) {

      throw new ListException("인덱스가 무효합니다!");
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

