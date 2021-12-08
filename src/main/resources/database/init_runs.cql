call apoc.load.json("file:///true_runs.json") yield value as vr
create
(r:Run
{
line_id: vr.line_id,
direction: vr.direction,
time: vr.time
}
)

