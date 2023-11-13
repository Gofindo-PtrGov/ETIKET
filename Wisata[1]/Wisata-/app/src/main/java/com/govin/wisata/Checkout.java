package com.govin.wisata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback;
import com.midtrans.sdk.corekit.core.MidtransSDK;
import com.midtrans.sdk.corekit.core.TransactionRequest;
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme;
import com.midtrans.sdk.corekit.models.BillingAddress;
import com.midtrans.sdk.corekit.models.CustomerDetails;
import com.midtrans.sdk.corekit.models.ShippingAddress;
import com.midtrans.sdk.corekit.models.snap.TransactionResult;
import com.midtrans.sdk.uikit.SdkUIFlowBuilder;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class Checkout extends AppCompatActivity {

    TextView nama_produk, deskripsi_produk, harga,totalPayment, qty,rating,txtorang,koordinat;
    Button chat;
    int totalPaymentAkhir;
    Boolean images1 = false, images2 = false, images3 = false, images4 = false;
    Bitmap Bgambar1, Bgambar2, Bgambar3, Bgambar4;
    ImageView img1, img2, img3, img4,tambah,kurang;
    ImageView edit;
    Bundle bundle;

    TextView date,time;
    CardView back,delete;
    MaterialButton btnBuy;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);


        bundle = getIntent().getExtras();


        nama_produk = findViewById(R.id.nama);
        deskripsi_produk = findViewById(R.id.ket);
        harga = findViewById(R.id.total);
        img1 = findViewById(R.id.image);
        koordinat = findViewById(R.id.radius);
        totalPayment = findViewById(R.id.total2);


        date = findViewById(R.id.date);
        time = findViewById(R.id.time);

        date.setText(Tanggal.getTanggal());
        time.setText(Tanggal.getWaktu()+" Wib");

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();

                int  mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Checkout.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                String date_boking =  dayOfMonth+ "-" + (monthOfYear + 1) + "-" +year ;

                                date.setText(date_boking);
                            }
                        }, mYear, mMonth,mDay );
                datePickerDialog.show();
            }
        });

        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Checkout.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                if (hourOfDay <10 && minute <10){
                                    time.setText("0"+hourOfDay + ":" +"0"+ minute+" Wib");
                                } else if (hourOfDay <10) {
                                    time.setText("0"+hourOfDay + ":"+ minute+" Wib");
                                }else if (minute <10) {
                                    time.setText(hourOfDay + ":"+"0"+ minute+" Wib");
                                }
                                else {

                                    time.setText(hourOfDay + ":" + minute+" Wib");
                                }

                            }
                        }, mHour, mMinute, true);
                timePickerDialog.show();
            }
        });


        tambah = findViewById(R.id.tambah);
        kurang = findViewById(R.id.kurang);
        qty = findViewById(R.id.qty);


        totalPaymentAkhir = Integer.parseInt(bundle.getString("harga"));

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int x = Integer.parseInt(qty.getText().toString())+1;
                qty.setText(x+"");
               int a =x*Integer.parseInt(bundle.getString("harga"));

               totalPaymentAkhir = a;
               totalPayment.setText(formatRupiah(Double.parseDouble(totalPaymentAkhir+"")));
            }
        });

        kurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int x = Integer.parseInt(qty.getText().toString())-1;
                if (x<=0){

                }else {

                    qty.setText(x+"");
                    int a =x*Integer.parseInt(bundle.getString("harga"));

                    totalPaymentAkhir = a;
                    totalPayment.setText(formatRupiah(Double.parseDouble(totalPaymentAkhir+"")));
                }
            }
        });


//        chat = findViewById(R.id.chat);
//        edit = findViewById(R.id.edit);
//        stok = findViewById(R.id.stok);

        btnBuy = findViewById(R.id.btnBuy);

//        getBanner();


        sharedPrefManager = new SharedPrefManager(this);
        if (sharedPrefManager.getStatus().equals("Penjual")){

            btnBuy.setVisibility(View.GONE);

        }
        else {

            btnBuy.setVisibility(View.VISIBLE);

        }




        if (bundle != null) {
            nama_produk.setText(bundle.getString("nama"));
            deskripsi_produk.setText(bundle.getString("deskripsi_produk"));
            koordinat.setText(bundle.getString("radius"));
//            rating.setText(bundle.getString("rating"));
            harga.setText(formatRupiah(Double.parseDouble(bundle.getString("harga"))));
            totalPayment.setText(formatRupiah(Double.parseDouble(bundle.getString("harga"))));
//            stok.setText(bundle.getString("stok"));
            Glide.with(Checkout.this).load(bundle.getString("image1")).into(img1);
//            Glide.with(DetailProduk.this).load(bundle.getString("image2")).into(img2);
//            Glide.with(DetailProduk.this).load(bundle.getString("image3")).into(img3);
//            Glide.with(DetailProduk.this).load(bundle.getString("image4")).into(img4);



        }



        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(), Checkout.class);
//                i.putExtra("id_produk",bundle.getString("id_produk"));
//                i.putExtra("nama",bundle.getString("nama"));
//                i.putExtra("harga",bundle.getString("harga"));
//                i.putExtra("radius",bundle.getString("radius"));
//                i.putExtra("deskripsi_produk",bundle.getString("deskripsi_produk"));
//                i.putExtra("image1",bundle.getString("image1"));
//                startActivity(i);


                Random rand = new Random();
                int id = rand.nextInt(999999);
                SharedPrefManager sharedPrefManager = new SharedPrefManager(Checkout.this);

                TransactionRequest transactionRequest = new TransactionRequest(sharedPrefManager.getUsername()+"-"+id, Double.parseDouble(totalPaymentAkhir+""));
                CustomerDetails customerDetails = new CustomerDetails();
                customerDetails.setCustomerIdentifier(sharedPrefManager.getNama()+"-"+sharedPrefManager.getUsername());
                customerDetails.setPhone("081372048313");
                customerDetails.setFirstName(sharedPrefManager.getNama());
                customerDetails.setEmail(sharedPrefManager.getEmail());

                ShippingAddress shippingAddress = new ShippingAddress();
                shippingAddress.setAddress("Jalan Andalas Gang Sebelah No. 1");
                shippingAddress.setCity("Jakarta");
                shippingAddress.setPostalCode("10220");
                customerDetails.setShippingAddress(shippingAddress);

                BillingAddress billingAddress = new BillingAddress();
                billingAddress.setAddress("Jalan Andalas Gang Sebelah No. 1");
                billingAddress.setCity("Jakarta");
                billingAddress.setPostalCode("10220");
                customerDetails.setBillingAddress(billingAddress);

                transactionRequest.setCustomerDetails(customerDetails);
                MidtransSDK.getInstance().setTransactionRequest(transactionRequest);
                MidtransSDK.getInstance().startPaymentUiFlow(Checkout.this);
            }
        });

        payment();
    }


    public void proses(String status,String status_bayar){
        String HttpURL = Data.SERVER+"wisata/proses_transaksi.php";
        final ProgressDialog progressDialog = new ProgressDialog(Checkout.this);
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
//                                sendMail("transfortasionlineriau@gmail.com");
                                Toast.makeText(getApplicationContext(),"Pemesanan Berhasil dilakukan",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity2.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(),"Pemesanan Gagal dilakukan",Toast.LENGTH_SHORT).show();

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


                params.put("id_user",sharedPrefManager.getUsername());
                params.put("id_destinasi",bundle.getString("id_produk"));
                params.put("qty", qty.getText().toString());
                params.put("total", totalPaymentAkhir+"");
                params.put("status_bayar", status_bayar);
                params.put("metode_payment", "");


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


    void payment(){

        SdkUIFlowBuilder.init()
                .setClientKey("SB-Mid-client-Bu5OWycocMlWjRsa") // client_key is mandatory
                .setContext(Checkout.this) // context is mandatory
                .setTransactionFinishedCallback(new TransactionFinishedCallback() {
                    @Override
                    public void onTransactionFinished(TransactionResult result) {
                        // Handle finished transaction here.
                        if (result.getResponse() != null) {
                            switch (result.getStatus()) {
                                case TransactionResult.STATUS_SUCCESS:
                                    Toast.makeText(Checkout.this, "Transaction Finished. ", Toast.LENGTH_LONG).show();
                                    proses("Diterima","Lunas");
                                    break;
                                case TransactionResult.STATUS_PENDING:
                                    Toast.makeText(Checkout.this, "Transaction Pending. " , Toast.LENGTH_LONG).show();
                                    proses("Pending","Belum Lunas");
                                    break;
                                case TransactionResult.STATUS_FAILED:
                                    Toast.makeText(Checkout.this, "Transaction Failed. ID: " + result.getResponse().getTransactionId() + ". Message: " + result.getResponse().getStatusMessage(), Toast.LENGTH_LONG).show();
                                    break;
                            }
//                            result.getResponse().getValidationMessages();
                        } else if (result.isTransactionCanceled()) {
                            Toast.makeText(Checkout.this, "Transaction Canceled", Toast.LENGTH_LONG).show();
                        } else {
                            if (result.getStatus().equalsIgnoreCase(TransactionResult.STATUS_INVALID)) {
                                Toast.makeText(Checkout.this, "Transaction Invalid", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Checkout.this, "Transaction Finished with failure.", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }) // set transaction finish callback (sdk callback)
                .setMerchantBaseUrl(BuildConfig.BASE_URL) //set merchant url (required)
//                .setMerchantBaseUrl("https://www.develovit.com/boking_lapangan/server_key.php/")
                .enableLog(true) // enable sdk log (optional)
                .setColorTheme(new CustomColorTheme("#FFE51255", "#B61548", "#FFE51255")) // set theme. it will replace theme on snap theme on MAP ( optional)
                .buildSDK();
    }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        }
        return formatRupiah.format(number);
    }
}