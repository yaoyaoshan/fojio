### lc23 合并k个有序链表

### 解法一

最小堆

首先把k个链表的首元素都加入最小堆中，它们会自动排好序。然后每次取出最小的那个元素加入最终结果的链表中，然后把取出元素的下一个元素再加入堆中

```cpp
ListNode* mergeKLists(vector<ListNode*>& lists) {
    auto cmp = [](ListNode* a, ListNode* b) {
      return a->val > b->val;
    };
    priority_queue<ListNode*, vector<ListNode*>, decltype(cmp)> q(cmp);
    for (auto head : lists) {
      if (head) q.push(head); // 记住要if(head)！
    }
    ListNode* dummyHead = new ListNode(-1);
    ListNode* cur = dummyHead;
    while (!q.empty()) {
      cur->next = q.top();
      q.pop();
      cur = cur->next;
      if (cur->next) q.push(cur->next);
    }
    return dummyHead->next;
}
```

### 解法二

归并1

```cpp
class Solution {
public:
    ListNode* mergeKLists(vector<ListNode*>& lists) {
        int n = lists.size();
        if (n == 0) return nullptr;
        while (n > 1) {
            int k = (n + 1) / 2;
            for (int i = 0; i < n / 2; ++i) {
                lists[i] = merge2Lists(lists[i], lists[i + k]);
            }
            n = k;
        }
        return lists[0];
    }
    
    ListNode* merge2Lists(ListNode* l1, ListNode* l2) {
        ListNode* dummyHead = new ListNode();
        ListNode* prev = dummyHead;
        while (l1 && l2) {
            if (l1->val <= l2->val) {
                prev->next = l1;
                prev = l1;
                l1 = l1->next;
            } else {
                prev->next = l2;
                prev = l2;
                l2 = l2->next;
            }
        }
        if (l1) prev->next = l1;
        if (l2) prev->next = l2;
        return dummyHead->next;
    }
};
```

### 解法三

归并2

```cpp
class Solution {
public:
    ListNode* mergeKLists(vector<ListNode*>& lists) {
        int n = lists.size();
        if (n == 0) return nullptr;
        return helper(lists, 0, n - 1);
    }
    
    ListNode* helper(vector<ListNode*>& lists, int left, int right) {
        if (left > right) return nullptr;
        if (left == right) return lists[left];
        int mid = left + (right - left) / 2;
        ListNode* l1 = helper(lists, left, mid);
        ListNode* l2 = helper(lists, mid + 1, right);
        return merge2Lists(l1, l2);
    }
    
    ListNode* merge2Lists(ListNode* l1, ListNode* l2) {
        if (!l1) return l2;
        if (!l2) return l1;
        ListNode* dummyHead = new ListNode();
        ListNode* prev = dummyHead;
        while (l1 && l2) {
            if (l1->val <= l2->val) {
                prev->next = l1;
                prev = l1;
                l1 = l1->next;
            } else {
                prev->next = l2;
                prev = l2;
                l2 = l2->next;
            }
        }
        if (l1) prev->next = l1;
        if (l2) prev->next = l2;
        return dummyHead->next;
    }
};
```



### 解法四

计数排序

将所有的结点值出现的最大值和最小值都记录下来，然后记录每个结点值出现的次数，这样从最小值遍历到最大值的时候，就会按顺序经过所有的结点值，根据其出现的次数，建立相对应个数的结点

```cpp
ListNode* mergeKLists(vector<ListNode*>& lists) {
    ListNode *dummy = new ListNode(-1), *cur = dummy;
    unordered_map<int, int> m;
    int mx = INT_MIN, mn = INT_MAX;
    for (auto node : lists) {
        ListNode *t = node;
        while (t) {
            mx = max(mx, t->val);
            mn = min(mn, t->val);
            ++m[t->val];
            t = t->next;
        }
    }
    for (int i = mn; i <= mx; ++i) {
        if (!m.count(i)) continue;
        for (int j = 0; j < m[i]; ++j) {
            cur->next = new ListNode(i);
            cur = cur->next;
        }
    }
    return dummy->next;
}
```

