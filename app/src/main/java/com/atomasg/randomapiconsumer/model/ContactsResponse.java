
package com.atomasg.randomapiconsumer.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContactsResponse {

    @SerializedName("results")
    @Expose
    private List<Contact> contacts;
    @SerializedName("info")
    @Expose
    private Info info;

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.contacts == null)? 0 :this.contacts.hashCode()));
        result = ((result* 31)+((this.info == null)? 0 :this.info.hashCode()));
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
        return (((this.contacts == rhs.contacts)||((this.contacts!= null)&&this.contacts.equals(rhs.contacts)))&&((this.info == rhs.info)||((this.info!= null)&&this.info.equals(rhs.info))));
    }

}
