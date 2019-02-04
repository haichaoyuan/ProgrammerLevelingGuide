## java 基础
摘录自 JCSprout

### 集合（Collection）
1. [ArrayListCore](./javabasis/src/main/java/com/hc/javabasis/collection/ArrayListCore.java)
最重要的两个属性分别是：elementData 数组和 size 大小, add 方法
2. [VectorCore](./javabasis/src/main/java/com/hc/javabasis/collection/VectorCore.java)
3. [LinkedListCore](./javabasis/src/main/java/com/hc/javabasis/collection/LinkedListCore.java)
4. [HashMapCore](./javabasis/src/main/java/com/hc/javabasis/collection/HashMapCore.java)
5. [HashSetCore](./javabasis/src/main/java/com/hc/javabasis/collection/HashSetCore.java)
    1. HashSet 是一个不允许存储重复元素的集合。实现比较简单，内部通过HashMap 来存储
    2. 利用put 数据到 HashMap，存在相同key 会返回旧的数据的特性来返回是否存在数据
6. [LinkedHashMapCore](./javabasis/src/main/java/com/hc/javabasis/collection/LinkedHashMapCore.java)
    1. LinkHashMap 增加双向链表 head、 tail，可根据写入顺序或访问顺序进行排序
    
    
    
    
### Java 多线程
#### 多线程操作避免线程切换
1. 采用无锁编程，可以将数据按照 Hash(id) 进行取模分段，每个线程处理各自分段的数据，从而避免使用锁。(CurrentHashMap 的分段锁)
2. 采用CAS(compare and swap)算法，如 Atomic
3. 合理创建线程，使用线程池。避免大部分线程处于 waiting 状态。

#### synchronized 关键字原理
1. JVM 是通过进入、退出对象监视器(Monitor)来实现对方法，同步块的同步。
2. 编译期间在调用时加入一个 monitor.enter 指令，在退出方法和异常处插入 monitor.exit 指令。
3. javac 编译Java 文件；java 执行；javap -c 反编译，查看字节码

#### 锁的全家福
1. 轻量锁。进入同步代码块时，先尝试记小本本(栈帧创建锁记录-Lock Record)，使用 CAS 方式来将对象头的 Mark Word 更新到小本本的数据，若不行会膨胀为重量锁。
2. 轻量锁初衷。大多数锁在整个同步周期都不会存在竞争，所以使用 CAS 比使用互斥开销更小
3. 一句话总结轻量锁：公告上官宣你有主了。
4. 偏向锁。进入同步块时，使用CAS 将线程ID 更新到锁对象的 Mark Word, 标志这是我的对象。
5. 偏向锁初衷。锁会多次被同一个线程获得锁。
6. 一句话总结偏向锁：官宣在盖上我的戳。
7. 适应性自旋。在使用 CAS 时，如果操作失败，CAS 会自旋在此尝试。
8. 适应性自旋初衷：下一秒肯定可以。
9. 重入锁。锁一般都具备可重入性，否则自身死锁了。
9. 读写锁。关键字 ReentrantReadWriteLock, 同时维护读锁和写锁，当写线程访问时，其他线程将阻塞，当读线程访问时则不会。读写分离，极大提高并发量。

#### Java 多线程三大特性
1. 三大特性分别是原子性、可见性和顺序性
2. i++ 不是原子性操作，具体可分为：
- 获取i的值
- 自增
- 再赋值给i
3. 保证 i++ 的原子性需要用到 synchronized 或 lock 来进行加锁，或者使用 AtomicInteger 这类原子类(本质是使用CPU的CAS指令)。
4. 可见性。CPU 从主内存读取数据效率不高 -> 产生CPU高速缓存 -> 每个线程保存一份
5. volatile 关键字，保证内存可见性，立即刷新主内存中数据。
6. synchronized 和加锁也能保证可见性，原理是释放锁之前，其他线程访问不到，开销更大。
7. 顺序性，避免编译器指令重排。volatile 可以保证顺序性。

#### ReentrantLock 实现原理