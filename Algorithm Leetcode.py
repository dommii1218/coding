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

*********************************************************
##21. Merge Two Sorted Lists
# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def mergeTwoLists(self, l1: ListNode, l2: ListNode) -> ListNode:
        if l1 is None:
            return l2
        if l2 is None: 
            return l1
        if l1 is None and l2 is None:
            return None
        
        if l1.val <= l2.val:
            dummy = ListNode(l1.val)
            dummy.next = self.mergeTwoLists(l1.next, l2)
        else:
            dummy = ListNode(l2.val)
            dummy.next = self.mergeTwoLists(l1, l2.next)
        
        return dummy
    
*********************************************************
##26. Remove Duplicates from Sorted Array
class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        
        j = 0
        
        for i in range(len(nums)):
            if nums[i] > nums[j]:
                j += 1                
                nums[j] = nums[i]
                
        return j+1
    
*********************************************************
##27. Remove Element
class Solution:
    def removeElement(self, nums: List[int], val: int) -> int:
        
        j = len(nums)-1
        
        for i in reversed(range(len(nums))):
            if nums[i] == val:
                
                temp = nums[i]
                nums[i] = nums[j]
                nums[j] = temp
                
                j -= 1
                
        return j+1
    
*********************************************************
##28. Implement strStr()
##bruce force:
class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        
        if needle == "":
            return 0

        j = 0
        i = 0
        
        while i < len(haystack):
            
            if needle[j] == haystack[i]:
                j += 1
                if j == len(needle):
                    return i - j + 1
                    break
            else: 
                i = i - j
                j = 0
                    
            i += 1
        
        return -1

#########################################
##KMF (Knuth Morris Pratt) Algorithm
class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        
        if needle == "":
            return 0
        ##KMP Algorithm
        
        ##needle dictionary : O(m)
        m = len(needle)
        dic = [0]*m
        j = 0    #length of postfix or suffix
        i = 1    #dic[0] == 0, start from index 1
        
        while i < m:
            if needle[i] == needle[j]:
                j += 1
                dic[i] = j
                i += 1
            else:
                if j != 0:
                    j = dic[j-1]
                else:
                    dic[i] = 0
                    i += 1
                    
        ##pattern matching: O(n)
        n = len(haystack)
        i = 0
        j = 0
        while i < n:
            if haystack[i] == needle[j]:
                i += 1
                j += 1
                if j == m:
                    return i-j
                    break
            else:
                if j != 0:
                    j = dic[j-1]
                else:
                    i += 1
            
        return -1
    
*********************************************************
##35. Search Insert Position
class Solution:
    def searchInsert(self, nums: List[int], target: int) -> int:
        left = 0
        right = len(nums) - 1
        
        while right - left > 1:
            mid = (left + right) // 2
            
            if target == nums[mid]:
                return mid
            elif target < nums[mid]:
                right = mid - 1
            else:
                left = mid + 1
        
        if target <= nums[left]:
            return left
        elif target <= nums[right]:
            return right
        else:
            return right + 1 
        
*********************************************************
##38. Count and Say
class Solution:
    def countAndSay(self, n: int) -> str:
        seq = "1"
        for i in range(n-1):
            seq = self.generate(seq)
        return seq
    
    def generate(self, seq: str) -> str:
        i = 0
        count = 1
        r = ""
        while i < (len(seq)-1):
            if seq[i+1] == seq[i]:
                count += 1
            else:
                r += str(count) + seq[i]
                count = 1
            i += 1
        return r + str(count) + seq[-1]
    
*********************************************************
##53. Maximum Subarray
'''
Kadane’s Algorithm:

Let OPT(j) = max{OPT(j-1)+A[j], A[j]}, OPT(j) = max sum of subarray ending at j.
The maximum sum is max{OPT(j)} for all 1 <= j <= n.
Boundary condition: OPT(0) = A[0].
There is n+1 subproblems, each requiring O(1) time in a bottom-up computation. Hence T(n) = O(n).
'''
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        OPT = [None]*len(nums)
        OPT[0] = nums[0]
        for j in range(1,len(nums)):
            OPT[j] = max(OPT[j-1]+nums[j], nums[j])     
        return max(OPT) 
