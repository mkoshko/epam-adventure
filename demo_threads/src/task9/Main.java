package task9;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<AudioChannel> list = new LinkedList<AudioChannel>() {
            {
                this.add(new AudioChannel(111));
                this.add(new AudioChannel(333));
                this.add(new AudioChannel(555));
                this.add(new AudioChannel(777));
                this.add(new AudioChannel(999));
            }
        };
        ChannelPool<AudioChannel> pool = new ChannelPool<>(list);
        for (int i = 0; i < 20; i++) {
            new Client(pool).start();
        }
    }
}
