
package com.atomasg.randomapiconsumer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Timezone {

    @SerializedName("offset")
    @Expose
    private String offset;
    @SerializedName("description")
    @Expose
    private String description;

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.offset == null)? 0 :this.offset.hashCode()));
        result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Timezone) == false) {
            return false;
        }
        Timezone rhs = ((Timezone) other);
        return (((this.offset == rhs.offset)||((this.offset!= null)&&this.offset.equals(rhs.offset)))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))));
    }

}
