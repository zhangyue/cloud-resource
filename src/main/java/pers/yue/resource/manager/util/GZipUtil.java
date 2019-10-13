package pers.yue.resource.manager.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 * Created by zhangyue182 on 06/12/2018
 */
public class GZipUtil {
    private static Logger logger = LoggerFactory.getLogger(ThreadUtil.getClassName());

    public static void decompress(File gzipFile, File decompressedFile) {
        logger.info("Decompress file {} to {}.", gzipFile, decompressedFile);
        try(
                FileInputStream fis = new FileInputStream(gzipFile);
                GZIPInputStream gis = new GZIPInputStream(fis);
                FileOutputStream fos = new FileOutputStream(decompressedFile)
        ) {
            byte[] buffer = new byte[4096];
            int len;
            while((len = gis.read(buffer)) != -1){
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            logger.info("{} when decompress file {} to {}.", e.getClass().getSimpleName(), gzipFile, decompressedFile);
            throw new RuntimeException(e);
        }
    }
}
