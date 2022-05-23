module pl.edu.pw.ee {
    requires transitive javafx.controls;
    requires javafx.fxml;

    opens pl.edu.pw.ee to javafx.fxml;
    exports pl.edu.pw.ee;
}
