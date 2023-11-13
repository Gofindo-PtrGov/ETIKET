package com.govin.wisata;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    EditText email,password;
    Button submit;
    TextView daftar;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPrefManager = new SharedPrefManager(this);

        if (sharedPrefManager.getSPSudahLogin() ){

            startActivity(new Intent(Login.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
        else if (!sharedPrefManager.getSPSudahLogin()) {


        }



        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        submit = findViewById(R.id.login);
//        daftar = findViewById(R.id.btnRegisterText);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().equals("") || password.getText().toString().equals("")){
                    Toast.makeText(Login.this, "Masih ada yang kosong", Toast.LENGTH_SHORT).show();
                }
                else {
                    Login(email.getText().toString(),password.getText().toString());

                }


            }
        });

//        daftar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), Register.class));
//            }
//        });

    }


    public void Login(String id_user,String pass){
        String HttpURL = Data.SERVER+"wisata/UserLogin.php?id_user="+id_user+"&password="+pass;
        final ProgressDialog progressDialog = new ProgressDialog(Login.this);
        progressDialog.setMessage("Loading... ");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //do stuffs with response of post



                        try {
                            if (response.equalsIgnoreCase("Login Berhasil")) {


                                progressDialog.dismiss();
                                getAkun(id_user);


                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),"Login Gagal, silakan cek username dan password anda",Toast.LENGTH_SHORT).show();

                            }
                        }catch (Exception e){
//                            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //do stuffs with response erroe
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Gagal Login, cek koneksi kamu",Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();


                params.put("id_user",id_user);
                params.put("password",pass);


                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


//    public void UserLoginFunction(final String telepon, final String password){
//
//        class UserLoginClass extends AsyncTask<String,Void,String> {
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//
//                Loading(Login.this,"");
//            }
//
//            @Override
//            protected void onPostExecute(String httpResponseMsg) {
//
//                super.onPostExecute(httpResponseMsg);
//
//                progressDialog.dismiss();
//
//                if(httpResponseMsg.equalsIgnoreCase("Login Berhasil")){
//
////                    finish();
//                    getAkun(telepon);
////                    sharedPrefManager.saveSPString(SharedPrefManager.ID_User, telepon);
////                    sharedPrefManager.saveSPString(SharedPrefManager.Password, password);
////                    sharedPrefManager.saveSPString(SharedPrefManager.Jenis_Peserta, jenis_id.getText().toString());
//
//                    // Shared Pref ini berfungsi untuk menjadi trigger session login
////                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
//
////                    Intent intent = new Intent(LoginUser.this, MainActivity.class);
////                    startActivity(intent);
//
//                }
//                else{
//
//                    Msg(Login.this,httpResponseMsg);
//                }
//
//            }
//
//            @Override
//            protected String doInBackground(String... params) {
//
//                hashMap.put("id_user",params[0]);
//
//                hashMap.put("password",params[1]);
//
//
//
//                finalResult = httpParse.postRequest(hashMap, HttpURL);
//
//                return finalResult;
//            }
//        }
//
//        UserLoginClass userLoginClass = new UserLoginClass();
//
//        userLoginClass.execute(telepon,password);
//    }

    private void getAkun(final String id_user){
        final ProgressDialog progressDialog = new ProgressDialog(Login.this);
        progressDialog.setMessage("Mengambil data Akun...");
        progressDialog.show();
        String url = Data.SERVER+"wisata/getakun.php?id_user="+id_user;
        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray("result");

                    JSONObject c = jsonArray.getJSONObject(0);
                    // simpan pada variabel
                    String nama = c.getString("nama");
                    String id_user2 = c.getString("id_user");

                    sharedPrefManager.saveSPString(SharedPrefManager.Nama, nama);
                    sharedPrefManager.saveSPString(SharedPrefManager.Username, id_user2);
                    sharedPrefManager.saveSPString(SharedPrefManager.Email, id_user);
                    sharedPrefManager.saveSPString(SharedPrefManager.Status, "User");

                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN,true);
                    progressDialog.dismiss();
                    startActivity(new Intent(Login.this, MainActivity2.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener(){


            @Override
            public void onErrorResponse (VolleyError error){

                Log.d("masalah : ",error.toString());

//                Toast.makeText(getActivity(),"Gagal Mendapatkan Data Akun, Periksa koneksi Anda",Toast.LENGTH_SHORT).show();
            }

        }
        );
        rq.add(jsonObjectRequest);


    }
}