package com.nema.kafka;

import com.nema.kafka.validate.UserNameValidator;
import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.apache.kafka.common.config.ConfigDef.Importance;
import com.github.jcustenborder.kafka.connect.utils.config.ConfigKeyBuilder;
import java.util.Map;



public class MySourceConnectorConfig extends AbstractConfig {

//  public static final String MY_SETTING_CONFIG = "my.setting";
//  private static final String MY_SETTING_DOC = "This is a setting important to my connector.";
  public static final String TOPIC_CONFIG = "topic";
  private static final String TOPIC_DOC = "Topic to write to.";

  public static final String OWNER_URL_CONFIG = "ownerUrl";
  private static final String OWNER_DOC = "the user who needs our services";

//  public static final String SINCE_CONFIG = "since.timestamp";
//  private static final String SINCE_DOC = "records updated after initial return will be return.\n"
//          + "This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ.\n";

  public static final String AUTH_USERNAME_CONFIG = "auth.odooUser";
  private static final String AUTH_USERNAME_DOC = "user name for authentication calls";

  public static final String AUTH_PASSWORD_CONFIG = "auth.password";
  private static final String AUTH_PASSWORD_DOC = "password for authentication calls";

  public static final String AUTH_DATABASE_CONFIG = "auth.db";
  private static final String AUTH_DATABASE_DOC = "database name for authentication calls";

  public static final String MODEL_NAME = "Model.name";
  private  static final String MODEL_NAME_DOC = "the database table name";

//  public static final String AUTH_HOST_CONFIG = "auth.url";
//  private static final String AUTH_HOST_DOC = "url of odoo instance for authentication calls";


//  public final String mySetting;

  public MySourceConnectorConfig(ConfigDef config , Map<String, String> parsedConfig) {
    super(config, parsedConfig);
  }
  public MySourceConnectorConfig(Map<String,String> parsedConfig){
    this(config(),parsedConfig);
  }


  public static ConfigDef config() {
    return new ConfigDef()
          .define(TOPIC_CONFIG,Type.STRING,Importance.HIGH,TOPIC_DOC)
          .define(OWNER_URL_CONFIG,Type.STRING,Importance.HIGH,OWNER_DOC)
          .define(AUTH_USERNAME_CONFIG,Type.STRING,Importance.HIGH, AUTH_USERNAME_DOC)
          .define(AUTH_PASSWORD_CONFIG,Type.STRING,Importance.HIGH,AUTH_PASSWORD_DOC)
          .define(AUTH_DATABASE_CONFIG,Type.STRING,Importance.HIGH,AUTH_DATABASE_DOC)
            .define(MODEL_NAME, Type.STRING,Importance.HIGH,MODEL_NAME_DOC);
  }

  public  String getOwnerConfig(){return this.getString(OWNER_URL_CONFIG);}
  public  String getAuthUsernameConfig(){return this.getString(AUTH_USERNAME_CONFIG);}
  public  String getAuthPasswordConfig(){return this.getString(AUTH_PASSWORD_CONFIG);}
  public  String getAuthDatabaseConfig(){return this.getString(AUTH_DATABASE_CONFIG);}
  public  String getTopicConfig(){return this.getString(TOPIC_CONFIG);}
  public String getModelName(){return this.getString(MODEL_NAME);}
}
