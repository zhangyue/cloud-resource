package resource.manager;

import java.io.File;

public interface Resource {
    /**
     * Gets the tag of the resource.
     * The tag is also used as the relative path of the resource.
     * @return
     */
    String getTag();

    /**
     * Gets the name of the resource.
     * @return
     */
    String getName();

    /**
     * Gets the resource file description.
     * @return
     */
    String getDescription();

    /**
     * Gets the resource bucket name.
     * @return
     */
    ResourceObject getResourceObject();

    /**
     * Gets the file in local.
     * @return
     */
    File getResourceFile();
}
