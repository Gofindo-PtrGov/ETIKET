package com.govin.wisata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TambahProduk extends AppCompatActivity {

    TextView nama_produk,deskripsi_produk,harga;
    Button submit;
    Boolean images1=false,images2=false,images3=false,images4=false;
    Bitmap Bgambar1,Bgambar2,Bgambar3,Bgambar4;
    ConstraintLayout btnAddImageContainer;
    MaterialCardView imageViewContainer;
    ImageView img1,img2,img3,img4,back;
    TextInputEditText rating;
    ArrayList<String> strings = new ArrayList<>();
    ArrayAdapter<String> adp1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_produk);

        nama_produk = findViewById(R.id.inputName);
        deskripsi_produk = findViewById(R.id.inputDescription);
        harga = findViewById(R.id.inputPrice);
        submit = findViewById(R.id.btnSave);
        rating = findViewById(R.id.inputType);

        adp1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strings);
//        kategori.setAdapter(adp1);
        img1 = findViewById(R.id.imageView);
        btnAddImageContainer = findViewById(R.id.btnAddImageContainer);
        imageViewContainer = findViewById(R.id.imageViewContainer);



        btnAddImageContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        imageViewContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cek();
            }
        });

//        getKategori();
    }


    void cek(){
        if (nama_produk.getText().toString().equals("")){
            nama_produk.setError("Nama Produk Wajib diisi");
        }
        else if (deskripsi_produk.getText().toString().equals("")) {
            deskripsi_produk.setError("Deskripsi Produk Wajib diisi");
        }

        else if (harga.getText().toString().equals("")) {
            harga.setError("Harga Wajib diisi");
        }


        else {
            proses();
        }
    }

    public void proses(){
        String HttpURL = Data.SERVER+"wisata/tambah_produk.php";
        final ProgressDialog progressDialog = new ProgressDialog(TambahProduk.this);
        progressDialog.setMessage("Loading... ");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //do stuffs with response of post



                        try {
                            if (response.endsWith("Pesan Terkirim")) {
//                                Intent intent = new Intent();
//                                intent.putExtra("msg", id_lingtra);
//                                setResult(11, intent);
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),"Berhasil di Upload",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),MainActivity2.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(),"Gagal di Upload",Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(getApplicationContext(),"Gagal di Proses, cek koneksi kamu", Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();


                params.put("nama_produk",nama_produk.getText().toString());
                params.put("deskripsi_produk",deskripsi_produk.getText().toString());
                params.put("harga", harga.getText().toString());
                params.put("rating", rating.getText().toString());
                params.put("image1", getStringImage(Bgambar1,images1));


                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
    }


    public String getStringImage(Bitmap bmp,Boolean status) {

        if (status == true){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageBytes = baos.toByteArray();
            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            return encodedImage;
        }
        else {

            return "";
        }



    }


    //Respon dari pengambilan data dari storage
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {
                Uri filePath = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
                    // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
                    setToGambar1(getResizedBitmap(bitmap, 1080),img1);
                    btnAddImageContainer.setVisibility(View.INVISIBLE);
                    imageViewContainer.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                images1 = true;

            }

            else  if (requestCode == 2) {


                Uri filePath = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
                    // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
                    setToGambar2(getResizedBitmap(bitmap, 1080),img2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                images2 = true;
            }

            else  if (requestCode == 3) {

                Uri filePath = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
                    // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
                    setToGambar3(getResizedBitmap(bitmap, 1080),img3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                images3 = true;

            }

            else  if (requestCode == 4) {

                Uri filePath = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
                    // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
                    setToGambar4(getResizedBitmap(bitmap, 1080),img4);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                images4 = true;

            }


        }


    }

    private void setToGambar1(Bitmap bmp, ImageView imageView) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        Bgambar1 = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imageView.setImageBitmap(Bgambar1);
    }

    private void setToGambar2(Bitmap bmp, ImageView imageView) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        Bgambar2 = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imageView.setImageBitmap(Bgambar2);
    }
    private void setToGambar3(Bitmap bmp, ImageView imageView) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        Bgambar3 = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imageView.setImageBitmap(Bgambar3);
    }
    private void setToGambar4(Bitmap bmp, ImageView imageView) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        Bgambar4 = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imageView.setImageBitmap(Bgambar4);
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }


    private void getKategori(){
        final ProgressDialog progressDialog = new ProgressDialog(TambahProduk.this);
        progressDialog.setMessage("Mengambil data..");
        progressDialog.setCancelable(true);
        progressDialog.show();
        String url = Data.SERVER+"reseller/get_kategori.php";
        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray("result");

                    if (jsonArray.isNull(0)){
                        progressDialog.dismiss();
                    }

                    for (int i = 0;i<jsonArray.length();i++){
                        JSONObject c = jsonArray.getJSONObject(i);
                        // simpan pada variabel
                        String id_kategori = c.getString("id_kategori");
                        String nama_kategori = c.getString("nama_kategori");


                        strings.add(nama_kategori);
                        adp1.notifyDataSetChanged();



                        progressDialog.dismiss();

                    }




//                    kategori1.setSelection(0);

//                    Toast.makeText(getApplicationContext(),pasaran,Toast.LENGTH_LONG).show();
//                    progressDialog.hide();




                } catch (JSONException e) {

//                    progressDialog.hide();
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
            }

        }, new Response.ErrorListener(){


            @Override
            public void onErrorResponse (VolleyError error){


//                progressDialog.hide();
                progressDialog.dismiss();
                Log.d("masalah : ",error.toString());

//                Toast.makeText(getActivity(),"Gagal Mendapatkan Data Akun, Periksa koneksi Anda",Toast.LENGTH_SHORT).show();
            }

        }
        );
        rq.add(jsonObjectRequest);


    }
}