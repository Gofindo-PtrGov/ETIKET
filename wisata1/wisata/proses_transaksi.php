<?php
	include('koneksi1.php');
	// date_default_timezone_set('Asia/Jakarta');
	// 	$date = date('Y-m-d G:i:s');

		//Mendapatkan Nilai Variable
		$id_user = $_POST['id_user'];
		$id_destinasi = $_POST['id_destinasi'];
		$qty = $_POST['qty'];
		$total = $_POST['total'];
		$status_bayar = $_POST['status_bayar'];
		$metode_bayar = $_POST['metode_payment'];
		
		
		
		//Pembuatan Syntax SQL
		$sql = "INSERT INTO `tiket`( `id_user`, `id_destinasi`, `qty`, `metode_payment`, `sub_total`, `total_payment`, `status`) VALUES ('$id_user','$id_destinasi','$qty','$metode_bayar','$total','$total','$status_bayar')";
		
	
		if(mysqli_query($con,$sql)){



			
				
				echo "Pesan Terkirim";

				}else
				 {
					echo "Gagal";
		}
			
			
	

		
	
?>