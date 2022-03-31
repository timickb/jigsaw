module me.timickb.jigsaw {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens me.timickb.jigsaw to javafx.fxml;
    exports me.timickb.jigsaw;
}