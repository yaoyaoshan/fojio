### lc209 和>=target的长度最小的子数组

### 解法一

和无重复字符的最大子串类似，双指针

```cpp
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
```

### 解法二

二分

```cpp
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
```

