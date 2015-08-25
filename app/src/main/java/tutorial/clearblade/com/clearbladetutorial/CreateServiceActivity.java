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
import android.widget.EditText;

import com.clearblade.platform.api.ClearBladeException;
import com.clearblade.platform.api.Code;
import com.clearblade.platform.api.CodeCallback;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CreateServiceActivity extends AppCompatActivity {

    private Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create, menu);
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

    public void createBusinessLogicActivity(View view) {

        String params = getParams();

        if (params.equals("")) {

            AlertDialog.Builder credBuilder = new AlertDialog.Builder(ctx);
            credBuilder.setMessage("City cannot be empty");
            credBuilder.setTitle("Code Service Error");
            credBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog dialog = credBuilder.create();
            dialog.show();
        } else {

            JsonObject paramsObject = new JsonParser().parse(params).getAsJsonObject();
            Code codeService = new Code("ServicePart4", paramsObject);
            codeService.executeWithParams(new CodeCallback() {

                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

                @Override
                public void done(JsonObject response) {
                    Log.i("CB", response.toString());
                    builder.setMessage("Response: " + response.toString());
                    builder.setTitle("Code Service Execution Successful");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(CreateServiceActivity.this, CreateBusinessLogicActivity.class);
                            startActivity(intent);
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

                @Override
                public void error(ClearBladeException exception) {
                    Log.i("CB", "Service Execution Failed. " + exception.getMessage());
                    builder.setMessage("Either the Code Service does not exist or it does not have proper execution permissions");
                    builder.setTitle("Error Executing Service");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
    }

    private String getParams() {

        EditText editText = (EditText) findViewById(R.id.editText2);
        String city = editText.getText().toString();

        if (city.equals("")) {

            return "";
        } else {

            String params = "{\"city\":" + city + "}";

            return params;
        }
    }
}
