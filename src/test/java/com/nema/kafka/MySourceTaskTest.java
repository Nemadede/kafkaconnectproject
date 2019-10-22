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

    private Map<String, String> initialConfig(){
        Map<String, String> baseProps = new HashMap<>();
        baseProps.put(OWNER_URL_CONFIG , "http://localhost:8069");
        baseProps.put(AUTH_USERNAME_CONFIG, "demodb@demo.com");
        baseProps.put(AUTH_PASSWORD_CONFIG, "123456");
        baseProps.put(AUTH_DATABASE_CONFIG, "demodb");
        baseProps.put(MODEL_NAME,"sale.order");
        baseProps.put(TOPIC_CONFIG, "OdooOne");
        return baseProps;
    }

    @Test
    public void test() throws InterruptedException {
        mySourceTask.config = new MySourceConnectorConfig(initialConfig());
        mySourceTask.nextQuerySince = Instant.parse("2017-01-01T00:00:00Z");
        mySourceTask.lastIdNumber = 1;
        mySourceTask.odooApIHttpClient = new OdooApIHttpClient(mySourceTask.config);
        mySourceTask.odooApIHttpClient.login("http://localhost:8069","demodb","demodb@demo.com","123456");
       List pull =  mySourceTask.poll();
       System.out.println(pull);

    }
}
