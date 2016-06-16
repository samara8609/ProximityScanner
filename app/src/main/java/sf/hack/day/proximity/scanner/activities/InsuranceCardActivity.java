package sf.hack.day.proximity.scanner.activities;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.widget.Toast;
import android.widget.TextView;
import android.nfc.Tag;

import java.math.BigInteger;

import sf.hack.day.proximity.scanner.model.MockData;

/**
 * Created by aaron on 6/15/16.
 */

public class InsuranceCardActivity extends Activity {

    private NfcAdapter adapter;
    private SharedPreferences preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insurance_card);

        adapter = NfcAdapter.getDefaultAdapter(this);

        if(null == adapter) {
            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        //handleIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(null != adapter) {
            final Intent intent = new Intent(this.getApplicationContext(), this.getClass());
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            final PendingIntent pendingIntent = PendingIntent.getActivity(this.getApplicationContext(), 0, intent, 0);
            adapter.enableForegroundDispatch(this, pendingIntent, null, null);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(adapter != null) {
            adapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        this.handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        String action = intent.getAction();

        if(NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {

            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            MockData mockData = new MockData(tag.getId().toString());

            TextView policy = (TextView) findViewById(R.id.txtPolicy);
            policy.setText(mockData.policyId);

            TextView agent = (TextView) findViewById(R.id.txtAgent);
            agent.setText(mockData.agent);

            TextView effectiveDate = (TextView) findViewById(R.id.txtDate);
            effectiveDate.setText(mockData.effectiveDate);

            TextView name = (TextView) findViewById(R.id.txtName);
            name.setText(mockData.customerName);

            TextView address1 = (TextView) findViewById(R.id.txtAddress1);
            address1.setText(mockData.street);

            TextView address2 = (TextView) findViewById(R.id.txtAddress2);
            address2.setText(mockData.city);

            TextView vehicle = (TextView) findViewById(R.id.txtVehicle);
            vehicle.setText(mockData.vin);
        }

    }

}
