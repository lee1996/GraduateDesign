package MyThread;

import android.util.Log;

/**
 * Created by leet on 17-11-27.
 */

public class RegisterThread extends Thread {
    @Override
    public void run() {
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            Log.e("ERROR","失败");
            e.printStackTrace();
        }
    }
}
