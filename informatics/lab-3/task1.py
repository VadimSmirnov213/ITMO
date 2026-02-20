

import re

pattern = ";</"
for i in range(5):
    string = input()
    print(len(re.findall(pattern, string, flags=0)))
