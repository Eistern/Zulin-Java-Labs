public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Executor executor = new Executor();
        executor.processFile("src\\workflow.txt");
    }
}
