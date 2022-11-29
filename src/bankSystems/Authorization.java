package bankSystems;

import java.util.HashMap;
import java.util.Map;
import sqlite3engine.DatabaseHandler;

public class Authorization {
    private String username;
    private String password;

    public Authorization(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Boolean login() {
        DatabaseHandler db = new DatabaseHandler();
        String[] columns = {"username", "password"};
        Map<String, String> values = new HashMap<String, String>();
        values.put("username", this.username);
        values.put("password", this.password);
        String auth = db.select("user", columns, values);
        return auth != null;
    }
}
