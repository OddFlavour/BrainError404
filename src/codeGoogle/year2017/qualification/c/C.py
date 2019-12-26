def get_range(_interval):
    return abs(_interval[0] - _interval[1])


def insert(into, _with):
    _inserted = False
    _temp = get_range(_with)

    for _i in reversed(range(len(into))):
        _a = get_range(into[_i])
        if _temp < _a:
            into.insert(_i + 1, _with)
            return

    if not _inserted:
        into.insert(0, _with)


file = open("C-small-practice-2.in", "r")

T = int(file.readline())

for case in range(0, T):
    (N, K) = [int(x) for x in file.readline().split(" ")]

    stack = [(0, N + 1)]

    for i in range(0, K - 1):
        interval = stack.pop(0)
        left = interval[0]
        right = interval[1]
        mid = (left + right) // 2

        closerToLeft = abs(mid - left) >= abs(mid - right)

        plate = [(left, mid), (mid, right)]
        if not closerToLeft:
            plate = [(mid, right), (left, mid)]

        # need to insert the new intervals into correct places
        for interval in plate:
            insert(stack, interval)

    # 'mid' is such 'S' that min(L_S, R_S) is maximal
    interval = stack.pop(0)
    left = interval[0]
    right = interval[1]
    mid = (left + right) // 2

    max_d = max(abs(mid - left),
                abs(mid - right)) - 1
    min_d = min(abs(mid - left),
                abs(mid - right)) - 1

    print("Case #{}: {} {}".format(case + 1, max_d, min_d))
