package sf.hack.day.proximity.scanner.activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void insuranceCardClick(View view) {
        startActivity(new Intent(MainActivity.this, InsuranceCardActivity.class));
    }

    public void vehicleCardClick(View view) {
        startActivity(new Intent(MainActivity.this, InsuranceCardActivity.class));
    }

    public void autoQuoteClick(View view) {
        startActivity(new Intent(MainActivity.this, AutoQuoteActivity.class));
    }
}
