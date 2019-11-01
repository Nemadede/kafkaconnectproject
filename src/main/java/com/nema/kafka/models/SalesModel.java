package com.nema.kafka.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

import static com.nema.kafka.SaleSchema.*;

public class SalesModel {

 //from sale_order_line table
    private int product_id;
    private String name;
    private float price_total;
    private float price_unit;
    private String create_date;
    private String write_date;
//from sale order table
private float amount_tax;
private float amount_total;
private float amount_untaxed;
private int company_id;
private String company_name;
private String product_name;
private String partner_name;
private  String confirmation_date;
private int  partner_id;
private String invoice_status;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public SalesModel(int product_id, String name, float price_total, float price_unit, String create_date, String write_date, float amount_tax, float amount_total, float amount_untaxed, int company_id, String company_name, String product_name, String partner_name, String confirmation_date, int partner_id, String invoice_status, Map<String, Object> additionalProperties) {
        this.product_id = product_id;
        this.name = name;
        this.price_total = price_total;
        this.price_unit = price_unit;
        this.create_date = create_date;
        this.write_date = write_date;
        this.amount_tax = amount_tax;
        this.amount_total = amount_total;
        this.amount_untaxed = amount_untaxed;
        this.company_id = company_id;
        this.company_name = company_name;
        this.product_name = product_name;
        this.partner_name = partner_name;
        this.confirmation_date = confirmation_date;
        this.partner_id = partner_id;
        this.invoice_status = invoice_status;
        this.additionalProperties = additionalProperties;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice_total() {
        return price_total;
    }

    public void setPrice_total(float price_total) {
        this.price_total = price_total;
    }

    public float getPrice_unit() {
        return price_unit;
    }

    public void setPrice_unit(float price_unit) {
        this.price_unit = price_unit;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getWrite_date() {
        return write_date;
    }

    public void setWrite_date(String write_date) {
        this.write_date = write_date;
    }

    public float getAmount_tax() {
        return amount_tax;
    }

    public void setAmount_tax(float amount_tax) {
        this.amount_tax = amount_tax;
    }

    public float getAmount_total() {
        return amount_total;
    }

    public void setAmount_total(float amount_total) {
        this.amount_total = amount_total;
    }

    public float getAmount_untaxed() {
        return amount_untaxed;
    }

    public void setAmount_untaxed(float amount_untaxed) {
        this.amount_untaxed = amount_untaxed;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPartner_name() {
        return partner_name;
    }

    public void setPartner_name(String partner_name) {
        this.partner_name = partner_name;
    }

    public String getConfirmation_date() {
        return confirmation_date;
    }

    public void setConfirmation_date(String confirmation_date) {
        this.confirmation_date = confirmation_date;
    }

    public int getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(int partner_id) {
        this.partner_id = partner_id;
    }

    public String getInvoice_status() {
        return invoice_status;
    }

    public void setInvoice_status(String invoice_status) {
        this.invoice_status = invoice_status;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    private SalesModel(){}

    public static SalesModel fromJson(JSONObject jsonObject) throws JSONException {
        SalesModel salesModel = new SalesModel();
        salesModel.setProduct_id(jsonObject.getInt(PRODUCT_ID));
//        salesModel.setOrder_id(jsonObject.getString(ORDER_ID));
        salesModel.setName(jsonObject.getString(NAME));
        salesModel.setPrice_total(jsonObject.getFloat(PRICE_TOTAL));
        salesModel.setPrice_unit(jsonObject.getFloat(PRICE_UNIT));
        salesModel.setCreate_date(jsonObject.getString(CREATE_DATE));
        salesModel.setWrite_date(jsonObject.getString(WRITE_DATE));
        salesModel.setAmount_tax(jsonObject.getFloat(AMOUNT_TAX));
        salesModel.setAmount_total(jsonObject.getFloat(AMOUNT_TOTAL));
        salesModel.setAmount_untaxed(jsonObject.getFloat(AMOUNT_UNTAXED));
        salesModel.setCompany_id(jsonObject.getInt(COMPANY_ID));
        salesModel.setConfirmation_date(jsonObject.getString(CONFIRMATION_DATE));
        salesModel.setPartner_id(jsonObject.getInt(PARTNER_ID));
        salesModel.setInvoice_status(jsonObject.getString(INVOICE_STATUS));
        salesModel.setCompany_name(jsonObject.getString(COMPANY_NAME));
        salesModel.setPartner_name(jsonObject.getString(PARTNER_NAME));
        salesModel.setProduct_name(jsonObject.getString(PRODUCT_NAME));
        return salesModel;
    }
}
