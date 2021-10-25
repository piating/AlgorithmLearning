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
