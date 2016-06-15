package sf.hackday.proximityscanner;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;

/**
 * Created by aaron on 6/15/16.
 */

public class InsuranceCardActivity extends Activity {

    private NfcAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: Need to set content view.
        //setContentView(R.layout.insurance_card);
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
}
