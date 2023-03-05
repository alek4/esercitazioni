import java.util.Scanner;

public class Soluzione {
    private static void print(StringMultiSet s) {
        System.out.println(s.size() + " " + s);
    }

    public static void main(String[] args) {
        ListMultiSet a, b;
        try (Scanner sc = new Scanner(System.in)) {
            String line = sc.nextLine();
            a = new ListMultiSet(line.split(" "));
            line = sc.nextLine();
            b = new ListMultiSet(line.split(" "));
        }

        print(a);
        print(b);
        print(a.union(b));
        print(a.intersection(b));
    } 
}
    