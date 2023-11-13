package com.govin.wisata;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TicketFragment extends Fragment {


    private RecyclerAdapterTicket recyclerAdapter;
    private RecyclerView recyclerView;
    private ArrayList<DataRecyclerView> listdata;
    private GridLayoutManager layoutmanager;
    TextView jml_produk,jml_reseller;
    TextView cari;
    ImageView logout;
    SharedPrefManager sharedPrefManager;
    public TicketFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);


        sharedPrefManager = new SharedPrefManager(getActivity());
//        if (sharedPrefManager.getStatus().equals("User")){
//
//            add.setVisibility(View.INVISIBLE);
//        }else {
//
//            add.setVisibility(View.VISIBLE);
//        }



        sharedPrefManager = new SharedPrefManager(getActivity());




        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutmanager=new GridLayoutManager(getActivity(),1);
        layoutmanager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutmanager);
        listdata = new ArrayList<DataRecyclerView>();
        recyclerAdapter = new RecyclerAdapterTicket(getActivity(),listdata);
        recyclerView.setAdapter(recyclerAdapter);

        if (sharedPrefManager.getStatus().equals("User")){

            getProduk();
        }else {
            getProdukAdmin();
        }

//        cari.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), Cari.class));
//            }
//        });



//
//
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN,false);
//                startActivity(new Intent(MainActivity.this, Login.class)
//                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//                finish();
//            }
//        });



        return view;

    }


    private void getProduk(){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Mengambil data..");
        progressDialog.setCancelable(true);
        progressDialog.show();
        String url = Data.SERVER+"wisata/get_tiket.php?id_user="+sharedPrefManager.getUsername();
        RequestQueue rq = Volley.newRequestQueue(getActivity());
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
                        String id_tiket = c.getString("id_tiket");
                        String id_produk = c.getString("id_");
                        String nama = c.getString("nama");
                        String qty = c.getString("qty");
                        String nm_produk = c.getString("name");
                        String deskripsi_produk = c.getString("desc");
                        String harga = c.getString("price");
                        String rating = c.getString("rating");
                        String koordinat = c.getString("koordinat");
                        String images = c.getString("image");

                        DataRecyclerView item = new DataRecyclerView();
                        item.setId_produk(id_produk);
                        item.setNm_produk(nm_produk);
                        item.setDeskripsi_produk(deskripsi_produk);
                        item.setHarga(harga);
                        item.setRating(rating);
                        item.setKategori(koordinat);
                        item.setImage(images);
                        item.setQty(qty);
                        item.setId_tiket(id_tiket);
                        item.setNama(nama);
                        listdata.add(item);
                        recyclerAdapter.notifyDataSetChanged();

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


    private void getProdukAdmin(){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Mengambil data..");
        progressDialog.setCancelable(true);
        progressDialog.show();
        String url = Data.SERVER+"wisata/get_tiket_admin.php";
        RequestQueue rq = Volley.newRequestQueue(getActivity());
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
                        String id_tiket = c.getString("id_tiket");
                        String id_produk = c.getString("id_");
                        String nama = c.getString("nama");
                        String qty = c.getString("qty");
                        String nm_produk = c.getString("name");
                        String deskripsi_produk = c.getString("desc");
                        String harga = c.getString("price");
                        String rating = c.getString("rating");
                        String koordinat = c.getString("koordinat");
                        String images = c.getString("image");

                        DataRecyclerView item = new DataRecyclerView();
                        item.setId_produk(id_produk);
                        item.setNm_produk(nm_produk);
                        item.setDeskripsi_produk(deskripsi_produk);
                        item.setHarga(harga);
                        item.setRating(rating);
                        item.setKategori(koordinat);
                        item.setImage(images);
                        item.setQty(qty);
                        item.setId_tiket(id_tiket);
                        item.setNama(nama);
                        listdata.add(item);
                        recyclerAdapter.notifyDataSetChanged();

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



    public  void refresh(){
        listdata.clear();
        recyclerAdapter.notifyDataSetChanged();
        getProduk();
    }
}