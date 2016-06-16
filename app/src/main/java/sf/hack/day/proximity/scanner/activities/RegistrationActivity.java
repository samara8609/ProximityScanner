package sf.hack.day.proximity.scanner.activities;

import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by aaron on 6/15/16.
 */

public class RegistrationActivity extends AppCompatActivity {

    private NfcAdapter nfcAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (null == nfcAdapter) {
            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }
    }
}