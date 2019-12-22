package ru.pochemuchki.musicproject.abstractclasses;


import javafx.scene.control.Button;

public abstract class ButtonAbstract {
    Button button;

    public ButtonAbstract(Button button) {
        this.button = button;
    }

    public ButtonAbstract(String nameButton) {
        this.button = new Button(nameButton) {{
            setOnAction(event -> click());
        }};
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Button getButton() {
        return button;
    }

    public abstract void click();
}