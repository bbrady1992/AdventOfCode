import sys
import string
import re

upper_lower = zip(string.ascii_uppercase, string.ascii_lowercase)

with open(sys.argv[1]) as f:
    polymer_string = f.readline().rstrip()
    replacement_made = True
    old_len = len(polymer_string)
    while replacement_made:
        for pair in upper_lower:
            pair = str(pair[0] + pair[1])
            replacement_pattern = pair + "|" + pair[::-1]
            for replacement in re.findall(pair + "|" + pair[::-1], polymer_string):
                polymer_string = polymer_string.replace(replacement, "")
        new_len = len(polymer_string)

        if old_len == new_len:
            replacement_made = False
        old_len = new_len


print(len(polymer_string))
