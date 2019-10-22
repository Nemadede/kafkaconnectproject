package com.nema.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.jcustenborder.kafka.connect.utils.VersionUtil;
import com.nema.kafka.models.Sales;
//import org.apache.http.client.utils.DateUtils;
import com.nema.kafka.utils.DateUtils;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nema.kafka.MySourceConnectorConfig.AUTH_USERNAME_CONFIG;
import static com.nema.kafka.MySourceConnectorConfig.OWNER_URL_CONFIG;
import static com.nema.kafka.MySourceSchema.*;
import static com.nema.kafka.OdooApIHttpClient.LOGGER;
import static java.util.Arrays.asList;

public class MySourceTask extends SourceTask {
  /*
    Your connector should never use System.out for logging. All of your classes should use slf4j
    for logging
 */
  private static final Logger log = LoggerFactory.getLogger(MySourceTask.class);
  public  MySourceConnectorConfig config;

  protected Instant nextQuerySince;
  protected Integer lastIdNumber;
  protected Integer nextIdToPull;
  protected Instant lastUpdatedAt;

  OdooApIHttpClient odooApIHttpClient;

  @Override
  public String version() {
    return VersionUtil.version(this.getClass());
  }

  @Override
  public void start(Map<String, String> map) {
    // Do things here that are required to start your task. This could be open a connection to a database, etc.
    config = new MySourceConnectorConfig(map);
//    initializeLastVariables();
    odooApIHttpClient = new OdooApIHttpClient(config);
    System.out.println( "Your configurations are...... " + config.getOwnerConfig() + config.getAuthDatabaseConfig() + config.getAuthUsernameConfig() + config.getAuthPasswordConfig());
    odooApIHttpClient.login(config.getOwnerConfig(), config.getAuthDatabaseConfig(), config.getAuthUsernameConfig(),config.getAuthPasswordConfig());
  }

//  private void initializeLastVariables() {
//    Map<String, Object> lastSourceOffset = null;
//    lastSourceOffset = context.offsetStorageReader().offset(sourcePartition());
//    if(lastSourceOffset == null) {
//      // we haven't gotten anything yet, so we initialize next_query_since to null
//      nextQuerySince = null;
//      lastIdNumber = -1;
//    } else {
//      //if we had fetched data already
//      // get the last update time field value
//      //TODO: get from schema
//      Object updatedAt = lastSourceOffset.get(UPDATED_AT_FIELD);
//      //get the id number of the last record gotten
//      //TODO: get from model
//      Object prevIdNumber = lastSourceOffset.get(CREATED_AT_FIELD);
//      //get the next id to start from
//      //TODO: get from model
//      Object nextIdNumber = lastSourceOffset.get(CREATE_UID_FIELD);
//      // conditions to get updates
//      if(updatedAt != null && (updatedAt instanceof String)){
//        nextQuerySince = Instant.parse((String) updatedAt);
//      }
//      if(prevIdNumber != null && (prevIdNumber instanceof String)){
//        lastIdNumber = Integer.valueOf((String) prevIdNumber);
//      }
//      if(nextIdNumber != null && (nextIdNumber instanceof String)){
//        nextIdToPull = Integer.valueOf((String) nextIdNumber);
//      }
//    }
//  }



  @Override
  public List<SourceRecord> poll() throws InterruptedException {
    //fetch data
    final ArrayList<SourceRecord> records = new ArrayList<>();
    //TODO: get the table fields in json formate, from getRecords method from the Api

    String[] params = {config.getAuthDatabaseConfig(),config.getAuthUsernameConfig(),config.getAuthPasswordConfig()};
//    Map<String,String> res;
//      odooApIHttpClient.login(config.getOwnerConfig(),config.getAuthDatabaseConfig(),config.getAuthUsernameConfig(),config.getAuthPasswordConfig());
    Object object = odooApIHttpClient.executeMethod(config.getModelName(),"search_read",asList(asList(
            asList("amount_total", ">", 0))),
            new HashMap() {{
                put("fields", asList("name","state","date_order","require_signature","require_payment","create_date","confirmation_date","user_id","partner_id","partner_invoice_id","partner_shipping_id","pricelist_id","amount_total", "currency_rate", "amount_untaxed","invoice_status","currency_rate","payment_term_id","company_id","team_id","commitment_date","create_uid","write_uid", "write_date","sale_order_template_id","campaign_id","source_id","medium_id","opportunity_id"));
            }});

//    JSONArray recordsArray = new JSONArray(object);
System.out.println( "here is the object" + object);
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    try {
      String json = ow.writeValueAsString(object);
      JSONArray json2 = new JSONArray(json);
//      System.out.println(json2);
      int i =0;
      for(Object obj: json2){
        i++;
        System.out.println(" Here is the first record gotten from the object:::::  "+ obj);
        Sales sale = Sales.fromJson((JSONObject) obj);
        System.out.println("sales " + sale);
        System.out.println(" the other sales" +generateSourceRecord(sale));
        SourceRecord sourceRecord = generateSourceRecord(sale);

        records.add(sourceRecord);
        System.out.println(sourceRecord);
          i++;
      }
System.out.println(i);
//      for (int i = 0; i<json2.length(); i++){
//        System.out.println(json2.length());
//        Sales sale = Sales.fromJson(json2.getJSONObject(i) );
////       SourceRecord sourceRecord = generateSourceRecord(sale);
////       records.add(sourceRecord);
//      }
   } catch (JsonProcessingException | JSONException e) {
        LOGGER.error("This is an error from the source task " + e);
 }
    return records;
  }

  private SourceRecord generateSourceRecord(Sales sale) {
    return new SourceRecord(
            sourcePartition(),
            null,
            config.getTopicConfig(),
            1,
            KEY_SCHEMA,
            buildRecordKey(sale),
            TABLE_SCHEMA,
            buildRecordValue(sale)
    );
  }

  @Override
  public void stop() {
    //Do whatever is required to stop your task.
  }
  // source partition method
  private Map<String,String>  sourcePartition() {
    Map<String,String> map = new HashMap<>();
    map.put(OWNER_URL_CONFIG,config.getOwnerConfig());
    map.put(AUTH_USERNAME_CONFIG, config.getAuthUsernameConfig());
    return map;
  }

  private Map<String,String> sourceOffset(Instant UpdatedAt){
      Map<String,String> map = new HashMap<>();
    map.put(UPDATED_AT_FIELD, DateUtils.MaxInstant(UpdatedAt, nextQuerySince).toString());
      return map;
  }

  private Struct buildRecordKey(Sales sale){
    //key schema
    Struct key = new Struct(KEY_SCHEMA)
            .put(TABLE_NAME_FIELD, config.getModelName());
//            .put(CREATE_DATE_FIELD, sale.getCreated_date())
//            .put(USER_ID_FIELD, sale.getId());
    return key;
  }

  private Struct buildRecordValue(Sales sale){
      Struct valueStruct = new Struct(TABLE_SCHEMA)
//              .put(REQUIRE_PAYMENT_FIELD, sale.getRequire_payment())
//              .put(PARTNER_ID_FIELD, sale.getPartner_id())
//              .put(PARTNER_INVOICE_FIELD, sale.getPartner_invoice_id())
//              .put(PARTNER_SHIPPING_FIELD, sale.getPartner_invoice_id())
//              .put(PRICELIST_ID_FIELD, sale.getPricelist_id())
              .put(INVOICE_STATUS_FIELD, sale.getInvoice_status())
              .put(AMOUNT_UNTAXED_FIELD, sale.getAmount_untaxed())
//              .put(AMOUNT_TAX_FIELD, sale.getAmount_tax())
              .put(AMOUNT_TOTAL_FIELD,sale.getAmount_total())
              .put(CURRENCY_RATE_FIELD,sale.getCurrency_rate())
//              .put(ACCESS_TOKEN_FIELD,sale.getAccess_token())
//              .put(TABLE_NOTE_FIELD,sale.getNote())
//              .put(TABLE_ORIGIN_FIELD,sale.getOrigin())
//              .put(COMPANY_ID_FIELD,sale.getCompany_id())
//              .put(TEAM_ID_FIELD, sale.getTeam_id())
              ;
      return valueStruct;
  }

}