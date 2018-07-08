package resource.manager;

public interface UploadableResource extends Resource {
    /**
     * Is the resource available in online resource store.
     * @return
     */
    boolean isAvailableInResourceStore();

    /**
     * Uploads the resource to resource store bucket.
     */
    void uploadToResourceStore();
}
