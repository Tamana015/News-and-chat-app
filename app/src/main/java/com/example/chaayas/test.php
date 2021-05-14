
<?php
$user="localhost";
$mysql_user="root";
$password="";
$dbname="login";
$con=mysql_connect($host,$user,$password);
if($con) {
    echo ("connection successfull");
} else {
    echo ("connection not successfull");
}
if($_SERVER['REQUEST_METHOD']=='POST'){
$username=$_POST['username'];
$email=$_POST['email'];
$password1=$_POST['password'];
$password2=$_POST['confirm password'];
$query="INSERT INTO 'login' ('username','email','password,'confirm password') VALUES ('$username','$email','$password1','$password2')";
if(mysql_query($con,$query)){
echo("register successfully");
}
else
{
echo("error in registeration");
}

}
else
{
echo("error in request method");
}
?>