### lc322 凑到amount最少需要的硬币数

### 解法

dp[i]记录凑到i最少需要的硬币数，dp[i] = min(dp[i - coin] + 1);