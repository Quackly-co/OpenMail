package ee.ve2.openmail;

public class Main {
    public static void main(String[] args) {
        OpenMailConsole console = OpenMailConsole.create(System.out, System.in);
        console.run(args);
    }
}
