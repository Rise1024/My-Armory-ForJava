### List视频文案
- [ArrayList源码](/Code-Video/src/main/java/com/video/javaOutline/conllection/list/ArrayList.java)
1、add 第 1 个元素的时候，
数组还为空，
数组容量会扩容到默认容量10

2、add 第 2 到 10 个元素的时候：
elementData长度经过第一次扩容已经变成了10
所以不会走扩容

3、add第11个元素的时候，
进入扩容
经过右移1运算等于15，
是原来数组容量的1.5倍
以此类推


### 相关图片
![img_1.png](image/img.png)


LinkedList
- 链表结构
- 增删快，访问慢
- 占用内存较多，每个节点中存储的是实际的数据和前后节点的位置
- 线程不安全,如果需要应用到多线程中，需要在外部做同步



ArrayList的特点
- 基于动态数组的数据结构
- 随机访问快，增删慢
- 相对于LinedList占用内存少，每个索引的位置是实际的数据
- 线程不安全，如果需要应用到多线程中，需要在外部做同步
- 
并不是所有的删除和插入操作LinkedList都比ArrayList快！

一千万个数据作为测试用例
LinkedList和ArrayList在一千万个元素中随机查询，可以看到ArrayList相对LinkedList快很多。

在头部删除，LinkedList要比ArrayList快。因为ArrayList在删除后有近一千万个元素要移动；而LinkedList只需要将头部的指针删除即可。
在中部删除， LinkedList反而要比ArrayList要慢，是因为LinkedList遍历五百万的元素比ArrayList移动五百万的元素还要慢。
在尾部删除，ArrayList不需要移动元素，LinkedList只需要删除尾部指针，因此两者耗时相近。

头部插入与头部删除一样，ArrayList同样需要移动近一千万的元素，而LinkedList只需要删除头部的指针即可。LinkedList比ArrayList快。
中部插入与中部删除类似，LinkedList的遍历速度没有ArrayList移动元素的速度快。ArrayList比LinkedList快。
尾部插入与尾部删除类似，LinkedList不需要遍历，ArrayList不需要移动元素，因此两者效率相近。

总结来看并不是所有的删除和插入操作LinkedList都比ArrayList快
在尾部插入和删除，LinkedList不需要遍历，ArrayList不需要移动元素，两者耗时相近
在中部插入和删除，LinkedList查找元素使用二分法查找需要遍历五百万的元素,比ArrayList移动五百万的元素还要慢。ArrayList比LinkedList快
在头部插入和删除，ArrayList同样需要移动近一千万的元素，LinkedList只需要删除头部的指针即可，LinkedList比ArrayList快。

















ArrayList的扩容机制



ArrayList LinkedList增删查时间复杂度分析


get(index)  
根据下标查询，顺序存储知道首个元素的地址，其他的位置很快就能确定，时间复杂度为O(1)
链式存储，从首个元素开始查找，直到查找到第 i个位置，时间复杂度为O(n)

add(E)  直接尾部添加，时间复杂度O(1)

add(index,E)  
顺序存储需要查找到元素然后执行插入或删除，时间复杂度为O(1)+O(n)=O(n);
链式存储同样需要先查找到元素然后在插入或删除，时间复杂度为O(n)+O(1)=O(n)

remove(E)  
顺序存储删除指定元素，后面元素要向前移动，时间复杂度O(n)
链式存储，直接 指针操作（找到前驱节点，再删除），时间复杂度O(1)





