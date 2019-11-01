package com.nema.kafka;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;

public class SaleSchema {
    public static String PRODUCT_ID = "product_id";
    public static String ORDER_ID ="order_id";
    public static String NAME = "name";
    public static String PRICE_TOTAL = "price_total";
    public static String PRICE_UNIT = "price_unit";
    public static String CREATE_DATE = "create_date";
    public static String WRITE_DATE = "write_date";
    public static String AMOUNT_TAX = "amount_tax";
    public static String AMOUNT_TOTAL = "amount_total";
    public static String AMOUNT_UNTAXED = "amount_untaxed";
    public static String COMPANY_ID = "company_id";
    public static String CONFIRMATION_DATE = "confirmation_date";
    public static String PARTNER_ID = "partner_id";
    public static String INVOICE_STATUS = "invoice_status";
    public static String COMPANY_NAME = "company_name";
    public static String PRODUCT_NAME = "product_name";
    public static String PARTNER_NAME = "partner_name";
    public static String TABLE_NAME = "sale_table";
//
//    public static String COMPANY_NAME = "";
//    public static String PARTNER_NAME = "";
//    public static String ORDER_NAME ="";
//    public static String PRODUCT_NAME= "";


    private static final String VSNAME = "schema_name";
    public static Schema ValueSchema = SchemaBuilder.struct().name(VSNAME)
        .version(1)
            .field(PRODUCT_ID,Schema.INT32_SCHEMA)
//            .field(ORDER_ID,Schema.STRING_SCHEMA)
            .field(NAME,Schema.STRING_SCHEMA)
            .field( PRICE_TOTAL, Schema.FLOAT32_SCHEMA)
            .field( PRICE_UNIT, Schema.FLOAT32_SCHEMA)
            .field( CREATE_DATE,Schema.STRING_SCHEMA)
            .field( WRITE_DATE,Schema.STRING_SCHEMA)
            .field( AMOUNT_TAX, Schema.FLOAT32_SCHEMA)
            .field( AMOUNT_TOTAL, Schema.FLOAT32_SCHEMA)
            .field( AMOUNT_UNTAXED , Schema.FLOAT32_SCHEMA)
            .field(COMPANY_ID ,Schema.INT32_SCHEMA)
            .field( CONFIRMATION_DATE,Schema.STRING_SCHEMA)
            .field( PARTNER_ID,Schema.INT32_SCHEMA)
            .field(INVOICE_STATUS,Schema.STRING_SCHEMA)
            .field(COMPANY_NAME,Schema.STRING_SCHEMA)
            .field(PRODUCT_NAME,Schema.STRING_SCHEMA)
            .field(PARTNER_NAME,Schema.STRING_SCHEMA)
        .build();


    private static final String KSSCHEMA ="schema_key" ;
    public static Schema Keyschema = SchemaBuilder.struct().name(KSSCHEMA)
            .version(1)
            .field("tableName",Schema.STRING_SCHEMA)
            .field("tableID",Schema.STRING_SCHEMA)
            .build();
}
