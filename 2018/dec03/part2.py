import sys
from collections import defaultdict

def parse_line(line):
    words = line.split(" ")
    ID = words[0]
    x = int(words[2].split(',')[0])
    y = int(words[2].split(',')[1][:-1])
    w = int(words[3].split('x')[0])
    h = int(words[3].split('x')[1])
    return ID, x, y, w, h

if len(sys.argv) != 2:
    exit("Usage: `python part1.py <input_file>`")

areaCounter = defaultdict(int)
with open(sys.argv[1]) as f:
    for line in f:
        ID, x, y, w, h = parse_line(line)
        for hor in range(w):
            for ver in range(h):
                areaCounter[(x + hor, y + ver)] += 1

    f.seek(0)
    for line in f:
        ID, x, y, w, h = parse_line(line)
        unique = True
        for hor in range(w):
            for ver in range(h):
                if areaCounter[(x + hor, y + ver)] > 1:
                    unique = False

        if unique:
            print(ID)
