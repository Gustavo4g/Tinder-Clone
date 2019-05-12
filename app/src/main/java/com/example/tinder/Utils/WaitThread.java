package com.example.tinder.Utils;

public class WaitThread extends Thread {

    public void run() {
        try {
            this.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
