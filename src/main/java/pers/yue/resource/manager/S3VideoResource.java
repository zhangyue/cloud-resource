package pers.yue.resource.manager;

/**
 * Created by Zhang Yue on 5/12/2018
 */
public class S3VideoResource extends S3Resource implements UploadableResource {
    private String packaging = null;
    private String coding = null;
    private Double duration = null;
    private Integer widthInPixel = null;
    private Integer heightInPixel = null;

    public S3VideoResource(String tag, String name, String description) {
        super(tag, name, description);
    }

    public S3VideoResource(
            String tag, String name, String description,
            String packaging, String coding, Double duration, Integer widthInPixel, Integer heightInPixel
    ) {
        super(tag, name, description);
        this.packaging = packaging;
        this.coding = coding;
        this.duration = duration;
        this.widthInPixel = widthInPixel;
        this.heightInPixel = heightInPixel;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getCoding() {
        return coding;
    }

    public void setCoding(String coding) {
        this.coding = coding;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Integer getWidthInPixel() {
        return widthInPixel;
    }

    public void setWidthInPixel(Integer widthInPixel) {
        this.widthInPixel = widthInPixel;
    }

    public Integer getHeightInPixel() {
        return heightInPixel;
    }

    public void setHeightInPixel(Integer heightInPixel) {
        this.heightInPixel = heightInPixel;
    }
}
