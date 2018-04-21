package thread;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static Object lockA = new Object();
    public static Object lockB = new Object();

    public static void main(String[] args) {
        String str  =   new  String( " hello " );
        ReferenceQueue< String > rq  =   new  ReferenceQueue < String > ();
        WeakReference< String > wf  =   new  WeakReference < String > (str, rq);
        str = null ;
        String str1 = wf.get();
    }
}
