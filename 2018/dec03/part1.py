import sys
from collections import defaultdict

def parse_line(line):
    words = line.split(" ")
    x = int(words[2].split(',')[0])
    y = int(words[2].split(',')[1][:-1])
    w = int(words[3].split('x')[0])
    h = int(words[3].split('x')[1])
    return x, y, w, h

if len(sys.argv) != 2:
    exit("Usage: `python part1.py <input_file>`")

areaCounter = defaultdict(int)
with open(sys.argv[1]) as f:
    for line in f:
        x, y, w, h = parse_line(line)
        for hor in range(w):
            for ver in range(h):
                areaCounter[(x + hor, y + ver)] += 1

overlapped = 0
for (vx, vy), v in areaCounter.items():
    if v >= 2:
        overlapped += 1

print(overlapped)
