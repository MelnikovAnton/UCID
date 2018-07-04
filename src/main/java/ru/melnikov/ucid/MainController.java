package ru.melnikov.ucid;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import org.apache.log4j.Logger;

import javax.telephony.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MainController {

    private static final Logger log = Logger.getLogger(MainController.class);

    @FXML
    private TextField ucidField;
    @FXML
    private Label lable;

    private final String login;
    private final String password;
    private final String station;

    private final Listener listener = new Listener(this);

    public MainController() throws IOException {

        Properties props = loadProperty();

        login = props.getProperty("login");
        password = props.getProperty("password");
        station = props.getProperty("station");

        try {
            Terminal terminal = getTerminal();
        } catch (MethodNotSupportedException | JtapiPeerUnavailableException | InvalidArgumentException | ResourceUnavailableException e) {
           log.error("Terminal dosen exist",e);
        }
    }

    private Terminal getTerminal() throws MethodNotSupportedException, ResourceUnavailableException, InvalidArgumentException, JtapiPeerUnavailableException {
        JtapiPeer peer = JtapiPeerFactory.getJtapiPeer(null);
        String service = peer.getServices()[0];
        Provider provider = peer.getProvider(service + ";login=" + login + ";passwd=" + password);
        Terminal terminal = provider.getTerminal(station);
        terminal.addCallListener(listener);
        return terminal;
    }


    private Properties loadProperty() throws IOException {
        Properties properties = new Properties();
        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream("app.properties")) {
            properties.load(inputStream);
        }
        return properties;
    }


    private void writeTextToClipboard(String s) {
        Platform.runLater(
                () -> {
                    Clipboard clipboard = Clipboard.getSystemClipboard();
                    Map<DataFormat, Object> content = new HashMap<>();
                    content.put(DataFormat.PLAIN_TEXT, s);

                    clipboard.setContent(content);
                }
        );

    }

    @FXML
    private void initialize() {
        lable.setText("UCID");
        ucidField.setEditable(false);
        ucidField.setOnAction(event -> {
            log.debug("event" + event);
            writeTextToClipboard(ucidField.getText());
        });
    }

    public void close() {
        log.info("Close App");
        System.exit(0);
    }


    public void setUcid(String ucid) {
        log.debug("UI get UCID " + ucid);
        ucidField.setText(ucid);
        ucidField.fireEvent(new ActionEvent());
    }
}
