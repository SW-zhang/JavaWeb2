package com.framework.common;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

public class MD5Util {

    public static void main(String[] args) {
        System.out.println(getMD5("admin"));
    }

    /**
     * 计算文件的MD5
     *
     * @param file
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws Exception
     */
    public static String getMD5FromFile(File file) throws IOException {
        InputStream fis;
        fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            int numRead = 0;
            while ((numRead = fis.read(buffer)) > 0) {
                md5.update(buffer, 0, numRead);
            }
            return BytesUtil.toHex(md5.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            fis.close();
        }
        return "";
    }

    /**
     * 计算数组的MD5
     *
     * @param bs
     * @param start
     * @param len
     * @return
     * @throws IOException
     */
    public static String getMD5FromBytes(byte[] bs, int start, int len) throws IOException {
        return getMD5FromBytes(Arrays.copyOfRange(bs, start, start + len));
    }

    /**
     * 计算数组的MD5
     *
     * @param bs
     * @return
     * @throws IOException
     */
    public static String getMD5FromBytes(byte[] bs) throws IOException {
        byte[][] bss = new byte[][]{bs};
        return getMD5FromBytes(bss);
    }

    /**
     * 计算数组的MD5
     *
     * @param bss
     * @return
     * @throws IOException
     */
    public static String getMD5FromBytes(byte[][] bss) throws IOException {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            for (byte[] bs : bss) {
                md5.update(bs, 0, bs.length);
            }
            return BytesUtil.toHex(md5.digest());
        } catch (NoSuchAlgorithmException e) {
        }
        return "";
    }

    /**
     * 计算字符串的MD5
     *
     * @param args
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getMD5(String args) {
        return getMD5(args, "UTF-8");

    }

    public static String getMD516(String args) {
        return getMD5(args, "UTF-8").substring(8, 24);

    }

    /**
     * 计算字符串的MD5，如果(null == 字符串)，则返回null；如果(字符串.equals(""))，则返回""。
     *
     * @param args
     * @return
     */
    public static String getMD5EmptyByEmpty(String args) {
        if (null == args) {
            return null;
        }
        if (StringUtils.isEmpty(args)) {
            return "";
        }
        return getMD5(args);
    }

    /**
     * 计算字符串的MD5
     *
     * @param args
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getMD5(String args, String encode) {
        MessageDigest alga;
        try {
            alga = MessageDigest.getInstance("MD5");
            try {
                alga.update(args.getBytes(encode));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            byte[] digesta = alga.digest();
            String str = BytesUtil.toHex(digesta); // to HexString
            return str;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 通过UUID生成MD5
     *
     * @return
     */
    public static String getMD5ByUUID() {
        return getMD5(UUID.randomUUID().toString());
    }

}
