package com.base.datastructure.collection;

import java.util.*;

/**
 * @Auther: zds
 * @Date: 2023/02/14/17:28
 * @Description:
 */
public class TestSetToList {

//    public static void setListOrder(List<String> orderRegulation, List<String> targetList) {
//        Collections.sort(targetList,((o1, o2) -> {
//            int io1 = orderRegulation.indexOf(o1);
//            int io2 = orderRegulation.indexOf(o2);
//            return io1 - io2;
//        }));
//    }

    /**
     * 使用LinkedHashSet对ArrayList进行排序
     *
     * @param arrayList
     * @param linkedHashSet
     * @return
     */
    private static List<String> sortListBySetOrder(List<String> arrayList, Set<String> linkedHashSet) {
        List<String> result = new ArrayList<>(linkedHashSet);
        result.retainAll(arrayList);
        arrayList.removeAll(result);
        result.addAll(arrayList);
        return result;
    }

    private static List<String> sortByLinkedHashSet(List<String> list, LinkedHashSet<String> linkedHashSet) {
        List<String> tempList = new ArrayList<>(linkedHashSet);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            list.set(i, tempList.get(i));
        }
        return list;
    }

    public static ArrayList<String> sortArraylist(ArrayList<String> arrayList, LinkedHashSet<String> linkedHashSet) {
        Map<String, Integer> map = new HashMap<>();
        int i = 0;

        for(String key : linkedHashSet) {
            map.put(key, i);
            i++;
        }

        arrayList.sort((o1, o2) -> {
            Integer index1 = map.get(o1);
            Integer index2 = map.get(o2);

            if (index1 != null && index2 != null) {
                return index1.compareTo(index2);
            }
            return 0;
        });
        return arrayList;
    }

    public static LinkedHashSet sortByOrder(LinkedHashSet list1,LinkedHashSet list2){
        LinkedHashSet result = new LinkedHashSet();
        Iterator it1 = list1.iterator();
        while (it1.hasNext()) {
            String str1 = (String) it1.next();
            Iterator it2 = list2.iterator();
            while (it2.hasNext()) {
                String str2 = (String) it2.next();
                if (str1.equals(str2)) {
                    result.add(str1);
                }
            }
        }
        return result;
    }

    public static Set<String> sort(LinkedHashSet<String> set1, LinkedHashSet<String> set2) {
        Iterator<String> iterator1 = set1.iterator();
        Iterator<String> iterator2 = set2.iterator();
        LinkedHashSet<String> resultSet = new LinkedHashSet<>();
        //遍历第二个LinkedHashSet
        while (iterator2.hasNext()) {
            String item2 = iterator2.next();
            while (iterator1.hasNext()) {
                String item1 = iterator1.next();
                //第一个LinkedHashSet中元素与第二个LinkedHashSet元素相同，则加入最终结果集
                if (item1.equals(item2)) {
                    resultSet.add(item1);
                }
            }
            //重置第一个LinkedHashSet迭代器
            iterator1 = set1.iterator();
        }
        set1.clear();
        set1.addAll(resultSet);
        return set1;
    }

    public static void main(String[] args) {
//        Set<String> linkedHashSet = new LinkedHashSet<>();
//        linkedHashSet.add("a");
//        linkedHashSet.add("c");
//        linkedHashSet.add("b");
//        linkedHashSet.add("w");
//        linkedHashSet.add("d");
//
//        System.out.println("排序前：" + linkedHashSet);
//        ArrayList<String> strings = new ArrayList<>(linkedHashSet);
//            System.out.println("排序后：" + strings);
//        long l = System.currentTimeMillis();
//        Collections.shuffle(strings);
//        System.out.println("排序后：" + strings);
//
//        LinkedHashSet<String> linkedHashSet2 = new LinkedHashSet<>();
//        linkedHashSet2.add("b");
//        linkedHashSet2.add("d");
//        linkedHashSet2.add("e");
//        linkedHashSet2.add("a");
//        linkedHashSet2.add("c");
//        linkedHashSet2.add("f");
//
////        Set<String> sort = sort(linkedHashSet, linkedHashSet2);
////        LinkedHashSet sort = sortByOrder(linkedHashSet, linkedHashSet2);
//        List<String> sort = sortListBySetOrder(strings, linkedHashSet2);
////        List<String> sort =sortByLinkedHashSet(strings, linkedHashSet2);
////        ArrayList<String> sort = sortArraylist(strings, linkedHashSet2);
//        System.out.println("排序后：" + sort);
//        System.out.println("排序后：" + (System.currentTimeMillis()-l));

//        HashMap<String, Boolean> objectObjectHashMap = new HashMap<>();
//        objectObjectHashMap.put("a",true);
//        objectObjectHashMap.put("b",false);
//        if (objectObjectHashMap.getOrDefault("c",false)){
//            System.out.println("test c"+objectObjectHashMap.get("c"));
//        }
//        System.out.println("test b"+objectObjectHashMap.get("b"));

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println("内部循环"+j);
                if (j>1){
                    break;
                }
            }
            System.out.println("外部循环"+i);
        }
    }
}
