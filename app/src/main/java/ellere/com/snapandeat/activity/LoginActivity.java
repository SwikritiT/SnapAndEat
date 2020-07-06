package ellere.com.snapandeat.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import ellere.com.snapandeat.Constants;
import ellere.com.snapandeat.R;

/**
 * Created by DELL on 6/24/2020.
 */

public class LoginActivity extends AppCompatActivity {
    Button loginbtn, gosignupbtn;
    EditText user, pass;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String UserName = "nameKey";
    public static final String Password = "passKey";
    public static final String Email = "emailKey";
    int PRIVATE_MODE = 0;
    // SessionManager sessionManager;

    SharedPreferences pref;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        gosignupbtn = (Button) findViewById(R.id.gotosignup_btn);
        loginbtn = (Button) findViewById(R.id.login_btn);
        user = (EditText) findViewById(R.id.username_login);
        pass = (EditText) findViewById(R.id.password_login);

        pref = getApplicationContext().getSharedPreferences("user_details", MODE_PRIVATE);
        intent = new Intent(LoginActivity.this, FeedActivity.class);
        if (pref.contains("username") && pref.contains("password")) {
            startActivity(intent);
        }
        loginbtn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            String username = user.getText().toString();
                                            String password = pass.getText().toString();


                                            if (username.equals("") || password.equals("")) {
                                                Toast.makeText(getBaseContext(), "Fill up all the field properly", Toast.LENGTH_SHORT).show();
                                                return;
                                            } else {

                                                UserLoginFunction(username, password);
                                            }

                                        }
                                    }
        );

        gosignupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    public void UserLoginFunction(final String username, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("flag");
                            String token = jsonObject.getString("code");
//                            JSONObject myObj=new JSONObject(success);
                            if (success.equals("1")) {
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("username", username);
                                editor.putString("password", password);
                                editor.putString("token",token);
                                editor.apply();
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Log.d("token", "the token is " + token);

                                Intent intent = new Intent(LoginActivity.this, FeedActivity.class);

                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "Enter correct username or password", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("test", "Error ==> " + e.toString());
                            Toast.makeText(LoginActivity.this, "Login failed ==>" + e.toString(), Toast.LENGTH_LONG).show();
                        }


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("test", "Error ==> " + error.toString());
                        Toast.makeText(LoginActivity.this, "Login Failed" + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

  //  @Override
//    protected void onStart() {
//        super.onStart();
//        SharedPreferences sharedPreferences
//                = getSharedPreferences("PreferenceActivity",MODE_PRIVATE);
//        SharedPreferences.Editor editor= sharedPreferences.edit();
//        editor.putString("name",
//                name.getText().toString());
//        editor.putInt("age",
//                Integer.parseInt(
//                        age.getText().toString()));
//        editor.commit();
//    }
}
