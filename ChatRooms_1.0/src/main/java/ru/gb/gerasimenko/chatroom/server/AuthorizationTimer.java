package ru.gb.gerasimenko.chatroom.server;

public class AuthorizationTimer implements Runnable{
    private Boolean flag;
    private Integer time;
    private final Integer millies;

    public AuthorizationTimer(Integer seconds) {
        this.millies = 1000;
        this.time = seconds * this.millies;
        this.flag = true;
    }


    @Override
    public void run() {
        while (time > 0 && getFlag()) {
            try {
                Thread.sleep(100);
                time -= 100;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        blockTimer();
    }


    public void blockTimer() {
        this.flag = false;
    }
    public synchronized Boolean getFlag() {
        return this.flag;
    }
    public synchronized Integer getTime() {
        return time;
    }
}
