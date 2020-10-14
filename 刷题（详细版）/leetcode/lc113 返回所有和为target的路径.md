### lc113 返回所有和为target的路径

### 解法

```cpp
void findPath(TreeNode* root, int sum, vector<vector<int>>& result, vector<int>& cur) {
    if (!root) return;
    cur.push_back(root->val);
    if (!root->left && !root->right) {
      	if (root->val == sum) result.push_back(cur);
    }
    if (root->left) findPath(root->left, sum - root->val, result, cur);
    if (root->right) findPath(root->right, sum - root->val, result, cur);
    cur.pop_back();
}

vector<vector<int>> pathSum(TreeNode* root, int sum) {
     vector<vector<int>> result;
     vector<int> cur;
     findPath(root, sum, result, cur);
     return result;
}
```