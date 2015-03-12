<?php 
require_once '../includes/db.php'; // mysql connnect

$query="select id_musica, concat('music/',nome) as nome from `johnt376_musikais`.`musica`"; //workaround problema com bind do angularjs
$result = $mysqli->query($query) or die($mysqli->error.__LINE__);

$arr = array();
if($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $arr[] = $row;	
    }
}

# JSON-encode the response
echo $json_response = json_encode($arr);
?>