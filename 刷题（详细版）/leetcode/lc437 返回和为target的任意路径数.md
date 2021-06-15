### lc437 返回和为target的任意路径数

### 解法一

递归

```cpp
class Solution {
public:
    int pathSum(TreeNode* root, int targetSum) {
        if (!root) return 0;
        return getSumStartWithNode(root, targetSum) + pathSum(root->left, targetSum) + pathSum(root->right, targetSum);
    }
    int getSumStartWithNode(TreeNode* node, int targetSum) {
      // 返回以node为起点的，和为targetSum的路径数
        if (!node) return 0;
        return (node->val == targetSum) + getSumStartWithNode(node->left, targetSum - node->val) + getSumStartWithNode(node->right, targetSum - node->val);
    }
};
```

### 解法二

dfs

```cpp
class Solution {
public:
    int pathSum(TreeNode* root, int targetSum) {
        int res = 0;
        vector<int> curPath;
        dfs(root, curPath, 0, res, targetSum);
        return res;
    }

    void dfs(TreeNode* root, vector<int>& curPath, int curSum, int& res, int targetSum) {
        if (!root) return;
        curPath.emplace_back(root->val);
        curSum += root->val;
        if (curSum == targetSum) ++res;
        // 求以当前结点结尾的、和为target的路径数
        int temp = curSum;
        for (int i = 0; i < (int)curPath.size() - 1; ++i) {
            temp -= curPath[i];
            if (temp == targetSum) ++res;
        }
        dfs(root->left, curPath, curSum, res, targetSum);
        dfs(root->right, curPath, curSum, res, targetSum);
        curPath.pop_back();
    }
};
```

### 解法三

优化版的解法二，dfs

```cpp
class Solution {
public:
    int pathSum(TreeNode* root, int targetSum) {
        int res = 0;
        unordered_map<int, int> m; // {路径和k: 以root为起点的，路径和为k的路径数}
        m[0] = 1;
        dfs(root, 0, res, targetSum, m);
        return res;
    }

    void dfs(TreeNode* root, int curSum, int& res, int targetSum, unordered_map<int, int>& m) {
        if (!root) return;
        curSum += root->val;
        res += m[curSum - targetSum]; // 以当前结点结尾的、和为targetSum的路径数 = 以根节点为起点的、和为curSum - targetSum的路径数
        ++m[curSum]; // 这句必须放在前面那句的后面，以满足targetSum=0的场景
        dfs(root->left, curSum, res, targetSum, m);
        dfs(root->right, curSum, res, targetSum, m);
        --m[curSum];
    }
};
```

