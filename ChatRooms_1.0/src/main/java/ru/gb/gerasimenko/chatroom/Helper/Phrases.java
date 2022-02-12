package ru.gb.gerasimenko.chatroom.Helper;

public enum Phrases {
    ALERT("Alert", "Внимание"),
    TIME_EXPIRED("Time for authorization has expired.", "Время для авторизации истекло"),
    ENTERED_IN_CHAT(" Entered in chat.", " Вошел в чат."),
    RECIPIENTS("Recipients", "Получатели"),
    LEAVE_CHAT(" Has left the chat", " Покинул чат"),
    AFTER_REGISTER_MSG("After registration, authorization is required", "После регистрации требуется авторизация"),
    MAIN_TITLE("Bla Bla Room", "Переговорка"),
    NICK("nick", "Ник"),
    LOGIN("log", "Логин"),
    PASSWORD("pas", "Пароль"),
    NEW_USER("New user ", "Новый пользователь "),
    NEED_AUTHOR("Authorization is required to continue", "Для продолжения требуется авторизация"),
    WRONG_AUTH("Wrong Login Or Password", "Неверный логин или пароль"),
    WRONG_DATA("Wrong data ", "Неверные данные "),
    CONFIRM_EXIT("Confirm exit.", "Подтверждение выхода."),
    RULE("The separator is comma!", "Разделяйте имена запятой"),
    AREYOUSHURE("Are you sure?", "Вы подтверждаете?");

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
