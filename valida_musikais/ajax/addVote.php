<?php 
require_once '../includes/db.php'; // The mysql database connection script

$music = $_GET['music'];
echo $music;
$image = $_GET['image'];
$vote = $_GET['vote'];
$created = time();

$query="INSERT INTO `musikais`.`musica_local`(id_musica,id_local,voto,data) VALUES ('$music', '$image', '$vote', '$created')";
//$query="INSERT INTO `musikais`.`musica_local`(id_musica,id_local,data) VALUES ('$music', '$image', '$created')";
$result = $mysqli->query($query) or die($mysqli->error.__LINE__);

$result = $mysqli->affected_rows;

echo $json_response = json_encode($result);

?>