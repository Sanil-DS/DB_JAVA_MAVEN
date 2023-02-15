package org.furlenco;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class DB {

    public String generateDisplayid() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        return "SH-" + generatedString.toUpperCase();
    }

    public String generateFalconAWB() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        return "SH-BLRYOLO-23B000-" + generatedString.toUpperCase();
    }


    Connection connection = null;

    public void dbConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://serviceability-staging.c6bcs7bajyvx.ap-south-1.rds.amazonaws.com/serviceability", "serviceability", "EPzo29cCvETknY60RuY9");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public static void main(String[] args) throws SQLException {

        DB db = new DB();
        // Establish the DB connection
        db.dbConnection();


        String falconAWB1 = db.generateFalconAWB();
        String displayID1 = db.generateDisplayid();


        FalconShipment falconShipment;
        Statement statement = db.connection.createStatement();
        falconShipment = new FalconShipment(displayID1, falconAWB1);
        System.out.println(falconShipment.shipmentAddQuery);
        statement.execute(falconShipment.shipmentAddQuery);
        statement.close();


        SelectStatement selectStatement = new SelectStatement(5010);
        PreparedStatement preparedStatement = db.connection.prepareStatement(selectStatement.selectQ);
        System.out.println(preparedStatement);

        //  Execute the query
        ResultSet rs = preparedStatement.executeQuery();

        //Process the ResultSet object.
        String capacity_commitment_id = null, shipment_id = null;
        while (rs.next()) {
            // get values using table header
            capacity_commitment_id = rs.getString("capacity_commitment_id");
            shipment_id = rs.getString("shipment_id");
        }
        System.out.println(capacity_commitment_id);
        System.out.println(shipment_id);

        //Terminate DB connection
        db.connection.close();
    }
}

