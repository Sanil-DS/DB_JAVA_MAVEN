package org.furlenco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;


public class DB {

    public String generateDisplayid() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return "SH-" + generatedString.toUpperCase();
    }

    public String generateFalconAWB() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return "SH-BLRYOLO-23B000-" + generatedString.toUpperCase();
    }

    Connection connection = null;

    public void dbConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://serviceability-staging.c6bcs7bajyvx.ap-south-1.rds.amazonaws.com/serviceability",
                            "serviceability", "EPzo29cCvETknY60RuY9");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public static void main(String[] args) throws SQLException {
        DB db = new DB();
        db.dbConnection();
        Statement statement;


        String falconAWB1 = db.generateFalconAWB(), displayID1 = db.generateDisplayid();
        String falconAWB2 = db.generateFalconAWB(), displayID2 = db.generateDisplayid();


        FALCON_SHIPMENT falconShipment;
        statement = db.connection.createStatement();
        falconShipment = new FALCON_SHIPMENT(displayID1, falconAWB1);
        System.out.println(falconShipment.shipmentAddQuery);
        statement.execute(falconShipment.shipmentAddQuery);
        statement.close();


        statement = db.connection.createStatement();
        falconShipment = new FALCON_SHIPMENT(displayID2, falconAWB2);
        System.out.println(falconShipment.shipmentAddQuery);
        statement.execute(falconShipment.shipmentAddQuery);
        statement.close();



        db.connection.close();
    }
}

