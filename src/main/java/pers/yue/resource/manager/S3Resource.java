package pers.yue.resource.manager;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.*;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import pers.yue.resource.manager.util.FileUtil;
import pers.yue.resource.manager.util.LogUtil;
import pers.yue.resource.manager.util.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by Zhang Yue on 5/12/2018
 */
public class S3Resource extends AbstractOnlineResource implements UploadableResource {
    private Logger logger = LoggerFactory.getLogger(ThreadUtil.getClassName());

    private static S3ResourceClient resourceStoreHelper;
    private static S3ResourceClient s3Helper;

    public S3Resource(String tag, String name, String description) {
        super(tag, name, description);

        ResourceObjectHolder store = rm.getResourceStore();
        resourceStoreHelper = new S3ResourceClient(
                store.getPin(), store.getUserId(),
                store.getAccessKey(), store.getSecretKey(),
                store.getEndpoint(),
                store.isPathStyleEnabled()
        );
        ResourceObjectHolder staging = rm.getResourceStaging();
        s3Helper = new S3ResourceClient(
                staging.getPin(), staging.getUserId(),
                staging.getAccessKey(), staging.getSecretKey(),
                staging.getEndpoint(),
                staging.isPathStyleEnabled()
        );
    }

    public S3ResourceClient getResourceStoreHelper() {
        return resourceStoreHelper;
    }

    public S3ResourceClient getS3Helper() {
        return s3Helper;
    }

    /**
     * Is the resource available in resource store.
     * @return
     */
    public boolean isAvailableInResourceStore() {
        return resourceStoreHelper.doesObjectExist(rm.getResourceStore().getBucketName(), resourceObject.getKey());
    }

    /**
     * Uploads the resource from resource store to resource store bucket.
     */
    public void uploadToResourceStore() {
        resourceStoreHelper.putObject(rm.getResourceStore().getBucketName(), resourceObject.getKey(), resourceFile);
        resourceStoreHelper.headObject(rm.getResourceStore().getBucketName(), resourceObject.getKey());
    }

    /**
     * Downloads the resource to local resource directory.
     */
    public void downloadFromResourceStore(){
        S3Object s3Object = resourceStoreHelper.getObject(rm.getResourceStore().getBucketName(), resourceObject.getKey());
        FileUtil.writeToFile(s3Object.getObjectContent(), resourceFile);
    }

    /**
     * Is the resource available in resource bucket.
     * @return
     */
    public boolean isAvailableInResourceBucket(){
        return s3Helper.doesObjectExist(rm.getResourceStaging().getBucketName(), resourceObject.getKey());
    }

    /**
     * Is the resource in resource staging bucket expired (resource store has newer last-modified.
     * @return
     */
    public boolean isResourceBucketExpired() {
        ObjectMetadata mdStaging = s3Helper.headObject(rm.getResourceStaging().getBucketName(), resourceObject.getKey());
        ObjectMetadata mdStore = resourceStoreHelper.headObject(rm.getResourceStore().getBucketName(), resourceObject.getKey());

        return mdStaging.getLastModified().getTime() < mdStore.getLastModified().getTime();
    }

    /**
     * Downloads the resource from resource bucket to local resource directory.
     */
    public void uploadToResourceBucket(){
        String bucketName = rm.getResourceStaging().getBucketName();
        /** TODO OSS-1110
        if(!s3Helper.doesBucketExistV2(bucketName)) {
         */
        if(!s3Helper.doesBucketExist(bucketName)) { /** TODO OSS-1110 */
            s3Helper.createBucket(bucketName);
        }
        s3Helper.putObject(bucketName, resourceObject.getKey(), resourceFile);
    }

    /**
     * Uploads the resource to resource bucket.
     */
    public void downloadFromResourceBucket(){
        S3Object s3Object = s3Helper.getObject(rm.getResourceStaging().getBucketName(), resourceObject.getKey());
        FileUtil.writeToFile(s3Object.getObjectContent(), resourceFile);
    }

    public class S3ResourceClient {
        private AmazonS3 s3Client;

        protected String pin, userId;
        protected String accessKeyId, secretAccessKey;
        protected String endpoint, region;
        protected boolean pathStyleEnabled;

        public S3ResourceClient(
                String pin, String userId,
                String accessKeyId, String secretAccessKey,
                String endpoint, boolean pathStyleEnabled
        ) {
            this.pin = pin;
            this.userId = userId;
            this.accessKeyId = accessKeyId;
            this.secretAccessKey = secretAccessKey;
            this.endpoint = endpoint;
            this.region = resolveRegion(endpoint);
            this.pathStyleEnabled = pathStyleEnabled;

            init();
        }

        private void init() {
            final AWSCredentials awsCredentials = new BasicAWSCredentials(getAccessKeyId(), getSecretAccessKey());

            AwsClientBuilder.EndpointConfiguration endpointConfig =
                    new AwsClientBuilder.EndpointConfiguration(endpoint, region);

            ClientConfiguration config = new ClientConfiguration();
            config.setProtocol(Protocol.HTTP);

            AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);

            AmazonS3ClientBuilder builder = AmazonS3Client.builder()
                    .withEndpointConfiguration(endpointConfig)
                    .withClientConfiguration(config)
                    .withCredentials(awsCredentialsProvider)
                    .withPathStyleAccessEnabled(pathStyleEnabled)
                    ;

            if(awsCredentials instanceof AnonymousAWSCredentials) {
                builder.withPathStyleAccessEnabled(true);
            }

            builder.disableChunkedEncoding();

            s3Client = builder.build();
        }

        private String resolveRegion(final String endpoint) {
            String rawRegion = endpoint.split("\\.")[1];
            String region = rawRegion;
            if(rawRegion.endsWith("-gray")) {
                region = rawRegion.replace("-gray", "");
            } else if(rawRegion.endsWith("-stag")) {
                region = rawRegion.replace("-stag", "-1");
            }
            return region;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public String getPin() {
            return pin;
        }

        public String getUserId() {
            return userId;
        }

        public String getAccessKeyId() {
            return accessKeyId;
        }

        public String getSecretAccessKey() {
            return secretAccessKey;
        }

        public boolean doesBucketExist(String bucketName) {
            printOperation("DoesBucketExist", getEndpoint(), getPin(), getUserId(), getAccessKeyId(), bucketName);

            boolean doesBucketExist = s3Client.doesBucketExist(bucketName);

            LogUtil.printResult("Does Bucket Exist", String.valueOf(doesBucketExist));

            return doesBucketExist;
        }

        public Bucket createBucket(String bucketName) {
            printOperation("CreateBucket", getEndpoint(), getPin(), getUserId(), getAccessKeyId(), bucketName);

            Bucket bucket = s3Client.createBucket(new CreateBucketRequest(bucketName));

            return bucket;
        }

        public boolean doesObjectExist(String bucketName, String key) {
            printOperation("DoesObjectExist",
                    getEndpoint(), getPin(), getUserId() , getAccessKeyId(), bucketName, key);

            boolean doesObjectExist = s3Client.doesObjectExist(bucketName, key);

            logger.info("= DoesObjectExist: {}", doesObjectExist);
            return doesObjectExist;
        }

        public PutObjectResult putObject(String bucketName, String key, File file) {
            printOperation("PutObject",
                    getEndpoint(), getPin(), getUserId(), getAccessKeyId(), bucketName, key);
            logger.info("File: {}", file.getName());

            return  s3Client.putObject(bucketName, key, file);
        }

        public S3Object getObject(String bucketName, String key) {
            printOperation("GetObject",
                    getEndpoint(), getPin(), getUserId() , getAccessKeyId(), bucketName, key);

            S3Object s3Object = s3Client.getObject(bucketName, key);
            printObjectSummary(s3Object);

            return s3Object;
        }

        public ObjectMetadata headObject(String bucketName, String key) {
            printOperation("HeadObject",
                    getEndpoint(), getPin(), getUserId(), getAccessKeyId(), bucketName, key);

            ObjectMetadata metadata = s3Client.getObjectMetadata(bucketName, key);
            printObjectMetadata(metadata);

            return metadata;
        }

        protected void printOperation(
                String operation, String endpoint, String pin, String userId, String accessKey, String bucket
        ) {
            logger.info("# [{}] {} | {}", operation, endpoint, bucket);
            logger.info("# {} | {} | {}", pin, userId, accessKey);
        }

        protected void printOperation(
                String operation, String endpoint, String pin, String userId, String accessKey, String bucket, String key
        ) {
            logger.info("# [{}] {} | {} | {}", operation, endpoint, bucket, key);
            logger.info("# {} | {} | {}", pin, userId, accessKey);
        }

        public void printObjectSummary(S3Object s3Object) {
            if(null == s3Object) {
                return;
            }

            if(s3Object.getObjectMetadata() == null) {
                return;
            }

            LogUtil.printResult("Metadata.ETag", s3Object.getObjectMetadata().getETag());
            LogUtil.printResult("Metadata.ContentMd5", s3Object.getObjectMetadata().getContentMD5());
            LogUtil.printResult("Metadata.ContentType", s3Object.getObjectMetadata().getContentType());
            LogUtil.printResult("Metadata.ContentLength", String.valueOf(s3Object.getObjectMetadata().getContentLength()));
            if (s3Object.getObjectMetadata().getContentRange() != null) {
                LogUtil.printResult("Metadata.ContentRange.start",
                        String.valueOf(s3Object.getObjectMetadata().getContentRange()[0]));
                if(s3Object.getObjectMetadata().getContentRange().length > 1) {
                    LogUtil.printResult("Metadata.ContentRange.end",
                            String.valueOf(s3Object.getObjectMetadata().getContentRange()[1]));
                }
            }
            if(s3Object.getObjectMetadata().getLastModified() != null) {
                LogUtil.printResult("Metadata.LastModified", String.valueOf(s3Object.getObjectMetadata().getLastModified()));
            }
            LogUtil.printResult("Metadata.PartCount", s3Object.getObjectMetadata().getPartCount());
        }

        public void printObjectMetadata(ObjectMetadata om) {
            if(om == null) {
                return;
            }
            LogUtil.printResult("Metadata.ContentMd5", om.getContentMD5());
            LogUtil.printResult("Metadata.ContentType", om.getContentType());
            LogUtil.printResult("Metadata.ETag", om.getETag());
            LogUtil.printResult("Metadata.InstanceLength", om.getInstanceLength());
            LogUtil.printResult("Metadata.LastModified", om.getLastModified());
        }
    }
}
