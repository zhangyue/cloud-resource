package pers.yue.resource.manager;

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
     * Gets the resource file in local, guaranteeing the existence of the resource file.
     * @return
     */
    File getResourceFile();

    /**
     * Gets the resource file in local.
     * @param guaranteeExistence Whether guarantees the existence of the resource file.
     *                           If true, it make sure the resource file exists in local. If not, it tries to retrieve
     *                           the resource file from resource staging bucket or (if not available in resource
     *                           staging bucket) resource store bucket, or generate the resource from scratch (depending
     *                           on the actual implementation).
     *                           If false, it just returns the file object without checking the existence of the file.
     * @return
     */
    File getResourceFile(boolean guaranteeExistence);
}
