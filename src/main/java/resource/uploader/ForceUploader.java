/**
 * Created by zhangyue182 on 05/14/2018
 */

package resource.uploader;

import resource.*;
import resource.manager.ResourceManager;
import resource.manager.UploadableResource;
import org.testng.annotations.Test;

/**
 * Force upload all resources to online resource store (even the resource already exist.
 * Usage:
 *      1. Add description of the new resources into a resource enum, e.g. ImageResource, VideoResource.
 *      2. Place the resource files into "${project_home}/local_resource/${tag}" directory.
 *          ("local_resource" is tracked by constant PATH_TO_LOCAL_RESOURCE_DIR)
 *      3. Run the corresponding @Test method below (or create a new method if it is a new resource enum.
 */
public class ForceUploader {

    @Test
    public void uploadAllImageResource() {
        forceUploadResource(ImageResource.class);
    }

    @Test
    public void uploadVideoResource() {
        forceUploadResource(VideoResource.class);
    }

    @Test
    public void uploadLargeFileResource() {
        forceUploadResource(LargeFileResource.class);
    }

    private void forceUploadResource(Class clazz) {
        for(UploadableResource resource : ResourceManager.getAllResources(clazz)) {
            if(!resource.getResourceFile(false).exists()) {
                continue;
            }
            resource.uploadToResourceStore();
        }
    }
}
