package jogo.iu.grafico;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import jogo.logica.JogoObservavel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PainelMinijogo extends GridPane {
    private JogoObservavel jogoObservavel;
    private Text[] textPerguntas;
    private TextField[] textFieldsRespostas;
    private Button buttonSubmeter;

    public PainelMinijogo(JogoObservavel jogoObservavel) {
        this.jogoObservavel = jogoObservavel;

        List<String> perguntas = jogoObservavel.getDadosMinijogo();

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    jogoObservavel.verificaMinijogo(null);

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Ultrapassou o tempo!");
                    alert.showAndWait();
                });
            }
        };

        timer.schedule(task, jogoObservavel.getTempoMinijogo());

        textPerguntas = new Text[5];
        textFieldsRespostas = new TextField[5];
        buttonSubmeter = new Button("Submeter");

        for (int i = 0; i < 5; i++) {
            textPerguntas[i] = new Text(perguntas.get(i));

            textFieldsRespostas[i] = new TextField();

            textFieldsRespostas[i].setPromptText("Resposta " + (i + 1));

            add(textPerguntas[i], 0, i);
            add(textFieldsRespostas[i], 1, i);
        }

        buttonSubmeter.setOnAction(event -> {
            List<String> respostas = new ArrayList<>();

            task.cancel();

            for (TextField textField:textFieldsRespostas) {
                respostas.add(textField.getText());
            }

            jogoObservavel.verificaMinijogo(respostas);

            if (jogoObservavel.ganhouMinijogo()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Ganhou o minijogo!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Respostas erradas");
                alert.showAndWait();
            }
        });

        add(buttonSubmeter, 1, 5);

        setPadding(new Insets(10));

        setVgap(5);
        setHgap(5);

        setAlignment(Pos.CENTER);
    }
}
