import sys
from collections import defaultdict

def parse_line(line):
    date_and_text = line.split(']')
    date = date_and_text[0].split()
    y,m,d = date[0].split('-')
    y,m,d = int(y[1:]), int(m), int(d)
    hour,minute = date[1].split(':')
    hour,minute = int(hour), int(minute)

    text = date_and_text[1][1:]

    return [y,m,d,hour,minute], text


events = []
with open(sys.argv[1]) as f:
    for line in f:
        datetime, text = parse_line(line)
        events.append((datetime, text))
    events.sort()

guardDict = dict()
guardTotals = dict()
for event in events:
    datetime = event[0]
    text = event[1]
    if text.startswith("Guard"):
        guardID = text.split("#")[1].split()[0]
        if guardID not in guardTotals.keys():
            guardDict[guardID] = defaultdict(int)
            guardTotals[guardID] = 0
    elif text.startswith("falls"):
        sleep_start = datetime[4]
    elif event[1].startswith("wakes"):
        sleep_stop = datetime[4]
        for i in range(sleep_start, sleep_stop):
            guardDict[guardID][i] += 1
            guardTotals[guardID] += 1

count = 0
for guard in guardDict.keys():
    for minute, minute_count in guardDict[guard].items():
        if minute_count > count:
            count = minute_count
            most_slept_minute = minute
            consistentGuardId = guard

print("Guard {} was the most consistent. He slept during minute {} a total of {} times".format(consistentGuardId, most_slept_minute, count))
print(f"The solution is {int(consistentGuardId) * int(most_slept_minute)}")
