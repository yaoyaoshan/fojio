### lc16 3sum closest

### 解法

```cpp
int threeSumClosest(vector<int>& nums, int target) {
    int n = nums.size();
    if (n < 3) return -1;
    int res = nums[0] + nums[1] + nums[2];
    int diff = abs(res - target);
    sort(nums.begin(), nums.end());
    for (int i = 0; i < n - 2; ++i) {
        int left = i + 1, right = n - 1;
        while (left < right) {
            int curSum = nums[i] + nums[left] + nums[right];
            int newDiff = abs(curSum - target);
            if (newDiff < diff) {
                res = curSum;
                diff = newDiff;
            }
            if (curSum > target) --right;
            else ++left;
        }
    }
    return res;
}
```

