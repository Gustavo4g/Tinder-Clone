package com.example.tinder.Activities;

public class threadMissatges extends Thread {

    chatRoomActivity c;
    boolean running;

    public threadMissatges(chatRoomActivity c){
        this.c = c;
        running = true;
    }

    @Override
    public void run(){
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
        while (running) {
            try {
                Thread.sleep(60000);
                c.actualizaMensajes();
            } catch (InterruptedException e) {

            }
        }
    }
}
