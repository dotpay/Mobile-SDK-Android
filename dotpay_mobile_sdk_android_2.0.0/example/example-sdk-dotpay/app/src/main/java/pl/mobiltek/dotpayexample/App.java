package pl.mobiltek.dotpayexample;

import androidx.multidex.MultiDexApplication;

import pl.mobiltek.paymentsmobile.dotpay.AppSDK;

/**
 * Created by pliszka on 19.05.16.
 */
public class App extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        AppSDK.initialize(this);
    }
}
