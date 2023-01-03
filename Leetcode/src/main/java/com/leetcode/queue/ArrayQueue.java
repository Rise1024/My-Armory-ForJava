package com.leetcode.queue;

/**
 * 队列先进先出
 * 编写ArrayQueued的队列，编写一个ArrayQueue类
 */

class ArrayQueue {
    //声明属性
    private int maxSize, front, rear;//最大容量，队列首个元素，队列最后一个元素
    private int[] QueArr;//存放数据，模拟队列

    //调用构造器赋初始值
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize + 1;
        QueArr = new int[maxSize];
        front = 0;//指向队列头部，即前一个位置
        rear = 0;//指向队列尾部，即最后一个数据
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        //直接加入数据
        QueArr[rear] = n;
        rear = (rear + 1) % maxSize;

    }

    //取出数据
    public int getQueue() {
        if (isEmpty()) {
            //通过抛出异常处理
            throw new RuntimeException("队列空，不能取数据");
        }
        //这里需要分析出front是指向队列的第一个元素
        //1、先把front对应的值保存到一个临时变量
        //2、将front后移,考虑取模
        //3、将临时保存的变量返回
        int value = QueArr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //有效数据个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的数据
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        //从front开始遍历
        for (int i = front; i < front + size(); i++) {
            System.out.printf("QueArr[%d]=%d\n", i % maxSize, QueArr[i % maxSize]);
        }
    }

    //显示队首
    public int headQueue() {
        //判断
        if (isEmpty()) {
            System.out.println("没有数据");
            throw new RuntimeException("队列为空");
        }
        return QueArr[front];
    }
}