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
### 四、滑动窗口
滑动窗口问题大多数时候使用双指针来解决。它的“解题模板”可以总结为一句话：右指针无脑滑动，左指针看情况收缩。
```
	int left = 0;//左指针
	int right = 0;//右指针
	int len = S.length();//数据集长度，这里是字符串长度
	//当右指针没有达到边界时
	while (right < len) {
	    if (某种条件) {
	    	//....
	        left++;//左指针收缩
	    }
	    //右指针无脑往右滑
	    right++;
	}
```
总结：

1.while中的if，有时候需要换成while，意思是直到某种条件成立前一直收缩左指针。

2.左右指针滑动过程中，注意边界问题。

3.滑动窗口法（双指针法）常用来解决最长（或最短）子数组（或子串）问题。注意它一般不能用来解决子序列问题，因为子数组或子串的元素是连续的，而子序列问题不要求元素连续，而指针滑动过程中扫过的元素是连续的。遇到子序列问题先考虑动态规划或回溯，避免一上来就尝试滑窗，浪费考试时间。

### 五、递归、回溯

递归是我最不擅长的，遇到递归我总是想能不能通过显示的使用栈来解决问题。所以这里也没什么经验。但是递归是如此重要，因为它是很多其他算法的基础，比如回溯，比如DFS。递归的定义如此简单，但是使用却非常灵活。这里我也没什么好办法，更没有什么模板，毕竟它就是一个方法自己调用自己而已。只能通过多练习《可信认证之九阳神功》、《可信软件开发能力提升》中的练习题来找感觉（文章最后给出pdf资源），其次如果觉得文字枯燥，在网上找一些动画讲解、视频教程。

回溯基于递归，它其实是一种暴力算法，用于穷举所有情况。回溯倒是有“解题模板”：
```
	/**
	LeetCode 39
	*/
	//result用来收集结果
	List<List<Integer>> result = new ArrayList<>();
	//这个全局变量只是为了减少backTracing方法的参数个数。。。
	int[] candidates; 
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		this.candidates = candidates;
		backTracing(0, 0, target, new ArrayList<>());
		return result;
	}
	 
	public void backTracing(int start, int curSum, int target, List<Integer> path) {
	    if (curSum > target) {
	    	return;
	    }
	    if (curSum == target) {
	        result.add(new ArrayList<>(path));
	        return;
	    }
	    for (int i = start; i < candidates.length; i++) {
	        curSum += candidates[i];
	        path.add(candidates[i]);
	        backTracing(i, curSum, target, path);
	        curSum -= path.get(path.size() - 1);
	        path.remove(path.size() - 1);
	    }
	}
```
上面的backTracing方法可以抽象成如下的模板：
```
	public void backTracing(int start, int curSum, int target, List<Integer> path) {
	    if (当前条件已经不符合，后续更不可能出现需要的结果了，结束本层递归) {
	    	return;
	    }
	    if (符合我们的条件时) {
	        //收集结果
	        result.add(new ArrayList<>(path));
	        return;
	    }
	    //循环遍历数据集
	    for (int i = start; i < candidates.length; i++) {
	        //改变状态
	        backTracing(i, curSum, target, path);//用新状态递归
	        //复原状态
	    }
	}
```
总结：

1. 方法中的第一个if，其实是一种“剪枝”，对于一些显然不会成立的条件，提前判断提前结束本层递归，会提高速度。实际题目中可能有多个if来“剪枝”。

2. 一般会用一个全局变量来收集结果。为什么用全局变量而不在backTracing方法定义局部变量呢？因为backTracing是个递归，定义局部变量每次递归都会重新赋值，无法求出整体的结果。

3. backTracing方法中的path、curSum这样意义的参数，一般是一定会有的。path表示到目前为止已经使用过的数据，curSum表示到目前为止的结果累加。总之不要狭隘的理解这两个参数，具体题目赋予它们具体的意义。

4. 最值得注意的一点，收集结果时，一定要重新new一个ArrayList，而不能直接把path加到result中，因为path在后续的递归中还会被改变。

如果觉得回溯难以掌握，可以去B站搜“代码随想录”，看up主的视频教程。其次到LeetCode上，在回溯分类下，多练几道题目找感觉。
