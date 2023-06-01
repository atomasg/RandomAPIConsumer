package com.atomasg.randomapiconsumer.ui.fragment;

import com.atomasg.randomapiconsumer.model.Contact;

import java.util.List;

public interface ContactListView {

    public void updateContacts(List<Contact> contactList);

    public void showErrorMessage(String msg);
}
