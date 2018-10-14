package ffzy.resource;

import ffzy.resource.manager.LocalGeneratedResource;
import ffzy.resource.manager.util.FileUtil;

/**
 * Image resources used by the tests
 */
public class VariousSizeLocalResource {

    public static LocalGeneratedResource data_1B =
            new LocalGeneratedResource("local_generated", "1B", 1L, "1B");

    public static LocalGeneratedResource data_1KB =
            new LocalGeneratedResource("local_generated", "1KB", 1 * FileUtil.ByteUnit.KB, "1KB");

    public static LocalGeneratedResource data_1K1B =
            new LocalGeneratedResource("local_generated", "1K1B", 1 * FileUtil.ByteUnit.KB + 1, "1K1B");

    public static LocalGeneratedResource data_4KB =
            new LocalGeneratedResource("local_generated", "4KB", 4 * FileUtil.ByteUnit.KB, "4KB");

    public static LocalGeneratedResource data_4K1B =
            new LocalGeneratedResource("local_generated", "4K1B", 4 * FileUtil.ByteUnit.KB + 1, "4K1B");

    // Size of data chunk written from JSS to DS
    public static LocalGeneratedResource data_64KB =
            new LocalGeneratedResource("local_generated", "64KB", 64 * FileUtil.ByteUnit.KB, "64KB");

    public static LocalGeneratedResource data_64K1B =
            new LocalGeneratedResource("local_generated", "64K1B", 64 * FileUtil.ByteUnit.KB + 1, "64K1B");

    // Maximum size for DS chunk store
    public static LocalGeneratedResource data_1MB =
            new LocalGeneratedResource("local_generated", "1MB", 1 * FileUtil.ByteUnit.MB, "1MB");

    public static LocalGeneratedResource data_1M1B =
            new LocalGeneratedResource("local_generated", "1M1B", 1 * FileUtil.ByteUnit.MB + 1, "1M1B");

    // Slice size of DS
    public static LocalGeneratedResource data_4MB =
            new LocalGeneratedResource("local_generated", "4MB", 4 * FileUtil.ByteUnit.MB, "4MB");

    public static LocalGeneratedResource data_4M1B =
            new LocalGeneratedResource("local_generated", "4M1B", 4 * FileUtil.ByteUnit.MB + 1, "4M1B");

    // Minimum multipart upload part size
    public static LocalGeneratedResource data_5MB =
            new LocalGeneratedResource("local_generated", "5MB", 5 * FileUtil.ByteUnit.MB, "5MB");

    public static LocalGeneratedResource data_6MB =
            new LocalGeneratedResource("local_generated", "6MB", 6 * FileUtil.ByteUnit.MB, "6MB");

    public static LocalGeneratedResource data_10MB =
            new LocalGeneratedResource("local_generated", "10MB", 10 * FileUtil.ByteUnit.MB, "10MB");

    public static LocalGeneratedResource data_11MB =
            new LocalGeneratedResource("local_generated", "11MB", 11 * FileUtil.ByteUnit.MB, "11MB");

    public static LocalGeneratedResource data_33MB =
            new LocalGeneratedResource("local_generated", "33MB", 33 * FileUtil.ByteUnit.MB, "33MB");
    
    public static LocalGeneratedResource data_64MB =
            new LocalGeneratedResource("local_generated", "64MB", 64 * FileUtil.ByteUnit.MB, "64MB");

    public static LocalGeneratedResource data_64M1B =
            new LocalGeneratedResource("local_generated", "64M1B", 64 * FileUtil.ByteUnit.MB + 1, "64M1B");

    public static LocalGeneratedResource data_100MB =
            new LocalGeneratedResource("local_generated", "100MB", 100 * FileUtil.ByteUnit.MB, "100MB");

    public static LocalGeneratedResource data_128M1B =
            new LocalGeneratedResource("local_generated", "128M1B", 128 * FileUtil.ByteUnit.MB + 1, "128M1B");

    public static LocalGeneratedResource data_200MB =
            new LocalGeneratedResource("local_generated", "200MB", 200 * FileUtil.ByteUnit.MB, "200MB");

    public static LocalGeneratedResource data_201MB =
            new LocalGeneratedResource("local_generated", "201MB", 201 * FileUtil.ByteUnit.MB, "201MB");

    public static LocalGeneratedResource data_300MB =
            new LocalGeneratedResource("local_generated", "300MB", 300 * FileUtil.ByteUnit.MB, "300MB");

    public static LocalGeneratedResource data_512MB =
            new LocalGeneratedResource("local_generated", "512MB", 512 * FileUtil.ByteUnit.MB, "512MB");

    public static LocalGeneratedResource data_1GB =
            new LocalGeneratedResource("local_generated", "1GB", 1 * FileUtil.ByteUnit.GB, "1GB");

    public static LocalGeneratedResource data_1G1B =
            new LocalGeneratedResource("local_generated", "1G1B", 1 * FileUtil.ByteUnit.GB + 1, "1G1B");

    /*
    For larger file please consider using LargeFileResource first.
     */
}
