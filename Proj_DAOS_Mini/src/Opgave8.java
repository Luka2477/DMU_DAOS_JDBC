import java.sql.*;
import java.util.Scanner;

public class Opgave8 {

    public static void main(String[] args) {
        try {
            String svr = "localhost\\SQLEXPRESS";
            String db = "daos_mini_proj";
            String us = "sa";
            String pw = "sa123";

            Connection connection = DriverManager.getConnection(String.format(
                    "jdbc:sqlserver://%s;databaseName=%s;user=%s;password=%s;",
                    svr, db, us, pw));
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Du kan altid fortryde ved at skrive ;");
                System.out.println();
                System.out.print("Venligst indtaste studiekoden: ");
                String studiekode = scanner.nextLine();
                if (studiekode.equalsIgnoreCase(";")) {
                    return;
                }

                System.out.print("Venligst indtaste eksamenskoden: ");
                String eksamenskode = scanner.nextLine();
                if (eksamenskode.equalsIgnoreCase(";")) {
                    return;
                }

                System.out.print("Venligst indtaste karakter (-3, 0, 2, 4, 7, 10, 12): ");
                String karakter = scanner.nextLine();
                if (karakter.equalsIgnoreCase(";")) {
                    return;
                }

                String bedoemmelse = "null";
                if (karakter.equalsIgnoreCase("-3")) {
                    System.out.print("Venligst indtaste bedømmelsen (IA, SY, IM): ");
                    bedoemmelse = scanner.nextLine();
                    if (bedoemmelse.equalsIgnoreCase(";")) {
                        return;
                    }
                }

                System.out.print("Venligst indtaste termin (format V2022, S2022): ");
                String termin = scanner.nextLine();
                if (termin.equalsIgnoreCase(";")) {
                    return;
                }

                if (studiekode.isEmpty() || eksamenskode.isEmpty() || karakter.isEmpty() ||
                        bedoemmelse.isEmpty() || termin.isEmpty()) {
                    System.out.println("Venligst udfylde alle felter...");
                } else {
                    String query = "insert into forsoeg values (?, ?, ?, ?, ?);";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.clearParameters();
                    preparedStatement.setInt(1, Integer.parseInt(karakter));

                    if (bedoemmelse.equalsIgnoreCase("null")) {
                        preparedStatement.setNull(2, Types.INTEGER);
                    } else {
                        preparedStatement.setString(2, bedoemmelse);
                    }

                    preparedStatement.setString(3, termin);
                    preparedStatement.setString(4, studiekode);
                    preparedStatement.setString(5, eksamenskode);

                    preparedStatement.execute();
                    System.out.println("Forsoeg er oprettet!");
                }
                System.out.println();
            }
        } catch (SQLException throwables) {
            System.out.println();

            if (throwables.getErrorCode() == 547) {
                if (throwables.getMessage().contains("userid_foreign")) {
                    System.out.println("Kunne ikke finde studerende med den pågældende studiekode.");
                } else if (throwables.getMessage().contains("eksamensid_foreign")) {
                    System.out.println("Kunne ikke finde eksamenen med den pågældende eksamenskode.");
                } else if (throwables.getMessage().contains("karakter_check")) {
                    System.out.println("Du skal indtaste -3, 0, 2, 4, 7, 10, eller 12 som karakter.");
                } else if (throwables.getMessage().contains("kode_check")) {
                    System.out.println("Du skal indtaste IA, SY, eller IM som bedømmelse.");
                } else {
                    System.out.println("Ukendt foreign key.");
                }
            } else {
                System.out.println("SQL-fejl: " + throwables.getMessage());
            }

            System.out.println();
        }
    }

}
