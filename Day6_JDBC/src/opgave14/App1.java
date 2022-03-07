package opgave14;

import java.sql.*;
import java.util.Scanner;

public class App1 {

    public static void main(String[] args) {
        try {
            String svr = "localhost\\SQLEXPRESS";
            String db = "cykelplaceringer";
            String us = "sa";
            String pw = "sa123";

            Connection connection = DriverManager.getConnection(String.format(
                    "jdbc:sqlserver://%s;databaseName=%s;user=%s;password=%s;",
                    svr, db, us, pw));
            Statement statement = connection.createStatement();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Indtast rytterinitialer: ");
            String rytterinitialer = scanner.nextLine();

            String query = String.format(
                    "select p.aarstal, p.plac " +
                    "from placering p " +
                    "where p.init = '%s';",
                    rytterinitialer);
            ResultSet result = statement.executeQuery(query);

            System.out.println("Årstal | Placering");
            while (result.next()) {
                String placering = (result.getString(2) == null) ? "UDGÅET" : result.getString(2);
                System.out.printf("%s   | %s%n", result.getString(1), placering);
            }

            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
