package com.govin.wisata;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class KelolaProfile extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;
    TextInputEditText nama,email,no_hp,password;
    MaterialButton btnSimpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        nama = findViewById(R.id.inputName);
        email = findViewById(R.id.inputEmail);
        no_hp = findViewById(R.id.inputPhoneNumber);
        password = findViewById(R.id.inputPassword);
        btnSimpan = findViewById(R.id.btnSave);

         sharedPrefManager = new SharedPrefManager(this);

        nama.setText(sharedPrefManager.getNama());
        email.setText(sharedPrefManager.getUsername());
        no_hp.setText(sharedPrefManager.getTelepon());


        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nama.length() ==0|| email.length()==0||no_hp.length()==0){
                    Toast.makeText(KelolaProfile.this, "Nama,Email dan Phone Number harus diisi", Toast.LENGTH_SHORT).show();
                }else {
                    proses();

                }
            }
        });


    }



    private void proses(){
        String HttpURL = Data.SERVER+"wisata/edit_profile.php";
        final ProgressDialog progressDialog = new ProgressDialog(KelolaProfile.this);
        progressDialog.setMessage("Loading... ");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //do stuffs with response of post



                        try {
                            if (response.endsWith("Berhasil")) {

                                sharedPrefManager.saveSPString(SharedPrefManager.Nama,nama.getText().toString());
                                sharedPrefManager.saveSPString(SharedPrefManager.Username,email.getText().toString());
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),"Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity2.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                finish();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),"Data Gagal Disimpan, silakan coba lagi", Toast.LENGTH_SHORT).show();

                            }
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //do stuffs with response erroe
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_LONG).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();

                params.put("id_user",email.getText().toString());
                params.put("nama",nama.getText().toString());
                params.put("no_hp",no_hp.getText().toString());
                params.put("password",password.getText().toString());


                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}