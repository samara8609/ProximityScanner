package sf.hack.day.proximity.scanner.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import sf.hack.day.proximity.scanner.model.Address;
import sf.hack.day.proximity.scanner.model.Person;
import sf.hack.day.proximity.scanner.services.ClientRetrieval;
import sf.hack.day.proximity.scanner.utilities.Converter;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(null != nfcAdapter) {
            final Intent intent = new Intent(this.getApplicationContext(), this.getClass());
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            final PendingIntent pendingIntent = PendingIntent.getActivity(this.getApplicationContext(), 0, intent, 0);
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
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

            Person person = ClientRetrieval.retrieve(Converter.bytesToHex(tag.getId()));

            if(null != person) {
                TextView txtFirstName = (TextView) findViewById(R.id.txtFName);
                txtFirstName.setText(person.name.first);

                TextView txtLastName = (TextView) findViewById(R.id.txtLName);
                txtLastName.setText(person.name.last);

                Address address = person.address;

                TextView txtAddressLine1 = (TextView) findViewById(R.id.txtAddress1);
                txtAddressLine1.setText(address.address1);

                TextView txtCity = (TextView) findViewById(R.id.txtCity);
                txtCity.setText(address.city);

                TextView txtState = (TextView) findViewById(R.id.txtState);
                txtState.setText(address.state);

                TextView txtZip = (TextView) findViewById(R.id.txtZip);
                txtZip.setText(address.zip);

                TextView txtEmail = (TextView) findViewById(R.id.txtEmail);
                txtEmail.setText(person.email);

                findViewById(R.id.button6).setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(this, "No policies were able to be retrieved.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void register(View view) {
        findViewById(R.id.txtRegistrationComplete).setVisibility(View.VISIBLE);
    }
}