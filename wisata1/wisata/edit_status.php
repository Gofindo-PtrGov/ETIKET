<?php
	include('koneksi1.php');

		$id_transaksi = $_POST['id_transaksi'];
		$status_kirim = $_POST['status_kirim'];
		
		
		
		//Pembuatan Syntax SQL
		$sql = "UPDATE transaksi set status_kirim = '$status_kirim' where id_transaksi = '$id_transaksi' ";
		
	
		if(mysqli_query($con,$sql)){


				sendNotif();
				echo "Berhasil";
		
			
			
		}else{
			echo "Gagal";
			
		}

		function sendNotif(){


		$id_transaksi = $_POST['id_transaksi'];
		$status_kirim = $_POST['status_kirim'];
		$nama = $_POST['nama'];
		$no_hp = $_POST['no_hp'];

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
			'target' => $no_hp,
		 'message' => 'Dear '.$nama.', 
Pesanan Anda sudah '.$status_kirim.'
Silakan lihat detail pesanan Anda di aplikasi Tanaman Hias.

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
					
		
		}
		
		
	
?>