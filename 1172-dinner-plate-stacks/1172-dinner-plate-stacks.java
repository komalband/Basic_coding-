import java.util.*;

class DinnerPlates {

    List<Stack<Integer>> stacks;
    PriorityQueue<Integer> pq; // stores indices of stacks with space
    int capacity;

    public DinnerPlates(int capacity) {
        this.capacity = capacity;
        stacks = new ArrayList<>();
        pq = new PriorityQueue<>();
    }

    public void push(int val) {

        // remove invalid indices from pq
        while (!pq.isEmpty() && pq.peek() < stacks.size()
                && stacks.get(pq.peek()).size() == capacity) {
            pq.poll();
        }

        // also remove out-of-bound indices
        while (!pq.isEmpty() && pq.peek() >= stacks.size()) {
            pq.poll();
        }

        if (!pq.isEmpty()) {
            int idx = pq.poll();
            stacks.get(idx).push(val);

            // if still not full, keep index
            if (stacks.get(idx).size() < capacity) {
                pq.offer(idx);
            }

        } else {
            // create new stack
            Stack<Integer> st = new Stack<>();
            st.push(val);
            stacks.add(st);

            // if capacity > 1, it still has space
            if (capacity > 1) {
                pq.offer(stacks.size() - 1);
            }
        }
    }

    public int pop() {

        // remove empty stacks from right
        while (!stacks.isEmpty() && stacks.get(stacks.size() - 1).isEmpty()) {
            stacks.remove(stacks.size() - 1);
        }

        if (stacks.isEmpty()) return -1;

        int idx = stacks.size() - 1;
        int val = stacks.get(idx).pop();

        // mark this stack as available
        pq.offer(idx);

        return val;
    }

    public int popAtStack(int index) {

        if (index >= stacks.size() || stacks.get(index).isEmpty()) {
            return -1;
        }

        int val = stacks.get(index).pop();

        // mark this stack as available
        pq.offer(index);

        return val;
    }
}