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
import android.widget.TextView;

import com.clearblade.platform.api.ClearBladeException;
import com.clearblade.platform.api.Collection;
import com.clearblade.platform.api.DataCallback;
import com.clearblade.platform.api.QueryResponse;
import com.google.gson.JsonElement;

public class LoginSuccessActivity extends AppCompatActivity {

    private Context ctx = this;
    public static String collectionData = "";
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        TextView text = new TextView(this);
        text = (TextView) findViewById(R.id.success);
        text.append(PlatformConstants.getUserEmail());

        mediaPlayer = MediaPlayer.create(LoginSuccessActivity.this, R.raw.tada);
        mediaPlayer.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_success, menu);
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

    public void collectionActivity(View view) {

        mediaPlayer.stop();
        final Collection collection = new Collection(PlatformConstants.getCollectionID());
        collection.fetchAll(new DataCallback() {
            @Override
            public void done(QueryResponse response) {
                Log.i("CB", "Fetch Successful " + response.getDataJsonAsString());
                collectionData = response.getDataJsonAsString();
                Intent intent = new Intent(LoginSuccessActivity.this, CollectionActivity.class);
                startActivity(intent);
            }

            @Override
            public void error(ClearBladeException exception) {
                Log.i("CB", "Collection fetch failed. " + exception.getMessage());

                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

                if (exception.getMessage().indexOf("Bad permissions") != -1) {

                    builder.setMessage("You haven't set the right permissions on the Collection");
                    builder.setTitle("Error Fetching Collection");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    builder.setMessage("This is awkward. It looks like you did not complete part 3 of the tutorial. Collection does not exist");
                    builder.setTitle("Error Fetching Collection");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }
}
