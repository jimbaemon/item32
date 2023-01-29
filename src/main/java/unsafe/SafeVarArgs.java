package unsafe;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SafeVarArgs {

    /**
     * 랜덤을 활용해 임의로 배열을 만들어 주는 함수
     */
    static <T> List<T> pickTwo(T a, T b, T c) {
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0:
                return List.of(a, b);
            case 1:
                return List.of(a, c);
            case 2:
                return List.of(b, c);
        }
        throw new AssertionError(); //도달할 수 없다.
    }

    public static void main(String[] args) {
        List<String> attributes = pickTwo("좋은", "빠른", "저렴한");
    }
}
