### lc112 判断二叉树是否有从根到节点的路径和为target

### 解法一：递归

```cpp
bool hasPathSum(TreeNode* root, int sum) {
    if (!root) return false;
    if (!root->left && !root->right) return root->val == sum;
    int flag = false;
    if (root->left) {
      flag = flag || hasPathSum(root->left, sum - root->val);
    }
    if (root->right) {
      flag = flag || hasPathSum(root->right, sum - root->val);
    }
    return flag;
}
```

### 解法二：迭代

迭代，把根节点的值累加到孩子节点上

```cpp
bool hasPathSum(TreeNode* root, int sum) {
if (!root) return false;
stack<TreeNode*> s;
s.push(root);
while (!s.empty()) {
  TreeNode* cur = s.top();
  s.pop();
  if (!cur->left && !cur->right && cur->val == sum) return true;
  if (cur->left) {
    cur->left->val += cur->val;
    s.push(cur->left);
  }
  if (cur->right) {
    cur->right->val += cur->val;
    s.push(cur->right);
  } 
}
return false;
}
```

