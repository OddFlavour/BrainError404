# def get_max_index(inp_list):
#     max_index = 0
#     for i in range(0, len(inp_list)):
#         if inp_list[max_index] < inp_list[i]:
#             max_index = i
#
#     return max_index
#
#
# def get_swaps(inp_list):
#     total_swaps = 0
#     sorted_list = []
#     for e in inp_list:
#         unsorted_list = sorted_list + [e]
#         (sorted_list, swaps) = adj_sort(unsorted_list)
#         total_swaps += swaps
#
#     return total_swaps
#
#
# def adj_sort(inp_list):
#     count = 0
#
#     i = len(inp_list) - 1
#     while i > 0 and inp_list[i-1] > inp_list[i]:
#         (inp_list[i-1], inp_list[i]) = (inp_list[i], inp_list[i-1])
#         i -= 1
#         count += 1
#
#     return inp_list, count
#
#
# def e_adj_sort(inp_list, new_val):
#     count = 0
#
#     i = len(inp_list)
#     while i > 0 and inp_list[i-1] > new_val:
#         i -= 1
#         count += 1
#
#     return count


f = open("B-large-practice.in", "r")

T = int(f.readline())

for init in range(0, T):
    N = int(f.readline())
    inputs = f.readline().split(" ")
    inputs = [int(x) for x in inputs]

    # max_pointer = get_max_index(inputs)
    # left = inputs[:max_pointer]
    # right = inputs[max_pointer+1:]
    # right.reverse()
    #
    # ans = 0
    #
    # # Process left
    # for i in range(len(left) - 1, -1, -1):
    #     # Get the swap count for not transferring
    #     partition = left[:i]
    #     partition.sort()
    #
    #     nt_count = e_adj_sort(partition, left[i])
    #     # Get the swap count for transferring
    #     partition = right[:]
    #     partition.sort()
    #
    #     t_count = (len(left) - i) + e_adj_sort(partition, left[i])
    #     # If transferring is better, transfer it
    #     if t_count < nt_count:
    #         ans += len(left) - i
    #         right += [left[i]]
    #         left.pop(i)
    #
    # # Process right
    # for i in range(len(right) - 1, -1, -1):
    #     # Get the swap count for not transferring
    #     partition = right[:i]
    #     partition.sort()
    #
    #     nt_count = e_adj_sort(partition, right[i])
    #     # Get the swap count for transferring
    #     partition = left[:]
    #     partition.sort()
    #
    #     t_count = (len(right) - i) + e_adj_sort(partition, right[i])
    #     # If transferring is better, transfer it
    #     if t_count < nt_count:
    #         ans += len(right) - i
    #         left += [right[i]]
    #         right.pop(i)
    #
    # ans += get_swaps(left) + get_swaps(right)
    ans = 0

    while len(inputs) > 0:
        min_pointer = 0
        for i in range(0, len(inputs)):
            if inputs[i] < inputs[min_pointer]:
                min_pointer = i

        left_swaps = min_pointer
        right_swaps = (len(inputs) - 1) - min_pointer

        if left_swaps < right_swaps:
            ans += left_swaps
        else:
            ans += right_swaps

        inputs.pop(min_pointer)

    print("Case #"+str(init+1)+":", ans)
