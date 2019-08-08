package task9;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AudioChannel {

    private int chanelID;

    public AudioChannel(int chanelID) {
        this.chanelID = chanelID;
    }
    public int getChanelID() {
        return chanelID;
    }

    public void setChanelID(int chanelID) {
        this.chanelID = chanelID;
    }
    public void using() {
        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
