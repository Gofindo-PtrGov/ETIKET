<?php
	include('koneksi1.php');

		$id_user = $_POST['id_user'];
		$email = $_POST['email'];
		$nama = $_POST['nama'];
		$password = $_POST['password'];
		
		
		
		//Pembuatan Syntax SQL
		$sql = "INSERT INTO `user`(`id_user`,`nama`,`password`,`email`) VALUES ('$id_user','$nama','$password','$email')";
		
	
		if(mysqli_query($con,$sql)){

			echo "Berhasil";
			
		}else{
			echo "Gagal";
			
		}
		
		
	
?>