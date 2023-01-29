import java.util.Arrays;

public class VarArgs {

    public static void varArgs(String... varArgs) {
        System.out.println(Arrays.toString(varArgs));
    }

    public static void main(String[] args) {
        varArgs("aa");
        varArgs("aa", "bb");
        varArgs("aa", "bb", "계속 늘어난다.");
    }

}
