class CustomStack {

    int[] stack;
    int[] inc;
    int top;
    int maxSize;

    public CustomStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
        inc = new int[maxSize];
        top = -1;
    }

    public void push(int x) {
        if (top == maxSize - 1) return;
        stack[++top] = x;
    }

    public int pop() {
        if (top == -1) return -1;

        int res = stack[top] + inc[top];

        // pass increment down
        if (top > 0) {
            inc[top - 1] += inc[top];
        }

        inc[top] = 0;
        top--;

        return res;
    }

    public void increment(int k, int val) {
        int i = Math.min(k - 1, top);
        if (i >= 0) {
            inc[i] += val;
        }
    }
}