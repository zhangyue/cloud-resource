## Test resource manager
* Manages test resource files in a hybrid manner
    * Resource files are centrally managed in automation-resource-store-do-not-remove bucket in cn-east-2.
    * In each test environment (including both in-door test environment and and each region online) a copy of the resource files are maintained in automation-resource-${pin}-${region} bucket for each user.
    * Each resource file is downloaded to local (under ${project_home}/local_resource) on use.
    * If no luxury to access cn-east-2 OSS service, just simply place the resource files (copied from a location where cn-east-2 OSS service can be accessed) into ${project_home}/local_resource/${tag} directory, the resource manager can then use them for test as well.
* Usage
    * Use existing resource files (already defined in com.jcloud.resource.XXXResource files):
        * Use a resource online:
                String bucketName = resource.getResourceObject().getBucket();
                String key = resource.getResourceObject.getKey();
        * Use a resource in local work sapce:
                File resourceFile = resource.getResourceFile();
    * Use a new resource file (not defined in com.jcloud.resource.XXXResource files yet):
        1. Edit one of the com.jcloud.resource.XXXResource  files (or create a new one, if none of the existing files can properly include your new resource).
        2. Place the new resource file into ${project_home}/local_resource/${tag}, where ${tag} is defined by the first parameter of the new "Resource" object in your Resource file.
        3. Run corresponding TestNG method in com.jcloud.Uploader.
* Basic flow
    * Use a resource online:
            if(isAvailableInResourceBucket()) {
                return resourceObject;
            }

            if(!resourceFile.exists()) {
                if(isAvailableInResourceStore()) {
                    downloadFromResourceStore();
                } else {
                    throw new ResourceNotFoundException(this);
                }
            }

            uploadToResourceBucket();

            return resourceObject;
    * Use a resource in local work space:
            if(resourceFile.exists()) {
                return resourceFile;
            }

            if(isAvailableInResourceBucket()) {
                downloadFromResourceBucket();
            } else if(isAvailableInResourceStore()) {
                downloadFromResourceStore();
                uploadToResourceBucket();
            } else {
                throw new ResourceNotFoundException(this);
            }

            return resourceFile;
