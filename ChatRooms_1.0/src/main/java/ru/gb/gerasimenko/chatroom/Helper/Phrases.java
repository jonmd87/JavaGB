package ru.gb.gerasimenko.chatroom.Helper;

public enum Phrases {
    ALLERT("Allert", "Внимание"),
    ENETERED_IN_CHAT(" Entered in chat.", " Вошел в чат."),
    WHO_SEND_TO("Who should I send message to?", "Кому я должен отправить сообщение?"),
    LEAVE_CHAT(" Has left the chat", " Покинул чат"),
    AFTER_REGISTER_MSG("After registration, authorization is required", "После регистрации требуется авторизация"),
    MAIN_TITLE("Bla Bla Room", "Переговорка"),
    NICK("nick", "Ник"),
    LOGIN("log", "Логин"),
    PASSWORD("pas", "Пароль"),
    NEW_USER("New user ", "Новый пользователь "),
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
