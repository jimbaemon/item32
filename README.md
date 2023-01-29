# 제네릭과 가변인수를 함께 쓸 때는 신중하라

## 가변 인수란?

메서드에 넘기는 인수의 개수를 클라이언트가 조절해 줄수 있게 해주는 기능

```java
public static void varArgs(String...varArgs) {
    System.out.println(Arrays.toString(varArgs));
    }
```

아래와 같이 원하는 인자의 숫자를 임의로 지정해 줄 수 있는 편리한 기능
```java
public static void main(String[]args) {
    varArgs("aa");
    varArgs("aa","bb");
    varArgs("aa","bb","계속 늘어난다.");
}
```
![img.png](img.png)

## 가변인수의 허점

가변인수 메서드를 호출하면 가변인수를 담기 위한 배열이 자동으로 하나 만들어진다.

호출시 생성되는 이 메서드가 내부에 노출되기 떄문에 힙오염(Heap Pollution) 위험에 노출될 수 있다.

컴파일로 이점을 알기 때문에 실체화 불가 타입(Generic, 매개변수화 타입 등)으로 varargs를 선언하면 컴파이럴가 경고를 보낸다.

```java
public static void varArgsWithParameterizedType(List<String>... stringList) {
    System.out.println("stringList = " + stringList);
}

public static <T> void varArgsWithGeneric(T... genericList) {
    System.out.println("genericList = " + genericList);
}
```
![img_1.png](img_1.png)

## 힙오염 발생 과정 예제
매개변수화 타입의 변수가 타입이 다른 객체를 참조하면 힙 오염이 발생 합니다.

이렇게 힙오염이 발생한 상황에서 컴파일러가 자동 생성한 형변환은 실패할 수 있으니, 제네릭 타입 시스템이 약속하는 타입 안전성의 근간이 흔들릴 수 있다.

```java
public static void dangerous(List<String>... stringLists) {
    Object[] objects = stringLists;
    objects[0] = List.of(1, 2, 3); // 힙 오염 발생
    String s = stringLists[0].get(0); // Class Cast Exception
}
```
위 메서드를 실행하면 ClassCastException을 던진다. 자믹 줄에 컴파일러가 생성한 형변환이 숨어 있기 때문이다.

이처럼 타입 안정성이 깨지니 **제네릭 varargs 배열 매개변수에 값을 저장하는 것은 안전하지 않다**.

이러한 위험성이 있음에도 제네릭 varargs 매개변수를 받는 메서드를 선언할 수 있게 한 이유는 실무에서 유용하기 때문이다.

실제로 자바 라이브러리 에서도 `Arrays.asList(T... a)`, `Collections.addAll(Collection<? super T> c, T... elements)`, `EnumSet.of(E first, E... rest)` 등을 제공해 주고 이들은 타입 Safe 하다.

## 가변인수를 안전하게 사용하는 방법
타입 안전하게 가변인수를 사용할수 있는 두가지 조건은 아래와 같다.
1. 가변인수 호출시 생성되는 제네릭 배열에 아무것도 저장하지 않고 (그 매개 변수들을 덮어쓰지 않고)
2. 그 배열의 참조가 밖으로 노출되지 않아야 한다.

위와 같은 조건으로 타입 Safe 한 가변인수 메서드를 생성했다면 @SafeVarargs 어노테이션을 이용해서 메서드가 안전함을 보장해야 한다.

