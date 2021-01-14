package pl.mobiltek.dotpayexample.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import pl.mobiltek.dotpayexample.R;
import pl.mobiltek.dotpayexample.model.ShopData;
import pl.mobiltek.paymentsmobile.dotpay.Configuration;
import pl.mobiltek.paymentsmobile.dotpay.events.PaymentEndedEventArgs;
import pl.mobiltek.paymentsmobile.dotpay.events.PaymentManagerCallback;
import pl.mobiltek.paymentsmobile.dotpay.exeptions.NotFoundDefaultPaymentCardException;
import pl.mobiltek.paymentsmobile.dotpay.exeptions.NotFoundPaymentCardException;
import pl.mobiltek.paymentsmobile.dotpay.exeptions.OneClickUnableException;
import pl.mobiltek.paymentsmobile.dotpay.managers.PaymentManager;
import pl.mobiltek.paymentsmobile.dotpay.model.PaymentInformation;
import pl.mobiltek.paymentsmobile.dotpay.utils.Settings;

public class ExampleActivity extends AppCompatActivity implements View.OnClickListener {

    private final String NAME = "firstname";
    private final String LAST_NAME = "lastname";
    private final String EMAIL = "email";

    private TextView versionSDK;
    private Button mPayBtn, mOneClickBtn;
    private ImageButton mCardBtn, mHistoryBtn;
    private PaymentManagerCallback paymentManagerCallback = new PaymentManagerCallback() {
        @Override
        public void onPaymentSuccess(PaymentEndedEventArgs paymentEndedEventArgs) {
            Toast.makeText(ExampleActivity.this, "Payment successful", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPaymentFailure(PaymentEndedEventArgs paymentEndedEventArgs) {
            Toast.makeText(ExampleActivity.this, "Payment failed", Toast.LENGTH_LONG).show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        initView();
        setupSDK();
    }


    private void initView() {
        versionSDK = (TextView) findViewById(R.id.versionSDK);
        versionSDK.setText("SDK " + Settings.getSDKVersion());

        mOneClickBtn = (Button) findViewById(R.id.oneClickBtn);
        mPayBtn = (Button) findViewById(R.id.formPaymentBtn);
        mHistoryBtn = (ImageButton) findViewById(R.id.historyBtn);
        mCardBtn = (ImageButton) findViewById(R.id.cardManagerBtn);

        mPayBtn.setOnClickListener(this);
        mOneClickBtn.setOnClickListener(this);
        mHistoryBtn.setOnClickListener(this);
        mCardBtn.setOnClickListener(this);

    }

    private void setupSDK() {
        PaymentManager.getInstance().setPaymentManagerCallback(paymentManagerCallback);
        PaymentManager.getInstance().setApplicationVersion(Configuration.TEST_VERSION);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.oneClickBtn:
                payOneClick();
                break;
            case R.id.formPaymentBtn:
                payFormPayment();
                break;
            case R.id.historyBtn:
                showPaymentHistory();
                break;
            case R.id.cardManagerBtn:
                showPaymentCartMenager();
                break;
        }
    }

    private void showPaymentCartMenager() {
        Intent intent = new Intent(this, PaymentCardManagerActivity.class);
        startActivity(intent);
    }

    private void payFormPayment() {
        PaymentManager.getInstance().initialize(this, getPaymentInformation());
    }

    private void payOneClick() {
        try {
            PaymentManager.getInstance().oneClickPayment(this, getPaymentInformation());
        } catch (NotFoundPaymentCardException e) {
            Toast.makeText(ExampleActivity.this, "Not found payment card", Toast.LENGTH_LONG).show();
        } catch (NotFoundDefaultPaymentCardException e) {
            Toast.makeText(ExampleActivity.this, "Not found default payment card", Toast.LENGTH_LONG).show();
        } catch (OneClickUnableException e) {
            Toast.makeText(ExampleActivity.this, "1Click unable", Toast.LENGTH_LONG).show();
        }
    }

    @NonNull
    private PaymentInformation getPaymentInformation() {
        PaymentInformation paymentInformation = new PaymentInformation(ShopData.getMerchantId(), ShopData.getProductPrice(), ShopData.getDescription(), ShopData.getCurrency());

        Map<String, String> sender = new HashMap<String, String>();
        sender.put(NAME, ShopData.getName());
        sender.put(LAST_NAME, ShopData.getLastName());
        sender.put(EMAIL, ShopData.getEmail());

        paymentInformation.setSenderInformation(sender);
        return paymentInformation;
    }

    private void showPaymentHistory() {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
}