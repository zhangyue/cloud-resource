/**
 * Created by Zhang Yue on 5/12/2018
 */
package ffzy.resource.manager;

/**
 * Describes an online resource object with bucket and key.
 */
public class ResourceObject {
    private String bucket;
    private String key;

    public ResourceObject(String bucket, String key) {
        this.bucket = bucket;
        this.key = key;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
