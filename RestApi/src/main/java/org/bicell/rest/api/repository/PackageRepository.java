package org.bicell.rest.api.repository;

import org.bicell.rest.api.dbhelper.OracleHelper;
import org.bicell.rest.api.dbhelper.VoltDbHelper;
import org.bicell.rest.api.entity.Package;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.voltdb.VoltTable;
import org.voltdb.client.Client;
import org.voltdb.client.ClientResponse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PackageRepository {

    public List<Package> packageDetailList() throws Exception{
        OracleHelper oracleHelper = new OracleHelper();
        Connection connection = oracleHelper.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from PACKAGE");
        List<Package> packageDetailList = new ArrayList<Package>();
        while (resultSet.next()) {
            packageDetailList.add(new Package(resultSet.getInt("PACKAGE_ID"),
                                        resultSet.getString("PACKAGE_NAME"),
                                        resultSet.getInt("AMOUNT_VOICE"),
                                        resultSet.getInt("AMOUNT_DATA"),
                                        resultSet.getInt("AMOUNT_SMS"),
                                        resultSet.getInt("DURATION")));
        }
        connection.close();
        return packageDetailList;
    }

    public List<Package> getPackageByMSISDNinList(String MSISDN) throws Exception{
        VoltDbHelper voltDbHelper = new VoltDbHelper();

        Client client = voltDbHelper.client();
        ClientResponse response;

        List<Package> packageInfo = new ArrayList<>();

        response = client.callProcedure("getPackage",MSISDN);
        VoltTable tablePackageInfo = response.getResults()[0];
        tablePackageInfo.advanceRow();
        int packageID =(int)(tablePackageInfo.getLong(0));
        String packageName =tablePackageInfo.getString(1);
        int amountVoice =(int)(tablePackageInfo.getLong(2));
        int amountData =(int)(tablePackageInfo.getLong(3));
        int amountSMS =(int)(tablePackageInfo.getLong(4));
        int duration =(int)(tablePackageInfo.getLong(5));

        packageInfo.add(new Package(packageID,packageName,amountVoice,amountData,amountSMS,duration));
        return packageInfo;

    }

    /*public List<Package> packageList() throws Exception{
        OracleHelper oracleHelper = new OracleHelper();
        Connection connection = oracleHelper.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from PACKAGE");
        List<Package> packageList = new ArrayList<Package>();
        while (resultSet.next()) {
            packageList.add(new Package(resultSet.getInt("PACKAGE_ID"),
                                        resultSet.getString("PACKAGE_NAME"),0,0,0,0));
        }
        connection.close();
        return packageList;
    }*/
}
