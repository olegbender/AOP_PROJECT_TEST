package aop_group_prjct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectDB {

    public List<User> SelectAll() {
        List<User> usersList = new ArrayList<>();
        String sql = "SELECT * FROM User";

        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            //Loop
            while (rs.next()) {
                User newUser = new User();
                newUser.Id(rs.getInt("id"));
                newUser.Card_number(rs.getString("card_number"));
                newUser.User_name(rs.getString("user_name"));
                newUser.Email(rs.getString("email"));
                newUser.Telephone(rs.getString("telephone"));
                newUser.Pin(rs.getInt("pin"));
                newUser.Balance(rs.getInt("balance"));

                usersList.add(newUser);

//                System.out.println("ID: " + rs.getString("Id") + "\tEMAIL: " + rs.getString("email"));
            }
            conn.close();
            return usersList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
//        return usersList;
// У випадку помилки функція повертає NULL
    }

    public void Insert(User client) {

        try (Connection con = connect();) {
            Statement stm = con.createStatement();
            String sql = "INSERT INTO User (card_number, user_name, email, telephone, pin, balance) VALUES('" + client.getCard_Number() + "', '" + client.getUser_name() + "', '" + client.getEmail() + "', '" + client.getTelephone() + "', '" + client.getPin() + "', '" + client.getBalance() + "');";
            stm.executeUpdate(sql);
            //'"+0+"',
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void writeDataBase() {
        List<User> db = SelectAll();
        System.out.println("***********************************************");
        db.forEach(e -> {
            System.out.println("ID: " + e.getId() + " | CARDNUM: " + e.getCard_Number() + " | NAME: " + e.getUser_name() + " | EMAIL: " + e.getEmail() + " | TELEPHONE: " + e.getTelephone() + " | PIN: " + e.getPin() + " | BALANCE: " + e.getBalance());
        });
    }

    public void DeleteUser(int selectedID) {
        String sql = "DELETE FROM User WHERE id = " + selectedID;

        try (Connection conn = connect(); Statement stmt = conn.createStatement();) {
            stmt.executeUpdate(sql);
            System.out.println("Deleted succes!");
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean Transfer(int senderId, int receiverId, int value) {
        String sql = "BEGIN TRANSACTION; UPDATE User SET balance = balance - " + value + " WHERE id = " + senderId + "; UPDATE User SET balance = balance + " + value + " WHERE id = " + receiverId + "; COMMIT";
        try (Connection conn = connect(); Statement stmt = conn.createStatement();) {
            stmt.executeUpdate(sql);
            System.out.println("=== TRANSFER SUCCES ===!");
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("TRANSFER FAILED: " + e.getMessage());
            return false;
        }
    }
    
    
    public Connection connect() {
        String url = "jdbc:sqlite:bankDB.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

}
