package ffzy.resource;

import ffzy.resource.manager.DownloadableResource;
import ffzy.resource.manager.S3Resource;

/**
 * Image resources used by the tests
 */
public class ImageResource {

    public static DownloadableResource txt_0_0_abc =
            new S3Resource("image", "abc.txt", "txt 0x0 62");
    public static DownloadableResource bmp_230_220_animal =
            new S3Resource("image", "animal.bmp", "bmp 230x220 152294");
    public static DownloadableResource jpg_230_220_animal =
            new S3Resource("image", "animal.jpg", "jpg 230x220 8988");
    public static DownloadableResource jpg_761_1024_dragon_gray_scale =
            new S3Resource("image", "dragon_grayScale.jpg", "jpg 761x1024 88527 gray_scale");
    public static DownloadableResource bmp_700_933_head_shot =
            new S3Resource("image", "headShot.bmp", "bmp 700x933 1959354");
    public static DownloadableResource tif_176_117_group_photo =
            new S3Resource("image", "groupPhoto.tif", "tif 176x117 17644");
    public static DownloadableResource jpg_1024_768_jelly_fish =
            new S3Resource("image", "Jellyfish.jpg", "jpg 1024x768 775702");
    public static DownloadableResource bmp_1024_768_image_bmp =
            new S3Resource("image", "image_bmp.bmp", "bmp 1024x768 2359350");

}
