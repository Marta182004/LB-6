package bankSystems;


import sqlite3engine.DatabaseHandler;

import java.util.*;

public class ControlDeposit extends Deposit {
        DatabaseHandler db = new DatabaseHandler();

        public ControlDeposit() {
        }
        public void getAllDeposits() {
                ArrayList data = db.selectAll("deposit", new String[]{"deposit_id",
                        "deposit_amount", "deposit_date", "deposit_time", "deposit_long_term", "bank_id"});
                Scanner scanner = new Scanner(System.in);
                System.out.println("Output no sort (1) or sort by amount (2)?");
                int choice = scanner.nextInt();
                switch (choice) {
                        case 1:
                                for (int i = 0; i < data.size(); i++) {
                                        HashMap<String, String> row = (HashMap<String, String>) data.get(i);
                                        System.out.println("Deposit id: " + row.get("deposit_id"));
                                        System.out.println("Deposit amount: " + row.get("deposit_amount"));
                                        System.out.println("Deposit date: " + row.get("deposit_date"));
                                        System.out.println("Deposit time: " + row.get("deposit_time"));
                                        System.out.println("Deposit long term: " + row.get("deposit_long_term"));
                                        System.out.println("Bank id: " + row.get("bank_id"));
                                        System.out.println("===================================");
                                }
                                break;
                        case 2:
                                Collections.sort(data, new Comparator<HashMap<String, String>>() {
                                        @Override
                                        public int compare(HashMap<String, String> o1, HashMap<String, String> o2) {
                                                return o1.get("deposit_amount").compareTo(o2.get("deposit_amount"));
                                        }
                                });
                                for (int i = 0; i < data.size(); i++) {
                                        HashMap<String, String> row = (HashMap<String, String>) data.get(i);
                                        System.out.println("Deposit id: " + row.get("deposit_id"));
                                        System.out.println("Deposit amount: " + row.get("deposit_amount"));
                                        System.out.println("Deposit date: " + row.get("deposit_date"));
                                        System.out.println("Deposit time: " + row.get("deposit_time"));
                                        System.out.println("Deposit long term: " + row.get("deposit_long_term"));
                                        System.out.println("Bank id: " + row.get("bank_id"));
                                        System.out.println("===================================");
                                }
                                break;
                }
        }
}
