package playground;

public class JavaPlayground {
    public static void main(String[] args) {
        System.out.println(Person.N_EYES);
        // Class level functionality. We are accessing the constant above through the class
    }
}

class Person {
    public static final int N_EYES = 2;
}
