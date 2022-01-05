public class OverloadingDemo {
    
    // name + parameters = signature
    /*
    public static int sum(int a, int b) {
        return a + b;
    }
    
    public static double sum(double a, double b) {  
        return a + b;
    }
    */
 
    /*
    public static int sum(double a, double b) {
        return (int)(a + b);
    }
    */
    
    public static double sum(int a, double b) {
        return a + b;
    }
    
    public static double sum(double a, int b) {
        return a + b;
    }
    
    public static void main(String[] args) {
        int a = 5;
        int b = 10; 
        System.out.println(sum(a, (double)b));
        
    }
    
}

