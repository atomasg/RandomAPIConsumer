
package com.atomasg.randomapiconsumer.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ContactsResponse {

    @SerializedName("results")
    @Expose
    private List<Contact> results;

    public List<Contact> getResults() {
        return results;
    }

    public void setResults(List<Contact> results) {
        this.results = results;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.results == null)? 0 :this.results.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ContactsResponse) == false) {
            return false;
        }
        ContactsResponse rhs = ((ContactsResponse) other);
        return ((this.results == rhs.results)||((this.results!= null)&&this.results.equals(rhs.results)));
    }

}
