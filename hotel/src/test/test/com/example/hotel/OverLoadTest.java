package test.com.example.hotel;

/**
 * @ClassName OverLoadTest
 * @Description TODO
 * @Author
 * @Date 2020/2/12 12:37
 **/
public class OverLoadTest {

    public final void myPrint(int a){
        System.out.println(a);
    }

    public static void myPrint(Integer a){
        System.out.println(a);
    }

    public final void myPrint(float a){
        System.out.println(a);
    }

    private static int myPrint(float a , int b) throws Exception{
        System.out.println(a);
        return b;
    }

    public static void main(String[] args){
        OverLoadTest test = new OverLoadTest();

        String s = "hello";
        s = s + "world";
        System.out.println(s);
    }
}
