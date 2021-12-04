call apoc.load.json("file:///lines.json") yield value as vl
create
(l:Line
{name: vl.name,
direction: vl.direction,
route: vl.route,
start_time: vl.start_time,
end_time: vl.end_time,
departure: vl.departure,
destination: vl.destination,
type: vl.type,
onewayTime: vl.onewayTime,
kilometer: vl.kilometer,
interval: vl.interval})

