<?php
  $host = 'localhost';
  $user = 'root';      
  $password = '';      
  $database = 'reseller';  
 
  $konek_db = mysql_connect($host, $user, $password) or die('Tidak terhubung') ;
  $find_db = mysql_select_db($database) ;

?> 