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

### 解法二：迭代 dfs

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

### 解法三：bfs

```cpp
class Solution {
public:
    bool hasPathSum(TreeNode *root, int sum) {
        if (root == nullptr) {
            return false;
        }
        queue<TreeNode *> que_node;
        queue<int> que_val;
        que_node.push(root);
        que_val.push(root->val);
        while (!que_node.empty()) {
            TreeNode *now = que_node.front();
            int temp = que_val.front();
            que_node.pop();
            que_val.pop();
            if (now->left == nullptr && now->right == nullptr) {
                if (temp == sum) {
                    return true;
                }
                continue;
            }
            if (now->left != nullptr) {
                que_node.push(now->left);
                que_val.push(now->left->val + temp);
            }
            if (now->right != nullptr) {
                que_node.push(now->right);
                que_val.push(now->right->val + temp);
            }
        }
        return false;
    }
};
```

