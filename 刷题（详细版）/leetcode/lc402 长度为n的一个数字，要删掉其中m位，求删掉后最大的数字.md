### lc402 长度为n的一个数字，要删掉其中m位，求删掉后最大的数字

### 解法一

最高位必然在前m+1位中，获取最高位之后开始递归

### 解法二

维护一个递增栈，只要发现当前的数字小于栈顶元素的话，就将栈顶元素移除；因为此时栈顶元素在高位上，就算后面的数字再大，也是在低位上，我们只有将高位上的数字尽可能的变小，才能使整个剩下的数字尽可能的小

```cpp
string removeKdigits(string num, int k) {
    string res = "";
    int n = num.size(), keep = n - k;
    for (char c : num) {
        while (!res.empty() && c < res.back() && k) {
            res.pop_back();
            --k;
        }
        res.push_back(c);
    }
    res.resize(keep);
    while (!res.empty() && res[0] == '0') res.erase(res.begin());
    return res.empty() ? "0" : res;
}
```

