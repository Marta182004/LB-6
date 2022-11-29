package bankSystems;

import sqlite3engine.DatabaseHandler;

public class Deposit {
    DatabaseHandler db = new DatabaseHandler();
    public Deposit() {
    }
    public void createDeposit(String amount, String date, String time, String longTerm, String bankId) {
        db.insert("deposit", new String[]{"deposit_amount", "deposit_date", "deposit_time", "deposit_long_term", "bank_id"},
                new String[]{amount, date, time, longTerm, bankId});
        System.out.println("Deposit created successfully");
    }

}
