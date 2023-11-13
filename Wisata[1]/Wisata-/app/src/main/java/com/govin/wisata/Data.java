package com.govin.wisata;

import android.app.ProgressDialog;

/**
 * Created by qwerty on 1/25/2018.
 */

public class Data {

    static ProgressDialog pDialog;
//    public static final String SERVER = "http://172.20.10.5/";
    public static final String SERVER = "http://develovit.com/";
//      public static final String SERVER = "http://10.0.2.2/";
//    public static final String SERVER = "http://192.168.43.211/";


    public static String id_user = "id_user ",nama,no_hp,password;





//    public void proses(){
//        String HttpURL = Data.SERVER+"mapwalet/admin/editpetani.php";
//        final ProgressDialog progressDialog = new ProgressDialog(Pasaran_Harga.this);
//        progressDialog.setMessage("Loading... ");
//        progressDialog.show();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpURL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        //do stuffs with response of post
//
//
//
//                        try {
//                            if (!response.equals("Gagal")) {
////                                Intent intent = new Intent();
////                                intent.putExtra("msg", id_lingtra);
////                                setResult(11, intent);
////                                Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_SHORT).show();
//                                finish();
//                            } else {
//                                Toast.makeText(getApplicationContext(),"Gagal di Perbarui",Toast.LENGTH_SHORT).show();
//
//                            }
//                        }catch (Exception e){
////                            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //do stuffs with response erroe
//                        progressDialog.dismiss();
//                        Toast.makeText(getApplicationContext(),"Gagal di Proses, cek koneksi kamu",Toast.LENGTH_SHORT).show();
//
//                    }
//                })
//        {
//            @Override
//            protected Map<String,String> getParams(){
//                Map<String,String> params = new HashMap<String, String>();
//
//
//                params.put("cong",edCong.getText().toString());
//                params.put("mangkok",edMangkok.getText().toString());
//                params.put("sudut", edSudut.getText().toString());
//                params.put("patahan", edPatahan.getText().toString());
//                params.put("kk", edKK.getText().toString());
//
//
//                return params;
//            }
//
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }


//    private void get1baris(String id){
////        final ProgressDialog progressDialog = new ProgressDialog(Pasaran_Harga.this);
////        progressDialog.setMessage("Mengambil data harga pasaran...");
////        progressDialog.setCancelable(false);
////        progressDialog.show();
//        String url = Data.SERVER+"mapwalet/admin/get_kategori.php?id_kategori="+id;
//        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//
//                    JSONObject jsonObject = new JSONObject(response.toString());
//                    JSONArray jsonArray = jsonObject.getJSONArray("result");
//
    //
//                    JSONObject c = jsonArray.getJSONObject(0);
//                    // simpan pada variabel
//                    String nama_kategori = c.getString("nama_kategori");
//                    String pasaran = c.getString("pasaran");
//
//                    edCong.setText(pasaran);
//
////                    kategori1.setSelection(0);
//
////                    Toast.makeText(getApplicationContext(),pasaran,Toast.LENGTH_LONG).show();
////                    progressDialog.hide();
//
//
//
//
//                } catch (JSONException e) {
//
////                    progressDialog.hide();
//                    e.printStackTrace();
//                }
//            }
//
//        }, new Response.ErrorListener(){
//
//
//            @Override
//            public void onErrorResponse (VolleyError error){
//
//
////                progressDialog.hide();
//                Log.d("masalah : ",error.toString());
//
////                Toast.makeText(getActivity(),"Gagal Mendapatkan Data Akun, Periksa koneksi Anda",Toast.LENGTH_SHORT).show();
//            }
//
//        }
//        );
//        rq.add(jsonObjectRequest);
//
//
//    }


}
