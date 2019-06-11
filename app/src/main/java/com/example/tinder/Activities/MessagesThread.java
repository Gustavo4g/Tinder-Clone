package com.example.tinder.Activities;

class MessagesThread extends Thread {

    private final ChatRoomActivity c;
    boolean running;

    public MessagesThread(ChatRoomActivity c) {
        this.c = c;
        running = true;
    }

    @Override
    public void run() {
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
        while (running) {
            try {
                Thread.sleep(15000);
                c.actualizaMensajes();
            } catch (InterruptedException ignored) {

            }
        }
    }
}
