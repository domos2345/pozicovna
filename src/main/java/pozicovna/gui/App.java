package pozicovna.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        LoggedOutSceneContoller controller = new LoggedOutSceneContoller();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("LoggedOut.fxml"));
        loader.setController(controller);
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        primaryStage.setTitle("Pozicovna naradia");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static class Run {
        public static void main(String[] args) {
            new Thread() {
                @Override
                public void run() {
                    javafx.application.Application.launch(App.class);
                }
            }.start();
        }
    }
}