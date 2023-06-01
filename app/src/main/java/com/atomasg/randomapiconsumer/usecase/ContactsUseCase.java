package com.atomasg.randomapiconsumer.usecase;

import com.atomasg.randomapiconsumer.model.Contact;
import com.atomasg.randomapiconsumer.ui.fragment.ContactDetailFragment;

import java.util.List;

public interface ContactsUseCase {

    public void getInitialContacts();

    public void getNextContacts();

    public Contact getDetail(String id);
}
