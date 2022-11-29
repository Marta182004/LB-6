package bankSystems;

import sqlite3engine.DatabaseHandler;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    public Bank() {
    }
    DatabaseHandler db = new DatabaseHandler();
    Map<String, String> data = new HashMap<String, String>();

    public void createBank(String name, String address, String city, String state, String zipCode, String country, String phone, String email, String website) {
        name = name;
        address = address;
        city = city;
        state = state;
        zipCode = zipCode;
        country = country;
        phone = phone;
        email = email;
        website = website;
        data.put("bank_name", name);
        if (db.select("bank", new String[]{"bank_name"}, data) != null) {
            System.out.println("Bank already exists");
        } else {
            db.insert("bank", new String[]{"bank_name", "bank_address", "bank_city", "bank_state",
                            "bank_zip_code", "bank_country", "bank_phone_number", "bank_email", "bank_website"},
                    new String[]{name, address, city, state, zipCode, country, phone, email, website});
            System.out.println("Bank created successfully");
        }
    }


}