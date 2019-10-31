package com.nema.kafka;

import com.alibaba.fastjson.JSON;
import com.github.jcustenborder.kafka.connect.utils.VersionUtil;
import com.google.gson.Gson;
import com.nema.kafka.models.SalesModel;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nema.kafka.MySourceConnectorConfig.AUTH_USERNAME_CONFIG;
import static com.nema.kafka.MySourceConnectorConfig.OWNER_URL_CONFIG;
import static com.nema.kafka.SaleSchema.*;
import static java.util.Arrays.asList;

public class SalesSourceTask extends SourceTask {
    public  MySourceConnectorConfig config;
    private static final Logger log = LoggerFactory.getLogger(MySourceTask.class);
    OdooApIHttpClient odooApIHttpClient = new OdooApIHttpClient();
    @Override
    public String version() {
        return VersionUtil.version(this.getClass());    }

    @Override
    public void start(Map<String, String> map) {

        config = new MySourceConnectorConfig(map);
        odooApIHttpClient = new OdooApIHttpClient(config);
        odooApIHttpClient.login(config.getOwnerConfig(), config.getAuthDatabaseConfig(), config.getAuthUsernameConfig(),config.getAuthPasswordConfig());
        System.out.println("You reached here too");
    }

    @Override
    public List<SourceRecord> poll() throws InterruptedException {
        final ArrayList<SourceRecord> records = new ArrayList<>();

        Object object = odooApIHttpClient.executeMethod( "sale.order.line","search_read",asList(asList(
                asList("price_unit",">",0))),
                new HashMap() {{
                    put("fields", asList("product_id","order_id","name", "price_total","price_unit","create_date", "write_date", "order_partner_id"));

                }});
        Object object2 = odooApIHttpClient.executeMethod( "sale.order","search_read",asList(asList(
                asList("require_payment", "=", "true"))),
                new HashMap() {{
                    put("fields", asList("amount_tax", "amount_total", "amount_untaxed", "company_id", "confirmation_date", "partner_id", "invoice_status"));

                }});

        String json = JSON.toJSONString(object);
        String json2 = JSON.toJSONString(object2);

        JSONArray json3 = new JSONArray(json);
        JSONArray json4 = new JSONArray(json2);
        JSONArray finaljson = new JSONArray();
//
//        finaljson.put(json3.get(0).toString());
//        finaljson.put(json3.get(1).toString());
        int i = 0;

        System.out.println(json3.getJSONObject(i).getJSONArray("product_id").toString());

        for (int k=0; i<json3.length(); k++){
            if (json3.getJSONObject(i).has("product_id")) {
//                String value = json3.getJSONObject(i).getJSONArray("product_id").toString();
//                json3.getJSONObject(i).put("product_id", value);
            }
        }

        System.out.println(json3);
        for(Object obj: json3){
//            if (json3.optJSONObject(i).names().opt(i)=="product_id"||json3.getJSONObject(i).has("product_id")) {}
//            json3.optJSONObject(i).names().opt(i)="product_id";

//            System.out.println(json3.getJSONObject(i).getJSONArray("product_id").toString());
            i++;
            finaljson.put(obj);
        }
        for(Object obj: json4){
            finaljson.put(obj);
        }
        for (int j = 0; j<finaljson.length(); j++){

        }
        for(Object obj: finaljson){
            SalesModel salesModel = SalesModel.fromJson((JSONObject) obj);
            SourceRecord sourceRecord = generateSourceRecord(salesModel);
            System.out.println("Here we go ++++++++++++++++++++++++++++++++ " + sourceRecord);
            records.add(sourceRecord);
        }

//        HashMap HashMap1 = new Gson().fromJson(object.toString(), HashMap.class);
//        HashMap HashMap2 = new Gson().fromJson(object2.toString(), HashMap.class);
//
//        while(HashMap1.h) {
//            Map.Entry mentry = (Map.Entry)HashMap1.next();
//            System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
//            System.out.println(mentry.getValue());
//        }




        return records;
    }

    public SourceRecord generateSourceRecord(SalesModel sale) {
        return new SourceRecord(
                sourcePartition(),
                null,
                config.getTopicConfig(),
                0,
                Keyschema,
                buildRecordKey(),
                ValueSchema,
                buildRecordValue(sale)
        );
    }
    @Override
    public void stop() {

    }
    private Struct buildRecordKey() {
//        Schema schema = null;
        Struct key = new Struct(Keyschema)
                .put("tableName", "sale");
        return key;
    }
    private Struct buildRecordValue(SalesModel sale){
        Struct valueStruct = new Struct(ValueSchema)
                .put(PRODUCT_ID,sale.getProduct_id())
                .put(ORDER_ID,sale.getOrder_id())
                .put(NAME,sale.getName())
                .put( PRICE_TOTAL, sale.getPrice_total())
                .put( PRICE_UNIT, sale.getPrice_unit())
                .put( CREATE_DATE,sale.getCreate_date())
                .put( WRITE_DATE,sale.getWrite_date())
                .put( AMOUNT_TAX, sale.getAmount_tax())
                .put( AMOUNT_TOTAL, sale.getAmount_total())
                .put( AMOUNT_UNTAXED , sale.getAmount_untaxed())
                .put(COMPANY_ID ,sale.getCompany_id())
                .put( CONFIRMATION_DATE,sale.getConfirmation_date())
                .put( PARTNER_ID,sale.getPartner_id())
                .put(INVOICE_STATUS,sale.getInvoice_status());
        return valueStruct;
    }
    private Map<String,String>  sourcePartition() {
        Map<String,String> map = new HashMap<>();
        map.put(OWNER_URL_CONFIG,config.getOwnerConfig());
        map.put(AUTH_USERNAME_CONFIG,config.getAuthUsernameConfig());

        return map;
    }
}
