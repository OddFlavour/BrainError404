f = open("A-large-practice.in", "r")

T = int(f.readline())

for init in range(0, T):
    inputs = f.readline().split(" ")

    N = int(inputs[0])
    disk_size = int(inputs[1])

    inputs = f.readline().split(" ")
    inputs = [int(x) for x in inputs]

    inputs.sort()

    disks = 0

    while len(inputs) > 0:
        cap = disk_size - inputs.pop(0)
        (left, right) = (0, len(inputs) - 1)

        best_pointer = None
        # Binary search for closest value to 'cap'
        while left <= right:
            pointer = (left + right) // 2
            if inputs[pointer] <= cap:
                if best_pointer is None or inputs[best_pointer] < inputs[pointer]:
                    best_pointer = pointer

            if inputs[pointer] < cap:
                left = pointer + 1
            elif inputs[pointer] > cap:
                right = pointer - 1
            else:
                break

        if best_pointer is not None and len(inputs) > 0:
            inputs.pop(best_pointer)

        disks += 1

    print("Case #"+str(init+1)+":", disks)
