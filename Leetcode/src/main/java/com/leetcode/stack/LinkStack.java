package com.leetcode.stack;

/**
 * 链表实现栈
 * 虽然用数组来制作堆栈算法比较简单，但是在制作变动的堆栈的时候数组容易造成大量的浪费。
 * 而链表的长度也是随时可以变动的，所以用链表来实现堆栈不会造成空间的浪费。
 * @param <T>
 */

public class LinkStack<T> {

    /**
     * 栈顶
     */
    private Node<T> mHead = null;

    /**
     * 栈内数量
     */
    private int size=0;

    private class Node<T> {
        /**
         * 下一个节点
         */
        public Node<T> next;
        /**
         * 本节点数据
         */
        public T mValue;
        public Node(T v){
            mValue = v;
            next = null;
        }
    }

    public void push(T v){
        Node<T> node = new Node<>(v);
        node.next = mHead;
        mHead = node;
        size++;
    }
    public T pop(){
        if (mHead == null){
            return null;
        }
        T v = mHead.mValue;
        mHead = mHead.next;
        size--;
        return v;
    }

    public static void main(String[] args) {
        LinkStack<String> linkStack=new LinkStack<>();
        linkStack.push("a");
        linkStack.push("b");
        linkStack.push("c");
        linkStack.push("d");
        linkStack.push("e");
        linkStack.push("f");
        System.out.println(linkStack.pop());
        System.out.println(linkStack.pop());
        System.out.println(linkStack.pop());
        System.out.println(linkStack.pop());
        System.out.println(linkStack.pop());
        System.out.println(linkStack.size);

    }
}
