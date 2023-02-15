package org.furlenco;

public class SelectStatement {
    String selectQ;

    public SelectStatement(int orderID) {
        selectQ = "SELECT t.*\n" +
                "FROM sms.items t\n" +
                "WHERE order_id=" + orderID + "";
    }
}
