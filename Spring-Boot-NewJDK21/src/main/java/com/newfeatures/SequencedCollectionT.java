package com.newfeatures;

import java.util.ArrayList;

/**
 *在顺序集合出现之前，
 * Java 并没有一个统一的接口来描述具有确定出现顺序的集合。
 * 与顺序集合的处理相关的方法，散落在 Java 集合类库的不同地方。这些方法并没有统一的声明，使用起来很不方便。
 * Java 21 的顺序集合会解决这些问题。增加了 3 个新的接口。
 */
public class SequencedCollectionT {
    public static void main(String[] args) {
        //List继承SequencedCollection接口
//        包含的方法：
//        reversed 方法返回一个逆序的 SequencedCollection 对象。
//        addFirst 和 addLast 方法分别在集合的起始和结束位置添加新的元素。
//        getFirst 和 getLast 方法分别获取集合的第一个和最后一个元素。
//        removeFirst 和 removeLast 方法分别删除集合的第一个和最后一个元素
        var list=new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.addFirst("d");
        list.addLast("e");
        list.reversed();
        //另外还有SequencedSet和SequencedMap顺序接口
    }
}
