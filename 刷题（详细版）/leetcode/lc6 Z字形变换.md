### lc6 Z字形变换

### 解法

```cpp
string convert(string s, int numRows) {
    if (numRows <= 1) return s;
    string res;
    int size = 2 * numRows - 2, n = s.size();
    for (int i = 0; i < numRows; ++i) {
        for (int j = i; j < n; j += size) {
            res += s[j];
            int pos = j + size - 2 * i;
            if (i != 0 && i != numRows - 1 && pos < n) res += s[pos];
        }
    }
    return res;
}
```