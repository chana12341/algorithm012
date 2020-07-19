学习笔记
* ## HashMap总结

* #### put（）方法

```
    //调用putVal方法，传入的参数分别为:
    //1.key的hash值,2.key值，3.value值，4.是否需要改变已存在的值（true：不改变，false：改变），5.是否为创建模式（false：创建模式）
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }
```

* ####  putVal()方法

```
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        //维护的Node键值对数组
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        //当hashmap中维护的table数组为空时
        if ((tab = table) == null || (n = tab.length) == 0)
            //新加入元素调用resize（）方法扩容
            n = (tab = resize()).length;
        //插入的位置tab[i]为空时，直接在tab[i]创建所添加的元素
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            //当key存在时直接覆盖value
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            //当节点属于树节点时，将它添加到红黑树相应位置
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            //为链表节点时
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        //将新元素尾插置链表
                        p.next = newNode(hash, key, value, null);
                        //链表节点太长时，树化
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            //当key存在时，覆盖value
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
```
* ####  resize()方法
```
final Node<K,V>[] resize() {
        //oldTab表示旧数组
        Node<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        //扩容阀值
        int oldThr = threshold;
        //新容量，新阀值
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                     oldCap >= DEFAULT_INITIAL_CAPACITY)
                // 翻倍阀值
                newThr = oldThr << 1; 
        }
        //当数组为空时
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        else {               // zero initial threshold signifies using defaults
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                      (int)ft : Integer.MAX_VALUE);
        }
        //设置新阀值
        threshold = newThr;
            //定义新数组 
            Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        table = newTab;
        //旧数组不为空时
        if (oldTab != null) {
            for (int j = 0; j < oldCap; ++j) {
                Node<K,V> e;
                if ((e = oldTab[j]) != null) {
                    //旧元素置为null
                    oldTab[j] = null;
                    if (e.next == null)
                        //当node没有后续节点时，直接将它置于新数组的新hash位置
                        newTab[e.hash & (newCap - 1)] = e;
                    else if (e instanceof TreeNode)
                        //当node为树结构时，将节点插入到新红黑树的树节点中
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    else { // preserve order
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }
```
* #### get()方法
```
public V get(Object key) {
        Node<K,V> e;
        //调用getNode（）方法，返回对应节点的value值
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }
```
* #### getNode()方法
```
final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            //当链表第一个元素hash相等，key相等时直接返回tab[(n-1)&hash]元素;
            if (first.hash == hash && // always check first node
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null) {
                //当为树节点
                if (first instanceof TreeNode)
                    //返回在红黑树中的该节点元素
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                do {
                    //遍历链表的后继节点，返回所查找的元素
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }
```