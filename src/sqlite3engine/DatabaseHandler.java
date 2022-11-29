package sqlite3engine;

import netscape.javascript.JSObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseHandler {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src\\data\\bank.db");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void createTable(String tableName, String[] columns) {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (";
        for (int i = 0; i < columns.length; i++) {
            sql += columns[i];
            if (i < columns.length - 1) {
                sql += ", ";
            }
        }
        sql += ");";
        try (Connection conn = DatabaseHandler.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseHandler.closeConnection();
        }
    }
    public void insert(String tableName, String[] columns, String[] values) {
        String sql = "INSERT INTO " + tableName + " (";
        for (int i = 0; i < columns.length; i++) {
            sql += columns[i];
            if (i < columns.length - 1) {
                sql += ", ";
            }
        }
        sql += ") VALUES (";
        for (int i = 0; i < values.length; i++) {
            sql += "'" + values[i] + "'";
            if (i < values.length - 1) {
                sql += ", ";
            }
        }
        sql += ");";
        try (Connection conn = DatabaseHandler.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseHandler.closeConnection();
        }
    }

    public void update(String tableName, String columns, String values, Map<String, String> condition) {
        String sql = "UPDATE " + tableName + " SET " + columns + " = '" + values + "' WHERE ";
        for (Map.Entry<String, String> entry : condition.entrySet()) {
            sql += entry.getKey() + " = '" + entry.getValue() + "'";
        }
        sql += ";";
        try (Connection conn = DatabaseHandler.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseHandler.closeConnection();
        }
    }

    public void delete(String tableName, String condition) {
        String sql = "DELETE FROM " + tableName + " WHERE " + condition + ";";
        try (Connection conn = DatabaseHandler.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseHandler.closeConnection();
        }
    }

    public String select(String tableName, String[] columns, Map<String, String> conditions) {
        String sql = "SELECT ";
        for (int i = 0; i < columns.length; i++) {
            sql += columns[i];
            if (i < columns.length - 1) {
                sql += ", ";
            }
        }
        sql += " FROM " + tableName + " WHERE ";
        int i = 0;
        for (Map.Entry<String, String> entry : conditions.entrySet()) {
            sql += entry.getKey() + " = '" + entry.getValue() + "'";
            if (i < conditions.size() - 1) {
                sql += " AND ";
            }
            i++;
        }
        sql += ";";
        try (Connection conn = DatabaseHandler.getConnection();
             Statement stmt = conn.createStatement()) {
            return stmt.executeQuery(sql).getString(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseHandler.closeConnection();
        }
        return null;
    }

    public ArrayList selectAll(String tableName, String[] columns) {
        String sql = "SELECT ";
        for (int i = 0; i < columns.length; i++) {
            sql += columns[i];
            if (i < columns.length - 1) {
                sql += ", ";
            }
        }
        sql += " FROM " + tableName + ";";
        try (Connection conn = DatabaseHandler.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList list = new ArrayList<>();
            while (rs.next()) {
                Map<String, String> el = new HashMap<String, String>();
                for (int i = 0; i < columns.length; i++) {
                    el.put(columns[i], rs.getString(columns[i]));
                }
                list.add(el);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseHandler.closeConnection();
        }
        return null;
    }

    public void dropTable(String tableName) {
        String sql = "DROP TABLE IF EXISTS " + tableName + ";";
        try (Connection conn = DatabaseHandler.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseHandler.closeConnection();
        }
    }

}
