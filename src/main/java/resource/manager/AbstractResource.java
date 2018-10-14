package resource.manager;

import resource.manager.exception.ResourceManagerNotInitializedException;
import resource.manager.exception.ResourceNotFoundException;
import resource.manager.util.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public abstract class AbstractResource implements Resource {
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
     * Is the resource already available in the resource bucket, considering the time stamp (last-modified field).
     */
    protected boolean isAlreadyAvailable = false;

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
     * Gets the resource file in local, guaranteeing the existence of the resource file.
     * @return
     */
    public File getResourceFile() {
        return getResourceFile(true);
    }

    /**
     * Gets the resource file in local.
     * @param guaranteeExistence Whether guarantees the existence of the resource file.
     *                           If true, it make sure the resource file exists in local. If not, it tries to retrieve
     *                           the resource file from resource staging bucket or (if not available in resource
     *                           staging bucket) resource store bucket.
     *                           If false, it just returns the file object without checking the existence of the file.
     * @return
     */
    public abstract File getResourceFile(boolean guaranteeExistence);
}
