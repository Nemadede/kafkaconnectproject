package com.nema.kafka.validate;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigException;
import org.apache.xmlrpc.client.XmlRpcClient;

import javax.print.DocFlavor;
import java.lang.Object;

import java.io.IOException;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;


public class UserNameValidator implements ConfigDef.Validator {
//    final XmlRpcClient client = new XmlRpcClient();
    @Override
    public void ensureValid(String name, Object value) {
        String user = (String) value;
        try{
           new URL(user).toURI();
        } catch (IOException | URISyntaxException e){
            throw new ConfigException(name,value,"check your user name and retry use format https://(name).odoo.com or http://IP:port");
    }
}
}
