package com.nema.kafka;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.nema.kafka.MySourceConnectorConfig.*;
import static com.nema.kafka.MySourceConnectorConfig.TOPIC_CONFIG;
//import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MySourceConnectorTest {
    private Map<String, String> initialConfig(){
        Map<String, String> baseProps = new HashMap<>();
        baseProps.put(OWNER_URL_CONFIG , "http://192.168.1.18:8069/");
        baseProps.put(AUTH_USERNAME_CONFIG, "demo@demo.com");
        baseProps.put(AUTH_PASSWORD_CONFIG, "demo");
        baseProps.put(AUTH_DATABASE_CONFIG, "Demo");
        baseProps.put(MODEL_NAME,"sale_order");
        baseProps.put(TOPIC_CONFIG, "OdooOne");
        return baseProps;
    }

    @Test
    public void taskConfigsShouldReturnOneTaskConfig() {
        MySourceConnector mySourceConnector = new MySourceConnector();
        mySourceConnector.start(initialConfig());
        assertEquals(mySourceConnector.taskConfigs(1).size(),1);
        assertEquals(mySourceConnector.taskConfigs(10).size(),1);
    }

    @Test
    public void taskReturn(){
        MySourceConnector mySourceConnector = new MySourceConnector();
        mySourceConnector.taskClass();
    }
}
