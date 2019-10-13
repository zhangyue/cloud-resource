package pers.yue.resource.manager.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(ThreadUtil.getClassName());

    public class ByteUnit {
        public static final long KB = 1024L;
        public static final long MB = 1048576L;
        public static final long GB = 1073741824L;
        public static final long TB = 1099511627776L;
    }

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

    /**
     * Create file with pretty generated content.
     * @param file
     * @param size
     * @return
     */
    public static File generateFileContent(File file, long size) {
        logger.info("Generate content for file {} length {}.", file, size);

        int maxLineLength = 512;

        try (RandomAccessFile r = new RandomAccessFile(file, "rw")) {

            r.setLength(size);

            long generatedSize = 0;
            int lineNumber = 0;
            byte[] line;

            while(generatedSize < size) {
                String timeString = getTimeString();
                long remainingLong = size - generatedSize;
                line = generateLine(lineNumber++, timeString, maxLineLength, remainingLong);
                generatedSize += line.length;
                r.write(line);
            }

        } catch (IOException e) {
            String message = e.getClass().getSimpleName() + " when generate content for file " + file.getPath() + ".";
            logger.error(message);
            throw new RuntimeException(message, e);
        }

        return file;
    }

    private static String getTimeString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss HHH");
        return simpleDateFormat.format(new Date()) + " created by OSS QE.";
    }

    private static byte[] generateLine(long lineNumber, String fixedStr, int fixedMaxLength, long remainingLength) {
        byte[] returnBytes;

//        logger.debug("Fixed max line length: {}.", fixedMaxLength);
//        logger.debug("Remaining length: {}.", remainingLength);

        if(fixedMaxLength < 1) {
            logger.warn("Max line length is less than 1. Nothing to generate.");
            return new byte[0];
        }

        int length;
        String headStr = String.format("%012d", lineNumber) + " " + fixedStr;
//        logger.debug("Head string length: {}.", headStr.length());

        if(remainingLength < headStr.length()) {
            length = (int)remainingLength;
            returnBytes = new byte[length];
            System.arraycopy(headStr.getBytes(), 0, returnBytes, 0, length - 1);
            returnBytes[length - 1] = '\n';

            return returnBytes;
        }

        if(remainingLength < fixedMaxLength) {
            length = (int)remainingLength;
        } else {
            length = headStr.length() + new Random(DateUtil.getCurrentTime())
                    .nextInt(fixedMaxLength - headStr.length() + 1);
        }

//        logger.debug("Random line length: {}.", length);

        returnBytes = new byte[length];
        System.arraycopy(headStr.getBytes(), 0, returnBytes, 0, headStr.getBytes().length);

        for (int i = headStr.getBytes().length; i < length; i++) {
            if (i == length - 1) {
                returnBytes[i] = '\n';
            } else if (i == headStr.getBytes().length) {
                returnBytes[i] = ' ';
            } else {
                returnBytes[i] = '*';
            }
        }

        return returnBytes;
    }
}
