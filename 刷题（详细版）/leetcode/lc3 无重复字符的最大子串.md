### lc3 无重复字符的最大子串

### 解法

双指针；右边的指针不要往回退

```cpp
int lengthOfLongestSubstring(string s) {
  int n = s.size();
  unordered_set<int> mySet;
  int j = 0, res = 0;
  for (int i = 0; i < n; ++i) {
    if (i != 0) mySet.erase(s[i - 1]);
    for (; j < n; ++j) {
      if (mySet.count(s[j])) {
        break;
      } else {
        mySet.insert(s[j]);
        res = max(res, j - i + 1);
      }
    }
  }
  return res;
}
```