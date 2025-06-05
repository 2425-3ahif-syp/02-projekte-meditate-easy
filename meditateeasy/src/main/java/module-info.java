module med.easy.meditateeasy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.h2database;
    requires javafx.web;

    opens med.easy.meditateeasy to javafx.fxml;
    exports med.easy.meditateeasy;
}