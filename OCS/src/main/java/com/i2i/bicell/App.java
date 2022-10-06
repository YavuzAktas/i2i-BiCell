package com.i2i.bicell;

import com.i2i.bicell.config.config;
import org.apache.logging.log4j.*;
import akka.actor.*;
import com.typesafe.config.ConfigFactory;
import com.i2i.bicell.listenAkka.Listener;
public class App {
    private static Logger log = LogManager.getLogger(App.class);

    public static void main(String[] args){
        ActorSystem system = ActorSystem.create("AkkaRemoteServer", ConfigFactory.load());
        system.actorOf(Props.create(Listener.class), "Listener");
        // ı made this listener declaration with hand, be careful !!!!!
    }
    public static void control(String[] args){
        String temp = args[0];
        if(temp.length()>0){ // first element of run time element
            log.info("File path read");
            config.set_FilePath(temp);
        }
        else{
            log.error("File path read error");
        }
    }

}