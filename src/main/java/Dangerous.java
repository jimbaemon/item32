import java.util.List;

public class Dangerous {

    /**
     * 28-3 예제
     */
/*    public static void errorCode() {
        List<String>[] stringList = new List<String>[1];
        List<Integer> intList = List.of(42);
        Object[] objects = stringList;
        objects[0] = intList; // 힙 오염 발생
        String s = stringList[0].get(0); // Class Cast Exception
    }*/

    /**
     * 메서드를 실행하면 ClassCastException을 던진다. 자믹 줄에 컴파일러가 생성한 형변환이 숨어 있기 때문이다.
     * <p>
     * 이처럼 타입 안정성이 깨지니 제네릭 varargs 배열 매개변수에 값을 저장하는 것은 안전하지 않다
     */
    public static void dangerous(List<String>... stringLists) {
        Object[] objects = stringLists;
        objects[0] = List.of(1, 2, 3); // 힙 오염 발생
        String s = stringLists[0].get(0); // Class Cast Exception
    }

    public static void main(String[] args) {
        dangerous(List.of("aa"), List.of("bb"), List.of("cc"));
    }

}
