### lc93 复原ip地址

### 解法

```cpp
class Solution {
public:
  vector<string> restoreIpAddresses(string s) {
    vector<string> res;
    if (s.size() < 4 || s.size() > 12) return res;
    restoreIpAddresses(s, 4, "", res);
    return res;
  }

  void restoreIpAddresses(string s, int k, string cur, vector<string>& res) {
    if (k == 1 && isValid(s)) {
      res.push_back(cur.substr(1) + "." + s);
      return;
    }
    for (int i = 1; i <= 3; ++i) { // 把前i位分出来
      if (i <= s.size() && isValid(s.substr(0, i))) {
        restoreIpAddresses(s.substr(i), k - 1, cur + "." + s.substr(0, i), res); // cur是string，所以传cur + "."+...这个右值
      }
    }
  }

  bool isValid(string s) {
    if (s.empty() || s.size() > 3 || (s.size() > 1 && s[0] == '0')) return false;
    int res = atoi(s.c_str());
    return res <= 255 && res >= 0;
  }
};
```