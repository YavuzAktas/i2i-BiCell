package org.bicell.rest.api.repository;

import org.bicell.rest.api.dbhelper.OracleHelper;
import org.bicell.rest.api.email.EmailSenderService;
import org.bicell.rest.api.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;

@Repository
public class LoginRepository {

    private EmailSenderService emailSenderService;

    public LoginRepository(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    public ResponseEntity loginCheck(String msisdn, String password) throws Exception {
        try {
            OracleHelper oracleHelper = new OracleHelper();
            Connection connection = oracleHelper.getConnection();


            CallableStatement callableStatement = connection.prepareCall("{ ? = call package_subscriber.login(?,?)}");
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setString(2, msisdn);
            callableStatement.setString(3, password);
            callableStatement.execute();

            int checkUser = callableStatement.getInt(1);
            if (checkUser==1) {
                ResponseMessage responseMessage=new ResponseMessage();
                responseMessage.setMessage("Login Successful.");
                return new ResponseEntity<>(responseMessage, HttpStatus.OK);

            } else {
                ResponseMessage loginResponse=new ResponseMessage();
                loginResponse.setMessage("Login unsuccessful.");
                return new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ResponseMessage loginResponse=new ResponseMessage();
            loginResponse.setMessage("Login unsuccessful.");
            return new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity forgotPassword(String email) throws Exception {

        OracleHelper oracleHelper = new OracleHelper();
        Connection connection = oracleHelper.getConnection();
        String sql = "{? = call package_subscriber.forget_password(?)}";

        CallableStatement callableStatement = connection.prepareCall(sql);
        callableStatement.registerOutParameter(1, Types.VARCHAR);
        callableStatement.setString(2, email);
        callableStatement.execute();

        String password=callableStatement.getString(1);

        if (password != null) {

            String toEmail = email;
            String subject = "BiCell Password";
            String body = "Your password is " + password;

            emailSenderService.sendEmail(toEmail, subject, body);
            ResponseMessage responseMessage=new ResponseMessage();
            responseMessage.setMessage("E-mail sent to "+toEmail);
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);

        } else {
            ResponseMessage responseMessage=new ResponseMessage();
            responseMessage.setMessage("User not found");
            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        }
    }

}
