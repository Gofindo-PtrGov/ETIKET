package com.govin.wisata;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.github.tntkhang.fullscreenimageview.library.FullScreenImageViewActivity;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DetailProduk extends AppCompatActivity {

    boolean deleteB =false;

    TextView nama_produk, deskripsi_produk, harga, stok,price,txtrating,txtrating2,rating,txtorang;
    Button chat;
    Boolean images1 = false, images2 = false, images3 = false, images4 = false;
    Bitmap Bgambar1, Bgambar2, Bgambar3, Bgambar4;
    ImageView img1, img2, img3, img4,fav;
    ImageView edit;
    Bundle bundle;
    CardView back,delete;
    MaterialButton btnBuy;
    SharedPrefManager sharedPrefManager;

    DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);


        databaseHandler = new DatabaseHandler(this);

        back = findViewById(R.id.btnBack);
        fav = findViewById(R.id.fav);
        delete = findViewById(R.id.btnDelete);
        nama_produk = findViewById(R.id.tvName);
        deskripsi_produk = findViewById(R.id.tvDesc);
        harga = findViewById(R.id.tvPrice);
        img1 = findViewById(R.id.imageView);


        price = findViewById(R.id.price);
        price.setText("PRICE");
        txtrating = findViewById(R.id.txtxt2);
        txtrating.setText("RATING");
        txtrating2 = findViewById(R.id.txtx2);
        txtrating2.setText("/10");
        txtorang = findViewById(R.id.txtx1);
        txtorang.setText("/Orang");
        rating = findViewById(R.id.txtxt3);
//        chat = findViewById(R.id.chat);
//        edit = findViewById(R.id.edit);
//        stok = findViewById(R.id.stok);

        btnBuy = findViewById(R.id.btnBuy);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        getBanner();


        sharedPrefManager = new SharedPrefManager(this);
        if (sharedPrefManager.getStatus().equals("Penjual")){

            delete.setVisibility(View.VISIBLE);

        }
        else {

            delete.setVisibility(View.GONE);

        }




        bundle = getIntent().getExtras();
        if (bundle != null) {
            nama_produk.setText(bundle.getString("nama"));
            deskripsi_produk.setText(bundle.getString("deskripsi_produk"));
            rating.setText(bundle.getString("rating"));
            harga.setText(formatRupiah(Double.parseDouble(bundle.getString("harga"))));
//            stok.setText(bundle.getString("stok"));
            Glide.with(DetailProduk.this).load(bundle.getString("image1")).into(img1);
//            Glide.with(DetailProduk.this).load(bundle.getString("image2")).into(img2);
//            Glide.with(DetailProduk.this).load(bundle.getString("image3")).into(img3);
//            Glide.with(DetailProduk.this).load(bundle.getString("image4")).into(img4);



        }


        String selectQuery = "SELECT  * FROM destinasi where id_ = '"+bundle.getString("id_produk")+ "'";

        DatabaseHandler db2 = new DatabaseHandler(this);
        SQLiteDatabase db = db2.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (!(cursor.moveToFirst()) || cursor.getCount() ==0){

                deleteB = false;

                   fav.setImageDrawable(getResources().getDrawable(R.drawable.outline_favorite_border_24));

               }else {
                deleteB = true;
                   fav.setImageDrawable(getResources().getDrawable(R.drawable.img_15));
               }



        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (deleteB == true){

                    try {
                        String queri = "DELETE from destinasi where id_ = '"+bundle.getString("id_produk")+"'";
                        DatabaseHandler db2 = new DatabaseHandler(getApplicationContext());
                        SQLiteDatabase db = db2.getWritableDatabase();
                        Cursor cursor = db.rawQuery(queri, null);
                       cursor.moveToFirst();

                        fav.setImageDrawable(getResources().getDrawable(R.drawable.outline_favorite_border_24));
                        deleteB = false;
                    }catch (Exception e){

                        Toast.makeText(DetailProduk.this, e.toString(), Toast.LENGTH_SHORT).show();
                        deleteB = true;
                        fav.setImageDrawable(getResources().getDrawable(R.drawable.img_15));
                    }


                }else if (deleteB == false){
                    try {
                        String queri = "INSERT INTO destinasi (id_,name,desc,price,rating,image,koordinat) Values ('"+bundle.getString("id_produk")+
                                "','"+bundle.getString("nama")+"','"+bundle.getString("deskripsi_produk")+"','"+bundle.getString("harga")+
                                "','"+bundle.getString("rating")+"','"+bundle.getString("image1")+"','')";
                        DatabaseHandler db2 = new DatabaseHandler(getApplicationContext());
                        SQLiteDatabase db = db2.getWritableDatabase();
                        Cursor cursor = db.rawQuery(queri, null);

                        fav.setImageDrawable(getResources().getDrawable(R.drawable.img_15));
                        cursor.moveToFirst();
                        deleteB = true;

                    }
                    catch (Exception e){

                        fav.setImageDrawable(getResources().getDrawable(R.drawable.outline_favorite_border_24));
                        Toast.makeText(DetailProduk.this, e.toString(), Toast.LENGTH_SHORT).show();
                        deleteB = false;
//                    Toast.makeText(DetailProduk.this, e.toString(), Toast.LENGTH_SHORT).show();

//                        if (e.toString().endsWith("(code 1555)")){
//                            fav.setImageDrawable(getResources().getDrawable(R.drawable.outline_favorite_border_24));
//                        }
//                        else {
//
//                            fav.setImageDrawable(getResources().getDrawable(R.drawable.img_15));
//                        }
                    }
                }






            }
        });




        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ale = new AlertDialog.Builder(DetailProduk.this);
                ale.setMessage("Anda yakin mau menghapus "+bundle.getString("nama")+" ?");
                ale.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        prosesHapus(bundle.getString("id_produk"));
                    }
                });
                ale.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                ale.show();

            }
        });


        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                chatWa("081568212993");

                Intent i = new Intent(getApplicationContext(), Checkout.class);
                i.putExtra("id_produk",bundle.getString("id_produk"));
                i.putExtra("nama",bundle.getString("nama"));
                i.putExtra("harga",bundle.getString("harga"));
                i.putExtra("radius",bundle.getString("radius"));
                i.putExtra("deskripsi_produk",bundle.getString("deskripsi_produk"));
                i.putExtra("image1",bundle.getString("image1"));
                startActivity(i);
            }
        });

        if (sharedPrefManager.getStatus().equals("Penjual")){
            btnBuy.setVisibility(View.INVISIBLE);
        }

//        edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), EditProduk.class);
//                i.putExtra("id_produk", bundle.getString("id_produk"));
//                i.putExtra("nama", bundle.getString("nama"));
//                i.putExtra("harga", bundle.getString("harga"));
//                i.putExtra("stok", bundle.getString("stok"));
//                i.putExtra("deskripsi_produk", bundle.getString("deskripsi_produk"));
//                i.putExtra("image1", bundle.getString("image1"));
//                i.putExtra("image2", bundle.getString("image2"));
//                i.putExtra("image3", bundle.getString("image3"));
//                i.putExtra("image4", bundle.getString("image4"));
//                startActivity(i);
//            }
//        });
//
//        edit.setVisibility(View.GONE);
//
//        final ArrayList<String> uriString = new ArrayList<>();
//        uriString.add(bundle.getString("image1"));
//        uriString.add(bundle.getString("image2"));
//        uriString.add(bundle.getString("image3"));
//        uriString.add(bundle.getString("image4"));

//        img1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onImageClickAction(uriString, 0);
//            }
//        });


    }

    private void onImageClickAction(ArrayList<String> uriString, int pos) {
        Intent fullImageIntent = new Intent(getApplicationContext(), FullScreenImageViewActivity.class);
        fullImageIntent.putExtra(FullScreenImageViewActivity.URI_LIST_DATA, uriString);
        fullImageIntent.putExtra(FullScreenImageViewActivity.IMAGE_FULL_SCREEN_CURRENT_POS, pos);
        startActivity(fullImageIntent);

    }

    void chatWa(String telepon) {
        String tel = telepon;
        if (tel.startsWith("0")) {
            String x = tel.substring(1);
            tel = "62" + x;
        } else if (tel.startsWith("+")) {
            String x = tel.substring(2);
            tel = "62" + x;
        }


        String url = "https://api.whatsapp.com/send?phone=" + tel + "&text=Apakah%20stok%20ready%20?";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        i.setPackage("com.whatsapp");
        startActivity(i);
    }

    private String formatRupiah(Double number) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        }
        return formatRupiah.format(number);
    }


    private void prosesHapus(final String id){
        String HttpURL = Data.SERVER+"wisata/hapus_produk.php";
        final ProgressDialog progressDialog = new ProgressDialog(DetailProduk.this);
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
                                startActivity(new Intent(getApplicationContext(), MainActivity2.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                finish();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),"Gagal menghapus, silakan coba lagi", Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_LONG).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();



                params.put("id_produk",id);


                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

}