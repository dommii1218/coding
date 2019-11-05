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
    def isValid(self, s: str) -> bool:
        stack = []
        dic = {")":"(", "]":"[","}":"{"}
        left = ["(", "[", "{"]
        
        if len(s) % 2 != 0:
            return False
        else:
            for i in range(len(s)):
                if s[i] in left:
                    stack.append(s[i])
                else:
                    if dic[s[i]] not in stack or dic[s[i]] != stack[-1]:
                        return False
                    else:
                        stack.pop(-1)
            
            return len(stack) == 0

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

*********************************************************
##66. Plus One
class Solution:
    def plusOne(self, digits: List[int]) -> List[int]:
        i = len(digits) - 1
        while i >= 0:
            if digits[i] == 9:
                digits[i] = 0
            else:
                digits[i] += 1
                break
                return digits
            i -= 1
        
        if digits[0] == 0:
            digits.insert(0, 1)
        
        return digits
    
*********************************************************
##67. Add Binary
class Solution:
    def addBinary(self, a: str, b: str) -> str:
        n_a = len(a)
        n_b = len(b)
        dif = abs(n_a - n_b)
        if n_a >= n_b:
            b = ('0'*dif) + b
        else:
            a = ('0'*dif) + a
            
        up = 0
        i = len(a) - 1
        r = ''

        while i >= 0:
            sum = int(a[i]) + int(b[i]) + up
            if sum <= 1:
                r = str(sum) + r
                up = 0
            else:
                r = str(sum-2) + r
                up = 1  
            i -= 1
        
        if up == 1:
            return '1' + r
        else:
            return r
        
*********************************************************
##69. Sqrt(x)
class Solution:
    def mySqrt(self, x: int) -> int:
        left = 0
        right = x
        
        if x == 1:
            return 1

        while right - left > 1:
            mid = (left + right) // 2
            
            if mid * mid == x:
                return mid
                break
            elif mid * mid > x:
                right = mid
            else:
                left = mid
            
        return left
    
*********************************************************
##70. Climbing Stairs
'''
Fibonacci Series

For climbing at n-th stairs, we can climb from (n-1)-th stairs with 1 step OR (n-2)-th stairs with 2 step.
Thus #n = #(n-1) + #(n-2)
'''
class Solution:
    def climbStairs(self, n: int) -> int:
        num = [1,2]
        for i in range(2,n):
            num.append(num[i-1] + num[i-2])  
        return num[n-1]
    
*********************************************************
##83. Remove Duplicates from Sorted List
'''
Traverse the list recursively from the head (or start) to end and after completion of recursion calls, 
compare the next node(returned node) and current node(head). 
If data of both nodes are equal then return the next (head-> next) node,
else return the current node(head).
'''
# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def deleteDuplicates(self, head: ListNode) -> ListNode:
        
        if not head or not head.next:
            return head
        
        if head.val == head.next.val:
            return self.deleteDuplicates(head.next)
        else: 
            head.next = self.deleteDuplicates(head.next)
            return head

*********************************************************
##88. Merge Sorted Array
class Solution:
    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        """
        Do not return anything, modify nums1 in-place instead.
        """
        for _ in range(len(nums1)-m):
            nums1.pop(-1)
        i, j = 0, 0
        
        while i < len(nums1) and j < n:
            if nums1[i] > nums2[j]:
                nums1.insert(i, nums2[j])
                j += 1
            i += 1
        nums1.extend(nums2[j:])
        
*********************************************************
##100. Same Tree
##BFS
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def BFS(self, s: TreeNode):
        queue = [s]
        order = []
        
        while queue:
            node = queue.pop(0)
            
            if node:
                order.append(node.val)
                queue.append(node.left)
                queue.append(node.right)
            else:
                order.append(node)
        
        return order
    
    def isSameTree(self, p: TreeNode, q: TreeNode) -> bool:
        return self.BFS(p) == self.BFS(q)
    
*********************************************************
##101. Symmetric Tree
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def isSymmetric(self, root: TreeNode) -> bool:
        return self.left_BFS(root) == self.right_BFS(root)

    def left_BFS(self, root: TreeNode) -> list:
        queue = [root]
        order = []
        
        while queue:
            node = queue.pop(0)
            if node:
                order.append(node.val)
                queue.append(node.left)
                queue.append(node.right)
            else:
                order.append(node)
        
        return order
    
    def right_BFS(self, root: TreeNode) -> list:
        queue = [root]
        order = []
        
        while queue:
            node = queue.pop(0)
            if node:
                order.append(node.val)
                queue.append(node.right)
                queue.append(node.left)
            else:
                order.append(node)
        
        return order

#########################################
##Recursive and Iterative
