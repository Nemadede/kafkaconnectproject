package com.nema.kafka.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static com.nema.kafka.ProductSchema.*;

public class ProductPricelist {
    private int id;
    private String name;
    private int sequence;
    private boolean active;
    private Instant write_date;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public ProductPricelist(int id, String name, int sequence, boolean active, Instant write_date, Map<String, Object> additionalProperties) {
        this.id = id;
        this.name = name;
        this.sequence = sequence;
        this.active = active;
        this.write_date = write_date;
        this.additionalProperties = additionalProperties;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Instant getWrite_date() {
        return write_date;
    }

    public void setWrite_date(Instant write_date) {
        this.write_date = write_date;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    private ProductPricelist(){}

    public static ProductPricelist fromJson(JSONObject jsonObject) throws JSONException{
        ProductPricelist productPricelist = new ProductPricelist();
        productPricelist.setId(jsonObject.getInt(FIELD_ID));
        productPricelist.setName(jsonObject.getString(NAME));
        productPricelist.setActive(jsonObject.getBoolean(ACTIVE));
        productPricelist.setSequence(jsonObject.getInt(SEQUENCE));
        System.out.println("You completed the match up " + productPricelist);
//        productPricelist.setWrite_date(jsonObject.getString(WRITE_DATE));
        return productPricelist;
    }
}
