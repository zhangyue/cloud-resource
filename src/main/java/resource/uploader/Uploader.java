/**
 * Created by zhangyue182 on 05/14/2018
 */

package resource.uploader;

import resource.*;
import resource.manager.ResourceManager;
import resource.manager.UploadableResource;
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
        for(UploadableResource resource : ResourceManager.getAllResources(ImageResource.class)) {
            if(resource.isAvailableInResourceStore()) {
                continue;
            }
            resource.uploadToResourceStore();
        }
    }

    @Test
    public void uploadVideoResource() {
        for(UploadableResource resource : ResourceManager.getAllResources(VideoResource.class)) {
            if(resource.isAvailableInResourceStore()) {
                continue;
            }
            resource.uploadToResourceStore();
        }
    }

    @Test
    public void uploadLargeFileResource() {
        for(UploadableResource resource : ResourceManager.getAllResources(LargeFileResource.class)) {
            if(resource.isAvailableInResourceStore()) {
                continue;
            }
            resource.uploadToResourceStore();
        }
    }
}
