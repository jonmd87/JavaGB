package ru.gb.gerasimenko.Helper;

public enum Define {
    SERVER("Server: ", "Сервер: "),
    CLIENT("Client: ", "Клиент: "),
    SERV_RUNNING("The server is running ...", "Сервер запущен ..."),
    SERV_WAITNG("The server is waiting ...", "Сервер ждет подключения ..."),
    SERVER_CONNNECTED("Server connected.", "Сервер подключен."),
    CLIENT_CONNNECTED("Client connected.", "Клиент подключен."),
    SESSION_CLOSED("Session closed.", "Сессия завершина."),
    QUERY("Query: ", "Запрос: "),
    ANSWER("Answer: ", "Ответ: "),
    END_SESION("/end", "/end");


    private String eng;
    private String rus;

    Define(String eng, String rus) {
        this.rus = rus;
        this.eng = eng;
    }

    public String getValue(byte lang) {
        return (lang == 0 ? getEng() : getRus());
    }

    private String getRus() {
        return rus;
    }

    private String getEng() {
        return eng;
    }
}
