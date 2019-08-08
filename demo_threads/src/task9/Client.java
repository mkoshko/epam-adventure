package task9;

public class Client extends Thread {
    private ChannelPool<AudioChannel> pool;
    public Client(ChannelPool<AudioChannel> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        AudioChannel chanel = null;
        try {
            chanel = pool.getResource(600);
            System.out.println("Chanel client #" + this.getId() + " took chanel #" + chanel.getChanelID());
            chanel.using();
        } catch (ResourceException e) {
            System.out.println("Client #" + this.getId() + " lost ->"
                    + e.getMessage());
        } finally {
            if (chanel != null) {
                System.out.println("Chanel client #" + this.getId() + " : " + chanel.getChanelID() + " chanel released");
                pool.returnResources(chanel);
            }
        }
    }
}
