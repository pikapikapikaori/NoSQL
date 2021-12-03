import json
import re
with open("./src/main/resources/database/lines.json", "r") as f:
    global line_list, line_index
    line_list = json.load(f)
    line_index = {}
    i = 0
    for line_dict in line_list:
        line_dict['onewayTime'] = int(line_dict['onewayTime'][1:3])
        try:
            line_dict['start_time'], line_dict['end_time'] = re.split(
                r'-', line_dict['runtime'])
        except:
            line_dict['start_time'], line_dict['end_time'] = re.split(
                r'\\', line_dict['runtime'])
        line_dict.pop('runtime', None)
        try:
            line_dict['departure'], line_dict['destination'] = re.split(
                r'-', line_dict['route'])
            direction_set = False
        except:
            line_dict['departure'], line_dict['destination'], _ = re.split(
                r'-', line_dict['route'])
        line_dict.pop('route', None)
        if line_dict['directional']:
            line_dict['direction'] = 'up'
            line_dict.pop('directional')
            line_index[line_dict['name'] + '路上行'] = i
            line_index[line_dict['name'] + '路下行'] = i
        else:
            line_dict['direction'] = 'circle'
            line_dict.pop('directional')
            line_index[line_dict['name'] + '路'] = i
        i += 1

with open("./src/main/resources/database/routes.json") as f:
    route_list = json.load(f)
    for route_dict in route_list:
        index = line_index[route_dict['name']]
        if route_dict['name'].endswith('下行'):
            down_list = line_list[index].copy()
            down_list['direction'] = 'down'
            down_list.pop( 'route' )
            line_list.append(down_list)
            index = len(line_list) - 1
            line_index[route_dict['name']] = index
        line_list[index]['route'] = route_dict['alongStation']

with open("./src/main/resources/database/stations.json") as f:
    global station_list
    station_list = json.load(f)

with open("./src/main/resources/database/timetables.json") as f:
    global run_list
    run_list = []
    temp_dict = json.load(f)
    for outer_dict in temp_dict:
        for inner_list in outer_dict['timetable']:
            inner_dict = {}
            if outer_dict['name'].endswith('上行'):
                inner_dict['direction'] = 'up'
                inner_dict['line_id'] = outer_dict['name'][:-3]
            elif outer_dict['name'].endswith('下行'):
                inner_dict['direction'] = 'down'
                inner_dict['line_id'] = outer_dict['name'][:-3]
            else:
                inner_dict['direction'] = 'circle'
                inner_dict['name'] = outer_dict['name'][:-2]
            inner_dict['time'] = inner_list
            run_list.append(inner_dict)

with open("./src/main/resources/database/true_runs.json", "w") as f:
    json.dump(run_list, f)

with open("./src/main/resources/database/true_line.json") as f:
    json.dump(line_list, f)

with open("./src/main/resources/database/true_station.json") as f:
    json.dump(station_list, f)
