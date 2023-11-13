<?php
error_reporting(0);
	include('koneksi1.php');
	date_default_timezone_set('Asia/Jakarta');
		$date = date('Y-m-d');
		$time = time('G:i');

		

	
		
		$image1 = $_POST['image1'];



		$random1 = random_word(20);
		$random2 = random_word(20);
		$random3 = random_word(20);
		$random4 = random_word(20);

		if ($image1 == "") {
			$images1 = "";
		}
		else {
			$images1 = $random1.".jpg";
		}

		



		
		$sql1 = "INSERT INTO `slide`( `image`) VALUES ('$images1') ";
	
		
		$queri1 = mysqli_query($con,$sql1);
		
	
		if($queri1){

			echo "Pesan Terkirim";
			if ($image1 !== "") {
			file_put_contents("gambar/slide/".$images1,base64_decode($image1));
			}

			
		}else{
			echo "Gagal";
			
		}



		function random_word($id = 20){
		$pool = '1234567890abcdefghijkmnpqrstuvwxyz';
		
		$word = '';
		for ($i = 0; $i < $id; $i++){
			$word .= substr($pool, mt_rand(0, strlen($pool) -1), 1);
		}
		return $word; 
	}
		
		
	
?>