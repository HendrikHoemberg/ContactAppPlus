package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import application.Contact;

public class ContactRepository implements IContactRepository {

    private static final String CONNECTION_STRING = "jdbc:sqlite:database.db";

    public ContactRepository() {
        createTables();
    }

    private void createTables() {
        String sql = "CREATE TABLE IF NOT EXISTS contacts (\r\n" + //
                        "\tcontact_id INTEGER PRIMARY KEY AUTOINCREMENT,\r\n" + //
                        "\tfirst_name TEXT,\r\n" + //
                        "\tlast_name TEXT,\r\n" + //
                        "\tphone_number TEXT,\r\n" + //
                        "\temail_address TEXT,\r\n" + //
                        "\tbirth_date TEXT,\r\n" + //
                        "\tgroup_id INTEGER,\r\n" + //
                        "\tstreet TEXT,\r\n" + //
                        "\thouse_number TEXT,\r\n" + //
                        "\tzip_code TEXT,\r\n" + //
                        "\tcity TEXT,\r\n" + //
                        "\tstate TEXT,\r\n" + //
                        "\tcountry TEXT,\r\n" + //
                        ");";

        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Contact> getAllContacts() {
        String sql = "SELECT * FROM contacts";
        List<Contact> contacts = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(sql)) {
            while (result.next()) {
                contacts.add(new Contact(
                        result.getInt("contact_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("phone_number")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public Contact getContactById(int id) {
        String sql = "";
        return null;
    }

    public void deleteContact(int id) {
        String sql = "";
    }

    @Override
    public void addContact(Contact contact) {
        String sql = "";
        throw new UnsupportedOperationException("Unimplemented method 'addContact'");
    }

    @Override
    public void updateContact(Contact contact) {
        String sql = "";
        throw new UnsupportedOperationException("Unimplemented method 'updateContact'");
    }
}
