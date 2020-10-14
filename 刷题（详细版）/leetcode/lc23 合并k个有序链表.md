### lc23 合并k个有序链表

### 解法

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

