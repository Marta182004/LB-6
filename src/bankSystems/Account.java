package bankSystems;

import sqlite3engine.DatabaseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Account extends Authorization {
    private String username;
    private String password;

    DatabaseHandler db = new DatabaseHandler();
    Map<String, String> values = new HashMap<String, String>();

    public Account(String username, String password) {
        super(username, password);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String  getBalance() {
        values.put("username", this.username);
        String balance = db.select("user", new String[]{"balance"}, values);
        return balance;
    }

    public String getDeposits() {
        values.put("username", this.username);
        String depositId = db.select("user", new String[]{"deposit_id"}, values);
        ArrayList<String> depositList = new ArrayList<String>();
        String[] depositArray = depositId.split(",");
        for (String deposit : depositArray) {
            Map<String, String> depositValues = new HashMap<String, String>();
            depositValues.put("deposit_id", deposit);
            String depositAmount = db.select("deposit", new String[]{"deposit_amount"}, depositValues);
            String depositDate = db.select("deposit", new String[]{"deposit_date"}, depositValues);
            String depositTime = db.select("deposit", new String[]{"deposit_time"}, depositValues);
            String depositLongTerm = db.select("deposit", new String[]{"deposit_long_term"}, depositValues);
            String depositBankId = db.select("deposit", new String[]{"bank_id"}, depositValues);
            Map<String, String> bankValues = new HashMap<String, String>();
            depositList.add( "\nDeposit id: " + deposit + "\n" +
                    "Deposit amount: " + depositAmount + "\n" +
                    "Deposit date: " + depositDate + "\n" +
                    "Deposit time: " + depositTime + "\n" +
                    "Deposit long term: " + depositLongTerm + "\n" +
                    "Bank id: " + depositBankId + "\n" +
                    "===================================");
        }
        return depositList.toString();
    }

    public void addDeposit(String id) {
        values.put("username", this.username);
        String depositId = db.select("user", new String[]{"deposit_id"}, values);
        if (depositId.equals("null")) {
            db.update("user", "deposit_id", id, values);
        } else {
            db.update("user", "deposit_id", depositId + "," + id, values);
        }
    }

    public void removeDeposit(String id) {
        values.put("username", this.username);
        String depositId = db.select("user", new String[]{"deposit_id"}, values);
        String[] depositArray = depositId.split(",");
        String newDepositId = "";
        for (String deposit : depositArray) {
            if (!deposit.equals(id)) {
                newDepositId += deposit + ",";
            }
        }
        newDepositId = newDepositId.substring(0, newDepositId.length() - 1);
        db.update("user", "deposit_id", newDepositId, values);
    }

    public void createAccount(String firstName, String lastName, String email,
                              String phoneNumber, String address, String city, String state, String zipCode, String country) {
        values.put("username", this.username);
        if (db.select("user", new String[]{"username"}, values) != null) {
            System.out.println("Account already exists");
        } else {
            db.insert("user", new String[]{"username", "password", "balance", "first_name", "last_name", "email",
                    "phone_number", "address", "city", "state", "zip_code", "country", "deposit_id", "withdraw", "transfer"},
                    new String[]{this.username, this.password, String.valueOf(0), firstName, lastName, email,
                            phoneNumber, address, city, state, zipCode, country, String.valueOf(-1), String.valueOf(-1), String.valueOf(-1)});
        }
    }
}
