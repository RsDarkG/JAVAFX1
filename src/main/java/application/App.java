package application;

import Utilities.Paths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;



public class App extends Application {

    public static void main(String[] args) {
        launch();
    }



    @Override
    public void start(Stage primaryStage) throws Exception {

        TabPane load = FXMLLoader.load(getClass().getResource(Paths.CAL_GUI));
        Scene scene = new Scene( load);

        String css = getClass().getResource("/CalGUIstyles.css").toExternalForm();
        scene.getStylesheets().add(css);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculadora");
        primaryStage.show();
    }
}
