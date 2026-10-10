
## 1. Experimental Discussion

### Overview

In this experiment a Binary Search Tree and a Hash Table in Java were compared to see how they perform when inserting, searching and deleting data. The time taken for each operation was recorded in milliseconds for data sets of 100, 1,000 and 10,000 items.

### Performance Results

Below are the results of the experiment showing the time taken per operation for numbers of elements.

Table 1: Performance Results

| Elements ($N$) | Operation      BST Time (ms) | Hash Table Time (ms) |

|---------------|---------------|--------------|----------------------|

| 100           | Insertion     | 0.036        | 0.017                |

100           | Searching     | 0.012        | 0.010

| 100           | Deletion      | 0.039        | 0.011

| 1,000         | Insertion     | 0.139        | 0.102                |

| 1,000         | Searching     | 0.129        | 0.087

| 1,000         | Deletion      | 0.150        | 0.093                |

| 10,000        | Insertion     | 1.600        | 0.999                |

| 10,000        | Searching     | 1.463        | 0.735                |

| 10,000        | Deletion      | 1.443        | 0.746                |

### 1. Experimental Results and Discussion

The Hash Table performed better than the Binary Search Tree in all nine tests. For instance when searching for 10,000 items the Hash Table took 0.735 milliseconds while the Binary Search Tree took 1.463 milliseconds.

From the results the Hash Table is more efficient than the Binary Search Tree when it comes to inserting, searching and deleting data.

## 2. Theoretical Time Complexity

The following table compares the time complexity of both data structures.

Table 2: Theoretical Comparison of Time Complexity

| Operation     | BST Average | BST Worst | Hash Table Average | Hash Table Worst |

|---------------|-------------|-----------|--------------------|------------------|

Insertion     | O(log n)    | O(n)      | O(1)               | O(n)             |

Searching     | O(log n)    | O(n)      | O(1)               | O(n)             |

| Deletion      | O(log n)    | O(n)      | O(1)               | O(n)             |

### 2. Theoretical Time Complexity

With the Hash Table the actual running time depends on the hashing algorithm used and how often collisions occur. For the Binary Search Tree the worst-case time occurs when the tree is completely unbalanced such as when itemsre added in ascending order.

## 3. Why Performance Differs in Practice

A Hash Table uses a hashing function to determine where an item should be stored in an array. A Binary Search Tree on the hand uses comparisons to insert and locate items in a tree structure. The performance of the Binary Search Tree depends heavily on how the tree's built. A balanced tree runs in O(log n) time. A skewed tree can take O(n) time. Factors like hash collisions and the overhead from the Java Virtual Machine also affect Hash Table performance.

In practice the Hash Table is usually faster because it has an average-case time of O(1) for operations. However the Binary Search Tree is better when you need ordered data or want to perform queries like finding the nearest value or range searches.

## 4. Conclusion

This experiment compared the performance of a Hash Table and a Binary Search Tree. The results showed that the Hash Table was faster for 100, 1,000 and 10,000 items. Binary Search Trees are useful when you need to maintain data in sorted order and have an average-case time of O(log n). So the best data structure to use depends on what you need.

This experiment also shows why it is important to consider performance when choosing a data structure. For example even though the Hash Table is on average a self-balancing Binary Search Tree could be a better choice, in certain cases.
