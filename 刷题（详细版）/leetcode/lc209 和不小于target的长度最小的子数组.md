### lc209 和>=target的长度最小的子数组

### 解法一

和无重复字符的最大子串类似，双指针

```cpp
// 写法一
int minSubArrayLen(int s, vector<int>& nums) {
    int total = accumulate(nums.begin(), nums.end(), 0);
    if (total < s) return 0;
    if (total == s) return nums.size();
    int left = 0, right = 0, curSum = nums[0], res = INT_MAX;
    while (right < nums.size()) {
        if (curSum >= s) {
            res = min(res, right - left + 1);
            curSum -= nums[left];
            ++left;
        } else {
            ++right;
        	if (right < nums.size()) curSum += nums[right];
        }
    }
    return res;
}
// 写法二
int minSubArrayLen(int target, vector<int>& nums) {
    int n = nums.size();
    if (n == 0) return 0;
    int left = 0, right = 0, curSum = 0, res = n + 1;
    while (right < n) {
        while (curSum < target && right < n) curSum += nums[right++];
        while (curSum >= target) {
            res = min(res, right - left);
            curSum -= nums[left++];
        }
    }
    return res == n + 1 ? 0 : res;
}
```

### 解法二

二分：建立sums 数组，其中 sums[i] 表示 nums 数组中 [0, i-1] 的和，然后对于 sums 中每一个值 sums[i]，用二分查找法找到子数组的右边界位置，使该子数组之和大于等于 sums[i] + s

```cpp
// 写法一
int minSubArrayLen(int s, vector<int>& nums) {
    int len = nums.size(), sums[len + 1] = {0}, res = len + 1;
    for (int i = 1; i < len + 1; ++i) sums[i] = sums[i - 1] + nums[i - 1];
    for (int i = 0; i < len + 1; ++i) {
        int right = searchRight(i + 1, len, sums[i] + s, sums);
        if (right == len + 1) break;
        if (res > right - i) res = right - i;
    }
    return res == len + 1 ? 0 : res;
} 
int searchRight(int left, int right, int key, int sums[]) {     
    while (left <= right) {
        int mid = (left + right) / 2;
        if (sums[mid] >= key) right = mid - 1;
        else left = mid + 1;
    }
    return left;
}
// 写法二
int minSubArrayLen(int target, vector<int>& nums) {
    int n = nums.size();
    if (n == 0) return 0;
    // sum[i] = nums[0] + nums[1] + ... + nums[i]
    vector<int> sum(n);
    sum[0] = nums[0];
    for (int i = 1; i < n; ++i) {
        sum[i] = sum[i - 1] + nums[i];
    }
    // for each i, find the smallest j that sum[j] - target >= sum[i]
    int res = n + 1;
    for (int i = 0; i < n; ++i) {
        if (sum[i] >= target) {
            res = min(res, i + 1);
        }
        if (i == n - 1) break;
        int left = i + 1, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (sum[mid] - target < sum[i]) left = mid + 1;
            else right = mid;
        }
        if (right < n) res = min(res, right - i);
    }
    return res == n + 1 ? 0 : res;
}
```

