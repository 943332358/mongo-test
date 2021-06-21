package org.yx.mongotest.leetcode;


import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 简单桶排序
 *
 * @author yangxin
 */
public class BucketSorting {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(7, 5, 3, 1, 3, 7, 3, 1, 9, 8);
        int[] i = new int[10];

        list.forEach(p -> i[p] = i[p] + 1);

        IntStream.range(0, 10)
                .mapToObj(m -> {
                    List<Integer> l = Lists.newArrayList();
                    for (int j = 0; j < i[m]; j++) {
                        l.add(m);
                    }
                    return l;
                })
                .flatMap(Collection::stream)
                .forEach(System.out::print);
    }
}
