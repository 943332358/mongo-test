package org.yx.mongotest.bst;

import lombok.var;

/**
 * @author yangxin
 */
public class Test {
    public static void main(String[] args) {
        var binarySearchTrees = new BinarySearchTrees<Integer, String>();
        binarySearchTrees.put(500, "张三1");
        binarySearchTrees.put(1231, "aaa2");
        binarySearchTrees.put(12, "bbb3");
        binarySearchTrees.put(4562, "ccc4");
        binarySearchTrees.put(21, "李四5");
        binarySearchTrees.put(2, "王五6");
        binarySearchTrees.put(643, "96320");
        binarySearchTrees.put(4584, "96322");
        binarySearchTrees.put(11, "96319");
        System.out.println(binarySearchTrees);
        System.out.println(binarySearchTrees.remove(4562));
        System.out.println(binarySearchTrees);

        System.out.println(binarySearchTrees.get(643));
        System.out.println(binarySearchTrees.get(4584));
    }
}
