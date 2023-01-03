package com.leetcode.stack;


/**
 * 使用数组实现栈
 * @param <T>
 */
public class ArrayStack<T>{
    private T[] arr;
    private int index;

    public ArrayStack(){
        arr = (T[])new Object[1];
        index = 0;
    }
    public void push(T v){
        if (index == arr.length){
            resize(arr.length * 2);
        }
        arr[index++] = v;
    }
    public T pop(){
        if(index == 0){
            return null;
        }
        T v = arr[--index];
        arr[index] = null;
        if (index > 0 && index<=arr.length/4){
            resize(arr.length/2);
        }
        return v;
    }
    private void resize(int size){
        if (size <= 1){
            return;
        }
        T[] newArr = (T[])new Object[size];
        for (int i=0;i<index;i++){
            newArr[i] = arr[i];
        }
        arr = newArr;
    }


    public static void main(String[] args) {
        ArrayStack stack=new ArrayStack();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.push("e");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());


    }
}
