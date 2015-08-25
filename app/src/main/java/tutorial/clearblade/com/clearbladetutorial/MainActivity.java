package tutorial.clearblade.com.clearbladetutorial;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.clearblade.platform.api.ClearBlade;
import com.clearblade.platform.api.ClearBladeException;
import com.clearblade.platform.api.InitCallback;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public Context ctx = this;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initializeClearBladeAnonymously(View view) {

        InitCallback initCallback = new InitCallback() {
            @Override
            public void done(boolean b) {
                Log.i("CB", "Init Successful");
                anonSuccess();
            }
            @Override
            public void error(ClearBladeException exp) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setMessage(R.string.initFail_message);
                builder.setTitle(R.string.initFail_title);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                Log.i("CB", "Unsuccessful init " + exp.getMessage());
            }
        };

        HashMap<String, Object> initOptions = new HashMap<String, Object>();
        initOptions.put("platformURL", PlatformConstants.getPlatformUrl());
        initOptions.put("messagingURL", PlatformConstants.getMessagingUrl());

        ClearBlade clearblade = new ClearBlade();
        clearblade.initialize(PlatformConstants.getSystemKey(), PlatformConstants.getSystemSecret(), initOptions, initCallback);
    }

    public void anonSuccess() {
        Intent intent = new Intent(MainActivity.this, AnonSuccessActivity.class);
        startActivity(intent);
    }
}
