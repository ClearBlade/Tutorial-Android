package tutorial.clearblade.com.clearbladetutorial;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.clearblade.platform.api.ClearBlade;
import com.clearblade.platform.api.ClearBladeException;
import com.clearblade.platform.api.InitCallback;

import java.util.HashMap;

public class LoginWithCertActivity extends AppCompatActivity {
    private Context ctx = this;
    private EditText certPath;
    private EditText certPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_cert);

        certPath = (EditText) findViewById(R.id.certPath);
        certPass = (EditText) findViewById(R.id.certPass);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_with_cert, menu);
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

    public void loginWithCert(View view) {

        if (certPath.getText().toString().equals("") || certPass.getText().toString().equals("")) {

            AlertDialog.Builder credBuilder = new AlertDialog.Builder(ctx);
            credBuilder.setMessage("Cert Path or Password cannot be empty");
            credBuilder.setTitle("Login Error");
            credBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog dialog = credBuilder.create();
            dialog.show();
        } else {
            InitCallback initCallback = new InitCallback() {
                @Override
                public void done(boolean b) {
                    Log.i("CB", "Auth user cert initialization successful");
                    loginSuccessful();
                }

                @Override
                public void error(ClearBladeException exp) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                    builder.setMessage("ClearBlade initialization failed. Please check your certificate path or password");
                    builder.setTitle(R.string.initFail_title);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                    Log.i("CB", "Initialization failed " + exp.getMessage());
                }
            };

            HashMap<String, Object> initOptions = new HashMap<String, Object>();
            initOptions.put("platformURL", PlatformConstants.getPlatformUrl());
            initOptions.put("messagingURL", PlatformConstants.getMessagingUrl());
            initOptions.put("certPath", certPath.getText().toString());
            initOptions.put("clientCertPassword", certPass.getText().toString());

            ClearBlade clearBlade = new ClearBlade();
            clearBlade.initializeWithCert(PlatformConstants.getSystemKey(), PlatformConstants.getSystemSecret(), initOptions, initCallback);
        }
    }

    public void loginSuccessful() {

        Intent intent = new Intent(LoginWithCertActivity.this, LoginSuccessActivity.class);
        startActivity(intent);
    }
}
