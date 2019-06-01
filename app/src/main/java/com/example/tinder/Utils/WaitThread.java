package com.example.tinder.Utils;

class WaitThread extends Thread {

    public void run() {
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
