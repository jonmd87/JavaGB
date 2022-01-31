package ru.gb.gerasimenko.chatroom.Helper;

public enum Buttons {
    FILE("File","Файл"),
    ENGLISH("English", "Английский"),
    RUSSIAN("Russian", "Русский"),
    AUTHORIZATION("Authorization", "Авторизация"),
    LANGUAGE("Language", "Язык"),
    REGISTRATION("Registration", "Регистрация"),
    SELECT_MEMBER("Select member", "Выберите участника"),
    GENERAL("General", "Общий"),
    SEND("Send", "Отправить"),
    OK("OK", "OK"),
    CANCEL("Cancel", "Отмена"),
    EXIT("Exit", "Выйти");

    private String rus;
    private String eng;

    Buttons(String eng, String rus) {
        this.eng = eng;
        this.rus = rus;
    }

    public String value(byte lang) {
        return lang == 0 ? getEng() : getRus();
    }

    private String getRus() {
        return this.rus;
    }

    private String getEng() {
        return this.eng;
    }
}