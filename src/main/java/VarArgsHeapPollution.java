import java.util.List;

public class VarArgsHeapPollution {

    public static void varArgsWithParameterizedType(List<String>... stringList) {
        System.out.println("stringList = " + stringList);
    }

    public static <T> void varArgsWithGeneric(T... genericList) {
        System.out.println("genericList = " + genericList);
    }
}
