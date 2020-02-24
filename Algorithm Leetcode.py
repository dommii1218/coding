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
##2. Add Two Numbers
# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        if not l1 and not l2:
            return None
        else:
            carry = 0
            head = l3 = ListNode(0)
            while l1 or l2:
                
                if not l1:
                    l1 = ListNode(0)
                if not l2:
                    l2 = ListNode(0)

                s = l1.val + l2.val + carry
                l3.next = ListNode(s % 10)
                carry = s//10
                l1 = l1.next
                l2 = l2.next
                l3 = l3.next
        
        if carry > 0:
            l3.next = ListNode(carry)
        return head.next  
    
*********************************************************
##3. Longest Substring Without Repeating Characters
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        dic, max_str, head = dict(), 0, 0
        for i in range(len(s)):
            c = s[i]
            if c in dic:
                head = max(head, dic[c] + 1)
            dic[c] = i
            max_str = max(i - head + 1, max_str)
        return max_str

*********************************************************
##4. Median of Two Sorted Arrays
class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        n, m = len(nums1), len(nums2)
        if n > m:
            nums3 = nums1
            nums1 = nums2
            nums2 = nums3
            n, m = len(nums1), len(nums2)
        
        left, right = 0, n
        while left <= right:
            p_x = (left + right) // 2
            p_y = (n + m + 1) // 2 - p_x
            
            if p_x == 0:
                x_leftmax = float('-inf')
            else:
                x_leftmax = nums1[p_x - 1]
                
            if p_x == n:
                x_rightmin = float('inf')
            else:
                x_rightmin = nums1[p_x]
                
            if p_y == 0:
                y_leftmax = float('-inf')
            else:
                y_leftmax = nums2[p_y - 1]
                
            if p_y == m:
                y_rightmin = float('inf')
            else:
                y_rightmin = nums2[p_y]
            
            if x_leftmax <= y_rightmin and y_leftmax <= x_rightmin:
                if (n + m) % 2 == 0:
                    return (max(x_leftmax, y_leftmax) + min(x_rightmin, y_rightmin))/2
                else:
                    return max(x_leftmax, y_leftmax)
            
            elif x_leftmax > y_rightmin:
                right = p_x - 1
            else:
                left = p_x + 1
                
*********************************************************
##5. Longest Palindromic Substring
##Expand Around Center
class Solution:
    def longestPalindrome(self, s: str) -> str:
        if not s or len(s) == 0: return ""
        head = 0
        tail = 0
        for i in range(len(s)):
            len1 = self.ExpandAroundCenter(s, i, i)
            len2 = self.ExpandAroundCenter(s, i, i + 1)
            max_len = max(len1, len2)
            if max_len > tail - head + 1:
                head = i - (max_len - 1) // 2
                tail = i + max_len // 2
        return s[head:(tail + 1)]
        
    def ExpandAroundCenter(self, s: str, left: int, right: int):
        if not s or len(s) == 0: return 0
        while left >= 0 and right < len(s) and s[left] == s[right]:    ##Pay attention to the symbols here.
            left -= 1
            right += 1
        return right - left - 1
    
*********************************************************
##6. ZigZag Conversion
class Solution:
    def convert(self, s: str, numRows: int) -> str:
        if not s:
            return ''
        if numRows == 1:
            return s
        
        building = ['']*numRows
        floor = 0
        step = 0
        for i in s:
            building[floor] += i
            if floor == 0:
                step = 1
            elif floor == numRows - 1:
                step = -1
            floor += step
        return ''.join(building)

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
        if - 2**31 <= x <= 2**31 - 1:
            sign = [-1, 1][x > 0]
            left = abs(x)
            right = 0
            while left:
                right = right * 10 + left % 10
                left = left // 10
            reverse = sign * right
            return (- 2**31 <= reverse <= 2**31 - 1)*reverse

*********************************************************
##8. String to Integer (atoi)
class Solution:
    def myAtoi(self, str: str) -> int:
        str = str.strip()
        res = ''
        if not str:
            return 0
        elif str[0] not in ('+', '-', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'):
            return 0
        elif str[0] in ('+', '-'):
            for x in str[1:]:
                if x in ('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'):
                    res += x
                else:
                    break
            if str[0] == '-' and res != "0" and res != "":
                res = str[0] + res
        else:
            for x in str:
                if x in ('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'):
                    res += x
                else:
                    break
        
        if not res:
             return 0
        return max(min(2147483647, int(res)), -2147483648)
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
#########################################        
class Solution:
    def isPalindrome(self, x: int) -> bool:
        if not x or x == 0:
            return True
        elif x < 0:
            return False  
        x_str = str(x)
        if len(x_str) % 2 == 0:
            left, right = len(x_str) // 2 - 1, len(x_str) // 2
        else:
            left, right = len(x_str) // 2, len(x_str) // 2
        
        while left > 0 and x_str[left] == x_str[right]:
            left -= 1
            right += 1
            
        if left == 0 and x_str[left] == x_str[right]:
            return True
        else:
            return False
        
*********************************************************
##10. Regular Expression Matching
##Dynamyc Programming
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        n = len(s)
        m = len(p)
        T = [[False for x in range(m+1)] for y in range(n+1)]
        T[0][0] = True
        for j in range(1,m+1):
            if p[j-1] == '*':
                T[0][j] = T[0][j-2]
        
        for i in range(1,n+1):
            for j in range(1,m+1):
                if s[i-1] == p[j-1] or p[j-1] == '.':
                    T[i][j] = T[i-1][j-1]
                elif p[j-1] == '*':
                    T[i][j] = T[i][j-2]
                    if s[i-1] == p[j-2] or p[j-2] == '.':
                        T[i][j] = T[i][j] or T[i-1][j]
        
        return T[n][m]

*********************************************************
##11. Container With Most Water
##Two Pointer Approach
class Solution:
    def maxArea(self, height: List[int]) -> int:
        max_Area = 0
        left = 0
        right = len(height) - 1
        while left < right:
            max_Area = max(max_Area, min(height[left], height[right]) * (right - left))
            if height[left] < height[right]:
                left += 1
            else:
                right -= 1
        
        return max_Area
*********************************************************
##12. Integer to Roman
class Solution:
    def intToRoman(self, num: int) -> str:
        roman_dic = {1:'I', 5:'V', 10:'X', 50:'L', 100:'C', 500:'D', 1000:'M'}
        digit_dic = {1:[1],
                     2:[1,1],
                     3:[1,1,1],
                     4:[1,5],
                     5:[5],
                     6:[5,1],
                     7:[5,1,1],
                     8:[5,1,1,1],
                     9:[1,10]}
        
        r = ''
        
        for x in [1000,100,10,1]:
            digit = num//x
            num %= x
            if digit == 0:
                continue
            else:
                for i in digit_dic[digit]:
                    r += roman_dic[i*x]
        
        return r
   
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
        min_str = min(strs, key = len, default = "")    ##which.min
        min_len = len(min_str)

        i = 0
        prefix = ""
        while i < min_len:
            if not all(items[i] == min_str[i] for items in strs):
                break
            prefix += min_str[i]
            i += 1
                    
        return prefix
    
*********************************************************
##15. 3Sum
class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        if len(nums) < 3 or not nums:
            return None
        
        res = set()
        nums.sort()
        for i in range(len(nums)):
            if nums[i] > 0:
                break
            elif i > 0 and nums[i] == nums[i-1]:
                continue
            else:
                l = i + 1
                r = len(nums) - 1
                while l < r:
                    if nums[i] + nums[l] + nums[r] == 0:
                        res.add((nums[i], nums[l], nums[r]))
                        l += 1
                        r -= 1
                    elif nums[i] + nums[l] + nums[r] > 0:
                        r -= 1
                    else:
                        l += 1
        return res
    
*********************************************************
##16. 3Sum Closest
class Solution:
    def threeSumClosest(self, nums: List[int], target: int) -> int:
        if len(nums) < 3 or not nums:
            return None
        nums.sort()
        res = sum(nums[:3])
        min_abs_diff = abs(res-target)
        for i in range(len(nums)):
            l = i + 1
            r = len(nums) - 1
            while l < r:
                sums =  nums[i] + nums[l] + nums[r]
                diff = sums - target
                if diff == 0:
                    return target
                elif diff < 0:
                    l += 1
                else:
                    r -= 1
                if abs(diff) < min_abs_diff:
                    res = sums
                    min_abs_diff = abs(diff)
        return res
    
*********************************************************
##17. Letter Combinations of a Phone Number
class Solution:
    def letterCombinations(self, digits: str) -> List[str]:
        dic = {'2':['a','b','c'],
               '3':['d','e','f'],
               '4':['g','h','i'],
               '5':['j','k','l'],
               '6':['m','n','o'],
               '7':['p','q','r','s'],
               '8':['t','u','v'],
               '9':['w','x','y','z']}
        
        res = []
        def backtrack(combn, next_digits):
            if not next_digits:
                res.append(combn)
            else:
                for i in dic[next_digits[0]]:
                    backtrack(combn + i, next_digits[1:])

        if not digits:
            return ''
        else:
            backtrack('',digits)
            return res
        
*********************************************************
##18. 4Sum
class Solution:
    def fourSum(self, nums: List[int], target: int) -> List[List[int]]:
        if not nums or len(nums) < 4:
            return None
        
        res = set()
        nums.sort()
        for i in range(len(nums)):
            for j in range(i+1,len(nums)):
                l = j + 1
                r = len(nums)-1
                while l < r:
                    if nums[i] + nums[j] + nums[l] + nums[r] == target:
                        res.add((nums[i],nums[j],nums[l],nums[r]))
                        l += 1
                        r -= 1
                    elif nums[i] + nums[j] + nums[l] + nums[r] > target:
                        r -= 1
                    else:
                        l += 1
        return list(res)
    
*********************************************************
##19. Remove Nth Node From End of List
class Solution:
    def removeNthFromEnd(self, head: ListNode, n: int) -> ListNode:
        dummy = ListNode(0)
        dummy.next = head
        first = second = dummy
        for i in range(n+1):
            first = first.next
        while first:
            first = first.next
            second = second.next
        second.next = second.next.next
        return dummy.next

*********************************************************
##20. Valid Parentheses
class Solution:
    def isValid(self, s: str) -> bool:
        dic = {')':'(', '}':'{', ']':'['}
        
        if len(s) % 2 != 0:
            return False
        if not s:
            return True
        
        stack, i = [], 0
        for i in range(len(s)):
            if s[i] in dic.values():
                stack.append(s[i])
            elif dic[s[i]] not in stack or dic[s[i]] != stack[-1]:
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
        if not l1:
            return l2
        if not l2:
            return l1
        if not l1 and not l2:
            return None
        
        if l1.val <= l2.val:
            dummy = ListNode(l1.val)
            dummy.next = self.mergeTwoLists(l1.next, l2)
        else:
            dummy = ListNode(l2.val)
            dummy.next = self.mergeTwoLists(l1, l2.next)
        return dummy
            
#########################################
class Solution:
    def merge2Lists(self, l1, l2):
        head = point = ListNode(0)
        while l1 and l2:
            if l1.val <= l2.val:
                point.next = l1
                l1 = l1.next
            else:
                point.next = l2
                l2 = l1
                l1 = point.next.next
            point = point.next
        if not l1:
            point.next=l2
        else:
            point.next=l1
        return head.next
    
*********************************************************
##23. Merge k Sorted Lists
##Merge and Conquer
class Solution:
    def mergeKLists(self, lists: List[ListNode]) -> ListNode:
        amount = len(lists)
        interval = 1
        while interval < amount:
            for i in range(0, amount - interval, 2 * interval):
                lists[i] = self.merge2Lists(lists[i], lists[i+interval])
            interval *= 2
        return lists[0] if lists else None
        
    def merge2Lists(self, l1: ListNode, l2:ListNode):
        if not l1:
            return l2
        if not l2:
            return l1
        if not l1 and not l2:
            return None
        
        if l1.val <= l2.val:
            dummy = ListNode(l1.val)
            dummy.next = self.merge2Lists(l1.next, l2)
        else:
            dummy = ListNode(l2.val)
            dummy.next = self.merge2Lists(l1, l2.next)
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
##29. Divide Two Integers
class Solution:
    def divide(self, dividend: int, divisor: int) -> int:
        MIN_VALUE = - 2 ** 31
        MAX_VALUE = 2 ** 31 - 1
        sign = 1
        res = 0
        if divisor == 0:
            return MAX_VALUE
        if dividend == MIN_VALUE and divisor == -1:
            return MAX_VALUE
        
        if dividend < 0:
            dividend = - dividend
            sign = - sign
        if divisor < 0:
            divisor = - divisor
            sign = - sign
        
        while dividend >= divisor:
            shift = 0
            while dividend >= (divisor << shift):
                shift += 1
            res += (1 << (shift - 1))
            dividend -= (divisor << (shift - 1))
        
        res *= sign
        return res if MIN_VALUE <= res <= MAX_VALUE else MIN_VALUE
                
*********************************************************
##31. Next Permutation:
class Solution:
    def nextPermutation(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        switch = -1
        n = len(nums)
        for i in range(n-2, switch, -1):
            if nums[i] < nums[i+1]:
                switch = i
                break
                
        if switch == -1:
            return nums.sort()
        
        j = n-1
        for i in range(switch+1,n):
            if nums[i] <= nums[switch]:
                j = i-1
                break

        nums[j], nums[switch] = nums[switch], nums[j]
        nums[switch+1:] = [nums[i] for i in range(n-1,switch,-1)]
        
        return nums
    
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
class Solution:
    def isSymmetric(self, root: TreeNode) -> bool:
        def recur(left,right):
            if not left and not right:return True
            if left and right:
                return left.val == right.val and recur(left.left,right.right) and recur(left.right,right.left)
            else:
                return False
        return recur(root,root)
    
*********************************************************
##104. Maximum Depth of Binary Tree
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def maxDepth(self, root: TreeNode) -> int:
        if not root:
            return 0
        return max(self.maxDepth(root.left), self.maxDepth(root.right)) + 1
    
*********************************************************
##107. Binary Tree Level Order Traversal II
