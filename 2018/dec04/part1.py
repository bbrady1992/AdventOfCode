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

max = 0
for ID, sleeptime in guardTotals.items():
    if sleeptime > max:
        max = sleeptime
        sleepiestGuard = ID

print(f"Guard that slept the most: {sleepiestGuard}")
print(guardDict[sleepiestGuard])

count = 0
for minute, minute_count in guardDict[sleepiestGuard].items():
    print("Minute: {}  -  Count: {}".format(minute, minute_count))
    if minute_count > count:
        count = minute_count
        most_slept_minute = minute

print("Most slept minute was {}, slept through {} times".format(most_slept_minute, count))

print(f"Answer is {int(sleepiestGuard) * int(most_slept_minute)}")
