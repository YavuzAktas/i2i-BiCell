package org.bicell.rest.api.repository;

import org.bicell.rest.api.dbhelper.OracleHelper;
import org.bicell.rest.api.dbhelper.VoltDbHelper;
import org.bicell.rest.api.entity.Subscriber;
import org.bicell.rest.api.response.ResponseMessage;
import org.springframework.stereotype.Repository;
import org.voltdb.VoltTable;
import org.voltdb.client.Client;
import org.voltdb.client.ClientResponse;
//import com.mycompany.hazelcastconfiguration2.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SubscriberRepository {

    //HazelcastConfiguration2 hazelcastConfiguration;


    public Subscriber getSubscriberByMSISDN(String MSISDN) throws Exception {

        OracleHelper oracleHelper = new OracleHelper();
        Connection connection = oracleHelper.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from SUBSCRIBER WHERE MSISDN=" + MSISDN);
        Subscriber subscriber=new Subscriber();
        while (resultSet.next()) {
            subscriber=new Subscriber(resultSet.getInt("SUBSC_ID"),
                    resultSet.getString("MSISDN"),
                    resultSet.getString("NAME"),
                    resultSet.getString("SURNAME"),
                    resultSet.getString("EMAIL"),
                    resultSet.getString("PASSWORD"),
                    resultSet.getString("SDATE"),
                    resultSet.getString("STATUS"));
        }

        return subscriber;

        /*VoltDbHelper voltDbHelper = new VoltDbHelper();
        Client client = voltDbHelper.client();
        ClientResponse response;

        response = client.callProcedure("ShowUserInformation", MSISDN);
        VoltTable subscriberTable = response.getResults()[0];
        int SUBSC_ID = ((int) subscriberTable.fetchRow(0).getLong(0));
        String _MSISDN = subscriberTable.fetchRow(0).getString(1);
        String NAME = subscriberTable.fetchRow(0).getString(2);
        String SURNAME = subscriberTable.fetchRow(0).getString(3);
        String EMAIL = subscriberTable.fetchRow(0).getString(4);
        String PASSWORD = subscriberTable.fetchRow(0).getString(5);
        String DATE = subscriberTable.fetchRow(0).getString(6);
        String STATUS = subscriberTable.fetchRow(0).getString(7);

        return new Subscriber(SUBSC_ID, _MSISDN, NAME, SURNAME, EMAIL, PASSWORD, DATE, STATUS);*/


    }

    public List<Subscriber> getAllSubscribers() throws Exception {
        OracleHelper oracleHelper = new OracleHelper();
        Connection connection = oracleHelper.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from SUBSCRIBER");
        List<Subscriber> subscriberList = new ArrayList<Subscriber>();
        while (resultSet.next()) {
            subscriberList.add(new Subscriber(
                    resultSet.getInt("SUBSC_ID"),
                    resultSet.getString("MSISDN"),
                    resultSet.getString("NAME"),
                    resultSet.getString("SURNAME"),
                    resultSet.getString("EMAIL"),
                    resultSet.getString("PASSWORD"),
                    resultSet.getString("SDATE"),
                    resultSet.getString("STATUS")));
        }
        connection.close();
        return subscriberList;
    }

    public ResponseMessage addNewSubscriberToOracle(Subscriber subscriber) throws Exception {
        try {
            OracleHelper oracleHelper = new OracleHelper();
            Connection connection = oracleHelper.getConnection();

            String sql = "{call package_subscriber.create_subscriber(?,?,?,?,?,?,?)}";

            CallableStatement callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, subscriber.getSubsc_id());
            callableStatement.setString(2, subscriber.getMsisdn());
            callableStatement.setString(3, subscriber.getName());
            callableStatement.setString(4, subscriber.getSurname());
            callableStatement.setString(5, subscriber.getEmail());
            callableStatement.setString(6, subscriber.getPassword());
            callableStatement.setInt(7, subscriber.getPackage_id());

            callableStatement.execute();
            connection.close();

            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setMessage("Subscriber successfully added to OracleDb.");
            return responseMessage;

        } catch (Exception e) {
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setMessage("An error occured while adding subscriber to OracleDb. Error: " + e.getMessage());
            return responseMessage;
        }

    }

    //not functional yet
    public ResponseMessage addNewSubscriberToVoltDb(Subscriber subscriber) throws Exception {
        try {
            VoltDbHelper voltDbHelper = new VoltDbHelper();
            Client client = voltDbHelper.client();
            client.callProcedure(
                    "UserInsert",
                    subscriber.getSubsc_id(),
                    subscriber.getMsisdn(),
                    subscriber.getName(),
                    subscriber.getSurname(),
                    subscriber.getEmail(),
                    subscriber.getPassword(),
                    subscriber.getPackage_id());
            //hazelcastConfiguration.putMsisdn(subscriber.getMsisdn(), subscriber.getMsisdn());

            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setMessage("Subscriber successfully added to OracleDb.");
            return responseMessage;
        } catch (Exception e) {
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setMessage("An error occured while adding subscriber to VoltDb. Error: " + e.getMessage());
            return responseMessage;
        }

    }
}
