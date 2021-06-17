package jogo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jogo.iu.grafico.VistaPrincipal;
import jogo.logica.JogoObservavel;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        JogoObservavel jogoObservavel = new JogoObservavel();
        Scene scene = new Scene(new VistaPrincipal(jogoObservavel), 715, 455);

        primaryStage.setMinWidth(scene.getWidth());
        primaryStage.setMinHeight(scene.getHeight());

        primaryStage.setTitle("4 em linha");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}