package com.i2i.bicell.VoltDb;

import org.apache.logging.log4j.*;
import org.voltdb.VoltTable;
import org.voltdb.client.*;
import java.io.IOException;
public class VoltDb{
    private String id;
    private int port;
    private Client client;
    private ClientResponse response = null;
    private Logger log = LogManager.getLogger(VoltDb.class);

    public VoltDb(){
        // id and port number implementation from rumeysa
        //this.
        log.info("VoltDb constructor");
        try {
            this.client=get_ClientVoltDB();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Client get_ClientVoltDB() throws IOException {
        ClientConfig config = new ClientConfig("advent", "xYZZy");
        Client client = ClientFactory.createClient(config);
        client.createConnection ( id,port);
        log.info("VoltDB Client Granted");

        return  client;
    }
    public String get_PackageName(String MSISDN){
        try {
            response = client.callProcedure("getPackage",MSISDN);
            log.info("Getting Package Name");
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (ProcCallException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        VoltTable table =response.getResults()[0];
        table.advanceRow();
        return table.getString(1);
    }

    public long get_UidByMSISDN(String MSISDN){

        try {
            //   client = getClientVoltDB();
            response = client.callProcedure("getMSISDNWithId",MSISDN);
            log.info("Getting Uid By MSISDN");

        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (ProcCallException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        VoltTable table3 =response.getResults()[0];
        table3.advanceRow();
        return table3.getLong(0);
    }

    public void send_VoiceAmount(String MSISDN, int usedAmount,int price){
        try {
            int uid= (int) get_UidByMSISDN(MSISDN);
            String packageName = get_PackageName(MSISDN);
            //    client = getClientVoltDB();
            client.callProcedure("updateBalanceVoice",uid,usedAmount,packageName,price);
            log.info("Send amount of voice used to VoltDB");
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (ProcCallException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void send_SmsAmount(String MSISDN, int usedAmount,int price){
        try {
            int uid= (int) get_UidByMSISDN(MSISDN);
            String packageName = get_PackageName(MSISDN);
            client.callProcedure("updateBalanceSMS",uid,usedAmount,packageName,price);
            log.info("Send amount of sms used to VoltDB");
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (ProcCallException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

    }

    public void send_GbAmount(String MSISDN, int usedAmount,int price){

        try {
            int uid= (int) get_UidByMSISDN(MSISDN);
            String packageName = get_PackageName(MSISDN);
            client.callProcedure("updateBalanceDATA",uid,usedAmount,packageName,price);
            log.info("Send amount of gb used to VoltDB");

        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (ProcCallException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

}