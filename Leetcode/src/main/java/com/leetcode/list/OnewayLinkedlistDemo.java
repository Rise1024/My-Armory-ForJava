package com.leetcode.list;

import java.util.Objects;

/**
 * @Auther: zds
 * @Date: 2023/02/01/11:59
 * @Description:
 * 单向链表
 **/
public class OnewayLinkedlistDemo {
    /**
     * 头节点
     */
    private Node head = null;

    /**
     * 定义一个节点对象Node
     */
    public static class Node {

        //数据域
        public Integer data;

        //指针域，指向下一个节点
        public Node next;

        public Node() {
        }

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }


    /**
     * 增加节点,链尾插入
     * @param data
     * @return
     */
    public Node addNode(int data) {
        Node node = new Node(data);
        //如果头节点为null,插入的节点就是头节点
        if (head == null) {
            head = node;
            //这里next为null
            //node.next=null;
            return node;
        }
        //若不为null,头节点赋值给一个临时局部变量
        Node temp = head;
        //如果next节点不为null
        //就赋值给temp下一个node节点，也就是查找尾节点
        while (temp.next != null) {
            temp = temp.next;
        }
        //然后插入链尾
        temp.next = node;
        return node;
    }

    /**
     * 删除指定节点
     * @param node
     */
    public void deleteNodeByNode(Node node) {
        Node temp = head;
        // 若删除头元素
        if ((temp.next == null) && node.next == null) {
            head = null;
            return;
        }

        Node preNode = null;

        while (temp != null) {
            if (temp.next == head.next) {
                head = temp.next;
                temp = null;
                return;
            }
            if ((temp.next == node.next)) {
                preNode.next = temp.next;
                temp = null;
            }
            if (temp != null) {
                preNode = temp;
                temp = temp.next;
            }
        }
    }


    // 获取链表长度
    public int getNodeListLenght() {
        int length = 0;
        Node temp = head;
        while (temp != null) {
            ++length;
            temp = temp.next;
        }
        return length;
    }

    // 删除链表中第几个节点
    public void deleteNodeByIndex(int index) {
        int lenght = getNodeListLenght();
        if (index < 1 || index > lenght) {
            return;
        }

        Node temp = head;
        Node preNode = null;
        int flag = 1;
        while (temp != null) {
            if (Objects.equals(flag, index)) {
                // 若删除的头节点
                if (index == 1) {
                    head = temp.next;
                    temp = null;
                    return;
                }
                if (temp.next != null) {
                    preNode.next = temp.next;
                } else {
                    // 删除的尾节点
                    preNode.next = null;
                }
                temp = null;

            }
            if (temp != null) {
                preNode = temp;
                temp = temp.next;
            }
            flag++;
        }
    }

    // 打印所有节点
    public void printNodeList() {
        Node temp = head;
        while (temp != null) {
            System.out.println("节点数据:" + temp.data);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        OnewayLinkedlistDemo onewayLinkedlistDemo = new OnewayLinkedlistDemo();
        Node node = onewayLinkedlistDemo.addNode(1);
        onewayLinkedlistDemo.addNode(2);
        onewayLinkedlistDemo.addNode(3);
        onewayLinkedlistDemo.addNode(4);
        onewayLinkedlistDemo.addNode(5);
        onewayLinkedlistDemo.printNodeList();

        System.out.println("链表长度:" + onewayLinkedlistDemo.getNodeListLenght());
        onewayLinkedlistDemo.deleteNodeByIndex(4);
        System.out.println("链表长度:" + onewayLinkedlistDemo.getNodeListLenght());
        onewayLinkedlistDemo.printNodeList();
        onewayLinkedlistDemo.deleteNodeByNode(node);
        System.out.println("链表长度:" + onewayLinkedlistDemo.getNodeListLenght());
        onewayLinkedlistDemo.printNodeList();
    }


}
