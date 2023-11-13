<?php
	$server 	= "localhost";
	$username 	= "root";
	$password	= "";
	$database 	= "wisata_govin";
	
	$con = mysqli_connect($server, $username, $password, $database);
	if (mysqli_connect_errno()) {
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
?>