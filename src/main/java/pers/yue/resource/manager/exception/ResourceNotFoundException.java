package pers.yue.resource.manager.exception;

import pers.yue.resource.manager.Resource;

/**
 * Created by Zhang Yue on 5/12/2018
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Resource resource) {
        super("Resource not found: " + resource.getTag() + "/" + resource.getName());
    }

    public ResourceNotFoundException(Resource resource, Throwable cause) {
        super("Resource not found: " + resource.getTag() + "/" + resource.getName(), cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
