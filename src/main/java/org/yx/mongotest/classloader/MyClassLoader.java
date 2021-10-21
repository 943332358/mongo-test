package org.yx.mongotest.classloader;

import lombok.SneakyThrows;
import lombok.var;

import java.io.DataInputStream;
import java.io.File;
import java.util.Arrays;

/**
 * @author yangxin
 * @date 2021-10-11 14:45
 * @since v1.6.5
 */
public class MyClassLoader extends ClassLoader {
    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("Class Loading Started for " + name);
        if (name.startsWith("org.yx")) {
            return getClass(name);
        }


        return super.loadClass(name);
    }

    @SneakyThrows
    private byte[] loadClassData(String name) {
        var inputStream = getClass().getClassLoader().getResourceAsStream(name);

        byte[] buff = new byte[inputStream.available()];

        var in = new DataInputStream(inputStream);

        in.readFully(buff);
        in.close();
        return buff;
    }


    private Class<?> getClass(String name) {
        String file = name.replace('.', File.separatorChar) + ".class";
        System.out.println("Name of File" + file);

        byte[] bytes = loadClassData(file);
        bytesToHex(bytes);
        System.out.println(Arrays.toString(bytes));

        // 修改字节码字符串"Welcome Frugalis Minds" to "Celcome Frugalis Minds"
        for (int i = 0; i < bytes.length; i++) {
            // 0x57 == W
            if (bytes[i] == 0x57) {
                // 0x43 == C
                bytes[i] = 0x43;
            }
        }


        var c = defineClass(name, bytes, 0, bytes.length);

        resolveClass(c);
        return c;
    }


    /**
     * 字节数组转16进制
     *
     * @param bytes 需要转换的byte数组
     * @return 转换后的Hex字符串
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex);
        }
        System.out.println(sb);
        return sb.toString();
    }
}
