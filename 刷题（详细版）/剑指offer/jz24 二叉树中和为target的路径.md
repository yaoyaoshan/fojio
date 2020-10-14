### jc24 二叉树中和为target的路径

### 解法

```cpp
class Solution {
public:
    void helper(TreeNode* root, int sum, vector<int>& cur, vector<vector<int>>& result) {
        if (!root) return;
        cur.push_back(root->val);
        if (root->left) helper(root->left, sum - root->val, cur, result);
        if (root->right) helper(root->right, sum - root->val, cur, result);
        if (!root->left && !root->right && sum == root->val) result.push_back(cur);
        cur.pop_back(); 
    }

    vector<vector<int>> pathSum(TreeNode* root, int sum) {
        vector<vector<int>> result;
        vector<int> cur;
        helper(root, sum, cur, result);
        return result;
    }
};
```
