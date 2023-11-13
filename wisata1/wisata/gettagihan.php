<?php 

	error_reporting(0);
	
	$telepon = $_GET['id_user'];
	
	//Importing database
	require_once('koneksi1.php');
	
	//Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai ID
	$sql = "SELECT * FROM `tagihan` join user on tagihan.id_user = user.id_user join jenis_peserta on jenis_peserta.id_jenis = user.id_jenis_peserta WHERE tagihan.id_user = $telepon and (status = 'Belum Lunas' or status = 'Pending' ) order by `date` ASC ";
	
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