package com.nema.kafka;

import com.alibaba.fastjson.JSON;
import com.github.jcustenborder.kafka.connect.utils.VersionUtil;
import com.nema.kafka.models.Sales;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.json.JSONArray;
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
import static java.util.Arrays.asList;

//import org.apache.http.client.utils.DateUtils;

public class MySourceTask extends SourceTask {
  public  MySourceConnectorConfig config;
  private static final Logger log = LoggerFactory.getLogger(MySourceTask.class);
  OdooApIHttpClient odooApIHttpClient = new OdooApIHttpClient();

  @Override
  public String version() {
    return VersionUtil.version(this.getClass());
  }


  @Override
  public void start(Map<String, String> map) {
    config = new MySourceConnectorConfig(map);
    odooApIHttpClient = new OdooApIHttpClient(config);
      odooApIHttpClient.login(config.getOwnerConfig(), config.getAuthDatabaseConfig(), config.getAuthUsernameConfig(),config.getAuthPasswordConfig());
      System.out.println("You reached here too");
  }

  @Override
  public List<SourceRecord> poll() throws InterruptedException {
    //fetch data
    final ArrayList<SourceRecord> records = new ArrayList<>();
//    System.out.println("See this _______" + config.getModelName());
    Object object = odooApIHttpClient.executeMethod( config.getModelName(),"search_read",asList(asList(
            asList("amount_total", ">", 0))),
            new HashMap() {{
//              put("fields", asList("name","state","date_order","require_signature","require_payment","create_date","confirmation_date","user_id","partner_id","partner_invoice_id","partner_shipping_id","pricelist_id","amount_total", "currency_rate", "amount_untaxed","invoice_status","currency_rate","payment_term_id","company_id","team_id","commitment_date","create_uid","write_uid", "write_date","sale_order_template_id","campaign_id","source_id","medium_id","opportunity_id"));
              put("fields", asList("name","state","amount_total", "currency_rate","amount_untaxed","invoice_status"));

            }});
//   JSONArray json = objectToJSONArray(object);
//    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//    String json = null;
//    try {
//      json = ow.writeValueAsString(object);
//    } catch (JsonProcessingException e) {
//      e.printStackTrace();
////    }
      System.out.println("The object   " + object.getClass().toString());
      String json = JSON.toJSONString(object);
//      System.out.println("Json data print out______________________________________________" + json);

    JSONArray json2 = new JSONArray(json);
//    System.out.println( "Here is nine's request________________________" + json2);
   for(Object obj: json2){
     Sales sale = Sales.fromJson((JSONObject) obj);
     SourceRecord sourceRecord = generateSourceRecord(sale);
     System.out.println("Here we go ++++++++++++++++++++++++++++++++ " + sourceRecord);
     records.add(sourceRecord);
   }
//
    return records;
  }

  public SourceRecord generateSourceRecord(Sales sale) {
      return new SourceRecord(
              sourcePartition(),
              null,
              config.getTopicConfig(),
              0,
              KEY_SCHEMA,
              buildRecordKey(sale),
              TABLE_SCHEMA,
              buildRecordValue(sale)
      );
  }

  //required to stop task
  @Override
  public void stop() {
    //Do whatever is required to stop your task.
  }


  // source partition method
  private Map<String,String>  sourcePartition() {
    Map<String,String> map = new HashMap<>();
map.put(OWNER_URL_CONFIG,config.getOwnerConfig());
map.put(AUTH_USERNAME_CONFIG,config.getAuthUsernameConfig());

    return map;
  }

  //source offset method
  private Map<String,String> sourceOffset(Instant UpdatedAt){
      Map<String,String> map = new HashMap<>();

      return map;
  }

  private Struct buildRecordKey(Sales sale){
    //key schema
    Struct key = new Struct(KEY_SCHEMA)
            .put(TABLE_NAME_FIELD, sale.getName());
//            .put(CREATE_DATE_FIELD, sale.getCreated_date())
//            .put(USER_ID_FIELD, sale.getId());
    return key;

  }

  private Struct buildRecordValue(Sales sale){
      Struct valueStruct = new Struct(TABLE_SCHEMA)
            .put(INVOICE_STATUS_FIELD, sale.getInvoice_status())
            .put(AMOUNT_UNTAXED_FIELD, sale.getAmount_untaxed())
//              .put(AMOUNT_TAX_FIELD, sale.getAmount_tax())
            .put(AMOUNT_TOTAL_FIELD,sale.getAmount_total())
            .put(CURRENCY_RATE_FIELD,sale.getCurrency_rate())
//              .put(TABLE_STATE_FIELD,sale.getState())
//              .put(ACCESS_TOKEN_FIELD,sale.getAccess_token())
//              .put(TABLE_NOTE_FIELD,sale.getNote())
//              .put(TABLE_ORIGIN_FIELD,sale.getOrigin())
//              .put(COMPANY_ID_FIELD,sale.getCompany_id())
//              .put(TEAM_ID_FIELD, sale.getTeam_id())
    //              .put(REQUIRE_PAYMENT_FIELD, sale.getRequire_payment())
//              .put(PARTNER_ID_FIELD, sale.getPartner_id())
//              .put(PARTNER_INVOICE_FIELD, sale.getPartner_invoice_id())
//              .put(PARTNER_SHIPPING_FIELD, sale.getPartner_invoice_id())
//              .put(PRICELIST_ID_FIELD, sale.getPricelist_id())
    ;
      return valueStruct;
  }

  //convert the object to json object
//  public static JSONArray objectToJSONArray(Object object){
//    Object json = null;
//    JSONArray jsonArray = null;
//    try {
//      json = new JSONTokener(object.toString()).nextValue();
//    } catch (JSONException e) {
//      e.printStackTrace();
//    }
//    if (json instanceof JSONArray) {
//      jsonArray = (JSONArray) json;
//    }
//    return jsonArray;
//  }

  //

}