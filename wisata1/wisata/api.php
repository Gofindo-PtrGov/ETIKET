<?php

$curl = curl_init();

      curl_setopt_array($curl, array(
        CURLOPT_URL => 'https://api.fonnte.com/send',
        CURLOPT_RETURNTRANSFER => true,
        CURLOPT_ENCODING => '',
        CURLOPT_MAXREDIRS => 10,
        CURLOPT_TIMEOUT => 0,
        CURLOPT_FOLLOWLOCATION => true,
        CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
        CURLOPT_CUSTOMREQUEST => 'POST',
        CURLOPT_POSTFIELDS => array(
      'target' => '081372048313',
      'message' => 'Dear Danil, 
Terima kasih! Pembayaran Anda sudah diterima. 
Silakan lihat detail pesanan Anda di bawah ini:

Rincian pembayaran :
Nama Produk : '.$nama_produk.' 
Qty : '.$qty.'
Total pembayaran : '.$total.' 
Waktu pembayaran : '.$date.'

Untuk informasi lebih lanjut, silakan hubungi tim support Tanaman Hias via e-mail tanamanhiasmustaqim@gmail.com atau telepon +6281351707329 .', 

      'countryCode' => '62', //optional
      ),
        CURLOPT_HTTPHEADER => array(
          'Authorization: zhQ0d7kA-hg6-nE+VDLw' //change TOKEN to your actual token
        ),
      ));

      $response = curl_exec($curl);

      curl_close($curl);
      echo $response;

?>