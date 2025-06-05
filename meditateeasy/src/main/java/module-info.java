module med.easy.meditateeasy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;

    opens med.easy.meditateeasy to javafx.fxml;
    opens med.easy.meditateeasy.model to javafx.base;
    exports med.easy.meditateeasy;
}