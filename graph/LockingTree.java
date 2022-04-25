/**
 * You are given a tree with n nodes numbered from 0 to n - 1 in the form of a parent array parent where parent[i] is the parent of the ith node. 
 * The root of the tree is node 0, so parent[0] = -1 since it has no parent.
 * You want to design a data structure that allows users to lock, unlock, and upgrade nodes in the tree.
 *
 * The data structure should support the following functions:
 *
 * Lock: Locks the given node for the given user and prevents other users from locking the same node. You may only lock a node using this function if the node is unlocked.
 * Unlock: Unlocks the given node for the given user. You may only unlock a node using this function if it is currently locked by the same user.
 * Upgrade: Locks the given node for the given user and unlocks all of its descendants regardless of who locked it. You may only upgrade a node if all 3 conditions are true:
 *       The node is unlocked,
 *       It has at least one locked descendant (by any user), and
 *       It does not have any locked ancestors.
 *
 * https://leetcode.com/problems/operations-on-tree/
 *
 * We store locks in a map from node number to user id and also create a graph from parent-link array provided in the constructor to create an adjacency-list graph and 
 * essentially pre-process child links. This speeds up searching for descendants during lock upgrade. Locking and unlocking is simply putting and removing a mapping for 
 * the given node number to a user id to and from locks map.
 * 
 * During lock upgrade we use DFS to traverse the graph and check for any lcoked descendants. We also use DFS to unlock the descendants.
 * Time complexity: constructor is O(n), locking and unlocking is O(1) and upgrading a node is O(logm) where m is the number of nodes in the tree with root at the node to upgrade.
 * Space complexity: constructor is O(n), locking and unlocking is O(1), upgrading a node is O(n) because of the marked array. 
 */
class LockingTree {
    private final List<Integer>[] graph;
    private final Map<Integer, Integer> locks = new HashMap<>();
    private final int[] parent;
    
    public LockingTree(int[] parent) {
        this.parent = parent;
        graph = (List<Integer>[]) new List[parent.length];
        
        for (int i = 1; i < parent.length; i++) {
            if (graph[parent[i]] == null) {
                graph[parent[i]] = new ArrayList<>();
            }
            graph[parent[i]].add(i);
        }
    }
    
    public boolean lock(int num, int user) {
        if (locks.containsKey(num)) {
            return false;
        }
        locks.put(num, user);
        return true;
    }
    
    public boolean unlock(int num, int user) {
        if (locks.containsKey(num) && locks.get(num) == user) {
            locks.remove(num);
            return true;
        }
        return false;
    }
    
    public boolean upgrade(int num, int user) {
        if (locks.containsKey(num)) {
            return false;
        }
        int[] marked = new int[parent.length];
        
        if (!checkDescendantsLocked(num, marked) || !checkAncestorsUnlocked(num)) {
            return false;
        }
        
        marked = new int[parent.length];
        
        unlockDescendants(num, marked);
        lock(num, user);
        
        return true;
    }
    
    private boolean checkDescendantsLocked(int num, int[] marked) {
        marked[num]++;
        
        if (locks.containsKey(num)) {
            return true;
        }
        
        if (graph[num] == null || graph[num].isEmpty()) {
            return false;
        }
        
        boolean res = false;
        
        for (int i = 0; i < graph[num].size(); i++) {
            if (marked[graph[num].get(i)] == 0) {
                res = checkDescendantsLocked(graph[num].get(i), marked);
                if (res) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean checkAncestorsUnlocked(int num) {
        do {
            if (locks.containsKey(num)) {
                return false;
            }
            num = parent[num];
        } while (num != -1);
        
        return true;
    }
    
    private void unlockDescendants(int num, int[] marked) {
        marked[num]++;
        
        if (locks.containsKey(num)) {
            locks.remove(num);
        }

        if (graph[num] == null || graph[num].isEmpty()) {
            return;
        }
        
        for (int i = 0; i < graph[num].size(); i++) {
            if (marked[graph[num].get(i)] == 0) {
                unlockDescendants(graph[num].get(i), marked);
            }
        }
    }
}
