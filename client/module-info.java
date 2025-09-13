module com.sji.rmiprotocoldemo.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires java.rmi;
    requires com.sji.rmiprotocoldemo.common;

    opens com.sji.rmiprotocoldemo.client to javafx.fxml;
}