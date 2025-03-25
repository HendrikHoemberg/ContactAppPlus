import java.util.List;

public interface IDBRepository {
    void addContact(Contact contact);
    List<Contact> getAllContacts();
    Contact getContactById(int id);
    void updateContact(Contact contact);
    void deleteContact(int id);
}
