package org.yx.mongotest.leetcode;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yangxin
 * <a href="https://leetcode-cn.com/problems/delete-node-in-a-linked-list/">删除链表中的节点</a>
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    static class Solution {
        public void deleteNode(ListNode node) {
            node.next.val = node.val;
            node.next.next = node.next;

        }

        public static void main(String[] args) {
/*            Stream.generate(() -> "aaa").limit(10).forEach(System.out::println);
            String[] strings = {"meiyangyang", "xiyangang", "lanyangyang"};
            Integer[] integers = {1, 2, 3};
            Stream stream = Stream.of(strings);
            Stream stream1 = Stream.of(integers);
            Stream stream2 = Stream.concat(stream, stream1);
            stream2.forEach(System.out::println);*/

            //Stream.iterate(0, t -> t + 1).limit(20).forEach(System.out::println);

            List<String> a = Stream.of("a", "b", "c").map(m -> m + "1")
                    .collect(Collectors.collectingAndThen(Collectors.toList(), t -> {
                        return t.stream().collect(Collectors.toList());
                    }));
            System.out.println(a);
        }
    }
}