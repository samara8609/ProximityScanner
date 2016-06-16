package sf.hack.day.proximity.scanner.activities;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import sf.hack.day.proximity.scanner.model.Address;
import sf.hack.day.proximity.scanner.model.Policy;
import sf.hack.day.proximity.scanner.model.Vehicle;
import sf.hack.day.proximity.scanner.services.PolicyRetrieval;
import sf.hack.day.proximity.scanner.services.QuoteRetrieval;
import sf.hack.day.proximity.scanner.services.VehicleRetrieval;
import sf.hack.day.proximity.scanner.utilities.Converter;


/**
 * Created by samar on 6/15/2016.
 */
public class AutoQuoteActivity  extends AppCompatActivity  implements OnItemSelectedListener{

    private NfcAdapter nfcAdapter;
    private Spinner spinner;
    private Button quoteBtn;
    private TextView quoteTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_quote);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(null == nfcAdapter) {
            Toast.makeText(this, "NFC not supported", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        quoteBtn = (Button) findViewById(R.id.btnQuote);
        quoteTxt = (TextView) findViewById(R.id.txtQuote);

        // Spinner element
        spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("25/50 Coverage");
        categories.add("50/100 Coverage");
        categories.add("100/300 Coverage");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

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

            Vehicle vehicle = VehicleRetrieval.retrieve(Converter.bytesToHex(tag.getId()));

            if(null != vehicle) {

                spinner.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(this, "No policies were able to be retrieved.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
