<?php 
require_once '../includes/db.php'; // mysql connnect

#$query="select id_local, nome from `johnt376_musikais`.`local`";
$query="SELECT 
    ml.id_musica, concat('music/',m.nome) as musica, ml.id_local, l.nome as localidade,  count(voto) as votos
FROM
    `musica_local`as ml
join `musica` as m on m.id_musica = ml.id_musica
join `local`as l on l.id_local = ml.id_local
WHERE
    ml.voto = 'Legal'
group by ml.id_musica , ml.id_local
order by count(ml.voto) desc limit 10";
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