package com.bitcamp.util;

//역할 :
//List 인터페이스를 구현한다.

//<인터페이스>
public abstract class abstractList implements List {

    //추상클래스가 되야한다.


// ==> 같은 패키지에 소속된 클래스나 서브 클래스에서 접근 가능(프로텍트)
protected int size; 

//서브클래스에게 상속해 줄 메서드를 미리 구현
@Override
public int size() {
    return size;
}
//사이즈라는 필드값을 리턴.
//List 인터페이스의 나머지 메서드는 추상 메서드인채로 그냥 둔다.

}


//프로텍트 = 서브클래스 / 같은 패키지에 소속된 클래스에서는 접근 가능