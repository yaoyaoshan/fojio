### lc146 LRU

### 解法

```cpp
class LRUCache {
    public:
    LRUCache(int capacity) {
        capacity_ = capacity;
    }

    int get(int key) {
        auto m_iter = m.find(key);
        if (m_iter == m.end()) return -1; // 找不到返回-1
        auto l_iter = m_iter->second;
        l.splice(l.begin(), l, l_iter); // 找到了则把它移到表头：在l.begin()前面插入
        return l_iter->second; // 返回key对应的value
    }

    void put(int key, int value) {
        auto m_iter = m.find(key); // 找到list中key对应的kv对
        if (m_iter != m.end()) {
            l.erase(m_iter->second);
            m.erase(key);
        }
        l.push_front(make_pair(key, value)); // kv对放入表头
        m[key] = l.begin();
        if (l.size() > capacity_) {
            int k = l.rbegin()->first;
            l.pop_back();
            m.erase(k);
        }
    }
    private:
    list<pair<int, int>> l; // 存储kv对，最近使用的在表头
    unordered_map<int, list<pair<int, int>>::iterator> m; // key，key对应的kv对在链表l中的位置
    int capacity_;
};
```

