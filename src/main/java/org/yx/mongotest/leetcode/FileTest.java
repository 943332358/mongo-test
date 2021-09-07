package org.yx.mongotest.leetcode;

import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author yangxin
 */
public class FileTest {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("C://test/test.txt");

        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, 10);

        while (bufferedInputStream.read() != -1) {

        }


        byte[] bytes = StreamUtils.copyToByteArray(fileInputStream);
        System.out.println(new String(bytes));


    }
}
