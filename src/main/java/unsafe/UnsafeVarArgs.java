package unsafe;

import java.util.concurrent.ThreadLocalRandom;

public class UnsafeVarArgs {

    /**
     * 두가지 조건중 배열의 참조가 밖으로 노출되면 안된다는것을 어기고 있다.
     */
    static <T> T[] toArray(T... args) {
        return args; //Object 타입을 반환한다
    }

    /**
     * 랜덤을 활용해 임의로 배열을 만들어 주는 함수
     */
    static <T> T[] pickTwo(T a, T b, T c) {
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0:
                return toArray(a, b);
            case 1:
                return toArray(a, c);
            case 2:
                return toArray(b, c);
        }
        throw new AssertionError(); //도달할 수 없다.
    }

    public static void main(String[] args) {
        String[] attributes = pickTwo("좋은", "빠른", "저렴한"); //String[] 타입으로 캐스팅 하지만 에러 발생
        //System.out.println("attributes = " + Arrays.toString(attributes));
    }
}
