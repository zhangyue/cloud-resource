package ffzy.resource.manager;

public interface DownloadableResource extends Resource {

    /**
     * Gets the resource bucket name.
     * @return
     */
    ResourceObject getResourceObject();
}
