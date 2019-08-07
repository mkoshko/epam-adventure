package task5._reentrantlock;

import java.util.UUID;

public class CommonResource {
    private String nuclear_rocket_launch_code
            = UUID.randomUUID().toString();

    public String getCode() {
        return nuclear_rocket_launch_code;
    }

    public void changeCode(String code) {
        nuclear_rocket_launch_code = code;
        System.out.println("Code changed for " + nuclear_rocket_launch_code);
    }
}
