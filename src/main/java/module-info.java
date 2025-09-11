module com.sji.rmiprotocoldemo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.sji.rmiprotocoldemo to javafx.fxml;
    exports com.sji.rmiprotocoldemo;
}