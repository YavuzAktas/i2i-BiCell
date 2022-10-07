package org.bicell.rest.api.repository;

import org.bicell.rest.api.dbhelper.OracleHelper;
import org.bicell.rest.api.dbhelper.VoltDbHelper;
import org.bicell.rest.api.entity.Balance;
import org.bicell.rest.api.entity.Subscriber;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.voltdb.VoltTable;
import org.voltdb.client.Client;
import org.voltdb.client.ClientResponse;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BalanceRepository {

    public List<Balance> deneme() throws Exception {//String MSISDN

        /*List<Balance> remainingBalanceForUsers = new ArrayList<>();
        Balance balanceForUser=new Balance();

        OracleHelper oracleHelper = new OracleHelper();
        Connection connection = oracleHelper.getConnection();

        //SUBSC_ID
        ResultSet result=connection.createStatement().executeQuery("SELECT SUBSC_ID FROM  SUBSCRIBER WHERE MSISDN ="+MSISDN);
        int SUBSC_ID = result.getInt(1);


        //PACKAGE_ID
        result=connection.createStatement().executeQuery("SELECT (SELECT AMOUNT_VOICE FROM PACKAGE WHERE PACKAGE_ID =BALANCE.PACKAGE_ID) - BAL_LVL_VOICE FROM BALANCE WHERE SUBSC_ID ="+SUBSC_ID);
        int PACKAGE_ID = result.getInt(1);

        //VOICE
        result=connection.createStatement().executeQuery("SELECT (SELECT AMOUNT_VOICE FROM PACKAGE WHERE PACKAGE_ID =BALANCE.PACKAGE_ID) - BAL_LVL_VOICE FROM BALANCE WHERE SUBSC_ID ="+SUBSC_ID);
        long VOICE = result.getLong(1);
        balanceForUser.setLvl_voice(VOICE);


        //DATA
        result=connection.createStatement().executeQuery("SELECT (SELECT AMOUNT_DATA  FROM PACKAGE WHERE PACKAGE_ID =BALANCE.PACKAGE_ID) - BAL_LVL_DATA FROM BALANCE WHERE SUBSC_ID ="+SUBSC_ID);
        long DATA = result.getLong(1);
        balanceForUser.setLvl_data(DATA);


        //SMS
        result=connection.createStatement().executeQuery("SELECT (SELECT AMOUNT_SMS FROM PACKAGE WHERE PACKAGE_ID =BALANCE.PACKAGE_ID) - BAL_LVL_SMS FROM BALANCE WHERE SUBSC_ID = "+SUBSC_ID);
        long SMS = result.getLong(1);
        balanceForUser.setLvl_sms(SMS);


        //PRICE
        result=connection.createStatement().executeQuery("SELECT PRICE FROM BALANCE WHERE SUBSC_ID ="+SUBSC_ID);
        long PRICE = result.getLong(1);
        balanceForUser.setPrice(PRICE);


        remainingBalanceForUsers.add(new Balance(SUBSC_ID, PACKAGE_ID, VOICE, DATA, SMS, PRICE));

        return remainingBalanceForUsers;*/

        /*OracleHelper oracleHelper = new OracleHelper();
        Connection connection = oracleHelper.getConnection();



        //VOICE
        CallableStatement callableStatement = connection.prepareCall("{call package_subscriber.get_remaining_voice(?)}");
        callableStatement.setString(1, MSISDN);
        callableStatement.execute();
        int VOICE = callableStatement.getInt(1);
        System.out.println(VOICE);*/

        List<Balance> balance = new ArrayList<>();

        balance.add(new Balance(49, 22, 750, 13, 98, 90));
        balance.add(new Balance(50, 23, 643, 10, 0, 85));
        balance.add(new Balance(52, 24, 529, 9, 132, 89));
        balance.add(new Balance(54, 25, 834, 17, 400, 119));

        return balance;
    }

    public List<Balance> getBalanceByMSISDN(String MSISDN) throws Exception {
        List<Balance> remainingBalanceForUsers = new ArrayList<>();
        Balance balanceForUser = new Balance();
        VoltDbHelper voltDbHelper = new VoltDbHelper();
        Client client = voltDbHelper.client();
        ClientResponse response;

        response = client.callProcedure("getMSISDNWithId", MSISDN);
        VoltTable tableMSISDNWitId = response.getResults()[0];
        tableMSISDNWitId.advanceRow();
        int SUBSC_ID = ((int) tableMSISDNWitId.getLong(0));

        response = client.callProcedure("ShowPackageAmountVoice", SUBSC_ID);
        VoltTable tableVoiceAmount = response.getResults()[0];
        int voice = ((int) tableVoiceAmount.fetchRow(0).getLong(0));
        balanceForUser.setLvl_voice(voice);

        response = client.callProcedure("ShowPackageAmountData", SUBSC_ID);
        VoltTable tableDataAmount = response.getResults()[0];
        int data = ((int) tableDataAmount.fetchRow(0).getLong(0));
        balanceForUser.setLvl_data(data);

        response = client.callProcedure("ShowPackageAmountSMS", SUBSC_ID);
        VoltTable tableSmsAmount = response.getResults()[0];
        int SMS = ((int) tableSmsAmount.fetchRow(0).getLong(0));
        balanceForUser.setLvl_sms(SMS);

        response = client.callProcedure("ShowPrice", SUBSC_ID);
        VoltTable tablePriceAmount = response.getResults()[0];
        int price = ((int) tablePriceAmount.fetchRow(0).getLong(0));
        balanceForUser.setPrice(price);

        int package_id = balanceForUser.getPackage_id();
        int subs_id = balanceForUser.getSubs_id();

        remainingBalanceForUsers.add(new Balance(subs_id, package_id, data, SMS, voice, price));

        return remainingBalanceForUsers;
    }
}
