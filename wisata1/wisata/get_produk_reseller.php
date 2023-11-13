<?php 

	error_reporting(0);
	
	$telepon = $_GET['id_user'];
	
	//Importing database
	require_once('koneksi1.php');

	if (isset($_GET['produk'])) {
		$sql = "SELECT count(*) FROM `produk` ";
	} else if (isset($_GET['user'])) {
		$sql = "SELECT count(*) FROM `user` ";
	}
	
	//Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai ID
	// $sql = "SELECT * FROM `user` WHERE id_user = $telepon ";
	
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