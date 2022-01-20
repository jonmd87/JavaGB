module ru.gb.gerasimenko.cowsbulls {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.gb.gerasimenko.cowsbulls to javafx.fxml;
    exports ru.gb.gerasimenko.cowsbulls;
}