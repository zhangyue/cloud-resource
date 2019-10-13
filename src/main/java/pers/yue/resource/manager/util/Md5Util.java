package pers.yue.resource.manager.util;

import com.amazonaws.SdkClientException;
import com.amazonaws.util.Md5Utils;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by Zhang Yue on 3/20/2018
 */
public class Md5Util {
    private static Logger logger = LoggerFactory.getLogger(ThreadUtil.getClassName());

    public static String getMd5(byte[] byteArray) {
        logger.info("Calculate MD5 for byte array {}:");
        String hexMd5 = new String(Hex.encodeHex(Md5Utils.computeMD5Hash(byteArray)));
        logger.info("    {}", hexMd5);
        return hexMd5;
    }

    public static String getMd5(String str) {
        String preview = str;
        if(str.length() > 256) {
            preview = str.substring(0, 256);
        }
        logger.info("Calculate MD5 for string: {}", preview);
        String hexMd5 = new String(Hex.encodeHex(Md5Utils.computeMD5Hash(str.getBytes())));
        logger.info("    {}", hexMd5);
        return hexMd5;
    }

    public static String getMd5(InputStream inputStream) {
        logger.info("Calculate MD5 for input stream:");
        try {
            String hexMd5 = new String(Hex.encodeHex(Md5Utils.computeMD5Hash(inputStream)));
            logger.info("    {}", hexMd5);
            return hexMd5;
        } catch(IOException e) {
            logger.error("{} when calculate MD5 for input stream.", e.getClass().getSimpleName(), e);
            throw new RuntimeException(e);
        }
    }

    public static String getMd5(File file) {
        logger.info("Calculate MD5 for file {}:", file);
        try {
            String hexMd5 = new String(Hex.encodeHex(Md5Utils.computeMD5Hash(file)));
            logger.info("    {}", hexMd5);
            return hexMd5;
        } catch(IOException e) {
            logger.error("{} when calculate MD5 for file {}.", e.getClass().getSimpleName(), file, e);
            throw new RuntimeException(e);
        }
    }

    public static String getMd5(File file, Long start, Long end) {
        logger.info("Calculate MD5 for file {} from range {} to {}:", file, start, end);
        return getMd5(FileUtil.copyFileRange(file, start, end));
    }

    // This is wrong. Use getMd5Aws() once the bug is fixed in JSS. See OSS-345.
    public static String getMd5Jss(List<String> md5StrList) {
        try {
            MessageDigest md5;
            md5 = MessageDigest.getInstance("MD5");
            StringBuilder totalMd5 = new StringBuilder();
            for (String md5Str : md5StrList) {
                totalMd5.append(md5Str);
            }
            byte[] buff = totalMd5.toString().getBytes("UTF-8");
            if (buff.length == 0) {
                return null;
            }
            md5.update(buff, 0, buff.length);
            String ret = new BigInteger(1, md5.digest()).toString(16);
            while (ret.length() < 32) {
                ret = "0" + ret;
            }
            return ret;
        } catch (Exception e) {
            logger.error("{} when get MD5 for MD5 list.", e.getClass().getSimpleName());
            throw new RuntimeException(e);
        }
    }

    public static String getMd5Aws(List<String> md5StrList) {
        byte[] md5BytesAll = new byte[0];

        for(String md5Str : md5StrList) {
            try {
                byte[] md5Bytes = Hex.decodeHex(md5Str.toCharArray());
                byte[] lastMd5BytesAll = md5BytesAll;
                md5BytesAll = new byte[lastMd5BytesAll.length + md5Bytes.length];
                System.arraycopy(lastMd5BytesAll, 0, md5BytesAll, 0,  lastMd5BytesAll.length);
                System.arraycopy(md5Bytes, 0, md5BytesAll, lastMd5BytesAll.length, md5Bytes.length);
            } catch (DecoderException e) {
                logger.error("{} when decode hex.", e.getClass().getSimpleName(), e);
                throw new RuntimeException(e);
            }
        }

        return Md5Util.getMd5(md5BytesAll);
    }

    public static String getMd5AsBase64(byte[] byteArray) {
        logger.info("Calculate MD5 as BASE64 for byte array {}:", byteArray);
        String base64Md5 = Md5Utils.md5AsBase64(byteArray);
        logger.info("    {}", base64Md5);
        return base64Md5;
    }

    public static String getMd5AsBase64(String str) {
        logger.info("Calculate MD5 as BASE64 for string {}:", str);
        String base64Md5 = Md5Utils.md5AsBase64(str.getBytes());
        logger.info("    {}", base64Md5);
        return base64Md5;
    }

    public static String getMd5AsBase64(InputStream inputStream) {
        logger.info("Calculate MD5 as BASE64 for input stream:");
        try {
            String base64Md5 = Md5Utils.md5AsBase64(inputStream);
            logger.info("    {}", base64Md5);
            return base64Md5;
        } catch(IOException e) {
            logger.error("{} when calculate MD5 as base64 for input stream.", e.getClass().getSimpleName(), e);
            throw new RuntimeException(e);
        } catch(SdkClientException e) {
            logger.error("{} when calculate MD5 as base64 for input stream.", e.getClass().getSimpleName(), e);
            throw new RuntimeException(e);
        }
    }

    public static String getMd5AsBase64(File file) {
        logger.info("Calculate MD5 as BASE64 for file {}:", file);
        try {
            String base64Md5 = Md5Utils.md5AsBase64(file);
            logger.info("    {}", base64Md5);
            return base64Md5;
        } catch(IOException e) {
            logger.error("{} when calculate MD5 as base64 for file {}.", e.getClass().getSimpleName(), file, e);
            throw new RuntimeException(e);
        }
    }

    public static String getMd5AsBase64(File file, Long start, Long end) {
        logger.info("Calculate MD5 as BASE64 for file {} from range {} to {}:", file, start, end);
        return getMd5AsBase64(FileUtil.copyFileRange(file, start, end));
    }

    public static String getMd5AsBase64(File file, long[] range) {
        String md5;
        if(range == null) {
            md5 = Md5Util.getMd5AsBase64(file);
        } else {
            if(range.length == 1) {
                md5 = Md5Util.getMd5AsBase64(file, range[0], null);
            } else if(range.length == 2) {
                md5 = Md5Util.getMd5AsBase64(file, range[0], range[1]);
            } else {
                String message = "Unexpected number of ranges.";
                logger.error(message);
                throw new RuntimeException(message);
            }
        }
        return md5;
    }

    public static String hexToBase64(String hex) {
        logger.info("Convert hex string {} to base64:", hex);
        try {
            byte[] decoded = Hex.decodeHex(hex.toCharArray());
            String base64 = Base64.encodeBase64String(decoded);
            logger.info("    {}", base64);
            return base64;
        } catch(DecoderException e) {
            logger.error("{} when decode hex string {}.", e.getClass().getSimpleName(), hex);
            throw new RuntimeException(e);
        }
    }
    public static byte[] decodeBase64(String base64) {
        logger.info("Decode base64 {} :", base64);
        byte[] decoded = Base64.decodeBase64(base64);
        logger.info("    {}", decoded);
        return decoded;
    }

    public static String decodeBase64String(String base64) {
        byte[] decoded = decodeBase64(base64);

        String decodedString = new String(decoded);
        logger.info("    {}", decodedString);
        return base64;
    }

    public static String md5AsBase64(byte[] input) {
        return com.amazonaws.util.Base64.encodeAsString(computeMD5Hash(input));
    }
    public static byte[] computeMD5Hash(byte[] input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return md.digest(input);
        } catch (NoSuchAlgorithmException e) {
            // should never get here
            throw new IllegalStateException(e);
        }
    }
    public static String getMd5AsSafeBase64(byte[] byteArray) {
        logger.info("Calculate MD5 as BASE64 for byte array {}:", byteArray);
        String safebase64Md5 =  Md5Util.md5AsBase64(byteArray).replace('+', '-').replace('/', '_');
        logger.info("{}", safebase64Md5);
        return safebase64Md5;
    }

}
