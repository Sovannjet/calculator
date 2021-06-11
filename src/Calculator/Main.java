package Calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("CalculatorUI.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMinWidth(290); // min width for the window
        stage.setMinHeight(300); // min height for the window
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
