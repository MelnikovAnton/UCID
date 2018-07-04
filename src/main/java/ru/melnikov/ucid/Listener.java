package ru.melnikov.ucid;

import com.avaya.jtapi.tsapi.LucentV5Call;
import com.avaya.jtapi.tsapi.adapters.CallListenerAdapter;
import org.apache.log4j.Logger;

import javax.telephony.CallEvent;

public class Listener  extends CallListenerAdapter {

    private static final Logger log = Logger.getLogger(MainController.class);

    private final MainController controller;

    public Listener(MainController controller) {
        this.controller = controller;
    }

    @Override
    public void callActive(CallEvent callEvent) {
        LucentV5Call v5 = (LucentV5Call) callEvent.getCall();
        String ucid = v5.getUCID();
        log.info("get new UCID " + ucid);
        controller.setUcid(ucid);
    }


}
