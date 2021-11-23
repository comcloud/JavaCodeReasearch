package com.cloud.MainTest;

import com.cloud.MainTest.bean.Person;
import com.cloud.MainTest.bean.ReferenceTest;
import com.cloud.MainTest.bean.ResizableCapacityLinkedBlockingQueue;
import com.cloud.MainTest.bean.Singleton;
import com.cloud.MainTest.jvm.LockEliminationDemo;
import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;

import java.io.*;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;

/**
 * @author 成都犀牛
 * @version 1.0
 * @datetime 2021/2/21 11:47 上午
 */
public class TestAllMethod {

    static final int HASH_BITS = 0x7fffffff; // usable bits of normal node hash
    private final static Random random = new Random();
    private static final int THREAD_COUNT = 550;
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()-> System.out.println("触碰到栅栏，放行"));
    static String staticString = new String("stringTest");
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;
    private static int number = 0;
    private final List<String> list = new ArrayList<>();
    private final StampedLock sl = new StampedLock();
    private List<Person> personList = new ArrayList<Person>() {
        {
            add(Person.builder().age(10).name("zhang").build());
            add(Person.builder().age(11).name("build").build());
            add(Person.builder().age(12).name("builder").build());
        }
    };
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 1L, TimeUnit.SECONDS,
            new ResizableCapacityLinkedBlockingQueue<>(100));
    private AtomicInteger count = new AtomicInteger(0);
    private int fibArrSize = 20;
    //保证三个线程依次执行
    private volatile int ticket = 1;

    static final int spread(int h) {
        return (h ^ (h >>> 16)) & HASH_BITS;
    }

    public static void sort(int[] arr) {
        int[] temp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        sort(arr, 0, arr.length - 1, temp);
    }

    private static void sort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(arr, left, mid, temp);//左边归并排序，使得左子序列有序
            sort(arr, mid + 1, right, temp);//右边归并排序，使得右子序列有序
            merge(arr, left, mid, right, temp);//将两个有序子数组合并操作
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//左序列指针
        int j = mid + 1;//右序列指针
        int t = 0;//临时数组指针
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        while (i <= mid) {//将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        while (j <= right) {//将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }

    private static void shellInsert(int[] arr, int d) {
        for (int i = d; i < arr.length; i++) {
            int j = i - d;
            int temp = arr[i];    //记录要插入的数据
            while (j >= 0 && arr[j] > temp) {  //从后向前，找到比其小的数的位置
                arr[j + d] = arr[j];    //向后挪动
                j -= d;
            }

            if (j != i - d)    //存在比其小的数
                arr[j + d] = temp;
        }
    }

    private static void shellSort(int[] arr) {
        if (arr == null || arr.length == 0)
            return;
        int d = arr.length / 2;
        while (d >= 1) {
            shellInsert(arr, d);
            d /= 2;
        }
    }

    private static int[] mergeSort(int[] array) {
        if (array == null || array.length < 2)
            return array;
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    /**
     * 归并排序——将两段排序好的数组结合成一个排序数组
     */
    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length)
                result[index] = right[j++];
            else if (j >= right.length)
                result[index] = left[i++];
            else if (left[i] > right[j])
                result[index] = right[j++];
            else
                result[index] = left[i++];
        }
        return result;
    }

    @Test
    public void exploreConcurrentHashMap() {
        ThreadLocal<String> local1 = new ThreadLocal<>();
        local1.set("1");
        ThreadLocal<String> local2 = new ThreadLocal<>();
        local2.set("2");
        String s = local1.get();

        Optional<Integer> max = personList.stream().map(Person::getAge).max(Comparator.comparingInt(ageOne -> ageOne));
        Optional<Person> maxAge = personList.stream().max(Comparator.comparingInt(Person::getAge));

        StringJoiner joiner = new StringJoiner(",");
        for (int i = 0; i < 100; i++) {
            joiner.add(i + "");
        }
        System.out.println(joiner.toString());
        int[] ints = new int[10];
        Person[] persons = new Person[5];
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
        AtomicInteger count = new AtomicInteger(10);
        Person personBuilder = Person.builder().age(100).name("newBuilder").build();
        personList.forEach(person -> {
            persons[0] = person;
            ints[0] = 10;
            count.set(count.getAndIncrement());
            count.decrementAndGet();
            personBuilder.setAge(-100);
        });
        System.out.println(Arrays.toString(persons));
        System.out.println("count = " + count);
        System.out.println(personBuilder);
        System.out.println(Arrays.toString(ints));

    }

    @Test
    public void thisTest() {
        personList.forEach(person -> {
            System.out.println(this);

        });
        Comparator<Object> comparator = (o1, o2) -> {
            System.out.println(this);
            return 0;
        };
        comparator.compare(personList, personList);
        System.out.println(comparator);

    }

    @Test
    public void setTest() {
        HashSet<Integer> set = new HashSet<>();

        set.add(1000);
        set.add(1000);
        set.add(1000);
        System.out.println(Arrays.toString(set.toArray()));

        HashMap<Integer, Integer> map = new HashMap<>(4);
        map.put(null, 10);
        System.out.println(map.get(null));
        map.put(ThreadLocalRandom.current().nextInt(), 10);
        map.put(ThreadLocalRandom.current().nextInt(), 10);
        map.put(ThreadLocalRandom.current().nextInt(), 10);
        map.put(ThreadLocalRandom.current().nextInt(), 10);
        System.out.println(map);
    }

    private void serialize(Person person) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                new File("/Users/rayss/IdeaProjects/JavaCodeReasearch/src/main/resources/result.txt")));
        oos.writeObject(person);
        oos.close();
    }

    private Person deserialize() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                new File("/Users/rayss/IdeaProjects/JavaCodeReasearch/src/main/resources/result.txt")));
        return (Person) ois.readObject();
    }

    @Test
    public void serTest() throws Exception {

        Map<Integer, String> map = Collections.synchronizedMap(new HashMap<>());
        map.put(20, "测试");
        ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(ThreadLocalRandom.current().nextInt(), "测试");
        concurrentHashMap.get(20);
        Person person = Person.builder().age(10).name("zhangsan").build();
        System.out.println("序列化前的结果: " + person);

        serialize(person);

        Person dPerson = deserialize();
        System.out.println("反序列化后的结果: " + dPerson);
    }

    @Test
    public void reorder() throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            CountDownLatch latch = new CountDownLatch(1);

            Thread one = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                }
                a = 1;
                x = b;
            });

            Thread other = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                }
                b = 1;
                y = a;
            });
            one.start();
            other.start();
            latch.countDown();
            one.join();
            other.join();

            String result = "第" + i + "次 (" + x + "," + y + "）";
            if (x == 0 && y == 0) {
                System.err.println(result);
                break;
            } else {
                System.out.println(result);
            }
        }
    }

    @Test
    public void referenceTest() {
        ReferenceTest test1 = new ReferenceTest();
        test1.setA(1);
        test1.setB(2);

        ReferenceTest test2 = test1;
        test2.setB(-1);
        System.out.println();
    }

    @Test
    public void singletonTest() throws InterruptedException {
        while (true) {
            for (int i = 0; i < 110; i++) {
                executor.execute(() -> {
                    Singleton instance = Singleton.getInstance();
                    if (instance != null) {
                        System.out.println("结束");
                        System.exit(0);
                    }
                });
            }
            Thread.sleep(4000);
        }
    }

    @Test
    public void softReferenceTest() throws IOException, InterruptedException {
        SoftReference<byte[]> reference = new SoftReference<>(new byte[1024 * 1024 * 10]);
        System.out.println(reference.get());
        System.gc();
        Thread.sleep(500);
        System.out.println(reference.get());

        //此时再创建一个数组，但是这个时候内存已经分配不足，此时系统便会自己进行垃圾回收会把软引用回收
        byte[] b = new byte[1024 * 1024 * 10];
        System.out.println(reference.get());
    }

    @Test
    public void phantomReferenceTest() {
        ReferenceQueue<Person> queue = new ReferenceQueue<>();
        PhantomReference<Person> reference = new PhantomReference<>(new Person(), queue);

    }

    @Test
    public void testCASByNothing() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        Thread[] threads = new Thread[10];
        //这里我们去开启10个线程，然后每个线程分别给number变量累加10000，最后的结果应该是10 0000
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    number++;
                }
                latch.countDown();
            });
        }
        Arrays.stream(threads).forEach(Thread::start);

        latch.await();
        System.out.println("无处理:    " + number);

    }

    @Test
    public void testCASBySynchronized() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        Thread[] threads = new Thread[10];
        Object o = new Object();
        //这里我们去开启10个线程，然后每个线程分别给number变量累加10000，最后的结果应该是10 0000
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                synchronized (o) {
                    for (int j = 0; j < 10000; j++) {
                        number++;
                    }

                    latch.countDown();
                }
            });
        }
        Arrays.stream(threads).forEach(Thread::start);

        latch.await();
        System.out.println("添加synchronized锁:    " + number);

    }

    @Test
    public void testCASByAtomic() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        Thread[] threads = new Thread[10];
        //这里我们去开启10个线程，然后每个线程分别给number变量累加10000，最后的结果应该是10 0000
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    count.incrementAndGet();
                }

                latch.countDown();
            });
        }
        Arrays.stream(threads).forEach(Thread::start);

        latch.await();
        System.out.println("使用原子类:    " + count);

    }

    @Test
    public void jolTest() throws InterruptedException {
        //java object layout
        //        System.out.println(ClassLayout.parseInstance(new Object[10]).toPrintable());
        //        System.out.println(ClassLayout.parseInstance(new int[0]).toPrintable());
        //        System.out.println(ClassLayout.parseInstance(new int[2]).toPrintable());
        //
        //        System.out.println(ClassLayout.parseInstance(new Object()).toPrintable());
        //        Object o = new Person();
        //        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        //        synchronized (o){
        //            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        //        }
        //        TimeUnit.SECONDS.sleep(5);
        Object o = new Object();
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        System.out.println("--------------------------------------------------");
        TimeUnit.SECONDS.sleep(5);
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }

    @Test
    public void futureTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> f = new CompletableFuture<>();

        new Thread(() -> {
            try {
                System.out.println("thread1:" + f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println("thread2:" + f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(3);
        f.complete("hello");
    }

    @Test
    public void hashTest() {
        for (int i = 0; i < 16; i++) {
            System.out.println((16 - 1) & spread(new Object().hashCode()));
        }
    }

    @Test
    public void getAndSetTest() {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        list.add(10);
        list.add(11);
        list.add(12);

        list.forEach(count -> {
            list.add(13);
            System.out.println(count);
        });
    }

    @Test
    public void countDownLatchTest() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        CountDownLatch latch = new CountDownLatch(list.size());
        for (Integer id : list) {
            Thread thread = new Thread(new SearchTask(id, latch));
            thread.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("龙珠已经找完，此时开始召唤神龙");
    }

    @Test
    public void cyclicBarrierTest() throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadNum = i + 1;
            TimeUnit.SECONDS.sleep(1);

            threadPool.execute(() -> threadNumTest(threadNum));
        }
        cyclicBarrier.reset();
        threadPool.shutdown();
    }

    public void threadNumTest(int threadNum) {
        System.out.println("threadNum:" + threadNum + " is ready");

        try {
            cyclicBarrier.await(60, TimeUnit.SECONDS);
        } catch (InterruptedException | TimeoutException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("threadNum:" + threadNum + " is finished");
    }

    @Test
    public void stringTest() throws NoSuchFieldException, IllegalAccessException {

        String str = "zhang";
        String str1 = str;
        System.out.println(str.replace("z", "T"));
        System.out.println("str = " + str);

        Field field = String.class.getDeclaredField("value");
        field.setAccessible(true);

        char[] chars = (char[]) field.get(str);
        chars[0] = 'Q';
        System.out.println(str);
    }

    @Test
    public void reverseTest() {
        //比较器
        Comparator comparator = Collator.getInstance(Locale.CHINA);

        HashMap<Person, String> map = new HashMap<>();
        map.put(new Person(20, "123", true, null), "hello");
        System.out.println(map.get(new Person(20, "123", true, null)));
        Person person1 = new Person(20, "123", true, null);
        Person person2 = new Person(20, "123", true, null);

        System.out.println(person1.equals(person2));
        //        System.out.println(reverse(1234344565));
    }

    public int reverse(int x) {
        int sum = 0;
        while (x != 0) {
            if ((sum * 10) / 10 != sum) {
                return 0;
            }
            sum = sum * 10 + x % 10;
            x /= 10;
        }
        return sum;
    }

    @Test
    public void hashCodeTest() throws InterruptedException {
        Map<String, Integer> map = new HashMap<>();
        map.put(null, null);
        map.put(null, null);
        map.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });

        //        System.out.println(Double.hashCode(1.0));
        //        System.out.println(Double.hashCode(1.0));
        //        System.out.println(Integer.hashCode(1));

        //        for (int i = 0; i < 100; i++) {
        //            System.out.println("31 * i =       " + 31 * i);
        //            System.out.println("(i << 5) - i = " + ((i << 5) - i));
        //        }
        //        CustomRunnable runnable = new CustomRunnable("thread 1");
        //        Thread thread = new Thread();
        //        thread.start();
        //        synchronized (this) {
        //            this.wait(1000);
        //
        //        }
        //        this.notify();

    }

    @Test
    public void threadLifeTest() throws ExecutionException, InterruptedException {
        System.out.println("线程启动");
        CompletableFuture.supplyAsync(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println(i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "自线程执行结束";
        });

    }

    @Test
    public void stringInConstantPoolOrHeapTest() {
        String tempString = "stringTest";
        System.out.println(staticString == tempString);

    }

    @Test
    public void timeTest() {
        // 10位的秒级别的时间戳
        long time1 = 1619685750911L;
        String result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time1));
        System.out.println(result1);

        System.out.println(new Date());
        System.out.println(new Date().getTime());

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        System.out.println(c.getTimeInMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String time = format.format(c.getTime());
        System.out.println(time);
    }

    @Test
    public void sleepTest() {
        System.out.println("start...");
        Field[] declaredFields = Person.class.getDeclaredFields();
        Field[] fields = Person.class.getFields();
        System.out.println("declaredFields = " + Arrays.toString(declaredFields));
        System.out.println("fields = " + Arrays.toString(fields));

        System.out.println(new Date().getTime());
        System.out.println("end...");
    }

    @Test
    public void literalTest() {
        String stringObject = new String("literal");
        String literal = "literal";
        String intern = stringObject.intern();
        new LockEliminationDemo();
        ReentrantLock lock = new ReentrantLock(true);
        lock.lock();
        lock.unlock();

        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        readLock.lock();
        readLock.unlock();
        writeLock.lock();
        writeLock.unlock();

        System.out.println("stringObject == literal = " + (stringObject == literal));
        System.out.println("(literal == intern) = " + (literal == intern));
        System.out.println("(stringObject == intern) = " + (stringObject == intern));
    }

    @Test
    public void searchTest() {
        int[] arr = { 1, 4, 6, 7, 8, 9, 10, 34, 23 };
        int searchElement = 6;
        System.out.println(binarySearchByIteration(arr, searchElement));
        System.out.println(search(arr, searchElement));
    }

    private int search(int[] arr, int ele) {
        return recursion(arr, 0, arr.length - 1, ele);
    }

    private int recursion(int[] arr, int left, int right, int ele) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (midVal > ele) {
            return recursion(arr, left, mid - 1, ele);
        } else if (midVal < ele) {
            return recursion(arr, left + 1, right, ele);
        } else {
            return mid;
        }
    }

    private int binarySearchByIteration(int[] arr, int ele) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            int midVal = arr[mid];
            if (midVal > ele) {
                right = mid - 1;
            } else if (midVal < ele) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 获取所有查找元素的索引，因为可能存在查找元素存在多个
     */
    private List<Integer> getAllIndex(int[] arr, int left, int right, int ele) {
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (midVal > ele) {
            return getAllIndex(arr, left, mid - 1, ele);
        } else if (midVal < ele) {
            return getAllIndex(arr, left + 1, right, ele);
        } else {
            List<Integer> list = new ArrayList<>();
            //因为数组是有序的，所以如果是相同的元素一定是挨着的，所以这里向左与向右判断即可
            for (int temp = left - 1; temp >= 0 && arr[temp] == ele; temp--) {
                list.add(temp);
            }
            for (int temp = right + 1; temp <= arr.length && arr[temp] == ele; temp++) {
                list.add(temp);
            }
            return list;
        }
    }

    @Test
    public void fibSearchTest() {
        int[] arr = { 1, 4, 6, 7, 8, 9, 10, 34, 89 };
        System.out.println(fibSearch(arr, 6));
    }

    private int fibSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0;
        int mid = 0;
        int[] f = getFib();
        //获取到斐波那契分割数组的下标，循环的意义在于f[k]-1就是索引，这个一定是要大于high的，最起码要把顺序表包裹进去
        while (high > f[k] - 1) {
            k++;
        }
        //此时可能f[k]对应的值已经超过了原来数组中的大小，所以我们需要复制一个数组出来，然后对这个数组进行多余的数字进行赋值为arr[high]
        int[] temp = Arrays.copyOf(arr, f[k]);
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                high = mid - 1;
                //之所以k--，f[k-1] = f[k - 1 - 1] + f[k - 1 -2]
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                return Math.min(mid, high);
            }
        }
        new Integer(1);
        return -1;

    }

    public final int[] getFib() {
        int[] fibArr = new int[fibArrSize];
        fibArr[0] = 1;
        fibArr[1] = 1;
        for (int i = 2; i < fibArr.length; i++) {
            fibArr[i] = fibArr[i - 1] + fibArr[i - 2];
        }
        return fibArr;
    }

    @Test
    public void nodeTest() {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);

        root.left = node1;
        node1.left = node2;
        node1.right = node3;
        root.right = node4;
        System.out.println(binaryTreePaths(root));
    }

    public int binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root != null) {
            resolve(list, "", root);
        }
        return list.stream().mapToInt(Integer::parseInt).sum();
    }

    private void resolve(List<String> list, String str, TreeNode node) {
        if (node == null) {
            return;
        }
        str += node.val;
        //此时说明是叶子节点，将不再往下遍历
        if (node.left == null && node.right == null) {
            list.add(str);
        } else {
            resolve(list, str, node.left);
            resolve(list, str, node.right);
        }
    }

    @Test
    public void numberTest() {
        HashMap<String, String> map = new HashMap<>(4);
        map.put("1", "2");
        map.put("2", "2");
        map.put("3", "2");
        map.put("4", "2");
        map.put("5", "2");
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    @Test
    public void charSubstring() {
        String str = "abcde hihi     a";
        String substring = str.substring(0, 10);
        if (substring.charAt(9) != ' ' && str.charAt(10) == ' ') {
            System.out.println(substring);
        } else if (substring.charAt(9) != ' ' && str.charAt(10) != ' ') {
            System.out.println(substring.substring(0, substring.lastIndexOf(' ')));
        } else {
            System.out.println(substring.trim());
        }
    }

    @Test
    public void violenceMatchTest() {
        String haystack = "mississippi";
        String needle = "issip";
        System.out.println(strStr(haystack, needle));
    }

    private int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }
        for (int i = 0, j = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            } else {
                i = i - j;
                j = 0;
            }
            if (j == needle.length()) {
                //匹配完成，之所以+1是因为没有进行i++
                return i - j + 1;
            }
        }
        return -1;
    }

    //保证三个线程同时执行
    @Test
    public void threadCountDownTest() throws InterruptedException {
        //表示要开启3个线程
        int size = 3;
        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis());
            }).start();
        }
        latch.getCount();
        TimeUnit.SECONDS.sleep(1);
        latch.countDown();
    }

    @Test
    public void threadOrderTest() {
        new Thread(() -> {
            if (ticket == 1) {
                for (int i = 0; i < 5; i++) {
                    System.out.print("a" + i + "\t");
                }
                System.out.println();
                ticket = 2;
            }
        }).start();
        new Thread(() -> {
            if (ticket == 2) {
                for (int i = 0; i < 5; i++) {
                    System.out.print("b" + i + "\t");
                }
                System.out.println();
                ticket = 3;
            }
        }).start();
        new Thread(() -> {
            if (ticket == 3) {
                for (int i = 0; i < 5; i++) {
                    System.out.print("c" + i + "\t");
                }
                System.out.println();
            }
        }).start();
    }

    //交替执行
    @Test
    public void threadAlternateTest() throws InterruptedException {
        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(1);
        Semaphore s3 = new Semaphore(1);

        s1.acquire();
        s2.acquire();

        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    s1.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A");
                s2.release();
            }
        });
        t1.start();
        new Thread(() -> {
            while (true) {
                try {
                    s2.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B");
                s3.release();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    s3.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("C");
                s1.release();
            }
        }).start();

        t1.join();
    }

    @Test
    public void readWriteLockTest() {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(true);
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        writeLock.lock();
        writeLock.unlock();
        readLock.lock();
        readLock.unlock();
    }

    @Test
    public void atomicTest() {
        AtomicReference<String> reference = new AtomicReference<>();
        AtomicInteger integer = new AtomicInteger();

        System.out.println("integer.incrementAndGet() = " + integer.incrementAndGet());
        System.out.println("integer.decrementAndGet() = " + integer.decrementAndGet());
        System.out.println("integer.get() = " + integer.get());
        integer.set(10);
        System.out.println("integer.get() = " + integer.get());
        System.out.println(integer.compareAndSet(10, 20));
        integer.addAndGet(3);
        integer.getAndDecrement();

        reference.updateAndGet(s -> {
            if ("test".equals(s)) {
                return "test";
            } else {
                return "other";
            }
        });
        System.out.println("reference.get() = " + reference.get());

        AtomicReferenceFieldUpdater<Person, String> updater = AtomicReferenceFieldUpdater
                .newUpdater(Person.class, String.class, "name");
        Person doug = new Person(10, "doug", true, null);
        updater.set(doug, "lea");
        System.out.println(doug);
    }

    @Test
    public void executorTest() {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10, 10L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
        poolExecutor.execute(() -> {

        });
    }

    private void bubbleSortByProp(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {//一共要排序size-1次
            //每次遍历标志位都要先置为0，才能判断后面的元素是否发生了交换
            int flag = 0;
            for (int j = 0; j < arr.length - 1 - i; j++) {//选出该趟排序的最大值往后移动
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = 1;//只要有发生了交换，flag就置为1
                }
            }
            //判断标志位是否为0，如果为0，说明后面的元素已经有序，就直接return
            if (flag == 0) {
                return;
            }
        }
    }

    private void bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0)
            return;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    private void bubbleSortByError(int[] arr) {
        int i = 0, j = 0;
        int k = arr.length - 1, pos = 0;//pos变量用来标记循环里最后一次交换的位置

        for (i = 0; i < arr.length - 1; i++) {//一共要排序size-1次
            //每次遍历标志位都要先置为0，才能判断后面的元素是否发生了交换
            int flag = 0;
            for (j = 0; j < k; j++) {//选出该趟排序的最大值往后移动
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = 1;//只要有发生了交换，flag就置为1
                    pos = j;//循环里最后一次交换的位置 j赋给pos
                }
            }
            k = pos;
            //判断标志位是否为0，如果为0，说明后面的元素已经有序，就直接return
            if (flag == 0) {
                return;
            }
        }
    }

    @Test
    public void bubbleTimeTest() {
        int[] arr = new int[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(100000);
        }
        long start = System.currentTimeMillis();
        bubbleSortByError(arr);
        long normalSortTime = System.currentTimeMillis();
        System.out.println(normalSortTime - start);

    }

    public void selectSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(arr, minIndex, i);
            }
        }
    }

    private void swap(int[] arr, int minIndex, int i) {
        int temp = arr[minIndex];
        arr[minIndex] = arr[i];
        arr[i] = temp;
    }

    private void insertSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 1; i < arr.length; i++) { //假设第一个数位置时正确的；要往后移，必须要假设第一个。
            //用来往后判断的一个迭代变量
            int j = i;
            int target = arr[i]; //待插入的
            //不断的判断j位置之前是否小于，如果小于则需要进行让arr[j]替换数值
            //本质上就是保证j前面的顺序以及让当前这一位如果不符合的话跟符合的j位置进行交换位置(经过循环之后J一直在变化)
            while (j > 0 && target < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            //这时候因为arr[j]可能已经放到了其他位置，这里就进行一个重新赋值
            arr[j] = target;
        }
    }

    private int partition(int[] arr, int left, int right) {
        int pivotKey = arr[left];

        while (left < right) {
            //循环到右边的位置小于pivotKey（基准数）的位置
            while (left < right && arr[right] >= pivotKey)
                right--;
            arr[left] = arr[right]; //把小的移动到左边，也就是直接让left位置数赋值这个right数
            //循环到右边的位置大于pivotKey（基准数）的位置
            while (left < right && arr[left] <= pivotKey)
                left++;
            //            swap(arr, left, right); //把大的交换到右边，把小的交换到左边。比较容易理解的
            //可以使用下面这句话替换的原因：基准数已经在pivotKey中保存了，所以不需要每次交换都设置一个temp变
            //量，在交换左右指针的时候只需要先后覆盖就可以了。这样既能减少空间的使用还能降低赋值运算的次数
            arr[right] = arr[left];//把大的移动到右边
        }
        //        swap(arr, pivotPointer, left); //最后把pivot交换到中间
        arr[left] = pivotKey; //最后把pivot赋值到中间
        return left;
    }

    private void quickSort(int[] arr, int left, int right) {
        if (left >= right)
            return;
        int pivotPos = partition(arr, left, right);
        //使用递归思想，将左边与右边依旧如此处理，跳出递归的条件是left>=right
        quickSort(arr, left, pivotPos - 1);
        quickSort(arr, pivotPos + 1, right);
    }

    @Test
    public void mergeSortTest() {
        int[] arr = { 3, 35, 4, 5, 43, 56, 234, 5, 34, 5, 34534, 5, 34, 5, 34, 5 };
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void sortTest() {
        int[] arr = { 23, 4, 2, 5, 43, 5 };
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void stampedLockTest() {
        //读模版
        // 乐观读
        long stamp = sl.tryOptimisticRead();
        // 读入方法局部变量
        // 校验stamp
        if (!sl.validate(stamp)) {
            // 升级为悲观读锁
            stamp = sl.readLock();
            try {
                // 读入方法局部变量
            } finally {
                //释放悲观读锁
                sl.unlockRead(stamp);
            }
        }

        //写模版
        long stampWrite = sl.writeLock();
        try {
            // 写共享变量
        } finally {
            sl.unlockWrite(stampWrite);
        }
    }

    @Test
    public void copyWriteTest() {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("1");
        list.get(0);
    }

    @Test
    public void poolTest() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        TestAllMethod method = new TestAllMethod();
        Future<TestAllMethod> submit = service.submit(() -> {
        }, method);
        System.out.println(submit.get());

    }

    @Test
    public void blockTest() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        ExecutorCompletionService service = new ExecutorCompletionService<Object>(executor);
    }

    private long f(int n) {
        if (n == 0 || n == 1) {
            return n;
        } else {
            return f(n - 1) + f(n - 2);
        }
    }

    @Test
    public void forkJoinTest() {
        long start = System.currentTimeMillis();
        //        int count = 40;
        //        System.out.println(f(count));
        //        System.out.println((System.currentTimeMillis() - start));

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        Integer invoke = pool.invoke(new Fib(40));
        System.out.println(invoke);
        System.out.println((System.currentTimeMillis() - start));

        //        String[] fc = {"hello world",
        //                "hello me",
        //                "hello fork",
        //                "hello join",
        //                "fork join in world"};
        //        //创建ForkJoin线程池
        //        ForkJoinPool fjp =
        //                new ForkJoinPool(3);
        //        //创建任务
        //        MR mr = new MR(
        //                fc, 0, fc.length);
        //        //启动任务
        //        Map result =
        //                (Map) fjp.invoke(mr);
        //        //输出结果
        //        result.forEach((k, v)->
        //                System.out.println(k+":"+v));

    }

    @Test
    public void stringJoinerTest() {
        StringJoiner joiner = new StringJoiner("&", "[", "]");
        joiner.add("1");
        joiner.add("2");
        joiner.add("3");
        System.out.println(joiner.toString());
    }

    @Test
    public void stringTokenizerTest() {
        StringTokenizer tokenizer = new StringTokenizer("1，2，3，4，5，5，6", "，", false);
        while (tokenizer.hasMoreElements()) {
            String token = tokenizer.nextToken();
            System.out.println(token);
        }
    }

    static class SearchTask implements Runnable {

        private Integer        id;
        private CountDownLatch latch;

        public SearchTask(Integer id, CountDownLatch latch) {
            this.id = id;
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println("开始寻找" + id + "号龙珠");
            int seconds = random.nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("花了" + seconds + "s,找到了第" + id + "号龙珠");
            latch.countDown();
        }
    }

    public static class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static class Fib extends RecursiveTask<Integer> {

        private final int n;

        public Fib(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            }
            Fib fib1 = new Fib(n - 1);
            //创建子任务
            fib1.fork();
            Fib fib2 = new Fib(n - 2);

            return fib2.compute() + fib1.join();
        }
    }

    //MR模拟类
    static class MR extends RecursiveTask {
        private String[] fc;
        private int      start, end;

        //构造函数
        MR(String[] fc, int fr, int to) {
            this.fc = fc;
            this.start = fr;
            this.end = to;
        }

        @Override
        protected Map compute() {
            if (end - start == 1) {
                return calc(fc[start]);
            } else {
                int mid = (start + end) / 2;
                MR mr1 = new MR(fc, start, mid);
                mr1.fork();
                MR mr2 = new MR(fc, mid, end);
                //计算子任务，并返回合并的结果
                return merge(mr2.compute(), (Map) mr1.join());
            }
        }

        //合并结果
        private Map merge(Map r1, Map r2) {
            Map result = new HashMap<>();
            result.putAll(r1);
            //合并结果
            r2.forEach((k, v) -> {
                Long c = (Long) result.get(k);
                if (c != null)
                    result.put(k, c + (Long) v);
                else
                    result.put(k, v);
            });
            return result;
        }

        //统计单词数量
        private Map calc(String line) {
            Map result = new HashMap<>();
            //分割单词
            String[] words = line.split("s+");
            //统计单词数量
            for (String w : words) {
                Long v = (Long) result.get(w);
                if (v != null)
                    result.put(w, v + 1);
                else
                    result.put(w, 1L);
            }
            return result;
        }
    }

    @Test
    public void streamTest(){
        List<Person> personList = new ArrayList<>();
        System.out.println(personList.stream().map(Person::getAge).collect(Collectors.toList()).size());
    }

}
