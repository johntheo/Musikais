<?php 
require_once '../includes/db.php'; // mysql connnect

$query="select id_local, nome from `johnt376_musikais`.`local`";
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