package jogo.iu.grafico;

import javafx.scene.control.ChoiceDialog;

import java.util.List;

public class PopUpModo extends ChoiceDialog {
    public PopUpModo(List<String> escolhasDialog) {
        super(escolhasDialog.get(0), escolhasDialog);

        setTitle("Escolhe Modo");
        setHeaderText("Selecionar modo");
    }
}
