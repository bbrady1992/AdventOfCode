import sys

if len(sys.argv) != 2:
    print("Usage: `python part1.py <input_file>`")
    sys.exit(1)

resultingFrequency = 0
with open(sys.argv[1]) as f:
    for line in f:
        value = int(line[1:])
        if line[0] == "+":
            resultingFrequency += value
        else:
            resultingFrequency -= value



print(resultingFrequency)
