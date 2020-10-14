### lc206 反转链表

### 解法

```cpp
class Solution {
public:
    ListNode* reverseKGroup(ListNode* head, int k) {
        if (!head || k <= 1) return head;
        ListNode* dummyHead = new ListNode(-1);
        ListNode* prev = dummyHead; // k个一组的组头的前面那个
        prev->next = head;
        ListNode* cur = head; // k个一组的组头
        for (int i = 1; cur; ++i) { // i从1开始！
          if (i % k == 0) {
            prev = reverseOneGroup(prev, cur->next); 
            cur = prev->next;
          } else {
            cur = cur->next;
          }
        }
        return dummyHead->next;
    }
    ListNode* reverseOneGroup(ListNode* pre, ListNode* end) { // 反转pre->next到end（不含end）的节点；返回反转后的组尾
        ListNode* cur = pre->next->next;
        ListNode* last = pre->next;
        while (cur != end) {
          last->next = cur->next; // 注意这里！需要把这组和原链表连起来！
          cur->next = pre->next;
          pre->next = cur;
          cur = last->next;
        }
        return last;
    }
};
```
