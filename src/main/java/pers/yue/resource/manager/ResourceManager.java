/**
 * Created by Zhang Yue on 5/12/2018
 */
package pers.yue.resource.manager;

import pers.yue.resource.manager.util.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines the resource store and resource staging location.
 */
public class ResourceManager {
    private static Logger logger = LoggerFactory.getLogger(ThreadUtil.getClassName());

    /**
     * Online resource store.
     */
    private ResourceObjectHolder resourceStore;

    /**
     * Resource staging.
     */
    private ResourceObjectHolder resourceStaging;

    /**
     * Resource manager singleton.
     */
    private static ResourceManager resourceManager;

    private ResourceManager(ResourceObjectHolder resourceStore, ResourceObjectHolder resourceStaging) {
        this.resourceStore = resourceStore;
        this.resourceStaging = resourceStaging;
    }

    private ResourceManager(
            String resourceStorePin, String resourceStoreUserId,
            String resourceStoreAccessKey, String resourceStoreSecretKey,
            String resourceStoreEndpoint,
            String resourceStoreBucketName,
            String resourcePin, String resourceUserId,
            String resourceAccessKey, String resourceSecretKey,
            String resourceEndpoint,
            String resourceBucketName,
            boolean pathStyleEnabled
    ) {
        this.resourceStore = new ResourceObjectHolder(
                resourceStorePin, resourceStoreUserId,
                resourceStoreAccessKey, resourceStoreSecretKey,
                resourceStoreEndpoint, resourceStoreBucketName,
                pathStyleEnabled
        );

        this.resourceStaging = new ResourceObjectHolder(
                resourcePin, resourceUserId,
                resourceAccessKey, resourceSecretKey,
                resourceEndpoint, resourceBucketName,
                pathStyleEnabled
        );
    }

    /**
     * Initializes the resource manager singleton.
     * @param resourceStorePin
     * @param resourceStoreUserId
     * @param resourceStoreAccessKey
     * @param resourceStoreSecretKey
     * @param resourceStoreEndpoint
     * @param resourceStoreBucketName
     * @param resourcePin
     * @param resourceUserId
     * @param resourceAccessKey
     * @param resourceSecretKey
     * @param resourceEndpoint
     * @param resourceBucketName
     * @param pathStyleEnabled
     * @return
     */
    public static ResourceManager initResourceManager(
            String resourceStorePin, String resourceStoreUserId,
            String resourceStoreAccessKey, String resourceStoreSecretKey,
            String resourceStoreEndpoint, String resourceStoreBucketName,
            String resourcePin, String resourceUserId,
            String resourceAccessKey, String resourceSecretKey,
            String resourceEndpoint, String resourceBucketName,
            boolean pathStyleEnabled
    ) {
        resourceManager = new ResourceManager(
                new ResourceObjectHolder(
                        resourceStorePin, resourceStoreUserId,
                        resourceStoreAccessKey, resourceStoreSecretKey,
                        resourceStoreEndpoint, resourceStoreBucketName,
                        pathStyleEnabled
                ),
                new ResourceObjectHolder(
                        resourcePin, resourceUserId,
                        resourceAccessKey, resourceSecretKey,
                        resourceEndpoint, resourceBucketName,
                        pathStyleEnabled
                )
        );
        return resourceManager;
    }

    /**
     * Gets the resource manager singleton.
     * @return
     */
    public static ResourceManager getResourceManager() {
        return resourceManager;
    }

    public ResourceObjectHolder getResourceStore() {
        return resourceStore;
    }

    public void setResourceStore(ResourceObjectHolder resourceStore) {
        this.resourceStore = resourceStore;
    }

    public ResourceObjectHolder getResourceStaging() {
        return resourceStaging;
    }

    public void setResourceStaging(ResourceObjectHolder resourceStaging) {
        this.resourceStaging = resourceStaging;
    }

    public static List<UploadableResource> getAllResources(Class clazz) {
        Field[] fields = clazz.getFields();
        List<UploadableResource> resources = new ArrayList<>();

        try {
            for (Field field : fields) {
                resources.add((UploadableResource) (field.get(clazz)));
            }
        } catch (IllegalAccessException e) {
            logger.error("{} when traversing member variables of class {}", e.getClass().getSimpleName(), clazz);
            throw new RuntimeException(e.getClass().getSimpleName() + " when traversing member variables of class "
                    + e.getClass().getSimpleName(), e);
        }

        return resources;
    }
}
