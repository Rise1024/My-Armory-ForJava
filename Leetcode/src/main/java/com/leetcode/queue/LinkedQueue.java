package com.leetcode.queue;


/**
 * 队列的链表实现
 * 对于链表来说增删快
 *
 * 队列的特点有一个队头指针，指向队头节点，一个队尾指针，指向队尾节点
 */
public class LinkedQueue {
 
    Node front;//队头指针，指向队头节点
    Node rail;//队尾指针，指向队尾节点
 
    int size = 0;//记录队列长度
 
 
    //构造函数
    public LinkedQueue() {
        front = rail = null;
    }
 
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }
 
    //添加元素
    public boolean addQueue(Object ele) {
        if (size == 0) {
            front = new Node(null, ele);
            //只有一个元素尾节点依然指向首节点
            rail = front;
            size++;
            return true;
        }
        Node s = new Node(null, ele);
        //这块有个主意的地方，一旦rail设置了next属性，因为front节点与rail节点指向了同一个node节点，持有同一个结点的一个引用，因此front节点next属性也被填充
        rail.setNext(s);
        rail = s;
        size++;
        return true;
    }


    /**
     * 链表插入元素
     *
     * @param data  插入元素
     * @param index 插入位置
     */
    public void insert(int data, int index) throws Exception {
        if (index < 0 || index > size) { // index > size 说的是当前链表的长度是size,不可能在大于size的索引处插入元素,可以在等于size的位置插入元素
            throw new IndexOutOfBoundsException("index is illegal");
        }
        Node insertedNode = new Node(null,data); // 将插入的字段，封装成Node类
        if (size == 0) { // (1) 链表为空
            front = insertedNode;
            rail = insertedNode;
        } else if (index == 0) { // (2) 插入头部
            insertedNode.next = front; // 把新节点的next指针，指向原先的头节点
            front = insertedNode; // 新节点变为链表的头节点
        } else if (index == size) { // (3) 插入尾部
            rail.next = insertedNode; // 把最后一个节点的next指针，指向新插入的节点
            rail = insertedNode; // 把新插入节点指向尾节点指针
        } else {  // 插入中间
            Node prevNode = get(index - 1);// 查找指定位置元素
            insertedNode.next = prevNode.next;
            prevNode.next = insertedNode;   // 此处需要自己画图进行理解，单向链表
        }
        size++;
    }

    /**
     * 链表删除元素
     *
     * @param index 删除的位置
     * @return 返回删除的节点 Node
     */
    public Node remove(int index) {
        reviewIndex(index);
        Node removeNode = null;
        if (index == 0) { // (1) 删除头节点
            removeNode = front;
            front = front.next;
        } else if (index == size - 1) { // (2) 删除尾部节点
            Node prevNode = get(index - 1);
            removeNode = prevNode.next;
            prevNode.next = null;
            rail = prevNode;
        } else { // 删除中间元素
            Node prevNode = get(index - 1);
            removeNode = prevNode.next;
            prevNode.next = prevNode.next.next;
        }
        size--;
        return removeNode;
    }


    /**
     * @param index 指定位置索引
     */
    public Node get(int index) {
        reviewIndex(index);
        Node tmp = front;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp;
    }

    //  输入下标的异常检测
    private void reviewIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index is illegal");
        }
    }
 
    /**
     * 删除元素,出队列
     * @return
     */
    public boolean deleteQueue() {
        if (isEmpty()) {
            System.out.println("当前队列为空");
            return false;
        }
 
        front = front.next;
        size--;
        return true;
 
    }
 
 
    public static void main(String[] args) {
        LinkedQueue queue = new LinkedQueue();
 
        queue.addQueue(1);
        queue.addQueue(2);
        queue.addQueue(3);
        queue.deleteQueue();
 
    }
 
 
}
 
/**
 * 首先链表底层是一个个结点
 */
class Node {

    //指向下一个节点
    Node next;

    //节点元素
    Object element;
 
    public Node(Node next, Object element) {
        this.next = next;
        this.element = element;
    }
 
    public Node getNext() {
        return next;
    }
 
    public void setNext(Node next) {
        this.next = next;
    }
 
    public Object getElement() {
        return element;
    }
 
    public void setElement(Object element) {
        this.element = element;
    }
 
}