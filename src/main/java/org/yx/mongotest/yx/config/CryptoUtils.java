package org.yx.mongotest.yx.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.UUID;

/**
 * 安全相关静态工具类，不可继承，不可实例化。
 * <p>
 * digest前缀的方法簇为摘要工具方法。常用于密码存储和对传输结果进行完整性校验，支持md5和sha-1两种散列算法。
 * 对于密码存储建议使用sha-1算法，还可以添加盐值和指定迭代次数来提高逆向查询的难度；
 * 在数据传输结校验应用场景下，建议使用md5算法以获取较好的性能。
 * <p>
 * signature前缀的方法簇为签名工具方法。
 * sha1,md5只是用来摘要，但因为是公开算法，人人都可以对公开内容做出相同的摘要，而Hmac是使用密钥的摘要，等同于des+sha1，可以达到签名的效果。
 * <p>
 * aes前缀的方法簇利用AES算法完成双向加密任务。
 * <p>
 * random前缀的方法簇用来进行各种随机值的生成。
 * <p>
 * 建议：使用盐值(salt)并进行1024次迭代的sha-1，Hmac和aes分别是摘要，签名和加密的首选协议。
 */
@Component
public final class CryptoUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(CryptoUtils.class);

    private static final int DEFAULT_AES_KEYSIZE = 128;
    private static final int DEFAULT_HMACSHA1_KEYSIZE = 160;
    private static final int DEFAULT_IVSIZE = 16;

    private static final String KEY_AES = "AES";
    private static final String KEY_AES_CBC = "AES/CBC/PKCS5Padding";
    private static final String KEY_HMACSHA1 = "HmacSHA1";
    private static final String KEY_MD5 = "MD5";
    private static final String KEY_SHA1 = "SHA-1";

    private final static String[] passwordDictionary = new String[]{
            "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f", "g", "h",
            "j", "k", "m", "n", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "A",
            "B", "C", "D", "E", "F", "G", "H", "J",
            "K", "L", "M", "N", "P", "Q", "R", "S",
            "T", "U", "W", "X", "Y", "Z"
    };
    private static SecureRandom random = new SecureRandom();

    /**
     * 使用 AES 解密字符串， 返回原始字符串。
     *
     * @param input Hex 编码的加密字符串，不能为空
     * @param key   符合 AES 要求的密钥，不能为空
     * @return 解密结果
     */
    public static String aesDecrypt(final byte[] input, final byte[] key) {
        if (input == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->aesDecrypt' 的 'input' 参数不能为空！");
        }
        if (key == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->aesDecrypt' 的 'key' 参数不能为空！");
        }

        byte[] decryptResult = tacitAes(input, key, Cipher.DECRYPT_MODE);
        return new String(decryptResult, StandardCharsets.UTF_8);
    }

    /**
     * 使用 AES 解密字符串， 返回原始字符串。
     *
     * @param input Hex 编码的加密字符串，不能为空
     * @param key   符合 AES 要求的密钥，不能为空
     * @param iv    初始向量，不能为空
     * @return 解密结果
     */
    public static String aesDecrypt(final byte[] input, final byte[] key, final byte[] iv) {
        if (input == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->aesDecrypt' 的 'input' 参数不能为空！");
        }
        if (key == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->aesDecrypt' 的 'key' 参数不能为空！");
        }
        if (iv == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->aesDecrypt' 的 'iv' 参数不能为空！");
        }

        byte[] decryptResult = tacitAes(input, key, iv, Cipher.DECRYPT_MODE);
        return new String(decryptResult, StandardCharsets.UTF_8);
    }

    /**
     * 使用 AES 加密原始字符串。
     *
     * @param input 原始输入字符数组，不能为空
     * @param key   符合 AES 要求的密钥，不能为空
     * @return 加密结果
     */
    public static byte[] aesEncrypt(final byte[] input, final byte[] key) {
        if (input == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->aesEncrypt' 的 'input' 参数不能为空！");
        }
        if (key == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->aesEncrypt' 的 'key' 参数不能为空！");
        }

        return tacitAes(input, key, Cipher.ENCRYPT_MODE);
    }

    /**
     * 使用 AES 加密原始字符串。
     *
     * @param input 原始输入字符数组，不能为空
     * @param key   符合 AES 要求的密钥，不能为空
     * @param iv    初始向量，不能为空
     * @return 加密结果
     */
    public static byte[] aesEncrypt(final byte[] input, final byte[] key, final byte[] iv) {
        if (input == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->aesEncrypt' 的 'input' 参数不能为空！");
        }
        if (key == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->aesEncrypt' 的 'key' 参数不能为空！");
        }
        if (iv == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->aesEncrypt' 的 'iv' 参数不能为空！");
        }

        return tacitAes(input, key, iv, Cipher.ENCRYPT_MODE);
    }

    /**
     * 生成随机的初始向量，默认大小为 cipher.getBlockSize()，16 字节。
     *
     * @return 随机初始向量
     */
    public static byte[] aesGenerateInitialVector() {
        byte[] bytes = new byte[DEFAULT_IVSIZE];
        random.nextBytes(bytes);
        return bytes;
    }

    /**
     * 生成 AES 密钥，返回字节数组，默认长度为 128 位 (16 字节)。
     *
     * @return 符合 AES 要求的密钥
     */
    public static byte[] aesGenerateKey() {
        return aesGenerateKey(DEFAULT_AES_KEYSIZE);
    }

    /**
     * 生成 AES 密钥，可选长度为 128/192/256 位。
     *
     * @param keysize 密钥长度
     * @return 符合 AES 要求的密钥
     */
    public static byte[] aesGenerateKey(final int keysize) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_AES);
            keyGenerator.init(keysize);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (GeneralSecurityException ex) {
            LOGGER.error("生成 AES 密钥时发生异常！", ex);
        }
        return null;
    }

    /**
     * 生成 AES 密钥
     *
     * @param key 密钥
     * @return 符合 AES 要求的密钥
     */
    public static byte[] aesGenerateKey(String key) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_AES);

            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes("UTF-8"));
            keyGenerator.init(secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (GeneralSecurityException | UnsupportedEncodingException ex) {
            LOGGER.error("生成 AES 密钥时发生异常！", ex);
        }
        return null;
    }

    /**
     * 使用 md5 散列算法对二进制数组内容进行摘要。
     *
     * @param input 输入值，不能为空
     * @return 散列后的值
     */
    public static byte[] digestMd5(final byte[] input) {
        if (input == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->digestMd5' 的 'input' 参数不能为空！");
        }
        return tacitDigestBinary(input, KEY_MD5, null, 1);
    }

    /**
     * 使用 md5 散列算法对二进制数组内容进行摘要。
     *
     * @param input 输入值，不能为空
     * @param salt  盐值，不能为空
     * @return 散列后的值
     */
    public static byte[] digestMd5(final byte[] input, final byte[] salt) {
        if (input == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->digestMd5' 的 'input' 参数不能为空！");
        }
        if (salt == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->digestMd5' 的 'salt' 参数不能为空！");
        }
        return tacitDigestBinary(input, KEY_MD5, salt, 1);
    }

    /**
     * 使用 md5 散列算法对二进制数组内容进行摘要。
     *
     * @param input      输入值，不能为空
     * @param salt       盐值，不能为空
     * @param iterations 迭代次数
     * @return 散列后的值
     */
    public static byte[] digestMd5(final byte[] input, final byte[] salt, final int iterations) {
        if (input == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->digestMd5' 的 'input' 参数不能为空！");
        }
        if (salt == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->digestMd5' 的 'salt' 参数不能为空！");
        }
        return tacitDigestBinary(input, KEY_MD5, salt, iterations);
    }

    /**
     * 使用 md5 散列算法对文件流内容进行摘要。
     *
     * @param input 输入流，不能为空
     * @return 散列后的值
     */
    public static byte[] digestMd5(final InputStream input) {
        if (input == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->digestMd5' 的 'input' 参数不能为空！");
        }
        return tacitDigestStream(input, KEY_MD5);
    }

    /**
     * 使用 md5 散列算法对字符串内容进行摘要。
     *
     * @param input 输入值，不能为空
     * @return 散列后的值
     */
    public static String digestMd5(final String input) {
        if (input == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->digestMd5' 的 'input' 参数不能为空！");
        }
        return CodecUtils.hexEncode(tacitDigestBinary(input.getBytes(StandardCharsets.UTF_8), KEY_MD5, null, 1));
    }

    /**
     * 使用 md5 散列算法对字符串内容进行摘要。
     *
     * @param input 输入值，不能为空
     * @param salt  盐值，不能为空
     * @return 散列后的值
     */
    public static String digestMd5(final String input, final byte[] salt) {
        if (input == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->digestMd5' 的 'input' 参数不能为空！");
        }
        if (salt == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->digestMd5' 的 'salt' 参数不能为空！");
        }
        return CodecUtils.hexEncode(tacitDigestBinary(input.getBytes(StandardCharsets.UTF_8), KEY_MD5, salt, 1));
    }

    /**
     * 使用 md5 散列算法对字符串内容进行摘要。
     *
     * @param input      输入值，不能为空
     * @param salt       盐值，不能为空
     * @param iterations 迭代次数
     * @return 散列后的值
     */
    public static String digestMd5(final String input, final byte[] salt, final int iterations) {
        if (input == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->digestMd5' 的 'input' 参数不能为空！");
        }
        if (salt == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->digestMd5' 的 'salt' 参数不能为空！");
        }
        return CodecUtils.hexEncode(tacitDigestBinary(input.getBytes(StandardCharsets.UTF_8), KEY_MD5, salt, iterations));
    }

    /**
     * 使用 sha-1 散列算法对二进制数组内容进行摘要。
     *
     * @param input 输入值，不能为空
     * @return 散列后的值
     */
    public static byte[] digestSha1(final byte[] input) {
        if (input == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->digestSha1' 的 'input' 参数不能为空！");
        }
        return tacitDigestBinary(input, KEY_SHA1, null, 1);
    }

    /**
     * 使用 sha-1 散列算法对二进制数组内容进行摘要。
     *
     * @param input 输入值，不能为空
     * @param salt  盐值，不能为空
     * @return 散列后的值
     */
    public static byte[] digestSha1(final byte[] input, final byte[] salt) {
        if (input == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->digestSha1' 的 'input' 参数不能为空！");
        }
        if (salt == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->digestSha1' 的 'salt' 参数不能为空！");
        }
        return tacitDigestBinary(input, KEY_SHA1, salt, 1);
    }

    /**
     * 使用 sha-1 散列算法对二进制数组内容进行摘要。
     *
     * @param input      输入值，不能为空
     * @param salt       盐值，不能为空
     * @param iterations 迭代次数
     * @return 散列后的值
     */
    public static byte[] digestSha1(final byte[] input, final byte[] salt, final int iterations) {
        if (input == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->digestSha1' 的 'input' 参数不能为空！");
        }
        if (salt == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->digestSha1' 的 'salt' 参数不能为空！");
        }
        return tacitDigestBinary(input, KEY_SHA1, salt, iterations);
    }

    /**
     * 使用 sha-1 散列算法对文件流内容进行摘要。
     *
     * @param input 输入流，不能为空
     * @return 散列后的值
     */
    public static byte[] digestSha1(final InputStream input) {
        if (input == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->digestSha1' 的 'input' 参数不能为空！");
        }
        return tacitDigestStream(input, KEY_SHA1);
    }

    /**
     * 使用 sha-1 散列算法对字符串内容进行摘要。
     *
     * @param input 输入值，不能为空
     * @return 散列后的值
     */
    public static String digestSha1(final String input) {
        if (input == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->digestSha1' 的 'input' 参数不能为空！");
        }
        return CodecUtils.hexEncode(tacitDigestBinary(input.getBytes(StandardCharsets.UTF_8), KEY_SHA1, null, 1));
    }

    /**
     * 使用 sha-1 散列算法对字符串内容进行摘要。
     *
     * @param input 输入值，不能为空
     * @param salt  盐值，不能为空
     * @return 散列后的值
     */
    public static String digestSha1(final String input, final byte[] salt) {
        if (input == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->digestSha1' 的 'input' 参数不能为空！");
        }
        if (salt == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->digestSha1' 的 'salt' 参数不能为空！");
        }
        return CodecUtils.hexEncode(tacitDigestBinary(input.getBytes(StandardCharsets.UTF_8), KEY_SHA1, salt, 1));
    }

    /**
     * 使用 sha-1 散列算法对字符串进行摘要。
     *
     * @param input      输入值，不能为空
     * @param salt       盐值，不能为空
     * @param iterations 迭代次数
     * @return 散列后的值
     */
    public static String digestSha1(final String input, final byte[] salt, final int iterations) {
        if (input == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->digestSha1' 的 'input' 参数不能为空！");
        }
        if (salt == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->digestSha1' 的 'salt' 参数不能为空！");
        }
        return CodecUtils.hexEncode(tacitDigestBinary(input.getBytes(StandardCharsets.UTF_8), KEY_SHA1, salt, iterations));
    }

    /**
     * 生成随机字节并将其置于 byte 数组中返回。
     * 可用于散列计算中盐值的生成等。
     *
     * @param length 数组长度
     * @return 随机字节
     */
    public static byte[] randomBinary(final int length) {
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        return bytes;
    }

    /**
     * 取随机数生成器序列的均匀分布的 int 值并返回。
     *
     * @return 整型随机数
     */
    public static int randomInt() {
        return Math.abs(random.nextInt());
    }

    /**
     * 取随机数生成器序列中 0（包括）和 max（不包括）之间均匀分布的 int 值并返回。
     *
     * @param max 最大值
     * @return 不大于 max 的整型随机数
     */
    public static int randomInt(final int max) {
        return Math.abs(random.nextInt(max));
    }

    /**
     * 取随机数生成器序列的均匀分布的 long 值并返回。
     *
     * @return 长整型随机数
     */
    public static long randomLong() {
        return Math.abs(random.nextLong());
    }

    /**
     * 生成指定长度的随机字符串，可用于密码和验证码的生成。
     * 为了避免混淆，随机序列去掉了两组容易混淆的字符：
     * <ul>
     * <li>小写的字母 <b>"l"</b> 跟数字  <b>"1"</b></li>
     * <li>大写的字母  <b>"O"</b> 跟数字  <b>"0"</b></li>
     * </ul>
     *
     * @param length 随机字符串长度
     * @return 指定长度的随机字符串
     */
    public static String randomString(final int length) {
        StringBuilder sBuffer = new StringBuilder();
        SecureRandom random = new SecureRandom();
        int maxLength = passwordDictionary.length;

        for (int i = 0; i < length; ++i) {
            sBuffer.append(passwordDictionary[random.nextInt(maxLength)]);
        }

        return sBuffer.toString();
    }

    /**
     * 生成 uuid 字符串。
     * 无分隔符，全部字母为大写。
     *
     * @return 随机的 uuid 字符串
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    /**
     * 生成指定格式的 uuid 字符串。
     *
     * @param separator 是否需要分隔符
     * @param upper     是否转换为大写字母
     * @return 随机的 uuid 字符串
     */
    public static String randomUUID(final boolean separator, final boolean upper) {
        String result = UUID.randomUUID().toString();
        if (!separator) {
            result = result.replaceAll("-", "");
        }
        if (upper) {
            result = result.toUpperCase();
        } else {
            result = result.toLowerCase();
        }
        return result;
    }

    /**
     * 使用 HMAC-SHA1 进行消息签名，返回字节数组，长度为 20 字节。
     *
     * @param input 原始输入字符数组，不能为空
     * @param key   HMAC-SHA1 密钥，不能为空
     * @return 签名
     */
    public static byte[] signatureFire(final byte[] input, final byte[] key) {
        if (input == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->signatureFire' 的 'input' 参数不能为空！");
        }
        if (key == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->signatureFire' 的 'key' 参数不能为空！");
        }

        try {
            SecretKey secretKey = new SecretKeySpec(key, KEY_HMACSHA1);
            Mac mac = Mac.getInstance(KEY_HMACSHA1);
            mac.init(secretKey);
            return mac.doFinal(input);
        } catch (GeneralSecurityException ex) {
            LOGGER.error("使用 HMAC-SHA1 算法进行签名时发生异常！", ex);
        }
        return null;
    }

    /**
     * 生成 HMAC-SHA1 密钥，返回字节数组，长度为 160 位 (20 字节)。
     * HMAC-SHA1 算法对密钥无特殊要求，RFC2401 建议最少长度为 160 位 (20 字节)。
     *
     * @return 密钥
     */
    public static byte[] signatureGenerateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_HMACSHA1);
            keyGenerator.init(DEFAULT_HMACSHA1_KEYSIZE);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (GeneralSecurityException ex) {
            LOGGER.error("生成 HMAC-SHA1 密钥时发生异常！", ex);
        }
        return null;
    }

    /**
     * 校验 HMAC-SHA1 签名是否正确。
     *
     * @param expected 已存在的签名，不能为空
     * @param input    原始输入字符串，不能为空
     * @param key      密钥，不能为空
     * @return 签名是否正确
     */
    public static boolean signatureValid(final byte[] expected, final byte[] input, final byte[] key) {
        if (expected == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->signatureValid' 的 'expected' 参数不能为空！");
        }
        if (input == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->signatureValid' 的 'input' 参数不能为空！");
        }
        if (key == null) {
            throw new IllegalArgumentException("方法 'CryptoUtils->signatureValid' 的 'key' 参数不能为空！");
        }
        byte[] actual = signatureFire(input, key);
        return Arrays.equals(expected, actual);
    }

    /**
     * 使用 AES 算法进行加密/解密
     *
     * @param input Hex 编码的加密字符串，不能为空
     * @param key   符合 AES 要求的密钥，不能为空
     * @param iv    初始向量，不能为空
     * @param mode  模式
     * @return 加密/解密结果
     */
    private static byte[] tacitAes(final byte[] input, final byte[] key, final byte[] iv, final int mode) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, KEY_AES);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance(KEY_AES_CBC);
            cipher.init(mode, secretKey, ivSpec);
            return cipher.doFinal(input);
        } catch (GeneralSecurityException ex) {
            LOGGER.error("使用 AES 算法进行加密/解密时发生异常！", ex);
        }
        return null;
    }

    /**
     * 使用 AES 算法进行加密/解密
     *
     * @param input Hex 编码的加密字符串，不能为空
     * @param key   符合 AES 要求的密钥，不能为空
     * @param mode  模式
     * @return 解密/加密结果
     */
    private static byte[] tacitAes(final byte[] input, final byte[] key, final int mode) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, KEY_AES);
            Cipher cipher = Cipher.getInstance(KEY_AES);
            cipher.init(mode, secretKey);
            return cipher.doFinal(input);
        } catch (GeneralSecurityException ex) {
            LOGGER.error("使用 AES 算法进行加密/解密时发生异常！", ex);
        }
        return null;
    }

    /**
     * 对输入的字符串进行摘要
     *
     * @param input      Hex 编码的加密字符串，不能为空
     * @param algorithm  算法
     * @param salt       盐值
     * @param iterations 迭代次数
     * @return 摘要结果
     */
    private static byte[] tacitDigestBinary(final byte[] input, final String algorithm,
                                            final byte[] salt, final int iterations) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            if (salt != null) {
                digest.update(salt);
            }

            byte[] result = digest.digest(input);

            for (int i = 1; i < iterations; i++) {
                digest.reset();
                result = digest.digest(result);
            }
            return result;
        } catch (GeneralSecurityException ex) {
            LOGGER.error("对输入的字符串进行摘要时发生异常！", ex);
        }
        return null;
    }

    /**
     * 对输入的字符串进行摘要
     *
     * @param input     Hex 编码的加密字符串，不能为空
     * @param algorithm 算法
     * @return 摘要结果
     */
    private static byte[] tacitDigestStream(final InputStream input, final String algorithm) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            int bufferLength = 8 * 1024;
            byte[] buffer = new byte[bufferLength];
            int read = input.read(buffer, 0, bufferLength);

            while (read > -1) {
                messageDigest.update(buffer, 0, read);
                read = input.read(buffer, 0, bufferLength);
            }

            return messageDigest.digest();
        } catch (IOException ex) {
            LOGGER.error("对输入的文件进行摘要时发生异常！", ex);
        } catch (GeneralSecurityException ex) {
            LOGGER.error("对输入的文件进行摘要时发生异常！", ex);
        }
        return null;
    }

    /**
     * main函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        byte[] salt = randomBinary(20);
        String encString = CryptoUtils.digestSha1("ideal123", salt);

        System.out.println("盐值：" + CodecUtils.hexEncode(salt));
        System.out.println("加密密码：" + encString);
    }

    private CryptoUtils() {
    }
}
