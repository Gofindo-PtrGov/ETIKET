<?php
error_reporting(0);
	include('koneksi1.php');
	
		

	
		$nama_produk = $_POST['nama_produk'];
		$deskripsi_produk = $_POST['deskripsi_produk'];
		$harga = $_POST['harga'];
		$rating = $_POST['rating'];
		$image1 = $_POST['image1'];



		$random1 = random_word(20);
		$random2 = random_word(20);
		$random3 = random_word(20);
		$random4 = random_word(20);

		if ($image1 == "") {
			$images1 = "";
		}
		else {
			$images1 = $random1.".jpg";
		}

		if ($image2 == "") {
			$images2 = "";
		}
		else {
			$images2 = $random2.".jpg";
		}

		if ($image3 == "") {
			$images3 = "";
		}
		else {
			$images3 = $random3.".jpg";
		}

		if ($image4 == "") {
			$images4 = "";
		}
		else {
			$images4 = $random4.".jpg";
		}



		
		$sql1 = "INSERT INTO `destinasi`( `name`, `desc`, `price`, `rating`, `image`, `koordinat`) VALUES ('$nama_produk','$deskripsi_produk','$harga','$rating','$images1','')";
	
		
		$queri1 = mysqli_query($con,$sql1);
		
	
		if($queri1){

			echo "Pesan Terkirim";
			if ($image1 !== "") {
			file_put_contents("gambar/produk/".$images1,base64_decode($image1));
			}

			if ($image2 !== "") {
			file_put_contents("gambar/produk/".$images2,base64_decode($image2));
			}

			if ($image3 !== "") {
			file_put_contents("gambar/produk/".$images3,base64_decode($image3));
			}

			if ($image4 !== "") {
			file_put_contents("gambar/produk/".$images4,base64_decode($image4));
			}

			
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