
package com.atomasg.randomapiconsumer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Registered {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("age")
    @Expose
    private Integer age;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.date == null)? 0 :this.date.hashCode()));
        result = ((result* 31)+((this.age == null)? 0 :this.age.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Registered) == false) {
            return false;
        }
        Registered rhs = ((Registered) other);
        return (((this.date == rhs.date)||((this.date!= null)&&this.date.equals(rhs.date)))&&((this.age == rhs.age)||((this.age!= null)&&this.age.equals(rhs.age))));
    }

}
