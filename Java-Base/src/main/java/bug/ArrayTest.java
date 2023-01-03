package bug;

import java.util.*;

/**
 * @Auther: zds
 * @Date: 2022/12/26/00:02
 * @Description:
 */
public class ArrayTest {

    /**
     * Arrays.asList(strArray)返回值是java.util.Arrays类中一个私有静态内部类java.util.Arrays.ArrayList，
     * 它并非java.util.ArrayList类。
     * java.util.Arrays.ArrayList类具有 set()，get()，contains()等方法，
     * 但是不具有添加add()或删除remove()方法,所以调用add()方法会报错。
     * @param args
     */
//    public static void main(String[] args) {
//        String[] strArray = new String[2];
//        List list = Arrays.asList(strArray);
//        list.add("1");
//        System.out.println(list);
//    }

//    public static void main(String[] args) {
//        String[] strArray = new String[2];
//        Set<String> set = new HashSet<String>(Arrays.asList(strArray));
//        System.out.println(set.contains("test"));
//    }

//    public static void main(String[] args) {
//        ArrayList<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c", "d"));
//        for (int i = 0; i < list.size(); i++) {
//            list.remove(i);
//        }
//        System.out.println(list);
//    }

    public static void add(List list, Object o){
        list.add(o);
    }
    public static void main(String[] args){
        List<String> list = new ArrayList<String>();
        add(list, 10);
        String s = list.get(0);
    }

}
