package com.govin.wisata;

/**
 * Created by qwerty on 29/05/2018.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class RecyclerAdapterTicket extends RecyclerView.Adapter<RecyclerAdapterTicket.ViewHolder> {
private ArrayList<DataRecyclerView> listdata;
private Activity activity;
private Context context;
SharedPrefManager sharedPrefManager;

public RecyclerAdapterTicket(Activity activity, ArrayList<DataRecyclerView> listdata) {
        this.listdata = listdata;
        this.activity = activity;
        }



    @Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_tiket, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
        }

@RequiresApi(api = Build.VERSION_CODES.N)
@Override
public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        //holder.mImage.setImageResource(listdata.get(position).getThubnail());


    sharedPrefManager = new SharedPrefManager(activity);
    if (sharedPrefManager.getStatus().equals("Penjual")){

        holder.ubah.setVisibility(View.GONE);

    }
    else {

        holder.ubah.setVisibility(View.GONE);

    }



     holder.nama.setText(listdata.get(position).getNm_produk());
    holder.ket.setText(listdata.get(position).getDeskripsi_produk());
    holder.name.setText(listdata.get(position).getNama());
    holder.qty.setText("Qty : "+listdata.get(position).getQty());
//     holder.harga.setText(formatRupiah(Double.parseDouble(listdata.get(position).getHarga())));
//    holder.stok.setText(listdata.get(position).getStok());

//    Glide.with(activity).load(Data.SERVER+"wisata/gambar/produk/"+listdata.get(position).getImage()).centerCrop().into(holder.image);

     holder.image.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
//             Intent i = new Intent(activity, DetailTicket.class);
//             i.putExtra("id_produk",listdata.get(position).getId_tiket());
//             i.putExtra("nama",listdata.get(position).getNm_produk());
//             i.putExtra("name",listdata.get(position).getNama());
//             i.putExtra("deskripsi_produk",listdata.get(position).getDeskripsi_produk());
//             i.putExtra("image1",Data.SERVER+"wisata/gambar/produk/"+listdata.get(position).getImage());
//             activity.startActivity(i);
         }
     });

    holder.all.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent i = new Intent(activity, DetailTicket.class);
//            i.putExtra("id_produk",listdata.get(position).getId_tiket());
//            i.putExtra("nama",listdata.get(position).getNm_produk());
//            i.putExtra("name",listdata.get(position).getNama());
//            i.putExtra("deskripsi_produk",listdata.get(position).getDeskripsi_produk());
//            i.putExtra("image1",Data.SERVER+"wisata/gambar/produk/"+listdata.get(position).getImage());
//            activity.startActivity(i);
        }
    });

//     holder.ubah.setOnClickListener(new View.OnClickListener() {
//         @Override
//         public void onClick(View v) {
//             Intent i = new Intent(activity, EditProduk.class);
//             i.putExtra("id_produk",listdata.get(position).getId_produk());
//             i.putExtra("nama",listdata.get(position).getNm_produk());
//             i.putExtra("harga",listdata.get(position).getHarga());
//             i.putExtra("kategori",listdata.get(position).getKategori());
//             i.putExtra("deskripsi_produk",listdata.get(position).getDeskripsi_produk());
//             i.putExtra("image1",Data.SERVER+"wisata/gambar/produk/"+listdata.get(position).getImage());
//             activity.startActivity(i);
//         }
//     });

//    holder.hapus.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//            builder.setMessage("Anda yakin mau menghapus "+listdata.get(position).getNm_produk()+" ?");
//            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    prosesHapus(listdata.get(position).getId_produk());
//                }
//            });
//            builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                }
//            });
//            builder.show();
//
//        }
//    });


//    SharedPrefManager sharedPrefManager = new SharedPrefManager(activity);
//    if (sharedPrefManager.getUsername().equals("admin")){
//        holder.aksi.setVisibility(View.VISIBLE);
//        holder.garis.setVisibility(View.VISIBLE);
//
//    }
//    else {
//
//        holder.aksi.setVisibility(View.GONE);
//        holder.garis.setVisibility(View.GONE);
//    }





        }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        }
        return formatRupiah.format(number);
    }

    private void prosesHapus(final String id){
        String HttpURL = Data.SERVER+"reseller/hapus_produk.php";
        final ProgressDialog progressDialog = new ProgressDialog(activity);
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
//                                ((MainActivity)activity).refresh();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(activity,"Gagal menghapus produk, silakan coba lagi", Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(activity,error.toString(), Toast.LENGTH_LONG).show();

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

        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(stringRequest);
    }


    @Override
public int getItemCount() {
        return listdata.size();
        }

public static class ViewHolder extends RecyclerView.ViewHolder {
//    public RelativeLayout cv;
    public TextView word,desc;
    RelativeLayout all,aksi,garis;
    Button lihat;
    TextView nama,harga,ket,radius,name,qty,stok,hapus;
    MaterialButton ubah;
    ImageView image;

    public ViewHolder(View v) {
        super(v);
        nama=v.findViewById(R.id.nama);
        ket= v.findViewById(R.id.ket);
        radius= v.findViewById(R.id.radius);
        image= v.findViewById(R.id.image);
        qty= v.findViewById(R.id.qty);
        name= v.findViewById(R.id.name);


        ubah= v.findViewById(R.id.btnEdit);
        all= v.findViewById(R.id.all);



    }
}

}