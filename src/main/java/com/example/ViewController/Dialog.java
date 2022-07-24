package com.example.ViewController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class Dialog {
    public static void information_message(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void error_message(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static String AskQuestion(String title, String Q) {
        TextInputDialog t = new TextInputDialog();
        t.setTitle(title);
        t.setContentText(Q);
        t.setHeaderText(null);
        t.showAndWait();
        return t.getEditor().getText();
    }

    public static String selectFromComboBox(String title, ArrayList<String> list) {
        Stage stage = new Stage();
        ComboBox comboBox = new ComboBox();
        for (String string : list)
            comboBox.getItems().add(string);

        VBox pane = new VBox();
        Label label = new Label("select a item");
        label.setFont(new Font(16));
        pane.getChildren().add(label);
        pane.getChildren().add(comboBox);
        Button button = new Button("select!");
        button.setTranslateX(70);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });
        pane.getChildren().add(new Label());
        pane.getChildren().add(button);
        Scene scene = new Scene(pane, 200, 100);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle(title);
        stage.showAndWait();
        return (String) comboBox.getValue();
    }
}
