package resource.manager.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(ThreadUtil.getClassName());

    public static File writeToFile(InputStream inputStream, String pathToFile) {
        File file = new File(pathToFile);
        return writeToFile(inputStream, file);
    }

    public static File writeToFile(InputStream inputStream, File file) {
        logger.info("Write input stream to file {}.", file);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            int read;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                fos.write(bytes, 0, read);
            }
            return file;
        } catch(IOException e) {
            String message = e.getClass().getSimpleName() + " when write input stream to file " + file.getPath() + ".";
            logger.error(message);
            throw new RuntimeException(message, e);
        }
    }

    public static void copyFileRange(File sourceFile, long start, long end, File destFile) {
        logger.info("Copy file {} range {} - {} to file {}", sourceFile, start, end, destFile);

        try (
                InputStream is = new FileInputStream(sourceFile);
                OutputStream os = new FileOutputStream(destFile)
        ) {
            is.skip(start);
            int readChar;
            while (((readChar = is.read()) != -1) && end >= start) {
                os.write(readChar);
                start++;
            }
        } catch (IOException e) {
            String message = e.getClass().getSimpleName() + "when copy file " + sourceFile.getPath()
                    + " range " + start + " - " + end + " to " + destFile.getPath() + ".";
            logger.error(message);
            throw new RuntimeException(message, e);

        }
    }

    public static File copyFileRange(File sourceFile, Long start, Long end) {
        String pathToRangeFile = sourceFile.getPath() + "-" + start;
        if(end != null) {
            pathToRangeFile += "-" + end;
        }
        File rangeFile = new File(pathToRangeFile);
        FileUtil.copyFileRange(sourceFile, start, end, rangeFile);
        return rangeFile;
    }
}
