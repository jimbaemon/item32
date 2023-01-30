import java.util.ArrayList;
import java.util.List;

public class SafeVarArgs {

    @SafeVarargs
    static <T> List<T> flatten(List<? extends T>... lists) {
        final List<T> result = new ArrayList<>();
        for (List<? extends T> list : lists) {
            result.addAll(list);
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> flatten = flatten(List.of("abc", "bcd", "efg"), List.of("kkk"));
    }
}
