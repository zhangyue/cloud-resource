/**
 * Created by Zhang Yue on 5/12/2018
 */
package ffzy.resource.manager;

/**
 * Resource object holder, where resource objects are stored.
 */
public class ResourceObjectHolder {
    private String pin, userId;
    private String accessKey, secretKey;
    private String endpoint, bucketName;

    private boolean pathStyleEnabled;

    public ResourceObjectHolder(
            String pin, String userId,
            String accessKey, String secretKey,
            String endpoint, String bucketName
    ) {
        init(pin, userId, accessKey, secretKey,
                endpoint, bucketName, true);
    }

    public ResourceObjectHolder(
            String pin, String userId,
            String accessKey, String secretKey,
            String endpoint, String bucketName,
            boolean pathStyleEnabled
    ) {
        init(pin, userId, accessKey, secretKey,
                endpoint, bucketName, pathStyleEnabled);
    }

    public void init(
            String pin, String userId,
            String accessKey, String secretKey,
            String endpoint, String bucketName,
            boolean pathStyleEnabled
    ) {
        this.pin = pin;
        this.userId = userId;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.endpoint = endpoint;
        this.bucketName = bucketName;
        this.pathStyleEnabled = pathStyleEnabled;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public ResourceObjectHolder withResourcePin(String resourcePin) {
        this.pin = resourcePin;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ResourceObjectHolder withResourceUserId(String resourceUserId) {
        this.userId = resourceUserId;
        return this;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public ResourceObjectHolder withResourceAccessKey(String resourceAccessKey) {
        this.accessKey = resourceAccessKey;
        return this;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public ResourceObjectHolder withResourceSecretKey(String resourceSecretKey) {
        this.secretKey = resourceSecretKey;
        return this;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public ResourceObjectHolder withResourceEndpoint(String resourceEndpoint) {
        this.endpoint = resourceEndpoint;
        return this;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public ResourceObjectHolder withResourceBucketName(String resourceBucketName) {
        this.bucketName = resourceBucketName;
        return this;
    }

    public boolean isPathStyleEnabled() {
        return pathStyleEnabled;
    }

    public void setPathStyleEnabled(boolean pathStyleEnabled) {
        this.pathStyleEnabled = pathStyleEnabled;
    }

    public ResourceObjectHolder withPathStyleEnabled(boolean pathStyleEnabled) {
        this.pathStyleEnabled = pathStyleEnabled;
        return this;
    }
}
