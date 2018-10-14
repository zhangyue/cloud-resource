/**
 * Created by zhangyue182 on 05/14/2018
 */

package ffzy.resource.uploader;

import ffzy.resource.ImageResource;
import ffzy.resource.LargeFileResource;
import ffzy.resource.VideoResource;
import resource.*;
import ffzy.resource.manager.ResourceManager;
import ffzy.resource.manager.UploadableResource;
import org.testng.annotations.Test;

/**
 * Upload all resources to online resource store.
 * Usage:
 *      1. Add description of the new resources into a resource enum, e.g. ImageResource, VideoResource.
 *      2. Place the resource files into "${project_home}/local_resource/${tag}" directory.
 *          ("local_resource" is tracked by constant PATH_TO_LOCAL_RESOURCE_DIR)
 *      3. Run the corresponding @Test method below (or create a new method if it is a new resource enum.
 */
public class Uploader {

    @Test
    public void uploadAllImageResource() {
        uploadResource(ImageResource.class);
    }

    @Test
    public void uploadVideoResource() {
        uploadResource(VideoResource.class);
    }

    @Test
    public void uploadLargeFileResource() {
        uploadResource(LargeFileResource.class);
    }

    private void uploadResource(Class clazz) {
        for(UploadableResource resource : ResourceManager.getAllResources(clazz)) {
            if(!resource.getResourceFile(false).exists()) {
                continue;
            }
            if(resource.isAvailableInResourceStore()) {
                continue;
            }
            resource.uploadToResourceStore();
        }
    }
}
