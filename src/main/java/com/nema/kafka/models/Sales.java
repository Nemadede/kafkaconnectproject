package com.nema.kafka.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static com.nema.kafka.MySourceSchema.*;

public class Sales {
    private int amount_tax;
    private int amount_total;
    private int amount_untaxed;
//    private String client_order_ref;
    private int company_id;
    private String create_date;
    private String confirmation_date;
    private String date_order;
    private int fiscal_position_id;
    private String invoice_status;
    private String name;
    private String note;
    private boolean origin;
    private int partner_id;
    private int partner_invoice_id;
    private int partner_shipping_id;
    private int payment_term_id;
    private int pricelist_id;
    private String state;
    private int user_id;
    private String updated_date;
    private String created_date;
    private int id;
    private String access_token;
    private int message_main_attachment_id;
    private String reference;
    private String validity_date;
    private boolean required_signature;
    private boolean require_payment;
    private int analytic_account_id;
    private int currency_rate;
    private int team_id;
    private String signed_by;
    private String commitment_date;
    private int create_uid;
    private int write_uid;
    private String write_date;
    private int sale_order_template_id;

    private Object closedAt;
    private Instant createdAt;
    private Instant updatedAt;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Sales(int amount_tax, int amount_total, int amount_untaxed, int company_id, String create_date, String confirmation_date, String date_order, int fiscal_position_id, String invoice_status, String name, String note, boolean origin, int partner_id, int partner_invoice_id, int partner_shipping_id, int payment_term_id, int pricelist_id, String state, int user_id, String updated_date, String created_date, int id, String access_token, int message_main_attachment_id, String reference, String validity_date, Boolean required_signature, Boolean require_payment, int analytic_account_id, int currency_rate, int team_id, String signed_by, String commitment_date, int create_uid, int write_uid, String write_date, int sale_order_template_id, Object closedAt, Instant createdAt, Instant updatedAt, Map<String, Object> additionalProperties) {


        this.amount_tax = amount_tax;
        this.amount_total = amount_total;
        this.amount_untaxed = amount_untaxed;
        this.company_id = company_id;
        this.create_date = create_date;
        this.confirmation_date = confirmation_date;
        this.date_order = date_order;
        this.fiscal_position_id = fiscal_position_id;
        this.invoice_status = invoice_status;
        this.name = name;
        this.note = note;
        this.origin = origin;
        this.partner_id = partner_id;
        this.partner_invoice_id = partner_invoice_id;
        this.partner_shipping_id = partner_shipping_id;
        this.payment_term_id = payment_term_id;
        this.pricelist_id = pricelist_id;
        this.state = state;
        this.user_id = user_id;
        this.updated_date = updated_date;
        this.created_date = created_date;
        this.id = id;
        this.access_token = access_token;
        this.message_main_attachment_id = message_main_attachment_id;
        this.reference = reference;
        this.validity_date = validity_date;
        this.required_signature = required_signature;
        this.require_payment = require_payment;
        this.analytic_account_id = analytic_account_id;
        this.currency_rate = currency_rate;
        this.team_id = team_id;
        this.signed_by = signed_by;
        this.commitment_date = commitment_date;
        this.create_uid = create_uid;
        this.write_uid = write_uid;
        this.write_date = write_date;
        this.sale_order_template_id = sale_order_template_id;
        this.closedAt = closedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.additionalProperties = additionalProperties;
    }


    public int getAmount_tax() {
        return amount_tax;
    }

    public void setAmount_tax(int amount_tax) {
        this.amount_tax = amount_tax;
    }

    public int getAmount_total() {
        return amount_total;
    }

    public void setAmount_total(int amount_total) {
        this.amount_total = amount_total;
    }

    public int getAmount_untaxed() {
        return amount_untaxed;
    }

    public void setAmount_untaxed(int amount_untaxed) {
        this.amount_untaxed = amount_untaxed;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getConfirmation_date() {
        return confirmation_date;
    }

    public void setConfirmation_date(String confirmation_date) {
        this.confirmation_date = confirmation_date;
    }

    public String getDate_order() {
        return date_order;
    }

    public void setDate_order(String date_order) {
        this.date_order = date_order;
    }

    public int getFiscal_position_id() {
        return fiscal_position_id;
    }

    public void setFiscal_position_id(int fiscal_position_id) {
        this.fiscal_position_id = fiscal_position_id;
    }

    public String getInvoice_status() {
        return invoice_status;
    }

    public void setInvoice_status(String invoice_status) {
        this.invoice_status = invoice_status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean getOrigin() {
        return origin;
    }

    public void setOrigin(boolean origin) {
        this.origin = origin;
    }

    public int getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(int partner_id) {
        this.partner_id = partner_id;
    }

    public int getPartner_invoice_id() {
        return partner_invoice_id;
    }

    public void setPartner_invoice_id(int partner_invoice_id) {
        this.partner_invoice_id = partner_invoice_id;
    }

    public int getPartner_shipping_id() {
        return partner_shipping_id;
    }

    public void setPartner_shipping_id(int partner_shipping_id) {
        this.partner_shipping_id = partner_shipping_id;
    }

    public int getPayment_term_id() {
        return payment_term_id;
    }

    public void setPayment_term_id(int payment_term_id) {
        this.payment_term_id = payment_term_id;
    }

    public int getPricelist_id() {
        return pricelist_id;
    }

    public void setPricelist_id(int pricelist_id) {
        this.pricelist_id = pricelist_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getMessage_main_attachment_id() {
        return message_main_attachment_id;
    }

    public void setMessage_main_attachment_id(int message_main_attachment_id) {
        this.message_main_attachment_id = message_main_attachment_id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getValidity_date() {
        return validity_date;
    }

    public void setValidity_date(String validity_date) {
        this.validity_date = validity_date;
    }

    public boolean getRequired_signature() {
        return required_signature;
    }

    public void setRequired_signature(boolean required_signature) {
        this.required_signature = required_signature;
    }

    public boolean getRequire_payment() {
        return require_payment;
    }

    public void setRequire_payment(boolean require_payment) {
        this.require_payment = require_payment;
    }

    public int getAnalytic_account_id() {
        return analytic_account_id;
    }

    public void setAnalytic_account_id(int analytic_account_id) {
        this.analytic_account_id = analytic_account_id;
    }

    public int getCurrency_rate() {
        return currency_rate;
    }

    public void setCurrency_rate(int currency_rate) {
        this.currency_rate = currency_rate;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getSigned_by() {
        return signed_by;
    }

    public void setSigned_by(String signed_by) {
        this.signed_by = signed_by;
    }

    public String getCommitment_date() {
        return commitment_date;
    }

    public void setCommitment_date(String commitment_date) {
        this.commitment_date = commitment_date;
    }

    public int getCreate_uid() {
        return create_uid;
    }

    public void setCreate_uid(int create_uid) {
        this.create_uid = create_uid;
    }

    public int getWrite_uid() {
        return write_uid;
    }

    public void setWrite_uid(int write_uid) {
        this.write_uid = write_uid;
    }

    public String getWrite_date() {
        return write_date;
    }

    public void setWrite_date(String write_date) {
        this.write_date = write_date;
    }

    public int getSale_order_template_id() {
        return sale_order_template_id;
    }

    public void setSale_order_template_id(int sale_order_template_id) {
        this.sale_order_template_id = sale_order_template_id;
    }

    public Object getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Object closedAt) {
        this.closedAt = closedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

   private Sales(){ }
    public static Sales fromJson(JSONObject jsonObject) throws JSONException {
        System.out.println("here you called me");
        Sales sales = new Sales();
        System.out.println("here you");
//        sales.setAmount_tax(jsonObject.getInt(AMOUNT_TAX_FIELD));System.out.println("here you");
        sales.setAmount_total(jsonObject.getInt(AMOUNT_TOTAL_FIELD));
        sales.setAmount_untaxed(jsonObject.getInt(AMOUNT_UNTAXED_FIELD));
//        sales.setClient_order_ref(jsonObject.getString(CLIENT_ORDER_FIELD));
//        sales.setCompany_id(jsonObject.getInt(COMPANY_ID_FIELD));System.out.println("here you");
//        sales.setCreate_date(jsonObject.getString(CREATE_DATE_FIELD));
//        sales.setConfirmation_date(jsonObject.getString(CONFIRMATION_FIELD));System.out.println(" 2 here you");
//        sales.setDate_order(jsonObject.getString(DATE_ORDER_FIELD));
//        sales.setFiscal_position_id(jsonObject.getInt(FISCAL_POSITION_FIELD));
        sales.setInvoice_status(jsonObject.getString(INVOICE_STATUS_FIELD));
        sales.setName(jsonObject.getString(TABLE_NAME_FIELD));
//        sales.setNote(jsonObject.getString(TABLE_NOTE_FIELD));
//        sales.setOrigin(jsonObject.getBoolean(TABLE_ORIGIN_FIELD)); System.out.println("here you");
//        sales.setPartner_id(jsonObject.getInt(PARTNER_ID_FIELD));
//        sales.setPartner_invoice_id(jsonObject.getInt(PARTNER_INVOICE_FIELD));System.out.println(" 1 here you");
//        sales.setPartner_shipping_id(jsonObject.getInt(PARTNER_SHIPPING_FIELD));
//        sales.setPayment_term_id(jsonObject.getInt(PAYMENT_TERM_FIELD));
//        sales.setPricelist_id(jsonObject.getInt(PRICELIST_ID_FIELD));
        sales.setState(jsonObject.getString(TABLE_STATE_FIELD));System.out.println("2 here you");
//        sales.setUser_id(jsonObject.getInt(USER_ID_FIELD));
//        sales.setId(jsonObject.getInt(TABLE_ID_FIELD));System.out.println("5 here you");
//        sales.setAccess_token(jsonObject.getString(ACCESS_TOKEN_FIELD)); System.out.println("3 here you");
//        sales.setMessage_main_attachment_id(jsonObject.getInt(MESSAGE_MAIN_FIELD));
//        sales.setReference(jsonObject.getString(TABLE_REFERENCE_FIELD));System.out.println("2 here you");
//        sales.setValidity_date(jsonObject.getString(VALIDITY_DATE_FIELD));
//        sales.setRequired_signature(jsonObject.getBoolean(REQUIRED_SIGNATURE_FIELD)); System.out.println("2 here you");
//        sales.setRequire_payment(jsonObject.getBoolean(REQUIRE_PAYMENT_FIELD)); System.out.println("2 here you");
//        sales.setAnalytic_account_id(jsonObject.getInt(ANALYTIC_ACCOUNT_FIELD));
        sales.setCurrency_rate(jsonObject.getInt(CURRENCY_RATE_FIELD));
//        sales.setTeam_id(jsonObject.getInt(TEAM_ID_FIELD));
//        sales.setSigned_by(jsonObject.getString(SIGNED_BY_FIELD));

//        sales.setCommitment_date(jsonObject.getString(COMMITMENT_DATE_FIELD));
//        sales.setCreate_uid(jsonObject.getInt(CREATE_UID_FIELD));
//        sales.setWrite_uid(jsonObject.getInt(WRITE_UID_FIELD));

//        sales.setWrite_date(jsonObject.getString(WRITE_DATE_FIELD));
//        sales.setSale_order_template_id(jsonObject.getInt(SALE_ORDER_FIELD));

        System.out.println("You completed the match up " + sales);
        return sales;
    }
}
