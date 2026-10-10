package org.example;

import java.util.*;
public class BSTHashTableComparison {



    static class BST {
        class Node {
            int key;
            Node left, right;

            Node(int key) {
                this.key = key;
            }
        }

        private Node root;

        public void insert(int key) {
            root = insert(root, key);
        }

        private Node insert(Node node, int key) {
            if (node == null) {
                return new Node(key);
            }

            if (key < node.key) {
                node.left = insert(node.left, key);
            } else if (key > node.key) {
                node.right = insert(node.right, key);
            }

            return node;
        }

        public boolean search(int key) {
            Node current = root;

            while (current != null) {
                if (key == current.key) {
                    return true;
                }

                if (key < current.key) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return false;
        }

        public void delete(int key) {
            root = delete(root, key);
        }

        private Node delete(Node node, int key) {
            if (node == null) {
                return null;
            }

            if (key < node.key) {
                node.left = delete(node.left, key);
            } else if (key > node.key) {
                node.right = delete(node.right, key);
            } else {
                if (node.left == null) return node.right;
                if (node.right == null) return node.left;

                Node successor = node.right;
                while (successor.left != null) {
                    successor = successor.left;
                }

                node.key = successor.key;
                node.right = delete(node.right, successor.key);
            }

            return node;
        }
    }



    static class HashTable {
        private static class Entry {
            int key;
            Entry next;

            Entry(int key, Entry next) {
                this.key = key;
                this.next = next;
            }
        }

        private final Entry[] buckets;

        HashTable(int capacity) {
            buckets = new Entry[capacity];
        }

        private int index(int key) {
            return Math.floorMod(Integer.hashCode(key), buckets.length);
        }

        public void insert(int key) {
            int i = index(key);
            if (search(key)) return;
            buckets[i] = new Entry(key, buckets[i]);
        }

        public boolean search(int key) {
            int i = index(key);

            for (Entry e = buckets[i]; e != null; e = e.next) {
                if (e.key == key) return true;
            }

            return false;
        }

        public void delete(int key) {
            int i = index(key);
            Entry current = buckets[i];
            Entry previous = null;

            while (current != null) {
                if (current.key == key) {
                    if (previous == null) {
                        buckets[i] = current.next;
                    } else {
                        previous.next = current.next;
                    }
                    return;
                }

                previous = current;
                current = current.next;
            }
        }
    }



    static long measure(Runnable operation) {
        long start = System.nanoTime();
        operation.run();
        return System.nanoTime() - start;
    }

    static void benchmark(int n) {
        List<Integer> values = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            values.add(i);
        }

        Collections.shuffle(values, new Random(42));

        BST bst = new BST();
        HashTable hash = new HashTable(n * 2 + 1);


        long bstInsert = measure(() -> {
            for (int x : values) bst.insert(x);
        });

        long hashInsert = measure(() -> {
            for (int x : values) hash.insert(x);
        });


        for (int i = 0; i < Math.min(n, 1000); i++) {
            bst.search(values.get(i));
            hash.search(values.get(i));
        }


        long bstSearch = measure(() -> {
            for (int x : values) bst.search(x);
        });

        long hashSearch = measure(() -> {
            for (int x : values) hash.search(x);
        });


        long bstDelete = measure(() -> {
            for (int x : values) bst.delete(x);
        });

        long hashDelete = measure(() -> {
            for (int x : values) hash.delete(x);
        });

        System.out.printf("%nElements: %,d%n", n);
        System.out.printf("%-12s %15s %15s%n",
                "Operation", "BST (ms)", "Hash (ms)");

        System.out.printf("%-12s %15.3f %15.3f%n",
                "Insertion", bstInsert / 1e6, hashInsert / 1e6);
        System.out.printf("%-12s %15.3f %15.3f%n",
                "Searching", bstSearch / 1e6, hashSearch / 1e6);
        System.out.printf("%-12s %15.3f %15.3f%n",
                "Deletion", bstDelete / 1e6, hashDelete / 1e6);
    }

    public static void main(String[] args) {

        benchmark(1000);

        System.out.println("\n===== FINAL RESULTS =====");

        benchmark(100);
        benchmark(1000);
        benchmark(10000);
    }
}
