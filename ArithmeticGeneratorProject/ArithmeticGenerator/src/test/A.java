package test;

public class A {
    public static void main(String[] args) {
        String a = "(2 x 5)";
        a = a.replace(" ", "");
        a = a.replace("(", "");
        a = a.replace(")", "");
        System.out.println(a);

        // String[] strs = a.split("x");
        // for(String s : strs){
        //     System.out.println(s);
        // }
    }
}
