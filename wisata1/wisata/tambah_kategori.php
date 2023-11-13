<?php
error_reporting(0);
	include('koneksi1.php');
	date_default_timezone_set('Asia/Jakarta');
		$date = date('Y-m-d');
		$time = time('G:i');

		//Mendapatkan Nilai Variable
		// $id_client = $_POST['id_client'];
		$nama_kategori = $_POST['nama_kategori'];
		// $koordinat = $_POST['koordinat'];
		// $id_image = $_POST['id_image'];
		// $status = "Aktif";
		
		
		
		//Pembuatan Syntax SQL
		$sql = "INSERT INTO `kategori`(`nama_kategori`) VALUES ('$nama_kategori')";
		
	
		if(mysqli_query($con,$sql)){

				echo "Berhasil";
			
			
		}else{
			echo "Gagal";
			
		}
		
		
	
?>