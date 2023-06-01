package com.atomasg.randomapiconsumer.data;

import com.atomasg.randomapiconsumer.model.Contact;

import java.util.List;

public class ContactsBean {

    private static ContactsBean instance;
    public static ContactsBean getInstance(){
        if (instance == null){
            instance = new ContactsBean();
        }
        return instance;
    }
    private List<Contact> contactsList;

    public List<Contact> getContacts() {
        return contactsList;
    }

    public void setContacts(List<Contact> contactsList) {
        this.contactsList = contactsList;
    }
}
