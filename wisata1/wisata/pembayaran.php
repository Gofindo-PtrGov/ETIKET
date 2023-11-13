<?php
error_reporting(0);
	include('koneksi2.php');
	date_default_timezone_set('Asia/Jakarta');
		$date = date('Y-m-d');
		$time = time('G:i');

		//Mendapatkan Nilai Variable
		// $id_client = $_POST['id_client'];
		$id_user = $_POST['id_user'];
		$id_tagihan = $_POST['id_tagihan'];
		$id_denda = $_POST['id_denda'];
		// $koordinat = $_POST['koordinat'];
		// $id_image = $_POST['id_image'];
		// $status = "Aktif";
		
		
		
		//Pembuatan Syntax SQL
		$sql = "INSERT INTO `ketenagakerjaan_transaksi`(`id_user`, `id_tagihan`, `id_denda`) VALUES ('$id_user','$id_tagihan','$id_denda')";
		
	
		if(mysql_query($sql)){

			$sql2 = mysql_query("UPDATE tagihan set status = 'Pending' where id_tagihan = '$id_tagihan' ");

			if ($sql2) {
				echo "Berhasil";
			} else{
				echo "Gagal";
			}
			
			
		}else{
			echo "Gagal";
			
		}
		
		
	
?>