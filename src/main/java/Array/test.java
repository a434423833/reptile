package Array;

import java.util.Iterator;
import java.util.List;

/**
 * Created by caohao on 2018-04-18.
 */
public class test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add("a" + i);
        }
        show(list);
        for (Iterator<String> it = list.iterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }
        new java.util.ArrayList<>();
    }

    private static void show(List list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println(list.size());
    }

}
