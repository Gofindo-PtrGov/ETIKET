<?php 

	error_reporting(0);
	
	$telepon = $_GET['id_user'];
	
	//Importing database
	require_once('koneksi1.php');
	
	//Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai ID
	$sql = "SELECT SUM(jumlah_tagihan) FROM `tagihan` join user ON user.id_user = tagihan.id_user join jenis_peserta ON jenis_peserta.id_jenis = user.id_jenis_peserta where user.id_user = $telepon and tagihan.status = 'Lunas';";
	
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