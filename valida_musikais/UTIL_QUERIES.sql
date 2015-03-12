-- RANK DOS MAIS VOTADOS
SELECT 
    id_musica, id_local, count(voto)
FROM
    `musica_local`
WHERE
    voto = 'Legal'
group by id_musica , id_local
order by count(voto) desc;