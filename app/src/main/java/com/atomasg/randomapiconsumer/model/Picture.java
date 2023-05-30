
package com.atomasg.randomapiconsumer.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Picture {

    @SerializedName("large")
    @Expose
    private String large;
    @SerializedName("medium")
    @Expose
    private String medium;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.thumbnail == null)? 0 :this.thumbnail.hashCode()));
        result = ((result* 31)+((this.medium == null)? 0 :this.medium.hashCode()));
        result = ((result* 31)+((this.large == null)? 0 :this.large.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Picture) == false) {
            return false;
        }
        Picture rhs = ((Picture) other);
        return ((((this.thumbnail == rhs.thumbnail)||((this.thumbnail!= null)&&this.thumbnail.equals(rhs.thumbnail)))&&((this.medium == rhs.medium)||((this.medium!= null)&&this.medium.equals(rhs.medium))))&&((this.large == rhs.large)||((this.large!= null)&&this.large.equals(rhs.large))));
    }

}
