call apoc.load.json("file:///true_stations.json") yield value as vs
create
(s:Station
{english: vs.english,
name: vs.name,
id: vs.id})
