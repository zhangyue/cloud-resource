package pers.yue.resource.manager;

import pers.yue.resource.manager.util.FileUtil;

import java.io.File;

public class LocalGeneratedResource extends AbstractResource implements Resource {

    protected long size;

    public LocalGeneratedResource(String tag, String name, long size, String description) {
        super(tag, name, description);
        this.size = size;
    }

    /**
     * Gets the resource file in local.
     * @param guaranteeExistence Whether guarantees the existence of the resource file.
     *                           If true, it makes sure the resource file exists in local. If not, it generates the file
     *                           and returns the file object.
     *                           If false, it just returns the file object without checking the existence of the file.
     * @return
     */
    public File getResourceFile(boolean guaranteeExistence) {
        if(!guaranteeExistence) {
            return resourceFile;
        }

        if(resourceFile.exists() && resourceFile.length() == size) {
            return resourceFile;
        }

        FileUtil.generateFileContent(resourceFile, size);

        return resourceFile;
    }
}
