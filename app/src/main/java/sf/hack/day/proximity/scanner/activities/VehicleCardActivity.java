package sf.hack.day.proximity.scanner.activities;

import android.app.Activity;
import android.nfc.NfcAdapter;
import android.os.Bundle;

/**
 * Created by aaron on 6/15/16.
 */

public class VehicleCardActivity extends Activity {

    private NfcAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_card);
    }
}
