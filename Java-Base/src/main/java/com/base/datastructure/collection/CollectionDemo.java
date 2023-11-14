package com.base.datastructure.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author zds
 * @Description
 * 1.Collection接口的子接口包括：Set接口和List接口
 * 2.Map接口的实现类主要有：HashMap、TreeMap、Hashtable、ConcurrentHashMap以及Properties等
 * 3.Set接口的实现类主要有：HashSet、TreeSet、LinkedHashSet等
 * 4.List接口的实现类主要有：ArrayList、LinkedList、Stack以及Vector等
 * @createTime 2022/4/26 17:38
 */
public class CollectionDemo {
    public static void main(String[] args) {

       /* ArrayList特点
                - 基于动态数组的数据结构
                - 随机访问快，增删慢
                - 占用内存少，每个索引的位置是实际的数据
                - 效率高，线程不安全*/



          /*      LinkedList
        - 链表结构
                - 增删快，访问慢（数据多的情况下增删也不一定快）
        - 占用内存较多，每个节点中存储的是实际的数据和前后节点的位置
                - 线程不安全





        Vector
                - 效率低
                - 线程安全
        HashMap
                - HashMap是无序的
                - 方法不是同步的
                - 线程不安全
                - 效率较高
                - key value允许null值
                - HashMap的父类是AbstractMap
        HashTable
                - HashTable是无序的
                - 方法是同步的
                - 线程安全
                - 效率较低（Hashtable的所有 public 方法声明中都有 synchronized关键字，HashMap的源码中则没有）
        - key value不允许null值
                - Hashtable的父类是Dictionary
        TreeMap
                - TreeMap是有序的
                - 不能重复
                - 当未实现 Comparator 接口时，value可以为null，key 不可以为null，否则抛 NullPointerException 异常；
        - 当实现 Comparator 接口时，若未对 null 情况进行判断，则可能抛 NullPointerException 异常。如果针对null情况实现了，可以存入，但是却不能正常使用get()访问，只能通过遍历去访问
                HashSet
        - 底层数据结构是哈希表
                - 唯一、无序
                - 两个方法：hashCode()和equals()
        LinkedHashSet
                - 底层数据结构是链表和哈希表
                - 由链表保证元素有序、哈希表保证元素唯一
                TreeSet
        - 底层数据结构是红黑树
                - 自然排序、比较器排序
                - 根据比较的返回值是否是0来决定是否唯一
                - 唯一、有序




                HashMap的put存储过程
        1、hash(key)，取key的hashcode进行高位运算，返回hash值
        2、如果hash数组为空，直接resize()
        3、对hash进行取模运算计算，得到key-value在数组中的存储位置i
（1）如果table[i] == null，直接插入Node<key,value>
（2）如果table[i] != null，判断是否为红黑树p instanceof TreeNode。
（3）如果是红黑树，则判断TreeNode是否已存在，如果存在则直接返回oldnode并更新；不存在则直接插入红黑树，++size，超出threshold容量就扩容
（4）如果是链表，则判断Node是否已存在，如果存在则直接返回oldnode并更新；不存在则直接插入链表尾部，判断链表长度，如果大于8则转为红黑树存储，++size，超出threshold容量就扩容

                List
        Arraylist ： Object[] 数组
        Vector ： Object[] 数组
        LinkedList ： 双向链表(JDK1.6 之前为循环链表，JDK1.7 取消了循环)

        Set
        HashSet （⽆序，唯⼀）: 基于 HashMap 实现的，底层采⽤ HashMap 来保存元素
        LinkedHashSet ： LinkedHashSet 是 HashSet 的⼦类，并且其内部是通过
        LinkedHashMap 来实现的。有点类似于我们之前说的 LinkedHashMap 其内部是基于
        HashMap 实现⼀样，不过还是有⼀点点区别的
        TreeSet （有序，唯⼀）： 红⿊树(⾃平衡的排序⼆叉树)

        Map
        HashMap ： JDK1.8 之前 HashMap 由数组+链表组成的，数组是 HashMap 的主体，链表则是主
        要为了解决哈希冲突⽽存在的（“拉链法”解决冲突）。JDK1.8 以后在解决哈希冲突时有了较⼤
        的变化，当链表⻓度⼤于阈值（默认为 8）（将链表转换成红⿊树前会判断，如果当前数组的⻓
        度⼩于 64，那么会选择先进⾏数组扩容，⽽不是转换为红⿊树）时，将链表转化为红⿊树，以
                减少搜索时间
        LinkedHashMap ： LinkedHashMap 继承⾃ HashMap ，所以它的底层仍然是基于拉链式散
        列结构即由数组和链表或红⿊树组成。另外， LinkedHashMap 在上⾯结构的基础上，增加了
⼀条双向链表，使得上⾯的结构可以保持键值对的插⼊顺序。同时通过对链表进⾏相应的操作，
        实现了访问顺序相关逻辑。详细可以查看：《LinkedHashMap 源码详细分析（JDK1.8）》
        Hashtable ： 数组+链表组成的，数组是 HashMap 的主体，链表则是主要为了解决哈希冲突
⽽存在的
        TreeMap ： 红⿊树（⾃平衡的排序⼆叉树）

        集合的选用：

        主要根据集合的特点来选⽤，⽐如我们需要根据键值获取到元素值时就选⽤ Map 接⼝下的集合，需
        要排序时选择 TreeMap ,不需要排序时就选择 HashMap ,需要保证线程安全就选⽤
        ConcurrentHashMap 。
        当我们只需要存放元素值时，就选择实现 Collection 接⼝的集合，需要保证元素唯⼀时选择实现
        Set 接⼝的集合⽐如 TreeSet 或 HashSet ，不需要就选择实现 List 接⼝的⽐如 ArrayList
        或 LinkedList ，然后再根据实现这些接⼝的集合的特点来选⽤。

        同步控制
        Collections 提供了多个 synchronizedXxx() ⽅法·，该⽅法可以将指定集合包装成线程同步的集
        合，从⽽解决多线程并发访问集合时的线程安全问题。
        我们知道 HashSet ， TreeSet ， ArrayList , LinkedList , HashMap , TreeMap 都是线程不安全
        的。 Collections 提供了多个静态⽅法可以把他们包装成线程同步的集合。
        最好不要⽤下⾯这些⽅法，效率⾮常低，需要线程安全的集合类型时请考虑使⽤ JUC 包下的并发集
        合。
⽅法如下：
        synchronizedCollection(Collection<T> c) //返回指定 collection ⽀持的同
        步（线程安全的）collection。
        synchronizedList(List<T> list)//返回指定列表⽀持的同步（线程安全的）
        List。
        synchronizedMap(Map<K,V> m) //返回由指定映射⽀持的同步（线程安全的）
        Map。
        synchronizedSet(Set<T> s) //返回指定 set ⽀持的同步（线程安全的）set。

        List、Map、Set的区别


        list有序，顺序是添加的顺序
        set无序指的是打乱了插入的顺序，不能重复。HashSet底层是HashMap是真正的无序；TreeSet有序，但这个顺序是根据排序规则排序的（二叉树排序）
        map是键值对

        HashMap、TreeMap和HashTable的区别

        HashMap和HashTable是无序的，TreeMap是有序的（二叉树排序）
        HashMap的方法不是同步的、线程不安全。Hashtable的方法是同步的、线程安全的；
        HashMap效率较高，Hashtable效率较低
        HashMap允许null值(key和value都允许)，HashTable不允许null值
        父类不同：HashMap的父类是AbstractMap，Hashtable的父类是Dictionary
                HashMap

        JDK1.8之前，HashMap采用数组+链表实现，即使用链表处理冲突，同一hash值的链表都存储在一个链表里。但是当位于一个数组中的元素较多，即hash值相等的元素较多时，通过key值依次查找的效率较低。
        JDK1.8及之后，HashMap采用数组+链表+红黑树实现，当链表长度超过阈值（8）时，将链表转换为红黑树，这样大大减少了查找时间
        HashMap的实现原理：
        首先有一个每个元素都是链表（可能表述不准确）的数组，当添加一个元素（key-value）时，就首先计算元素key的hash值，以此确定插入数组中的位置，但是可能存在同一hash值的元素已经被放在数组同一位置了，这时就添加到同一hash值的元素的后面，他们在数组的同一位置，但是形成了链表，同一各链表上的Hash值是相同的，所以说数组存放的是链表。而当链表长度太长时，链表就转换为红黑树，这样大大提高了查找的效率。
        当链表数组的容量超过初始容量的0.75时，再散列将链表数组扩大2倍，把原链表数组的搬移到新的数组中

        方法描述	抛出异常	返回特殊的值	一直阻塞	超时退出
        插入数据	add(e)	offer(e)	put(e)	offer(e,time,unit)
        获取并移除队列的头	remove()	poll()	take()	poll(time,unit)
        获取但不移除队列的头	element()	peek()	不可用	不可用*/


        //map
        Map hashMap=new HashMap();
        Map treeMap=new TreeMap();
        Map concurrentHashMap=new ConcurrentHashMap();
        Map properties=new Properties();
        Map hashTable=new Hashtable();


        hashMap.put(null,"dsdd");
        treeMap.put("a","dsdd");
        concurrentHashMap.put("a","dsdd");
        properties.put("a","dsdd");
        hashTable.put("a","dsdd");



        //list
        /**
         * ArrayList
         *                 - 基于动态数组的数据结构
         *                 - 随机访问快，增删慢
         *                 - 占用内存少，每个索引的位置是实际的数据
         *                 - 效率高，线程不安全
         */
        List arrayList= new ArrayList();

        /**
         * LinkedList
         *                 - 链表结构
         *                 - 增删快，访问慢（数据多的情况下增删也不一定快）
         *                 - 占用内存较多，每个节点中存储的是实际的数据和前后节点的位置
         *                 - 线程不安全
         */
        List LinkedList=new LinkedList();

        /**
         *  ArrayList和LinkedList的区别
         *
         *
         *        1、 LinkedList首部插入数据很快，因为只需要修改插入元素前后节点的prev值和next值即可
         *         ArrayList首部插入数据慢，因为数组复制的方式移位耗时多
         *
         *
         *        2、 LinkedList中间插入数据慢，因为遍历链表指针（二分查找）耗时多
         *         ArrayList中间插入数据快，因为定位插入元素位置快，移位操作的元素没那么多
         *
         *
         *       3、  LinkedList尾部插入数据慢，因为遍历链表指针（二分查找）耗时多
         *         ArrayList尾部插入数据快，因为定位速度快，插入后移位操作的数据量少
         */


    }

}
