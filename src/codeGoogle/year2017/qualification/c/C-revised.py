def bin_insert(_list, _value):
    left = 0
    right = len(_list) - 1
    while left < right:
        mid = (left + right) // 2
        if _list[mid] < _value:
            left = mid + 1
        else:
            right = mid

    _list.insert(left, _value)


file = open("C-small-practice-1.in", "r")

T = int(file.readline())

for case in range(0, T):
    (N, K) = [int(x) for x in file.readline().split(" ")]

    counter = 0
    ranges = [N]

    for person in range(0, K - 1):
        focus = ranges[counter] - 1
        counter += 1

        new_ranges = [focus // 2, focus // 2]
        if focus % 2 == 1:
            new_ranges[0] += 1

        for new_range in new_ranges:
            # ranges.append(new_range)
            bin_insert(ranges, new_range)

    focus = ranges[counter]
    max_d = focus // 2
    min_d = focus // 2 if focus % 2 == 1 else focus // 2 - 1

    print("Case #{}: {} {}".format(case + 1, max_d, min_d))
