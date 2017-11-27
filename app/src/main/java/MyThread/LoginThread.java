package MyThread;

/**
 * Created by leet on 17-11-27.
 */

public class LoginThread extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
