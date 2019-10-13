package pers.yue.resource.manager;

import pers.yue.resource.manager.exception.ResourceNotFoundException;
import pers.yue.resource.manager.util.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public abstract class AbstractOnlineResource extends AbstractResource implements UploadableResource {
    private static Logger logger = LoggerFactory.getLogger(ThreadUtil.getClassName());

    public AbstractOnlineResource(String tag, String name, String description) {
        super(tag, name, description);
    }

    /**
     * Gets the name of the bucket where the resource is uploaded.
     * @return
     */
    public ResourceObject getResourceObject() {
        if(isAlreadyAvailable) {
            return resourceObject;
        }

        if(isAvailableInResourceBucket() && !isResourceBucketExpired()) {
            isAlreadyAvailable = true;
            return resourceObject;
        }

        if(!resourceFile.exists()) {
            if(isAvailableInResourceStore()) {
                downloadFromResourceStore();
            } else {
                throw new ResourceNotFoundException(this);
            }
        }

        uploadToResourceBucket();

        isAlreadyAvailable = true;
        return resourceObject;
    }

    /**
     * Gets the resource file in local.
     * @param guaranteeExistence Whether guarantees the existence of the resource file.
     *                           If true, it makes sure the resource file exists in local. If not, it tries to retrieve
     *                           the resource file from resource staging bucket or (if not available in resource
     *                           staging bucket) resource store bucket.
     *                           If false, it just returns the file object without checking the existence of the file.
     * @return
     */
    public File getResourceFile(boolean guaranteeExistence) {
        if(!guaranteeExistence) {
            return resourceFile;
        }

        if(resourceFile.exists()) {
            return resourceFile;
        }

        if(isAvailableInResourceBucket() && !isResourceBucketExpired()) {
            downloadFromResourceBucket();
        } else if(isAvailableInResourceStore()) {
            downloadFromResourceStore();
            uploadToResourceBucket();
        } else {
            throw new ResourceNotFoundException(this);
        }

        isAlreadyAvailable = true;
        return resourceFile;
    }

    /**
     * Is the resource available in online resource store.
     * @return
     */
    public abstract boolean isAvailableInResourceStore();

    /**
     * Uploads the resource from resource store to resource store bucket.
     */
    public abstract void uploadToResourceStore();

    /**
     * Downloads the resource to local resource directory.
     */
    public abstract void downloadFromResourceStore();

    /**
     * Is the resource available in resource bucket.
     * @return
     */
    public abstract boolean isAvailableInResourceBucket();

    public abstract boolean isResourceBucketExpired();

    /**
     * Downloads the resource from resource bucket to local resource directory.
     */
    public abstract void uploadToResourceBucket();

    /**
     * Uploads the resource to resource bucket.
     */
    public abstract void downloadFromResourceBucket();
}
