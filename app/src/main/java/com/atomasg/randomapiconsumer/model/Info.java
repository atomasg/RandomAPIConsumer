
package com.atomasg.randomapiconsumer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("seed")
    @Expose
    private String seed;
    @SerializedName("results")
    @Expose
    private Integer results;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("version")
    @Expose
    private String version;

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public Integer getResults() {
        return results;
    }

    public void setResults(Integer results) {
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.page == null)? 0 :this.page.hashCode()));
        result = ((result* 31)+((this.seed == null)? 0 :this.seed.hashCode()));
        result = ((result* 31)+((this.results == null)? 0 :this.results.hashCode()));
        result = ((result* 31)+((this.version == null)? 0 :this.version.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Info) == false) {
            return false;
        }
        Info rhs = ((Info) other);
        return (((((this.page == rhs.page)||((this.page!= null)&&this.page.equals(rhs.page)))&&((this.seed == rhs.seed)||((this.seed!= null)&&this.seed.equals(rhs.seed))))&&((this.results == rhs.results)||((this.results!= null)&&this.results.equals(rhs.results))))&&((this.version == rhs.version)||((this.version!= null)&&this.version.equals(rhs.version))));
    }

}
