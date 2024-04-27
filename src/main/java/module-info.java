module com.proj.calproj {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.proj.calproj to javafx.fxml;
    exports com.proj.calproj;
    exports com.proj.calproj.Controllers;
    exports com.proj.calproj.Controllers.Admin;
    exports com.proj.calproj.Controllers.Physician;
    exports com.proj.calproj.Models;
    exports com.proj.calproj.Views;

}