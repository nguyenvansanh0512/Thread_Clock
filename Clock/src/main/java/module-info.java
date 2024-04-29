module com.gui.clock2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.gui.clock2 to javafx.fxml;
    exports com.gui.clock2;

    opens com.gui.clock2.application to javafx.fxml, javafx.graphics ;
    exports com.gui.clock2.controller ;
    opens com.gui.clock2.controller to javafx.fxml, javafx.graphics ;
}