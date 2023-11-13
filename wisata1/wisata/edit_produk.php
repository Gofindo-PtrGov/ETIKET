<?php
	include('koneksi1.php');
	// date_default_timezone_set('Asia/Jakarta');
	// 	$date = date('Y-m-d');
	// 	$time = time('G:i');

		

	
		$id_produk = $_POST['id_produk'];
		$nama_produk = $_POST['nama_produk'];
		$deskripsi_produk = $_POST['deskripsi_produk'];
		$harga = $_POST['harga'];
		$rating = $_POST['rating'];
		$image1 = $_POST['image1'];


		$random1 = random_word(20);
		$images1 = $random1.".jpg";

		if ($image1 == "") {
			$sql1 = "UPDATE destinasi set `name` = '$nama_produk', `desc` = '$deskripsi_produk',`price`= '$harga', `rating` = '$rating' where `id_` = '$id_produk' ";
		}

		else {
			$sql1 = "UPDATE destinasi set `name` = '$nama_produk', `desc` = '$deskripsi_produk',`price` = '$harga', `rating` = '$rating', `image` = '$images1' where `id_` = '$id_produk' ";
		}

		
	
		
		$queri1 = mysqli_query($con,$sql1);
		
	
		if($queri1){

			
			if ($image1 !== "") {
			file_put_contents("gambar/produk/".$images1,base64_decode($image1));
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