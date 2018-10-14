package ffzy.resource;

import ffzy.resource.manager.S3VideoResource;
import ffzy.resource.manager.util.VideoUtil;

/**
 * Video resources used by the tests.
 */
public class VideoResource {

    /*
    Small and medium
     */
    public static S3VideoResource video_mp4_h264_720p_medium = new S3VideoResource(
            "video", "big_buck_bunny_720p_50mb.mp4",
            "mp4 h264 1280x720 281.880000 51M",
            VideoUtil.PACKAGING_MP4, VideoUtil.CODING_H264, 281.88, 1280, 720
    );
    public static S3VideoResource video_mp4_h264_720p_small = new S3VideoResource(
            "video", "big_buck_bunny_720p_5mb.mp4",
            "mp4 h264 1280x720 29.568000 6M",
            VideoUtil.PACKAGING_MP4, VideoUtil.CODING_H264, 29.568, 1280, 720
    );
    public static S3VideoResource video_mp4_h264_360p_small =
            new S3VideoResource("video", "Geri_s_Game.mp4", "mp4 h264 640x360 293.200000 17M");
    public static S3VideoResource video_mp4_hevc_2k_small
            = new S3VideoResource("video", "BigBuckBunny_2000hevc.mp4", "mp4 hevc 3840x2160 10.000000 3M");
    public static S3VideoResource video_mp4_mpeg4_360p_small
            = new S3VideoResource("video", "big_buck_bunny_360p_10mb.mp4", "mp4 mpeg4 640x368 68.720000 11M");
    public static S3VideoResource video_3gp_h263_144p_small = new S3VideoResource(
            "video", "big_buck_bunny_144p_1mb.3gp",
            "3gp h263 176x144 40.666667 1M",
            VideoUtil.PACKAGING_3GP, VideoUtil.CODING_H263, 40.666667, 176, 144
    );
    public static S3VideoResource video_mkv_h264_360p_small
            = new S3VideoResource("video", "big_buck_bunny_360p_5mb.mkv", "mkv h264 640x360 42.608000 6M");
    public static S3VideoResource video_mkv_hevc_960p_small
            = new S3VideoResource("video", "video-h265.mkv", "mkv hevc 1920x960 16.160000 6M");
    public static S3VideoResource video_mkv_mpeg4_720p_small
            = new S3VideoResource("video", "big_buck_bunny_720p_10mb.mkv", "mkv mpeg4 1280x720 56.200000 11M");
    public static S3VideoResource video_mpg_mpeg2_p_small
            = new S3VideoResource("video", "katamari-star8-10s.mpeg", "mpg mpeg2video 1280x720 9.879000 11M");
    public static S3VideoResource video_divx_mpeg4_1080p_medium
            = new S3VideoResource("video", "Micayala_DivX1080p_ASP.divx", "divx mpeg4 1920x768 137.095292 20M");
    public static S3VideoResource video_flv_flv1_720p_small
            = new S3VideoResource("video", "big_buck_bunny_720p_10mb.flv", "flv flv1 1280x720 54.082000 11M");
    public static S3VideoResource video_flv_vp6f_480p_small
            = new S3VideoResource("video", "sampleClip_06.flv", "flv vp6f 800x344 111.920000 11M");
    public static S3VideoResource video_avi_mpeg4_416p_medium = new S3VideoResource(
            "video", "Flowers_and_Trees_1932.avi",
            "avi mpeg4 544x416 469.427761 77M",
            VideoUtil.PACKAGING_AVI, VideoUtil.CODING_MPEG4, 469.427761, 544, 416
    );
    public static S3VideoResource video_rm_rv40_360p_small
            = new S3VideoResource("video", "hanma.rm", "rm rv40 448x336 159.752000 7M");
    public static S3VideoResource video_rm_rv30_240p_small
            = new S3VideoResource("video", "video-anthem-rtr-october_2003-350kbps.rm", "rm rv30 320x240 70.541000 3M");
    public static S3VideoResource video_rm_rv10_360p_medium
            = new S3VideoResource("video", "dzq.rm", "rm rv10 640x368 273.407000 23M");
    public static S3VideoResource video_rmvb_rv40_360p_medium
            = new S3VideoResource("video", "dzq.rmvb", "rmvb rv40 640x368 274.923000 21M");
    public static S3VideoResource video_rmvb_rv40_720p_medium
            = new S3VideoResource(
            "video", "hanma.rmvb",
            "rmvb rv40 1280x720 159.752000 21M",
            VideoUtil.PACKAGING_RMVB, VideoUtil.CODING_RV40, 159.752, 1280, 720
    );
    public static S3VideoResource video_wmv_wmv2_480p_small = new S3VideoResource(
            "video", "sampleClip_05.wmv",
            "wmv wmv2 640x480 11.912000 1M",
            VideoUtil.PACKAGING_WMV, VideoUtil.CODING_WMV2, 11.912, 640, 480
    );
    public static S3VideoResource video_wmv_wmv3_360p_small
            = new S3VideoResource("video", "hanma.wmv", "wmv wmv3 448x336 159.744000 14M");
    public static S3VideoResource video_wmv_vc1_720p_medium
            = new S3VideoResource("video", "Dracula 480p.wmv", "wmv vc1 1440x480 52.736000 36M");
    public static S3VideoResource video_mov_mpeg4_1080p_medium
            = new S3VideoResource("video", "hanma.mov", "mov mpeg4 1920x1080 159.766000 38M");
    public static S3VideoResource video_mov_svq3_480p_small
            = new S3VideoResource("video", "katamari-star8-10s.mov", "mov svq3 640x480 10.010000 27M");
    public static S3VideoResource video_asf_msmpeg4v3_360p_small
            = new S3VideoResource("video", "hanma.asf", "asf msmpeg4v3 448x336 159.791000 11M");
    public static S3VideoResource video_f4v_h264_360p_small
            = new S3VideoResource("video", "hanma.f4v", "f4v h264 448x336 159.634000 14M");
    public static S3VideoResource video_ts_h264_720p_medium
            = new S3VideoResource("video", "hanma.ts", "ts h264 1280x720 159.776000 208M");
    public static S3VideoResource video_webm_vp8_360p_small
            = new S3VideoResource("video", "small.webm", "webm vp8 560x320 5.568000 1M");
    public static S3VideoResource video_vob_mpeg2video_1080p_small
            = new S3VideoResource("video", "jellyfish-25-mbps-hd-hevc.vob", "vob mpeg2video 1920x1080 30.030000 14M");
//    public static S3VideoResource video_3gp_h263_144p_small2
//            = new S3VideoResource("video", "video.3gp", "3gp h263 176x144 40.667000 1M");
    public static S3VideoResource m3u8_wildlife
            = new S3VideoResource("video", "wildlief.m3u8", "wildlief m3u8");

    /*
    Large
     */
    public static S3VideoResource video_mp4_h264_4k_large = new S3VideoResource(
            "video", "COSTA_RICA_4K.mp4",
            "mp4 h264 3840x2160 308.570000 579M",
            VideoUtil.PACKAGING_MP4, VideoUtil.CODING_H264, 308.570000, 3840, 2160
    );

    /*
    Extreme large
     */
    public static S3VideoResource video_ts_hevc_4k_extreme_large = new S3VideoResource(
            "video", "DFL.Supercup.2014.BVB.vs.FCB.ts",
            "ts hevc 3840x2160 3394.009989 13G",
            VideoUtil.PACKAGING_TS, VideoUtil.CODING_HEVC, 3394.009989, 3840, 2160
    );

}
