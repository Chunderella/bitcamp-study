package com.eomcs.quiz.ex01.sol;

// 출처: codefights.com
// 한 줄에 버그가 있다. 고쳐라! 

/* n!(n 팩토리얼) 값을 조사하여 몇 개의 0으로 끝나는 지(trailing zeros) 개수를 리턴하라.
예1) 10!의 값은 두 개의 0으로 끝난다.
예2) 23!은 4개의 값으로 끝난다. (23!의 값은 4바이트를 초과하기 때문에 테스트하지 말아라!)
 */
/* 원문
Given an integer n, find the number of trailing zeros 
in the decimal representation of n! 
(the exclamation mark means factorial).

Example

for n = 10 output should be 2

[input] integer n

a positive integer
[output] integer
 */
//
// [시간 복잡도]
// - O(k) : k는 5^k <= n < 5^(k+1), n은 n!에서 n 값.
//
public class Test07 {
  public static void main(String[] args) {
    System.out.println(factorialTrailingZeros(1) == 0);
    System.out.println(factorialTrailingZeros(2) == 0);
    System.out.println(factorialTrailingZeros(3) == 0);
    System.out.println(factorialTrailingZeros(4) == 0);
    System.out.println(factorialTrailingZeros(5) == 1);
    System.out.println(factorialTrailingZeros(10) == 2);
    System.out.println(factorialTrailingZeros(15) == 3);
    System.out.println(factorialTrailingZeros(20) == 4);
    System.out.println(factorialTrailingZeros(25) == 6);
    System.out.println(factorialTrailingZeros(30) == 7);
    System.out.println("=============================");

    //    for (int i = 1; i < 100; i++) {
    //      System.out.printf("%d ==> %d, %d\n",
    //          i, factorialTrailingZeros(i), factorialTrailingZeros2(i));
    //    }
  }

  static int factorialTrailingZeros(int n) {  
    int result = 0;
    int primeFactor = 5;
    while (n >= primeFactor) {
      result += n / primeFactor;
      primeFactor *= 5;      
    }
    return result;
  } //수학식을 표현하는 방식  

  static int factorialTrailingZeros2(int n) {  
    int result = 0;
    for (int i = 5; i <= n; i += 5) {
      int number = i;
      while (number % 5 == 0) {
        number /= 5;
        result++;
      }
    }//5의 제곱승을 나누는데 반복문을 돌면서 계산하는 것.
    return result;
  }
}

// "Legendre's formula(= de Polignac's formula)" 수학식을 적용: 
// => f(n) = |n/5| + |n/5^2| + |n/5^3| + ... + |n/5^k|,
// => 5^(k+1) > n,
// => 5^k <= n < 5^(k+1)
// => k = log_5(n)
//
