package com.nema.kafka;

import org.apache.kafka.common.config.ConfigDef;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.nema.kafka.MySourceConnectorConfig.*;

public class MySourceConnectorConfigTest {
    private ConfigDef configDef = MySourceConnectorConfig.config();

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
    public void doc(){
        System.out.println(MySourceConnectorConfig.config().toRst());
    }

    @Test
    public void canReadConfigCorrectly() {
        MySourceConnectorConfig config = new MySourceConnectorConfig(initialConfig());
        config.getAuthPasswordConfig();
        config.getAuthUsernameConfig();
        config.getAuthDatabaseConfig();

    }
}
