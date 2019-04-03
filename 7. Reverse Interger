class Solution:
    def reverse(self, x):
        if (-2**31) <= x <= (2**31-1):
            s = [1, -1][x < 0]      #sign
            r = int(s * int(str(s*x)[::-1]))
            if (-2**31) <= r <= (2**31-1): return r
            else: return 0
