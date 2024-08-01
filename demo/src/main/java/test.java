import com.example.SubClass;
import com.example.SuperClass;

public class test {
  public static void main(String[] args) {
    SubClass subClass = new SubClass();
    System.out.println(subClass.x);
    subClass.method();

    SuperClass superClass = subClass;
    System.out.println(superClass.x);
    superClass.method();
}
        
}
