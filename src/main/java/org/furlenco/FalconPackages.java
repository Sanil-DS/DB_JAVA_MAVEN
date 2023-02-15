package org.furlenco;

public class FalconPackages {
    String packageAddQuery;

    public FalconPackages(String shipment_id, String display_id, String awb_number) {
        packageAddQuery = "INSERT INTO falcon.packages ( name, shipment_id, status, display_id, awb_number, volume_in_cft, weight_in_grams, created_at, updated_at, created_by,\n" +
                "                             updated_by)\n" +
                "VALUES ( 'Crest Queen Bed- Box 1 of  1', " + shipment_id + ", 'OUT_FOR_FULFILMENT', '" + display_id + "', '" + awb_number + "', 8, 2, '2023-02-09 08:46:52.957655 +00:00',\n" +
                "        '2023-02-09 10:57:29.895400 +00:00', null, null);";
    }

}
