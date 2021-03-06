package com.nosql.nosqlproject.dao;

import java.util.ArrayList;

import com.nosql.nosqlproject.entity.Line;
import com.nosql.nosqlproject.entity.Station;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface LineRepository extends Neo4jRepository<Line, String> {

    @Query("""
            match
                (l:Line)
            return l
            """)
    ArrayList<Line> get_all_line_names();

    @Query("""
            match
                (l:Line {name: {line_id}})
            return l limit 1
            """)
    Line find_lineId_line(String line_id);

    @Query("""
    match (s1:Station{name: {station_name1}}), (s2:Station{name: {station_name2}})
with s1.id as pid, s2.id as nid
match
    (n:Line {name: {line_name}}) where apoc.coll.indexOf(n.route, pid) > 0 and  apoc.coll.indexOf(n.route, pid) < apoc.coll.indexOf(n.route, nid)
with apoc.coll.indexOf(n.route, pid) as pindex, apoc.coll.indexOf(n.route, nid) as nindex,n
match (r:Run {line_id: n.name, direction:n.direction})
with  n.name as name, n.direction as direction, r.time as time, pindex, nindex
return name limit 1
    """)
    String find_lineId_stationName_path_lineName(String line_name, String station_name1, String station_name2);

    @Query("""
    match (s1:Station{name: {station_name1}}), (s2:Station{name: {station_name2}})
with s1.id as pid, s2.id as nid
match
    (n:Line {name: {line_name}}) where apoc.coll.indexOf(n.route, pid) > 0 and  apoc.coll.indexOf(n.route, pid) < apoc.coll.indexOf(n.route, nid)
with apoc.coll.indexOf(n.route, pid) as pindex, apoc.coll.indexOf(n.route, nid) as nindex,n
match (r:Run {line_id: n.name, direction:n.direction})
with  n.name as name, n.direction as direction, r.time as time, pindex, nindex
return direction limit 1
    """)
    String find_lineId_stationName_path_direction(String line_name, String station_name1, String station_name2);

    @Query("""
    match (s1:Station{name: {station_name1}}), (s2:Station{name: {station_name2}})
with s1.id as pid, s2.id as nid
match
    (n:Line {name: {line_name}}) where apoc.coll.indexOf(n.route, pid) > 0 and  apoc.coll.indexOf(n.route, pid) < apoc.coll.indexOf(n.route, nid)
with apoc.coll.indexOf(n.route, pid) as pindex, apoc.coll.indexOf(n.route, nid) as nindex,n
match (r:Run {line_id: n.name, direction:n.direction})
with  n.name as name, n.direction as direction, r.time as time, pindex, nindex
return time[pindex] limit 1
    """)
    String find_lineId_stationName_path_departtime(String line_name, String station_name1, String station_name2);

    @Query("""
    match (s1:Station{name: {station_name1}}), (s2:Station{name: {station_name2}})
with s1.id as pid, s2.id as nid
match
    (n:Line {name: {line_name}}) where apoc.coll.indexOf(n.route, pid) > 0 and  apoc.coll.indexOf(n.route, pid) < apoc.coll.indexOf(n.route, nid)
with apoc.coll.indexOf(n.route, pid) as pindex, apoc.coll.indexOf(n.route, nid) as nindex,n
match (r:Run {line_id: n.name, direction:n.direction})
with  n.name as name, n.direction as direction, r.time as time, pindex, nindex
return time[nindex] limit 1
    """)
    String find_lineId_stationName_path_desttime(String line_name, String station_name1, String station_name2);

    @Query("""
    match (s1:Station{name: {station_name1}}), (s2:Station{name: {station_name2}})
with s1.id as pid, s2.id as nid
match
    (n:Line {name: {line_name}}) where apoc.coll.indexOf(n.route, pid) > 0 and  apoc.coll.indexOf(n.route, pid) < apoc.coll.indexOf(n.route, nid)
with apoc.coll.indexOf(n.route, pid) as pindex, apoc.coll.indexOf(n.route, nid) as nindex,n
match (r:Run {line_id: n.name, direction:n.direction})
with  n.name as name, n.direction as direction, r.time as time, pindex, nindex
return pindex limit 1
    """)
    int find_lineId_stationName_path_departind(String line_name, String station_name1, String station_name2);

    @Query("""
    match (s1:Station{name: {station_name1}}), (s2:Station{name: {station_name2}})
with s1.id as pid, s2.id as nid
match
    (n:Line {name: {line_name}}) where apoc.coll.indexOf(n.route, pid) > 0 and  apoc.coll.indexOf(n.route, pid) < apoc.coll.indexOf(n.route, nid)
with apoc.coll.indexOf(n.route, pid) as pindex, apoc.coll.indexOf(n.route, nid) as nindex,n
match (r:Run {line_id: n.name, direction:n.direction})
with  n.name as name, n.direction as direction, r.time as time, pindex, nindex
return nindex limit 1
    """)
    int find_lineId_stationName_path_destind(String line_name, String station_name1, String station_name2);

    @Query("""
            match (s1:Station{name:{station1}}), (s2:Station{name:{station2}})
            with s1.id as departure, s2.id as destination
            match
                (l:Line) where apoc.coll.indexOf(l.route, departure) > 0 and apoc.coll.indexOf(l.route, departure) < apoc.coll.indexOf(l.route, destination)
            return l.name + l.direction
            """)
    ArrayList<String> find_directRoute(String station1, String station2);

    /*
     * @Query(""" match (l:Run) where {station_id} in r.route """) public
     * ArrayList<Demand8> find_station_time_line(String station_id, String
     * base_time, String last_time);
     *
     * public ArrayList<Demand8> find_station_time_nearest3(String station_id,
     * String base_time);
     */

    @Query("""
            match
                (l:Line) where {station_id} in l.route
            return l.name + l.direction
            """)
    ArrayList<String> get_lines_in_a_station(String station_id);

    @Query("""
        match (ss:Station{id:{station_id1}}), (se:Station{id:{station_id2}})
        unwind nodes(shortestpath((ss)-[*0..15]-> (se))) as res
        return res.id
    """)
    ArrayList<String> shortestpath_by_id_id(String station_id1, String station_id2);

    @Query("""
        match (ss:Station{id:{station_id1}}), (se:Station{id:{station_id2}})
        unwind nodes(shortestpath((ss)-[*0..15]-> (se))) as res
        return res.name
    """)
    ArrayList<String> shortestpath_by_id_name(String station_id1, String station_id2);

    @Query("""
        match (ss:Station{id:{station_id1}}), (se:Station{id:{station_id2}})
        unwind nodes(shortestpath((ss)-[*0..15]-> (se))) as res
        return res.english
    """)
    ArrayList<String> shortestpath_by_id_eng(String station_id1, String station_id2);

    @Query("""
        match (s:Station{name:{station_name}})
        return s.id
    """)
    ArrayList<String> get_all_station_ids_by_name(String station_name);

    @Query("""
            match (l:Line) where l.name =~ "^[0-9]+"
            return count(l)
            """)
    Integer count_type_l();

    @Query("""
            match (k:Line) where k.name starts with "K"
            return count(k)
            """)
    Integer count_type_k();

    @Query("""
            match (g:Line) where g.name starts with "G"
            return count(g)
            """)
    Integer count_type_g();

    @Query("""
            match (n:Line) where n.name starts with "N"
            return count(n)
            """)
    Integer count_type_n();

    @Query("""
            match
                (r:Run{line_id:{line_name}, direction: {line_direct}})
            return r.time[0] limit 1
            """)
    String get_start_time_in_one_run(String line_name, String line_direct);

    @Query("""
            match
                (r:Run{line_id:{line_name}, direction: {line_direct}})
            return r.time[-1] limit 1
            """)
    String get_end_time_in_one_run(String line_name, String line_direct);

    @Query("""
            match
                (n:Line {name:{line_id}})
            detach delete n
            match
                (r:Run{line_id:n.name})
            detach delete r
            match
                (a:Station) -[r]-> (b:Station) where n.name in r.lines and size(r.lines) = 1
            delete r
            match
                (a:Station) where not (a) -- ()
            delete a
            return n limit 1
            """)
    Line delete_line(@Param("lien_id") String line_id);

    @Query("""
            match
                (l:Line {name: {line_id}}) where {station_id} in l.route
            with apoc.coll.indexOf(l.route, {station_id}) as i
            set l.route[i] = {new_station_id}
            with l.route[i-1] as prev, l.route[i+1] as next
            case prev
                when not null then
                    match (p:Station{id: prev}) -[r]-> (n:Station{id: {station_id}})
                    set r.lines = [x IN r.lines WHERE x <> {line_id}];
                    merge p -[rh]-> (m:Station{id: {new_station_id}})
                    on match
                        set rh.lines= rh.lines + {line_id}
                    on create
                        set rk.lines = [{line_id}]
            end
            case next
                when not null then
                    match (p:Station{id: {station_id}}) -[r]-> (n: Station{id: next})
                    set r.lines = [x IN r.lines WHERE x <> {line_id}];
                    merge (m: Station{id: {new_station_id}}) -[rk]-> n
                    on match
                        set rk.lines = rk.lines + {line_id}
                    on create
                        set rk.lines = [{line_id}]
            end
            return l limit 1
            """)
    Line change_line(String line_id, String station_id, String new_station_id);
}
