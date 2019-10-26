##1. Two Sum
class Solution:
    def twoSum(self, nums, target):
        if len(nums) <= 1:
            return False
        dict = {}
        for i in range(len(nums)):
            if nums[i] in dict:
                return(dict[nums[i]],i)
            else:
                dict[target-nums[i]] = i
                
*********************************************************
##7. Reverse Integer
class Solution:
    def reverse(self, x):
        if (-2**31) <= x <= (2**31-1):
            s = [1, -1][x < 0]      #sign
            r = s * int(str(s*x)[::-1])
            if (-2**31) <= r <= (2**31-1): 
                return r
            else: 
                return 0
            
#########################################
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

*********************************************************
##9. Palindrome Number
class Solution:
    def isPalindrome(self, x: int) -> bool:
        if x >= 0:
            right = 0
            left = x
            while left:
                right = right*10 + left%10
                left = left//10
            if right == x:
                return True
        else:
            return False

*********************************************************
##13. Roman to Integer
class Solution:
    def romanToInt(self, s: str) -> int:
        roman = {'I': 1, 'V':5, 'X':10, 'L':50, 'C':100, 'D':500, 'M':1000}
        
        sums = 0
        minus = 0
        
        for i in range(len(s)-1):
            if roman[s[i]] < roman [s[i+1]]:
                minus += roman[s[i]]
            sums += roman[s[i]]
        
        result = sums - 2*minus + roman[s[-1]]
        
        return result

*********************************************************
##14. Longest Common Prefix
class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        
        n = len(strs)
        
        min_str = min(strs, key = len, default = "")    ##which.min
        min_len = len(min_str)

        i = 0
        prefix = ""
        while i < min_len:
            if all(items[i] == min_str[i] for items in strs) == False:
                break
            prefix = prefix + min_str[i]
            i += 1
                    
        return prefix
    
*********************************************************
##20. Valid Parentheses
class Solution:
    def __init__(self):
        self.stack = []
        self.dic = {")":"(", "}":"{" , "]":"["}
        self.checker = [")","}","]"]
    def isValid(self, S):
        if len(S) % 2 != 0:
            return False
        for i in range(len(S)):
            if S[i] is '(' or S[i] is '{' or S[i] is '[':
                self.stack.append(S[i])
            elif S[i] in self.checker:
                if self.dic[S[i]] not in self.stack:
                    return False
                if self.stack[-1] != self.dic[S[i]]:
                    return False
                self.stack.pop(-1)
        return len(self.stack) == 0
