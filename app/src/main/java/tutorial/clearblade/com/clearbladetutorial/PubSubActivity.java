package tutorial.clearblade.com.clearbladetutorial;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.clearblade.platform.api.ClearBladeException;
import com.clearblade.platform.api.InitCallback;
import com.clearblade.platform.api.Message;
import com.clearblade.platform.api.MessageCallback;

public class PubSubActivity extends AppCompatActivity {

    private Context ctx = this;
    private int qualityOfService = 1;
    private String topic = "weather";
    private Message message;
    private LinearLayout linearLayout;
    private ArrayAdapter<String> mqttMessageAdapter;
    private ListView messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pub_sub);

        mqttMessageAdapter = new ArrayAdapter<String>(this, R.layout.message);
        messages = (ListView) findViewById(R.id.listView);
        messages.setAdapter(mqttMessageAdapter);


        hidePublishView();
        initMessaging();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pub_sub, menu);
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

    public void hidePublishView() {

        linearLayout = (LinearLayout) findViewById(R.id.publishLayout);
        linearLayout.setVisibility(View.INVISIBLE);
    }

    public void initMessaging() {

        message = new Message(ctx, qualityOfService, new InitCallback() {

            @Override
            public void done(boolean result) {

            }
            @Override
            public void error(ClearBladeException exception) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setMessage("Failed to connect to MQTT Broker. " + exception.getMessage());
                builder.setTitle("Connection Failure");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(PubSubActivity.this, MessagingActivity.class);
                        startActivity(intent);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void subscribe(View view) {

        message.subscribe(topic, new MessageCallback() {

            @Override
            public void done(String topic, String message) {

                mqttMessageAdapter.add("Topic: " + topic + "\n" + "Message: " + message);
            }
        });
        view.setEnabled(false);
        Button button = (Button) findViewById(R.id.button11);
        button.setText("Subscribed!");
        showPubishView();
    }

    public void showPubishView() {

        linearLayout.setVisibility(View.VISIBLE);
    }

    public void publish(View view) {

        EditText messageToPublish = (EditText) findViewById(R.id.editText);
        message.publish(topic, messageToPublish.getText().toString());
    }
}
