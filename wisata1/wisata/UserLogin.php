<?php

 if($_SERVER['REQUEST_METHOD']=='POST'){

 include 'koneksi1.php';
 
 
 $telepon = $_POST['id_user'];
 $password = $_POST['password'];
 
 $Sql_Query = "SELECT * from `user` where email = '$telepon' and password = '$password' ";
 
 $check = mysqli_fetch_array(mysqli_query($con,$Sql_Query));
 
 if(isset($check)){
 
 echo "Login Berhasil";
 }
 else{
 echo "Invalid Username or Password Please Try Again";
 }
 
 }else{
 echo "Silakan Cek Lagi";
 }
mysqli_close($con);

?>