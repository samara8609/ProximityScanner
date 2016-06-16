package sf.hack.day.proximity.scanner.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Toast;
import android.widget.TextView;
import android.nfc.Tag;

import sf.hack.day.proximity.scanner.model.Address;
import sf.hack.day.proximity.scanner.model.Policy;
import sf.hack.day.proximity.scanner.model.Vehicle;
import sf.hack.day.proximity.scanner.services.PolicyRetrieval;
import sf.hack.day.proximity.scanner.utilities.Converter;

/**
 * Created by aaron on 6/15/16.
 */

public class InsuranceCardActivity extends AppCompatActivity {

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

            Policy policy = PolicyRetrieval.retrievePolicy(Converter.bytesToHex(tag.getId()));

            if(null != policy) {
                TextView policyText = (TextView) findViewById(R.id.txtPolicy);
                policyText.setText(policy.id);

                TextView agentText = (TextView) findViewById(R.id.txtAgent);
                agentText.setText(policy.agent);

                TextView effectiveDateText = (TextView) findViewById(R.id.txtDate);
                effectiveDateText.setText(policy.effectiveDate);

                TextView nameText = (TextView) findViewById(R.id.txtName);
                nameText.setText(policy.customerName);

                Address address = policy.address;
                TextView address1 = (TextView) findViewById(R.id.txtAddress1);
                address1.setText(address.address1);

                TextView address2 = (TextView) findViewById(R.id.txtAddress2);
                address2.setText(address.city + ", " + address.state + " " + address.zip);

                Vehicle vehicle = policy.vehicle;
                TextView vehicleText1 = (TextView) findViewById(R.id.txtVehicle1);
                vehicleText1.setText(vehicle.vin);

                TextView vehicleText2 = (TextView) findViewById(R.id.txtVehicle2);
                vehicleText2.setText(vehicle.year + " " + vehicle.make + " " + vehicle.model);


            } else {
                Toast.makeText(this, "No policies were able to be retrieved.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
