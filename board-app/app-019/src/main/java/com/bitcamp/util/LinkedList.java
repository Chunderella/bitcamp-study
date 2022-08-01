package com.bitcamp.util;


/**
 * Node를 이용해 값의 목록을 관리하는 일을 한다.
 * 
 * @author bongchun
 *
 */

public class LinkedList {

  private Node head; //첫 node의 주소를 저장
  private Node tail; // 마지막 node 의 주소를 저장
  private  int size; // 노드의 개수 => 저장된 데이터의 개수


  /**
   * 파라미터로 주어진 값을 노드에 담아 리스트에 끝에 연결한다.
   * @param value
   */
  //<===================add=====================>
  public void append(Object value) {
    //값을 담은Node 생성
    Node node = new Node(value);
    size++; // 목록의 크기를 한 개 증가시킨다. (this 생략)

    //Node 생성 후 값을 저장한다.
    // node.value = value;
    //리스트의 끝에 노드를 붙인다.
    //만약 리스트에 노드가 없다면
    if(tail == null) {
      head = tail = node; //첫 노드를 등록한다.
      return;
    }

    tail.next = node;   // 리스트 끝에 새 노드를 연결한다.
    node.prev = tail;   // 새 노드가 현재의 끝 노드를 가르키게 한다.
    tail = node;        // 새 노드를 끝 노드로 만든다.
  }
  //<===================get=====================>
  public Object retrieve(int index) {

    //인덱스의 유효 여부 검사
    if(index < 0 || index >= size) {
      throw new ListException("인덱스의 범위를 초과했습니다!");
    }
    //인덱스의 해당하는 노드를 찾을때 head부터 시작한다.     
    Node cursor = head; 
    //지정된 인덱스의 노드 주소를 알아낸다.
    for (int i = 0; i < index; i++) {
      cursor = cursor.next;
    }
    //cursor가 가르키는 노드의 값을 꺼내 리턴한다.
    return cursor.value;
  }
  //<==================remove=================>

  public Object delete(int index) {
    //인덱스의 유효 여부 검사
    if(index < 0 || index >= size) {
      throw new ListException("인덱스의 범위를 초과했습니다!");
    }

    //목록 크기를 한개 줄인다.
    size--;
    //<<=====노드가 한개 남았을때=====>
    //삭제할 값을 임시 보관하여 메서드를 리턴할 때
    //호출자에게 전달한다.
    Object deleted;

    if (head == tail) { //만약 헤드와 테일이 같을 경우(노드가 1개밖에 안남았을때)
      deleted = head.value;  //<< 노드를 삭제하기 전에 리턴할 수 있도록 값을 임시 보관한다.
      head.value = null;
      head = tail = null; // 노드에 들어있는 값 객체의 주소를 비운다(초기화시킨다.)
      return deleted; //delete을 호출하면 삭제를 하되 삭제한 객체를 리턴해준다.
      //메서드를 종료할때 호줄자에게 삭제한 값을 리턴한다.    
    }

    //삭제할 노드를 찾기 위해 시작 노드를 head로 설정한다. < head에서 시작한다.
    Node cursor = head;
    //지정된 인덱스의 노드 주소를 알아낸다.
    for (int i = 0; i < index; i++) {
      cursor = cursor.next;
    }
    //<<=====맨 앞 노드를 삭제할때=====>
    //찾은 노드의 앞/뒤 노드를 바로 연결한다.
    if(cursor.prev != null) { //삭제할 노드 앞에 노드가 있다면 
      cursor.prev.next = cursor.next; //현재 노드의 다음 노드 주소를 "이전 노드"의 next의 저장
    } else { //삭제할 노드가 맨 앞의 노드라면 헤드를 바꿔야함.
      head = cursor.next; //삭제할 다음 노드를 시작 노드로 설정한다.
      head.prev = null; //시작 노드이기 때문에 앞노드를 가르키지 않게 한다.
    }

    //<<=====맨 뒤 노드를 삭제할때=====>
    if(cursor.next != null) { //마지막 노드가 아니라면
      cursor.next.prev = cursor.prev; // 현재 노드의 이 전 노드 주소를 "다음 노드"의 prev 저장
    } else { //마지막 노드라면
      tail = cursor.prev; //현재 커서의 이전 노드를 마지막 노드로 설정한다.
      tail.next = null; // 마지막 노드이기 때문에 다음 노드를 가르키지 않게 한다.
    }
    //삭제할 노드를 초기화 시킨다. 
    //==> garbage 객체가 다른 garbage 객체를 참조하지 않게 한다.
    //
    deleted = cursor.value; // 노드를 삭제하기 전에 노드에 들어있는 값을 임시 보관해둔다.
    cursor.value = null;
    cursor.prev = null;
    cursor.next = null;
    return deleted; // 메서드를 리턴할때 삭제된 값을 호출자에게 전달한다.
  }
  public int length() {
    return this.size;
  }
  public Object[] getArray() {
    //값을 담을 배열 준비
    Object[] arr = new Object[size];
    //노드를 따라가면서 값을 꺼내 배열에 담는다.
    Node cursor = head;
    for(int i = 0; i < size; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    }
    return arr;

  }

}
