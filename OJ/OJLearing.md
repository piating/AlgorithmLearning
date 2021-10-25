### 一、常用数据结构

LinkedList:实现了Queue接口，具有先进先出（FIFO）的特性，在广度优先搜索中，常用来存后续节点。

ArrayDeque:用于在算法中当Stack使用，性能高于Stack。同时具有FIFO和LIFO（后进先出，栈）特性。在深度优先搜索中，常用来存后续节点。

PriorityQueue:通过在构造函数中传入自定义的Comparator，可以实现大顶堆或者小顶堆。大顶堆的意思是，数据集中按照Comparator规则比较下来较大的数据在堆的顶部。

这几个数据结构实在是高频，一定要了解一下他们的使用场景和常用Api。

### 二、排序

Java的Arrays、Collections类，都有现成的排序方法，绝对比我们自己手写排序来的快。

考试题目也很少会让你手撕排序算法，所以，熟悉一下Arrays、Collections的方法，包括但不限于sort，binarySerch等，就够用了。

其中sort方法需要注意，可以传入自定义的Comparator。在2021.5.21号的专业级科目一试题的第一题中，就考到了多个维度的排序，所以怎么定义这个Comparator需要熟练掌握。
```
	public int[] queryRoom(int area, int price, int rooms, int[] address, int[][] orderBy) {
	        List<Room> filterRooms = filter(area, price, rooms);
	        Collections.sort(filterRooms, new Comparator<Room>() {
	            @Override
	            public int compare(Room o1, Room o2) {
	                return innerCompare(o1, o2, address, orderBy);
	            }
	        });
	        int[] res = new int[filterRooms.size()];
	        for (int i = 0; i < res.length; i++) {
	            res[i] = filterRooms.get(i).id;
	        }
	        return res;
	    }
	private int innerCompare(Room r1, Room r2, int[] address, int[][] orderBy) {
	        int value = 0;
	        int distanceR1 = cacDistance(r1, address);
	        int[] attr1 = new int[]{r1.area, r1.price, distanceR1};
	        int distanceR2 = cacDistance(r2, address);
	        int[] attr2 = new int[]{r2.area, r2.price, distanceR2};
	        //多个条件综合考虑，最后返回一个value
	        for (int[] rule : orderBy) {
	            int type = rule[0];
	            int order = rule[1];
	            int i = type - 1;
	            if (attr1[i] == attr2[i]) {
	                continue;
	            }
	            if (order == 1) {
	                value = attr1[i] - attr2[i];
	            } else {
	                value = attr2[i] - attr1[i];
	            }
	            return value;
	        }
	        if (value == 0) {
	            value = r1.id - r2.id;
	        }
	        return value;
	    }
```
其中Comparator的compare方法的返回值要注意一下，比如要比较的a,b是两个double类型的值，我们不能直接返回a-b，因为compare的返回值类型是int，可能导致溢出。
### 三、单调栈
单调栈的思想是：维护一个栈内元素单调递增或者单调递减的栈，循环要处理的数据集，当当前数据大于/小于栈顶元素时，取出栈顶元素，重复该步骤，直至将当前数据入栈时栈还能保持单调性，则将当前数据入栈。说起来拗口，直接看“通项公式”：
```
	Deque<Integer> stack = new ArrayDeque<>();
	//T是一个数组，也可能是一个集合。这里用数组举例
	for (int i = 0; i < T.length; i++) {
	    //当栈不为空，且当前T[i]大于栈顶元素时
	    while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
	        //...拿栈顶元素做一些操作
	        //将该元素出栈
	        stack.pop();
	    }
	    //当前数据入栈
	    stack.push(i);
	}
```
总结：

1.注意stack内存放的其实是数组下标。stack内存放什么具有一定的灵活性，有时候存数据元素本身，有时候存数据在集合中的下标，实际做题时，看哪种更方便我们实现算法。

2.while循环的意思是，不断去除栈顶元素直到将当前元素入栈时，栈还保持单调性。
