package ru.gb.gerasimenko.cowsbulls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class CowsBullsController {
    @FXML
    public MenuItem MenuItemNewGame;
    @FXML
    public MenuItem MenuItemExit;
    @FXML
    private TextArea ResultArea;
    @FXML
    private TextField AnswerField;


    private Game game;
    private CowsBullsCounter cowsBullsCounter;

    public CowsBullsController() {
        this.game = new Game();
    }

    public void onClickCheckButton(ActionEvent actionEvent) {
        final String answer = AnswerField.getText();
        if (checkValidAnswer(answer)) {
            System.out.println(game.getNumber());  // оставил для проверки, в финальной версии уберу этот метод
            cowsBullsCounter = game.calculateCowsBulls(answer);
            if (cowsBullsCounter.getBulls() == 4) {
                winnerWindow();
            }
            ResultArea.appendText(answer + Names.TABULATION4.getStr() + cowsBullsCounter.toString());
        } else {
            alertWindow(answer);
        }
        AnswerField.clear();
    }

    private void winnerWindow() {
        Alert win = new Alert(Alert.AlertType.CONFIRMATION);
        win.setTitle(Names.WINNER_TITLE.getStr());
        win.setHeaderText(Names.HIDDEN_NUMBER_WAS.getStr() + game.getNumber());
        win.setContentText(Names.YOUR_ANSWER.getStr() + AnswerField.getText());

        ButtonType newGame = new ButtonType(MenuItemNewGame.getText());
        ButtonType exitGame = new ButtonType(MenuItemExit.getText());
        win.getButtonTypes().setAll(newGame, exitGame);

        Optional<ButtonType> result = win.showAndWait();
        if (result.get() == newGame) {
                MenuItemNewGame.fire();
        } else {
            MenuItemExit.fire();
        }
    }


    private void alertWindow(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Names.ALERT_TITLE.getStr());
        alert.setHeaderText(Names.INCORRECT_DATA.getStr());
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean checkValidAnswer(String str) {
        if (str != null && str.length() == 4) {
            for (Character ch : str.toCharArray()) {
                if (!Character.isDigit(ch)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public void onExitSelected(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void onNewGameSelected(ActionEvent actionEvent) {
        this.game = new Game();
        AnswerField.clear();
        ResultArea.clear();
    }
}