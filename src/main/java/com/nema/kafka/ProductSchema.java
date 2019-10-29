package com.nema.kafka;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;

public class ProductSchema {
    public static String FIELD_ID = "id";
    public static String NAME = "name";
    public static String ACTIVE = "active";
    public static String SEQUENCE = "sequence";
    public static String WRITE_DATE = "write_date";
    public static String SCHEMA_KEY = "OdooTableKey";
    public static String SCHEMA_VALUE= "product_pricelist";

   public static Schema Vschema = SchemaBuilder.struct().name(SCHEMA_VALUE)
            .version(1)
            .field(FIELD_ID,Schema.INT32_SCHEMA)
            .field(NAME,Schema.STRING_SCHEMA)
            .field(ACTIVE,Schema.BOOLEAN_SCHEMA)
            .field(SEQUENCE,Schema.INT32_SCHEMA)
//            .field(WRITE_DATE,Schema.OPTIONAL_STRING_SCHEMA)
            .build();
   public static Schema Kschema = SchemaBuilder.struct().name(SCHEMA_KEY)
            .version(1)
            .field("tableName",Schema.STRING_SCHEMA)
            .field("tableID",Schema.STRING_SCHEMA)
            .build();
}
