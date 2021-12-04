match (r:Run), (l:Line) where r.line_id = l.name and r.direction = l.direction
merge (r) -[:BelongsTo]-> (l)

