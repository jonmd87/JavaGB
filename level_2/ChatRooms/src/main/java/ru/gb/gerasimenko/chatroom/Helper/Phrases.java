package ru.gb.gerasimenko.chatroom.Helper;

public enum Phrases {
    ALLERT("Allert", "Внимание"),
    MAIN_TITLE("Bla Bla Room", "Переговорка"),
    NICK("Nick", "Ник"),
    LOGIN("Login", "Логин"),
    PASSWORD("Password", "Пароль"),
    NEED_AUTHOR("Authorization is required to continue", "Для продолжения требуется авторизация"),
    WRONG_AUTH("Wrong Login Or Password", "Неверный логин или пароль"),
    CONFIRM_EXIT("Confirm exit.", "Подтверждение выхода."),
    AREYUSHURE("Are you sure?", "Вы подтверждаете?");

    private String rus;
    private String eng;

    Phrases(String eng, String rus) {
        this.eng = eng;
        this.rus = rus;
    }

    public String value(byte lang) {
        return lang == 0 ? getEng() : getRus();
    }

    public String getRus() {
        return this.rus;
    }

    public String getEng() {
        return this.eng;
    }
}
