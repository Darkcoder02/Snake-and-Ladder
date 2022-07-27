module com.project.snake_and_ladder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.project.snake_and_ladder to javafx.fxml;
    exports com.project.snake_and_ladder;
}