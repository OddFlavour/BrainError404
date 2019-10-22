f = open("A-large-practice.in", "r")

T = int(f.readline())

for init in range(1, T+1):
    inputs = f.readline().split(" ")
    inputs = [int(x) for x in inputs]

    N = inputs[0]

    transistors = [((trans * inputs[1] + inputs[2]) % inputs[3] + inputs[4]) for trans in range(0, N)]

    partialSums = [0]
    for i in range(0, N):
        partialSums.append(partialSums[i] + transistors[i])

    best = 0
    for lBound in range(0, N+1):
        (left, right) = (lBound, N)

        while left < right:
            pointer = (left + right) // 2
            (midSum, rightSum) = (partialSums[pointer] - partialSums[lBound], partialSums[N] - partialSums[pointer])

            best = max(best, partialSums[N] - max(partialSums[lBound], max(midSum, rightSum)))

            if midSum > rightSum:
                if right == pointer:
                    break

                right = pointer
            elif midSum < rightSum:
                if left == pointer:
                    break

                left = pointer
            else:
                break

    answer = best / partialSums[N]

    print("Case #"+str(init)+":", answer)
