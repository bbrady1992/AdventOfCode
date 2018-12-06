import sys
import string
import re

upper_lower = zip(string.ascii_uppercase, string.ascii_lowercase)

def react_polymer(polymer_string):
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
    return polymer_string


unit_removed_length = dict()
with open(sys.argv[1]) as f:
    polymer_string = f.readline().rstrip()
    for pair in upper_lower:
        replacement_pattern = pair[0] + "|" + pair[1]
        temp_polymer_string = polymer_string
        for replacement in re.findall(replacement_pattern, temp_polymer_string):
            temp_polymer_string = temp_polymer_string.replace(replacement, "")
        reacted_length = len(react_polymer(temp_polymer_string))
        unit_removed_length[pair[0]] = reacted_length

min_length = 50000
for letter,length in unit_removed_length.items():
    if length < min_length:
        min_length = length
        min_letter = letter

print("Best results: Remove letter {} for a reacted polymer length of {}".format(min_letter, min_length))
