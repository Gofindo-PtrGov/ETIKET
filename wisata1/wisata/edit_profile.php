<?php
	include('koneksi1.php');

		$id_user = $_POST['id_user'];
		$no_hp = $_POST['no_hp'];
		$nama = $_POST['nama'];
		$password = $_POST['password'];




		if ($password == "") {

				$sql = "UPDATE user set id_user = '$id_user', nama = '$nama' where id_user = '$id_user' ";
				
			
				if(mysqli_query($con,$sql)){

					echo "Berhasil";
					
				}else{
					echo "Gagal";
					
				}
		

		}
		
		else {
				$sql = "UPDATE user set id_user = '$id_user', nama = '$nama', password = '$password' where id_user = '$id_user' ";
				
			
				if(mysqli_query($con,$sql)){

					echo "Berhasil";
					
				}else{
					echo "Gagal";
					
				}

		}
		
		
	
?>