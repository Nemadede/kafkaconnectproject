package com.nema.kafka;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nema.kafka.MySourceConnectorConfig.*;
import static com.nema.kafka.MySourceConnectorConfig.TOPIC_CONFIG;

public class MySourceTaskTest {
    MySourceTask mySourceTask = new MySourceTask();
    tstClass testclass = new tstClass();


    private Map<String, String> initialConfig(){
        Map<String, String> baseProps = new HashMap<>();
        baseProps.put(OWNER_URL_CONFIG , "http://localhost:8069");
        baseProps.put(AUTH_USERNAME_CONFIG, "demodb@demo.com");
        baseProps.put(AUTH_PASSWORD_CONFIG, "123456");
        baseProps.put(AUTH_DATABASE_CONFIG, "demodb");
        baseProps.put(MODEL_NAME,"product.pricelist");
        baseProps.put(TOPIC_CONFIG, "OdooOne");
        return baseProps;
    }

//    @Test
//    public void test() throws InterruptedException {
//        mySourceTask.config = new MySourceConnectorConfig(initialConfig());
//        mySourceTask.start(initialConfig());
//      mySourceTask.poll();
//    }

    @Test
    public void test7() throws InterruptedException {
testclass.config = new MySourceConnectorConfig(initialConfig());
testclass.start(initialConfig());
        List pull =  testclass.poll();
        System.out.println(pull);

    }

}
