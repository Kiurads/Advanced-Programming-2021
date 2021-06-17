package jogo.iu.grafico;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RegistarNomesJanela {
    private boolean registado;
    private String jogador1;
    private String jogador2;

    private Stage stage;

    private Text titulo;

    private TextField nome1;
    private TextField nome2;

    private Label labelNome1;
    private Label labelNome2;

    private Button buttonOk;
    private Button buttonCancel;

    public void show() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        titulo = new Text("Registar nomes");

        nome1 = new TextField();
        nome2 = new TextField();

        labelNome1 = new Label("Jogador 1: ");
        labelNome2 = new Label("Jogador 2: ");

        buttonOk = new Button("OK");
        buttonCancel = new Button("Cancelar");

        registado = false;

        nome1.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                buttonOk.setDisable(nome1.getText().equals(nome2.getText()) ||
                        nome1.getText().trim().isEmpty() ||
                        nome2.getText().trim().isEmpty());
            }
        });

        nome2.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                buttonOk.setDisable(nome1.getText().equals(nome2.getText()) ||
                        nome1.getText().trim().isEmpty() ||
                        nome2.getText().trim().isEmpty());
            }
        });

        nome1.setPromptText("Insira um nome");
        nome2.setPromptText("Insira um nome");

        buttonOk.setDisable(true);

        buttonOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                jogador1 = nome1.getText();
                jogador2 = nome2.getText();

                registado = true;

                stage.close();
            }
        });

        buttonCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.add(titulo, 1, 0);

        gridPane.add(nome1, 2, 1);
        gridPane.add(nome2, 2, 2);

        gridPane.add(labelNome1, 1, 1);
        gridPane.add(labelNome2, 1, 2);

        gridPane.add(buttonOk, 1, 3);
        gridPane.add(buttonCancel, 2, 3);

        gridPane.setGridLinesVisible(true);

        buttonOk.setMaxWidth(Double.MAX_VALUE);
        buttonCancel.setMaxWidth(Double.MAX_VALUE);

        Scene scene = new Scene(gridPane, 250, 125);

        stage.setResizable(false);

        stage.setTitle("Registar nomes");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public String getJogador1() {
        return jogador1;
    }

    public String getJogador2() {
        return jogador2;
    }

    public boolean isRegistado() {
        return registado;
    }
}
