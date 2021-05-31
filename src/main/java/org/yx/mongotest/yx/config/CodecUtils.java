package org.yx.mongotest.yx.config;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 各种格式的编码加码工具类.
 * <p>
 * 集成Commons-Codec,Commons-Lang及JDK提供的编解码方法.
 */
public class CodecUtils {

    /**
     * Hex编码.
     *
     * @param input byte数组类型参数
     * @return String字符串
     */
    public static String hexEncode(byte[] input) {
        return Hex.encodeHexString(input);
    }

    /**
     * Hex解码.
     *
     * @param input 字符串
     * @return byte数组
     */
    public static byte[] hexDecode(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            throw new IllegalStateException("Hex Decoder exception", e);
        }
    }

    /**
     * Hex解码.返回String
     *
     * @param input String类型输入
     * @return String类型输出
     */
    public static String hexDecodeStr(String input) {
        try {
            byte[] b = Hex.decodeHex(input.toCharArray());
            return new String(b, StandardCharsets.UTF_8);
        } catch (DecoderException e) {
            throw new IllegalStateException("Hex Decoder exception", e);
        }
    }

    /**
     * Base64编码.
     *
     * @param input byte数组类型输入
     * @return String字符串
     */
    public static String base64Encode(byte[] input) {
        return new String(Base64.encodeBase64(input), StandardCharsets.UTF_8);
    }

    /**
     * Base64编码, URL安全(将Base64中的URL非法字符如+,/=转为其他字符, 见RFC3548).
     *
     * @param input byte数组类型输入
     * @return String字符串
     */
    public static String base64UrlSafeEncode(byte[] input) {
        return Base64.encodeBase64URLSafeString(input);
    }

    /**
     * Base64解码.
     *
     * @param input String类型字符串输入
     * @return byte数组类型值
     */
    public static byte[] base64Decode(String input) {
        return Base64.decodeBase64(input);
    }

    /**
     * URL 编码, Encode默认为UTF-8.
     *
     * @param input String类型输入
     * @return String字符串
     */
    public static String urlEncode(String input) {
        try {
            return URLEncoder.encode(input, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }
    }

    /**
     * URL 解码, Encode默认为UTF-8.
     *
     * @param input String字符串
     * @return String字符串
     */
    public static String urlDecode(String input) {
        try {
            return URLDecoder.decode(input, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }
    }

    /**
     * Html 转码.
     *
     * @param html String类型HTML字符串
     * @return String字符串
     */
    public static String htmlEscape(String html) {
        return StringEscapeUtils.escapeHtml4(html);
    }

    /**
     * Html 解码.
     *
     * @param htmlEscaped String字符串
     * @return String字符串
     */
    public static String htmlUnescape(String htmlEscaped) {
        return StringEscapeUtils.unescapeHtml4(htmlEscaped);
    }

    /**
     * Xml 转码.
     *
     * @param xml String字符串
     * @return String字符串
     */
    @SuppressWarnings("deprecation")
    public static String xmlEscape(String xml) {
        return StringEscapeUtils.escapeXml(xml);
    }

    /**
     * Xml 解码.
     *
     * @param xmlEscaped String字符串
     * @return String字符串
     */
    public static String xmlUnescape(String xmlEscaped) {
        return StringEscapeUtils.unescapeXml(xmlEscaped);
    }

    /**
     * 字符串转换unicode
     */
    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);// 取出每一个字符
            unicode.append("\\u" + Integer.toHexString(c));// 转换为unicode
        }
        return unicode.toString();
    }

    /**
     * unicode 转字符串
     */
    public static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");
        if (hex.length == 1) {
            return unicode;
        } else {
            for (int i = 1; i < hex.length; i++) {
                int data = Integer.parseInt(hex[i], 16);// 转换出每一个代码点
                string.append((char) data);// 追加成string
            }
            return string.toString();
        }
    }

    /**
     * 私有构造方法。
     * 静态方法无需实例化，不应该有公共构造函数。
     */
    private CodecUtils() {
        throw new IllegalAccessError("Utility class");
    }
}
