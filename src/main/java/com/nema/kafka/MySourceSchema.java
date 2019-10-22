package com.nema.kafka;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;


public class MySourceSchema {

    public static String NEXT_TABLE_FIELD = "next_table";
    public static String USER_INFO = "odoodemouser@odoo.com";
    //To identify the time at which the table was received and updated in the database
    public static String CREATED_AT_FIELD = "created_at";
    public static String UPDATED_AT_FIELD = "updated_at";



    //sales_order field
    public static String TABLE_ID_FIELD = "id";
    public static String ACCESS_TOKEN_FIELD = "access_token";
    public static String MESSAGE_MAIN_FIELD = "message_main_attachment_id";
    public static String TABLE_NAME_FIELD = "name";
    public static String TABLE_ORIGIN_FIELD = "origin";
    public static String CLIENT_ORDER_FIELD = "client_order_ref";
    public static String TABLE_REFERENCE_FIELD = "reference";
    public static String TABLE_STATE_FIELD = "state";
    public static String DATE_ORDER_FIELD = "date_order";
    public static String VALIDITY_DATE_FIELD = "validity_date";
    public static String REQUIRED_SIGNATURE_FIELD = "required_signature";
    public static String REQUIRE_PAYMENT_FIELD = "require_payment";
    public static String CREATE_DATE_FIELD = "create_date";
    public static String CONFIRMATION_FIELD = "confirmation_date";
    public static String USER_ID_FIELD = "user_id";
    public static String PARTNER_ID_FIELD = "partner_Id";
    public static String PARTNER_INVOICE_FIELD = "partner_invoice_id";
    public static String PARTNER_SHIPPING_FIELD = "partner_shipping_id";
    public static String PRICELIST_ID_FIELD = "pricelist_id";
    public static String ANALYTIC_ACCOUNT_FIELD = "analytic_account_id";
    public static String INVOICE_STATUS_FIELD = "invoice_status";
    public static String TABLE_NOTE_FIELD = "note";
    public static String AMOUNT_UNTAXED_FIELD = "amount_untaxed";
    public static String AMOUNT_TAX_FIELD = "amount_tax";
    public static String AMOUNT_TOTAL_FIELD = "amount_total";
    public static String CURRENCY_RATE_FIELD = "currency_rate";
    public static String PAYMENT_TERM_FIELD = "payment_term_id";
    public static String FISCAL_POSITION_FIELD = "fiscal_position_id";
    public static String COMPANY_ID_FIELD = "company_id";
    public static String TEAM_ID_FIELD = "team_id";
    public static String SIGNED_BY_FIELD = "signed_by";
    public static String COMMITMENT_DATE_FIELD = "commitment_date";
    public static String CREATE_UID_FIELD = "create_uid";
    public static String WRITE_UID_FIELD = "write_uid";
    public static String WRITE_DATE_FIELD = "write_date";
    public static String SALE_ORDER_FIELD = "sale_order_template_id";

    //schema names
    public static String SCHEMA_KEY = "Odoo Table Key";
    public static String SCHEMA_VALUE_PRODUCT_CATEGORY = "product_category";
    public static String SCHEMA_VALUE_SALES_ORDER = "sales_order";

// TODO: create key schema per user as key instead of tables
    //key schema
    public static Schema KEY_SCHEMA = SchemaBuilder.struct().name(SCHEMA_KEY)
            .version(1)
            .field(TABLE_NAME_FIELD, Schema.STRING_SCHEMA)
//            .field(TABLE_ID_FIELD, Schema.INT32_SCHEMA)
//            .field(CREATE_DATE_FIELD, Schema.STRING_SCHEMA)
//            .field(CREATE_UID_FIELD, Schema.INT32_SCHEMA)
//            .field(WRITE_DATE_FIELD, Schema.STRING_SCHEMA)
//            .field(WRITE_UID_FIELD, Schema.INT32_SCHEMA)
//            .field(DATE_ORDER_FIELD, Schema.STRING_SCHEMA)
//            .field(USER_ID_FIELD, Schema.INT32_SCHEMA)
            .build();

    //Optional schema
//    public static Schema OPT_SCHEMA = SchemaBuilder.struct().name(SCHEMA_VALUE_PRODUCT_CATEGORY)
//            .version(1)
//            .field(TABLE_NAME1_FIELD, Schema.STRING_SCHEMA)
//            .field(TABLE_ID1_FIELD, Schema.INT32_SCHEMA)
//            .field(CREATE_UID1_FIELD, Schema.INT32_SCHEMA)
//            .field(CREATE_DATE1_FIELD, Schema.STRING_SCHEMA)
//            .field(WRITE_DATE1_FIELD, Schema.STRING_SCHEMA)
//            .field(WRITE_UID1_FIELD, Schema.INT32_SCHEMA)
//            .field(PARENT_ID_FIELD, Schema.INT32_SCHEMA)
//            .optional()
//            .build();

    //Value schema
    public static Schema TABLE_SCHEMA = SchemaBuilder.struct().name(SCHEMA_VALUE_SALES_ORDER)
            .version(1)
//            .field(REQUIRED_SIGNATURE_FIELD, Schema.STRING_SCHEMA)
//            .field(REQUIRE_PAYMENT_FIELD, Schema.STRING_SCHEMA)
//            .field(PARTNER_ID_FIELD, Schema.INT32_SCHEMA)
//            .field(PARTNER_INVOICE_FIELD, Schema.INT32_SCHEMA)
//            .field(PARTNER_SHIPPING_FIELD, Schema.INT32_SCHEMA)
//            .field(PRICELIST_ID_FIELD, Schema.INT32_SCHEMA)
            .field(INVOICE_STATUS_FIELD, Schema.STRING_SCHEMA)
            .field(AMOUNT_UNTAXED_FIELD, Schema.INT32_SCHEMA)
//            .field(AMOUNT_TAX_FIELD, Schema.INT32_SCHEMA)
            .field(AMOUNT_TOTAL_FIELD, Schema.INT32_SCHEMA)
            .field(CURRENCY_RATE_FIELD, Schema.INT32_SCHEMA)
//            .field(TABLE_NOTE_FIELD,Schema.STRING_SCHEMA)
//            .field(TABLE_ORIGIN_FIELD,Schema.BOOLEAN_SCHEMA)
//            .field(ACCESS_TOKEN_FIELD,Schema.STRING_SCHEMA)
            .field(TABLE_NAME_FIELD, Schema.STRING_SCHEMA)
//            .field(COMPANY_ID_FIELD, Schema.INT32_SCHEMA)
//            .field(TEAM_ID_FIELD, Schema.INT32_SCHEMA)
//            .field(CREATED_AT_FIELD, Schema.STRING_SCHEMA)
//            .field(UPDATED_AT_FIELD, Schema.STRING_SCHEMA)

//            .field(COMPLETE_NAME_FIELD, OPT_SCHEMA) // i did this just to reference OPT_SCHEMA variable


            .build();


}
