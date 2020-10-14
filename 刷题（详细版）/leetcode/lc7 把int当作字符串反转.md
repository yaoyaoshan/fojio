### lc7 把int当作字符串反转

### 解法

符号为负不影响结果；要防溢出

```cpp
int reverseInt(int x) {
    int y = 0;
        while (x) {
        if (abs(y) > INT_MAX / 10) return 0;
        y = y * 10 + x % 10;
        x /= 10;
    }
    return y;
}
```