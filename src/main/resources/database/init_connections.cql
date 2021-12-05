match (n:Line)
unwind n.route as st
with apoc.coll.indexOf(n.route, st) as i, n, st
with n.route[i+1] as stn, n, st
match (sn:Station{id:stn}) where stn is not null and stn <> st
match (sp:Station{id:st})
merge (sp) -[r:Connection]-> (sn)
    on create
        set r.lines = [n.name]
    on match
        set r.lines = r.lines + n.name


