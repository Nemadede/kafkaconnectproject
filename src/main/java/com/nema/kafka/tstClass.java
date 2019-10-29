package com.nema.kafka;

import com.alibaba.fastjson.JSON;
import com.github.jcustenborder.kafka.connect.utils.VersionUtil;
import com.nema.kafka.models.ProductPricelist;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
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
import com.nema.kafka.ProductSchema.*;

import static com.nema.kafka.MySourceConnectorConfig.AUTH_USERNAME_CONFIG;
import static com.nema.kafka.MySourceConnectorConfig.OWNER_URL_CONFIG;
import static com.nema.kafka.ProductSchema.*;
import static java.util.Arrays.asList;

public class tstClass extends SourceTask {

    private static final Logger log = LoggerFactory.getLogger(MySourceTask.class);
    public  MySourceConnectorConfig config;
    OdooApIHttpClient odooApIHttpClient = new OdooApIHttpClient();

    @Override
    public String version() {
        return VersionUtil.version(this.getClass());
    }

    @Override
    public void start(Map<String, String> map) {
        config = new MySourceConnectorConfig(map);
        odooApIHttpClient.login(config.getOwnerConfig(), config.getAuthDatabaseConfig(), config.getAuthUsernameConfig(),config.getAuthPasswordConfig());
        System.out.println("You reached here too");

    }

    @Override
    public List<SourceRecord> poll() throws InterruptedException {
        ArrayList<SourceRecord> records = new ArrayList<>();
        Object object = odooApIHttpClient.executeMethod( config.getModelName(),"search_read",asList(asList(
                asList("id",">",0))),new HashMap(){{
                    put("fields", asList("id","name","active","sequence"));
        }});



        System.out.println("The object   " + object.getClass().toString());
        String json = JSON.toJSONString(object);
//      System.out.println("Json data print out______________________________________________" + json);
        JSONArray jsonArray = new JSONArray(json);
        for(Object obj:jsonArray){
            ProductPricelist productPricelist = ProductPricelist.fromJson((JSONObject) obj);
            SourceRecord sourceRecord = generateSourceRecord(productPricelist);
            records.add(sourceRecord);
        }


        return records;
    }

    @Override
    public void stop() {

    }
    public SourceRecord generateSourceRecord(ProductPricelist productPricelist){
       return new SourceRecord(
               sourcePartition(),
               null,
               config.getTopicConfig(),
               0,
               Kschema,
               buildRecordKey(),
               Vschema,
               buildRecordValue(productPricelist)
       );

    }

    private Map<String,String>  sourcePartition() {
        Map<String,String> map = new HashMap<>();
        map.put(OWNER_URL_CONFIG,config.getOwnerConfig());
        map.put(AUTH_USERNAME_CONFIG,config.getAuthUsernameConfig());
        return map;
    }
    private Struct buildRecordKey(){
//        Schema schema = null;
        Struct key = new Struct(Kschema)
                .put("tableName","product_pricelist")
                .put("tableID","S003");
        return key;
    }


    private Struct buildRecordValue(ProductPricelist productPricelist){
        Struct valueStruct = new Struct(Vschema)
                .put(FIELD_ID, productPricelist.getId())
                .put(NAME,productPricelist.getName())
                .put(ACTIVE,productPricelist.isActive())
                .put(SEQUENCE,productPricelist.getSequence());


        return valueStruct;
    }


}
