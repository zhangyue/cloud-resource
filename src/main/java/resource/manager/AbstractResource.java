package resource.manager;

import resource.manager.exception.ResourceManagerNotInitializedException;
import resource.manager.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import resource.manager.util.ThreadUtil;

import java.io.File;

public abstract class AbstractResource implements UploadableResource {
    private static Logger logger = LoggerFactory.getLogger(ThreadUtil.getClassName());

    /**
     * Tag of the resource.
     * Also used as the relative path of the resource.
     */
    private String tag;

    /**
     * Name of the resource.
     */
    private String name;

    /**
     * Description of the resource.
     */
    private String description;

    /**
     * Path to local resource directory.
     */
    public static final String PATH_TO_LOCAL_RESOURCE_DIR = "local_resource";

    /**
     * The file object of the local resource directory.
     */
    protected File localResourceTagDir;

    /**
     * Resource manager singleton.
     */
    final protected ResourceManager rm = ResourceManager.getResourceManager();

    /**
     * The resource object, describing an online resource object with bucket and key
     */
    protected ResourceObject resourceObject;

    /**
     * The local resource file.
     */
    protected File resourceFile;

    public AbstractResource(String tag, String name, String description) {
        this.tag = tag;
        this.name = name;
        this.description = description;

        if(rm == null) {
            logger.error("Resource manager is not initialized.");
            throw new ResourceManagerNotInitializedException();
        }
        resourceObject = new ResourceObject(rm.getResourceStaging().getBucketName(), tag + "/" + name);

        localResourceTagDir = new File(PATH_TO_LOCAL_RESOURCE_DIR + File.separator + tag);
        localResourceTagDir.mkdirs();
        resourceFile = new File(localResourceTagDir, name);
    }

    /**
     * Gets the tag of the resource.
     * The tag is also used as the relative path of the resource.
     * @return
     */
    public String getTag() {
        return tag;
    }

    /**
     * Gets the name of the resource.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the resource file description.
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the name of the bucket where the resource is uploaded.
     * @return
     */
    public ResourceObject getResourceObject() {
        if(isAvailableInResourceBucket()) {
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

        return resourceObject;
    }

    /**
     * Gets the file in local.
     * @return
     */
    public File getResourceFile() {
        if(resourceFile.exists()) {
            return resourceFile;
        }

        if(isAvailableInResourceBucket()) {
            downloadFromResourceBucket();
        } else if(isAvailableInResourceStore()) {
            downloadFromResourceStore();
            uploadToResourceBucket();
        } else {
            throw new ResourceNotFoundException(this);
        }

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

    /**
     * Downloads the resource from resource bucket to local resource directory.
     */
    public abstract void uploadToResourceBucket();

    /**
     * Uploads the resource to resource bucket.
     */
    public abstract void downloadFromResourceBucket();
}
