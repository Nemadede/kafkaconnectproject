package com.nema.kafka;

import com.alibaba.fastjson.JSON;
import com.github.jcustenborder.kafka.connect.utils.VersionUtil;
import com.google.gson.Gson;
import com.nema.kafka.models.SalesModel;
import com.nema.kafka.utils.DateUtils;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.ZonedDateTime;
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

    protected Instant nextQuerySince;
    protected Integer lastIdNumber;
    @Override
    public String version() {
        return VersionUtil.version(this.getClass());    }

    @Override
    public void start(Map<String, String> map) {

        config = new MySourceConnectorConfig(map);
        odooApIHttpClient = new OdooApIHttpClient(config);
        odooApIHttpClient.login(config.getOwnerConfig(), config.getAuthDatabaseConfig(), config.getAuthUsernameConfig(),config.getAuthPasswordConfig());
        initializeLastVariables();
        System.out.println("You reached here too");
    }

    public void initializeLastVariables(){
        System.out.println("\n\n\n\nit entered the initialized last variable");
        Map<String, Object> lastSourceOffset = null;
        lastSourceOffset = context.offsetStorageReader().offset(sourcePartition());
        if (lastSourceOffset == null){
            nextQuerySince = config.getSince();
            System.out.println("\n\n\nhere is next query since now "+nextQuerySince);
            lastIdNumber = 1;
        }else{
            Object updatedAt = lastSourceOffset.get(log);
            Object idNumber = lastSourceOffset.get(SALE_ID);
            System.out.println("\n\n\n\nhere is next id value "+updatedAt + "and it" + idNumber);
        }
    }

    @Override
    public List<SourceRecord> poll() throws InterruptedException {
        final ArrayList<SourceRecord> records = new ArrayList<>();
        int x = 0;

        Object saleOrderLineObject = odooApIHttpClient.executeMethod( "sale.order.line","search_read",asList(asList(
                asList("price_unit",">",0))),
                new HashMap() {{
                    put("fields", asList("product_id","order_id","name", "price_total","price_unit","create_date", "write_date"));

                }});
        Object saleOrderObject = odooApIHttpClient.executeMethod( "sale.order","search_read",asList(asList(
                asList("require_payment", "=", "true"))),
                new HashMap() {{
                    put("fields", asList("amount_tax", "amount_total", "amount_untaxed", "company_id", "confirmation_date", "partner_id", "invoice_status"));

                }});

        String saleOderLineString = JSON.toJSONString(saleOrderLineObject);
        String saleOrderString = JSON.toJSONString(saleOrderObject);


        JSONArray saleOrderLineArray = new JSONArray(saleOderLineString);
        JSONArray saleOrderArray = new JSONArray(saleOrderString);

        for (int i=0; i<saleOrderLineArray.length(); i++){

            for (int k = 0; k<saleOrderArray.length(); k++) {

                if (saleOrderLineArray.getJSONObject(i).getJSONArray("order_id").get(0) == saleOrderArray.optJSONObject(k).get("id")){

                    for (int j = 0; j<saleOrderArray.optJSONObject(k).length(); j++){

                        try {
                            saleOrderLineArray.getJSONObject(i).put((String) saleOrderArray.optJSONObject(k).names().opt(j), saleOrderArray.optJSONObject(k).get((String) saleOrderArray.optJSONObject(k).names().opt(j)));

                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }
            }

        }

        for (int k=0; k<saleOrderLineArray.length(); k++) {

            saleOrderLineArray.getJSONObject(k).put("sale_id", saleOrderLineArray.getJSONObject(k).get("id"));

            saleOrderLineArray.getJSONObject(k).put("product_name", saleOrderLineArray.getJSONObject(k).getJSONArray("product_id").get(1));
            saleOrderLineArray.getJSONObject(k).put("partner_name", saleOrderLineArray.getJSONObject(k).getJSONArray("partner_id").get(1));
            saleOrderLineArray.getJSONObject(k).put("company_name", saleOrderLineArray.getJSONObject(k).getJSONArray("company_id").get(1));
            saleOrderLineArray.getJSONObject(k).put("product_id", saleOrderLineArray.getJSONObject(k).getJSONArray("product_id").get(0));
            saleOrderLineArray.getJSONObject(k).put("partner_id", saleOrderLineArray.getJSONObject(k).getJSONArray("partner_id").get(0));
            saleOrderLineArray.getJSONObject(k).put("company_id", saleOrderLineArray.getJSONObject(k).getJSONArray("company_id").get(0));
            saleOrderLineArray.getJSONObject(k).put("confirmation_date", saleOrderLineArray.getJSONObject(k).get("confirmation_date").toString());

            saleOrderLineArray.optJSONObject(k).remove("id");
            saleOrderLineArray.optJSONObject(k).remove("order_id");

        }

        System.out.println("\n\nClean data boy, see it------------"+saleOrderLineArray);

        lastIdNumber = saleOrderLineArray.optJSONObject(saleOrderLineArray.length()-1).getInt("sale_id") ;

        SourceRecord sourceRecord = null;

        for(Object obj: saleOrderLineArray){
            SalesModel salesModel = SalesModel.fromJson((JSONObject) obj);
             sourceRecord = generateSourceRecord(salesModel);
            records.add(sourceRecord);
            x++;
        }

        System.out.println("\n\n\nhere is the data" + sourceRecord);

        if (x > 0) log.info(String.format("\n\n\nFetched %s record(s)", x));

        return records;
    }


    public SourceRecord generateSourceRecord(SalesModel sale) {
        return new SourceRecord(
                sourcePartition(),
                sourceOffset(sale.getSale_id()),
                config.getTopicConfig(),
                null,
                Keyschema,
                buildRecordKey(),
                ValueSchema,
                buildRecordValue(sale)
        );
    }
    @Override
    public void stop() {

    }

    private Map<String,String>  sourcePartition() {
        Map<String,String> map = new HashMap<>();
        map.put(OWNER_URL_CONFIG,config.getOwnerConfig());
        map.put(AUTH_USERNAME_CONFIG,config.getAuthUsernameConfig());

        return map;
    }
    private Map<String, String> sourceOffset(Integer updatedAt) {
        Map<String, String> map = new HashMap<>();

        map.put(SALE_ID, lastIdNumber.toString());
        return map;
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
                .put( PARTNER_NAME,sale.getPartner_name())
                .put( COMPANY_NAME,sale.getCompany_name())
                .put( PRODUCT_NAME,sale.getProduct_name())
                .put(SALE_ID, sale.getSale_id())
                .put(INVOICE_STATUS,sale.getInvoice_status());
        return valueStruct;
    }
}
