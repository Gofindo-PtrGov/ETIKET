<?php
error_reporting(0);
	include('koneksi2.php');
	date_default_timezone_set('Asia/Jakarta');
		$date = date('Y-m-d');
		$time = time('G:i');

		

	
		$id_user = $_POST['id_user'];
		$id_tagihan = $_POST['id_tagihan'];
		$image = $_POST['image'];


		$random = random_word(20);

		// $server = "http://192.168.43.221/";
		// $path1 = "../image_cerita/".$random.".jpg";
		$image_cerita = $random.".jpg";

		// if ($image == "") {
		// 	$actualpath = "";
		// } 
		// else
		//  {
		// $actualpath = "http://192.168.43.221/komik_aina/$path1";
		// }
		
		
		//Pembuatan Syntax SQL
		$sql1 = "UPDATE ketenagakerjaan_transaksi set struk_pembayaran = '$image_cerita' where id_user = '$id_user' and id_tagihan = '$id_tagihan' ";
	
		
		$queri1 = mysql_query($sql1);
		
	
		if($queri1){
			if ($image !== "") {
			file_put_contents("gambar/".$image_cerita,base64_decode($image));
			}

			echo "Pesan Terkirim";
			
		}else{
			echo "Gagal";
			
		}



		function random_word($id = 20){
		$pool = '1234567890abcdefghijkmnpqrstuvwxyz';
		
		$word = '';
		for ($i = 0; $i < $id; $i++){
			$word .= substr($pool, mt_rand(0, strlen($pool) -1), 1);
		}
		return $word; 
	}
		
		
	
?>