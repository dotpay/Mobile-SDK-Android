package pl.mobiltek.dotpayexample.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import pl.mobiltek.dotpayexample.R;
import pl.mobiltek.dotpayexample.model.ShopData;
import pl.mobiltek.paymentsmobile.dotpay.fragment.PaymentCardManagerFragment;

public class PaymentCardManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_manager);
        initPaymentCardListFragment();
    }

    private void initPaymentCardListFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = PaymentCardManagerFragment.newInstance(ShopData.getMerchantId(), ShopData.getCurrency(), ShopData.getLanguage());
        ft.replace(R.id.fragmentContainer, fragment, null).commit();
    }

}