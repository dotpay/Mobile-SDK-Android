package pl.mobiltek.dotpayexample;

import android.app.Application;

import pl.mobiltek.paymentsmobile.dotpay.AppSDK;

/**
 * Created by pliszka on 19.05.16.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppSDK.initialize(this);
    }
}
