package com.i2i.bicell.listenAkka;

import com.i2i.bicell.Message.Message;
import com.i2i.bicell.VoltDb.VoltDb;
import org.apache.logging.log4j.*;
import akka.actor.*;
import com.i2i.bicell.operations.operations;
public class Listener extends UntypedAbstractActor{
    private Logger log = LogManager.getLogger(Listener.class);
    private VoltDb voltdb = new VoltDb();
    private operations operation = new operations(voltdb);

    public Listener(){
        log.info("Listener Constructor");
    }
    public void onReceive(Object m) { // onreceive is a func of untypedabstractactor
        log.info("Receive({})", (Throwable) m);
        if (m instanceof Message.usage){
            log.info("Listener getting data");
            log.info("Subscriber id {}", ((Message.usage) m).getSubsc_id());
            log.info("Package id {}", ((Message.usage) m).getPackage_id());
            log.info("Balance data {}", ((Message.usage) m).getBlc_data());
            log.info("Balance sms {}", ((Message.usage) m).getBlc_sms());
            log.info("Balance voice {}", ((Message.usage) m).getBlc_voice());
            log.info("Start date {}", ((Message.usage) m).getSdate());
            log.info("End date {}", ((Message.usage) m).getEdate());
            log.info("Price {}", ((Message.usage) m).get_Price());

            operation.start(m);
        }

    }

}