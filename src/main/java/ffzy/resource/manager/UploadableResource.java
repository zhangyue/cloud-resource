package ffzy.resource.manager;

public interface UploadableResource extends DownloadableResource {
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
