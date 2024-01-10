<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [数据结构](#%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84)
    - [1. 数组](#1-%E6%95%B0%E7%BB%84)
    - [2. 链表](#2-%E9%93%BE%E8%A1%A8)
    - [3. 栈](#3-%E6%A0%88)
    - [4. 队列](#4-%E9%98%9F%E5%88%97)
    - [5. 树](#5-%E6%A0%91)
    - [6. 堆](#6-%E5%A0%86)
    - [7. 图](#7-%E5%9B%BE)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->
# 数据结构
当然可以！这里有一些简单的例子帮你理解不同的数据结构：

### 1. 数组
数组是一种线性数据结构，用于存储相同类型的元素。它们在内存中连续存储，可以通过索引快速访问元素。
```java
// 示例：整型数组
int[] arr = {1, 2, 3, 4, 5};
System.out.println(arr[0]); // 输出第一个元素：1
```
```python
# 例子：Python中的数组
arr = [1, 2, 3, 4, 5]
print(arr[0])  # 输出第一个元素：1
```

### 2. 链表
链表也是一种线性数据结构，但不同于数组，链表中的元素在内存中不必连续存储。每个元素（节点）都包含指向下一个节点的指针。
```java
// 示例：链表节点的简单实现
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// 创建链表：1 -> 2 -> 3
Node node1 = new Node(1);
Node node2 = new Node(2);
Node node3 = new Node(3);

node1.next = node2;
node2.next = node3;

System.out.println(node1.next.data); // 输出链表中第二个节点的数据：2
```
```python
# 例子：链表节点的简单实现
class Node:
    def __init__(self, data):
        self.data = data
        self.next = None

# 创建链表：1 -> 2 -> 3
node1 = Node(1)
node2 = Node(2)
node3 = Node(3)

node1.next = node2
node2.next = node3

print(node1.next.data)  # 输出链表中第二个节点的数据：2
```

### 3. 栈
栈是一种后进先出（LIFO）的数据结构，只能在一端进行操作。最后压入栈的元素最先被弹出。

```java
// 示例：使用Java的Stack类实现栈
import java.util.Stack;

Stack<Integer> stack = new Stack<>();
stack.push(1); // 压入元素1
stack.push(2); // 压入元素2
System.out.println(stack.pop()); // 弹出栈顶元素：2
```
```python
# 例子：使用Python的列表实现栈
stack = []
stack.append(1)  # 压入元素1
stack.append(2)  # 压入元素2
print(stack.pop())  # 弹出栈顶元素：2
```

### 4. 队列
队列是一种先进先出（FIFO）的数据结构，允许在一端添加元素，在另一端移除元素。
```java
// 示例：使用Java的Queue接口实现队列
import java.util.LinkedList;
import java.util.Queue;

Queue<Integer> queue = new LinkedList<>();
queue.offer(1); // 向队列中添加元素1
queue.offer(2); // 向队列中添加元素2
System.out.println(queue.poll()); // 移除队列中的第一个元素：1
```
```python
# 例子：使用Python的队列模块实现队列
from queue import Queue

queue = Queue()
queue.put(1)  # 向队列中添加元素1
queue.put(2)  # 向队列中添加元素2
print(queue.get())  # 移除队列中的第一个元素：1
```

### 5. 树
树是一种非线性数据结构，由节点和边组成，每个节点最多有一个父节点和多个子节点。
```java
// 示例：简单的树结构
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

// 创建树
TreeNode root = new TreeNode(1);
root.left = new TreeNode(2);
root.right = new TreeNode(3);

System.out.println(root.left.val); // 输出树中左子节点的值：2
```
```python
# 例子：简单的树结构
class TreeNode:
    def __init__(self, value):
        self.value = value
        self.children = []

# 创建树
root = TreeNode("A")
child1 = TreeNode("B")
child2 = TreeNode("C")
root.children = [child1, child2]

print(root.children[0].value)  # 输出树中第一个子节点的值：B
```

### 6. 堆
堆是一种特殊的树形数据结构，通常是一个完全二叉树，用于实现优先队列。在最小堆中，父节点的值小于等于其子节点的值。

```java
// 示例：使用Java的PriorityQueue实现最小堆
import java.util.PriorityQueue;

PriorityQueue<Integer> minHeap = new PriorityQueue<>();
minHeap.add(3); // 向堆中添加元素3
minHeap.add(1); // 向堆中添加元素1
System.out.println(minHeap.poll()); // 从堆中弹出最小元素：1
```
```python
# 例子：使用Python的heapq模块实现堆
import heapq

heap = []
heapq.heappush(heap, 3)  # 向堆中添加元素3
heapq.heappush(heap, 1)  # 向堆中添加元素1
print(heapq.heappop(heap))  # 从堆中弹出最小元素：1
```

### 7. 图
图是由节点（顶点）和连接这些节点的边组成的非线性数据结构。
```java
// 示例：简单的图结构
import java.util.*;

class Graph {
    Map<String, List<String>> graph;

    Graph() {
        graph = new HashMap<>();
    }

    void addEdge(String node, String neighbor) {
        graph.computeIfAbsent(node, k -> new ArrayList<>()).add(neighbor);
    }
}

// 创建图
Graph graph = new Graph();
graph.addEdge("A", "B");
graph.addEdge("A", "C");

System.out.println(graph.graph.get("A")); // 输出节点'A'的邻居节点：[B, C]
```
```python
# 例子：简单的图结构
class Graph:
    def __init__(self):
        self.graph = {}

    def add_edge(self, node, neighbor):
        if node not in self.graph:
            self.graph[node] = []
        self.graph[node].append(neighbor)

# 创建图
graph = Graph()
graph.add_edge('A', 'B')
graph.add_edge('A', 'C')
print(graph.graph['A'])  # 输出节点'A'的邻居节点：['B', 'C']
```
