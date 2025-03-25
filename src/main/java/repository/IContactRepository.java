package repository;

import java.util.List;

import application.Contact;

public interface IContactRepository {
    void addContact(Contact contact);
    List<Contact> getAllContacts();
    Contact getContactById(int id);
    void updateContact(Contact contact);
    void deleteContact(int id);
}
