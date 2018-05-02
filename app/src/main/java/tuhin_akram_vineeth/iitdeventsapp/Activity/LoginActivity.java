package tuhin_akram_vineeth.iitdeventsapp.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.BindView;
import tuhin_akram_vineeth.iitdeventsapp.R;
import tuhin_akram_vineeth.iitdeventsapp.Utils.CustomRequest;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login) Button _loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //login();
                onLoginSuccess();

            }
        });

    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String email = _emailText.getText().toString().trim();
        final String password = _passwordText.getText().toString().trim();

        final String url = "http://10.194.26.215/login.php";



        // TODO: Implement your own authentication logic here.
        final RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        // Display the response string.
                                        Log.d("Response : ",response );
                                        onLoginSuccess();
                                       // _response.setText(response);
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("ErrorResponse : ",error.toString() );
                                onLoginFailed();

                            }
                        }) {
                            @Override
                            public byte[] getBody() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("user_id", email);
                                params.put("password", password);
                                String your_string_json = new JSONObject(params).toString() ; // put your json
                                return your_string_json.getBytes();
                            }
                        };
                        queue.add(stringRequest);
                        queue.start();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
        _loginButton.setEnabled(true);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        startActivity(new Intent(this, EventPost.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;
        String email = _emailText.getText().toString().trim();
        String password = _passwordText.getText().toString().trim();
        boolean flag = false;
        if(email.length()==11) {
            if (email.substring(0, 4).matches("\\d+")) {
                String temp = email.substring(7, email.length());
                if (temp.matches("\\d+")) {
                    flag = true;
                } else {
                }
            } else {
            }
        }
        else
        {

        }

        if (flag==false) {
            _emailText.setError("enter a valid entry number");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}