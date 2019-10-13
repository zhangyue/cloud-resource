package pers.yue.resource;

import pers.yue.resource.manager.DownloadableResource;
import pers.yue.resource.manager.GZippedS3Resource;
import pers.yue.resource.manager.S3Resource;

/**
 * Image resources used by the tests
 */
public class LargeFileResource {

    public static DownloadableResource file_1GB_20180517 =
            new GZippedS3Resource("large_file", "1GB_20180517.gz", "1GB_20180517");
    public static DownloadableResource file_1GB_20180611 =
            new GZippedS3Resource("large_file", "1GB_20180611.gz", "1GB_20180611");
    public static DownloadableResource file_1GB_20180612 =
            new GZippedS3Resource("large_file", "1GB_20180612.gz", "1GB_20180612");
    public static DownloadableResource file_5GB_20180517 =
            new GZippedS3Resource("large_file", "5GB_20180517.gz", "5GB_20180517");
    public static DownloadableResource file_5GB_20180611 =
            new GZippedS3Resource("large_file", "5GB_20180611.gz", "5GB_20180611");
    public static DownloadableResource file_5GB_20180612 =
            new GZippedS3Resource("large_file", "5GB_20180612.gz", "5GB_20180612");
    public static DownloadableResource file_5GB_20180703 =
            new S3Resource("large_file", "5GB_20180703", "5GB_20170703");
}
