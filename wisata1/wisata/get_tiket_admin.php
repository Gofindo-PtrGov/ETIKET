<?php 

	error_reporting(0);
	
	
	//Importing database
	require_once('koneksi1.php');
	
	//Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai ID
	$sql = "SELECT * FROM `tiket` join user on tiket.id_user = user.id_user join destinasi on destinasi.id_ = tiket.id_destinasi order by tiket.id_tiket DESC";
	
	//Mendapatkan Hasil 
	$r = mysqli_query($con,$sql);
	
	//Memasukkan Hasil Kedalam Array
	$result = array();
	while ($row = mysqli_fetch_array($r)){
	// array_push($result,array(
			$result[] = $row;
}
	//Menampilkan dalam format JSON
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>