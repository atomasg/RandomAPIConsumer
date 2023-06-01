
package com.atomasg.randomapiconsumer.model;


import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Name {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("first")
    @Expose
    private String first;
    @SerializedName("last")
    @Expose
    private String last;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result * 31) + ((this.title == null) ? 0 : this.title.hashCode()));
        result = ((result * 31) + ((this.last == null) ? 0 : this.last.hashCode()));
        result = ((result * 31) + ((this.first == null) ? 0 : this.first.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Name) == false) {
            return false;
        }
        Name rhs = ((Name) other);
        return ((((this.title == rhs.title) || ((this.title != null) && this.title.equals(rhs.title))) && ((this.last == rhs.last) || ((this.last != null) && this.last.equals(rhs.last)))) && ((this.first == rhs.first) || ((this.first != null) && this.first.equals(rhs.first))));
    }

    @NonNull
    @Override
    public String toString() {
        return title + " " + first + " " + last;
    }
}
