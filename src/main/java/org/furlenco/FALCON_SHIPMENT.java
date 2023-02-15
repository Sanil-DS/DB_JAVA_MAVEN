package org.furlenco;

public class FALCON_SHIPMENT {
    String shipmentAddQuery;

    public FALCON_SHIPMENT(String displayid, String awb) {
        shipmentAddQuery = "INSERT INTO falcon.shipments (fc_id, user_id, type, status, display_id, awb_number, selected_fulfilment_date, promise_date, service_time_in_minutes,\n" +
                "                              created_at, updated_at, created_by, updated_by, shipment_group_id, champ_proof_of_fulfilment, is_priority, fixed_time_in_minutes,\n" +
                "                              vertical, product_name, client_claimed_state)\n" +
                "VALUES (990087, 27702, 'DELIVERY', 'OUT_FOR_FULFILMENT',\n" +
                "        '" + displayid + "',\n" +
                "        '" + awb + "', null,\n" +
                "        '2023-02-11 12:00:00.000000 +00:00', 10,\n" +
                "        '2023-02-09 08:45:47.132297 +00:00',\n" +
                "        '2023-02-10 10:55:52.915219 +00:00',\n" +
                "        null, null, 1882, null, false, 0,\n" +
                "        'FURLENCO_RENTAL', 'Crest Queen Bed - Grey',\n" +
                "        null);";
    }


}
