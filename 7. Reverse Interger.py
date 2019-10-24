class Solution:
    def reverse(self, x):
        if (-2**31) <= x <= (2**31-1):
            s = [1, -1][x < 0]      #sign
            r = s * int(str(s*x)[::-1])
            if (-2**31) <= r <= (2**31-1): 
                return r
            else: 
                return 0
            
-----------------------------------------------------
class Solution:
    def reverse(self, x: int) -> int:
        if -2**31 <= x <= 2**31 - 1:
            sign = [-1, 1][x > 0]
            right = 0
            left = abs(x)
            while left:
                right = right*10 + left%10
                left = left//10
            reverse = sign*right
            if -2**31 <= reverse <= 2**31 - 1:
                return reverse
            else:
                return 0
