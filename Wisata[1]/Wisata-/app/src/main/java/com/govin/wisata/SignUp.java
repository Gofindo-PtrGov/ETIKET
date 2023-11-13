package com.govin.wisata;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SignUp extends AppCompatActivity {

    EditText nama,email,password;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nama = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        save = findViewById(R.id.login);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nama.length()==0|| email.length()==0|| password.length()==0){

                    Toast.makeText(SignUp.this, "Masih ada kosong, cek lagi ya..", Toast.LENGTH_SHORT).show();
                }else{

                    proses();
                }
            }
        });

    }


    private void proses(){
        String HttpURL = Data.SERVER+"wisata/daftar.php";
        final ProgressDialog progressDialog = new ProgressDialog(SignUp.this);
        progressDialog.setMessage("Loading... ");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //do stuffs with response of post



                        try {
                            if (response.endsWith("Berhasil")) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),"Pendaftaran Berhasil", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), Login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                finish();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),"Pendaftaran Gagal, silakan coba lagi", Toast.LENGTH_SHORT).show();

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
                Random rand = new Random();
                int id = rand.nextInt(999999);
                params.put("id_user",id+"");
                params.put("email",email.getText().toString());
                params.put("nama",nama.getText().toString());
                params.put("password",password.getText().toString());


                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}