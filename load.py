import json
import re
with open("./src/main/resources/database/lines.json", "r") as f:
    line_list = json.load(f)
    for line_dict in line_list:
        line_dict['onewayTime'] = int(line_dict['onewayTime'][1:3])
        try:
            line_dict['start_time'], line_dict['end_time'] = re.split(r'-', line_dict['runtime'])
        except:
            line_dict['start_time'], line_dict['end_time'] = re.split(r'\\', line_dict['runtime'])
        line_dict.pop( 'runtime' , None)
        try:
            line_dict['departure'], line_dict['destination'] = re.split(r'-', line_dict['route'])
            direction_set = False
        except:
            line_dict['departure'], line_dict['destination'], _ = re.split(r'-', line_dict['route'])
            line_dict['direction'] = 'circle'
            direction_set = True
        line_dict.pop('route', None)
        if not direction_set:
            if line_dict['directional']:
                line_dict['direction'] = 'up'
            else:
                line_dict['direction'] = 'down'
        line_dict.pop('directional')

with open("./src/main/resources/database/routes.json") as f:

