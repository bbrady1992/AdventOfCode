import sys

if len(sys.argv) != 2:
    print("Usage: `python part2.py <input_file>`")
    sys.exit(1)

resultingFrequency = 0
frequencies = {0}
repeatFrequencyFound = False

with open(sys.argv[1]) as input_file:
    while not repeatFrequencyFound:
        for line in input_file:
            value = int(line[1:])
            if line[0] == "+":
                resultingFrequency += value
            else:
                resultingFrequency -= value

            if resultingFrequency in frequencies:
                print(resultingFrequency)
                repeatFrequencyFound = True
                break
            else:
                frequencies.add(resultingFrequency)
        input_file.seek(0)
