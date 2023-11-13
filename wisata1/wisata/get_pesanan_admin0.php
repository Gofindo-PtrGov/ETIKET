<?php 

	error_reporting(0);
	
	// $status_kirim = $_GET['status_kirim'];
	
	//Importing database
	require_once('koneksi1.php');
	
	//Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai ID
	$sql = "SELECT * FROM `transaksi` join user on transaksi.id_user = user.id_user join produk on produk.id_produk = transaksi.id_produk order by transaksi.id_transaksi DESC";
	
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