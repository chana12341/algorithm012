# 学习笔记

### 1.数组、链表与跳表的特性
a.数组（Array）是在内存中的一块连续空间，它查询快可以通过内存管理器进行访问，时间复杂度是O(1)，但在插入与删除数据时，通常会造成数据搬移由此时间复杂度是O(n)。头插入与尾插入的时间复杂度为O（1）。
 
b.链表(LinkedList)的节点有前驱或者后继的指针（引用）指向上一节点或者下一节点，链表通过头结点就能访问所有元素，插入与删除数据的时间复杂度为O(1)，而访问元素必须从
头节点开始，由此查询的时间复杂度为O(n)。头插入与尾插入的时间复杂度为O(1)。
 
c.跳表(SkipList)的前提条件是数据有序，它的出现是为了代替平衡树，具有原理简单、容易实现、方便拓展、效率高等优点，被使用于redis、levelDB等新的热门项目中。它在链表的基础上增加了多级索引，能够更快的查询到数据，查询的时间复杂度为O(log n)，插入与删除数据时由于需要更新索引，时间复杂度为O(n)。空间复杂度为O(n)

### 2.栈、队列、优先队列、双端队列
a.栈(stack)是一种先进后出的数据结构，一般可用于解决具有最近相关性的问题,其插入与删除的时间复杂度为O(1),取出数据的时间复杂度为O(n)，其java实现为Stack,但效率不高，推荐使用Deque。

b.队列(queue)是一种先进先出的数据结构，插入与删除的时间复杂度为O(1),取出数据的时间复杂度为O(n)。

c.优先队列中的数据具有优先级，按照优先级取出，插入与删除的时间复杂度为O(logn)，取出数据的时间复杂度为O(log n)，其在java中的实现是ProrityQueue，其储存的元素需要实现Comparable接口。

d.双端队列(Deque)是队列以及栈的结合，插入与删除的时间复杂度为O(1),查询的时间复杂度为O(n)

### 3.解题总结

a.移动零、删除有序数组重复元素以及有效山脉数组这三题都使用了双指针的方式解题，通过两个指针记录特殊的数据指针或者遍历数据的指针，再或是头尾指针，这类问题能通过多个指针的方法解决。

b.接雨水问题需要应用左右边界的思想，也可以通过栈解决。

c.两数之和的解法主要是通过hashmap以数组的值为键，数组的下标为值进行储存，然后遍历数组判断hashmap中是否有符合要求的元素。