public class FirstDemo {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
        
        System.out.println("Let's demonstrate a simple calculation:");
        int answer;
        answer = 2 + 2;
        System.out.println("2 plus 2 is " + answer);
        
        String greeting = "Hello";
        System.out.println("Length of \"" + greeting + "\" is " + greeting.length());

        System.out.printf("%d + %d = %d\n", 2, 2, 4);
        String token = "JavaClass ";
        token = token.concat("password");
        System.out.println(token);
    }
}
