def print_lake(grid):
    for row in range(len(grid) - 1, -1, -1):
        print(grid[row])

    print("===")


def traverse(focus, grid):
    # print(focus)
    (f_x, f_y) = focus
    grid[f_y][f_x] = 1
    if f_y != H - 1:
        # Perform DFS until a path to an end position is found, searching in LEFT->UP->RIGHT
        # Obtain neighbours
        neighbours = []
        # Find the neighbours
        # Left
        if f_x > 0 and grid[f_y][f_x - 1] == 0:
            neighbours.append((f_x - 1 if f_x > 0 else 0, f_y))
        # Up
        if f_y < H - 1 and grid[f_y + 1][f_x] == 0:
            neighbours.append((f_x, f_y + 1 if f_y < H - 1 else H - 1))
        # Right
        if f_x < W - 1 and grid[f_y][f_x + 1] == 0:
            neighbours.append((f_x + 1 if f_x < W - 1 else W - 1, f_y))

        result = 0
        for neighbour in neighbours:
            result = traverse(neighbour, grid)
            if result == 1:
                break
        #
        # if result == 0:
        #     for (n_x, n_y) in neighbours:
        #         grid[n_y][n_x] = 0
    else:
        result = 1

    return result


f = open("C-small-practice.in", "r")

T = int(f.readline())

for init in range(0, T):
    (W, H, B) = (int(x) for x in f.readline().split(" "))

    grid = [[0 for j in range(0, W)] for i in range(0, H)]

    for buildings in range(0, B):
        (X0, Y0, X1, Y1) = (int(x) for x in f.readline().split(" "))

        for x in range(X0, X1+1):
            for y in range(Y0, Y1+1):
                grid[y][x] = 2

    for pos in range(0, len(grid[0])):
        if grid[0][pos] != 2:
            grid[0][pos] = 1

    ans = 0
    # From each possible start position
    for pos in range(0, len(grid[0])):
        if grid[0][pos] != 2:
            ans += traverse((pos, 0), grid)

    print("Case #"+str(init+1)+":", ans)
