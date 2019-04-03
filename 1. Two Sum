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
