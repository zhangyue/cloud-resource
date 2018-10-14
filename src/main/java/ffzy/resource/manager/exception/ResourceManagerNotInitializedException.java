package ffzy.resource.manager.exception;

/**
 * Created by Zhang Yue on 5/13/2018
 */
public class ResourceManagerNotInitializedException extends RuntimeException {
    public ResourceManagerNotInitializedException() {
        super("Resource manager not initialized.");
    }
}
