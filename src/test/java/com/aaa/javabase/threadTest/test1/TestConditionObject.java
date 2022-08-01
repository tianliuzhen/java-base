package com.aaa.javabase.threadTest.test1;

/**
 * @author liuzhen.tian
 * @version 1.0 TestConditionObject.java  2022/6/4 23:32
 */
public class TestConditionObject {
    private static final long serialVersionUID = 1173984872572414699L;
    /**
     * First node of condition queue.
     */
    private transient Node firstWaiter;
    /**
     * Last node of condition queue.
     */
    private transient Node lastWaiter;

    static final class Node {
        String name = "";
        /**
         * Marker to indicate a node is waiting in shared mode
         */
        static final Node SHARED = new Node();
        /**
         * Marker to indicate a node is waiting in exclusive mode
         */
        static final Node EXCLUSIVE = null;

        /**
         * waitStatus value to indicate thread has cancelled
         */
        static final int CANCELLED = 1;
        /**
         * waitStatus value to indicate successor's thread needs unparking
         */
        static final int SIGNAL = -1;
        /**
         * waitStatus value to indicate thread is waiting on condition
         */
        static final int CONDITION = -2;
        static final int PROPAGATE = -3;

        volatile int waitStatus;

        volatile Node prev;

        volatile Node next;

        volatile Thread thread;

        Node nextWaiter;

        /**
         * Returns true if node is waiting in shared mode.
         */
        final boolean isShared() {
            return nextWaiter == SHARED;
        }

        final Node predecessor() throws NullPointerException {
            Node p = prev;
            if (p == null)
                throw new NullPointerException();
            else
                return p;
        }

        Node() {    // Used to establish initial head or SHARED marker
        }

        Node(int waitStatus, String name) {
            this.waitStatus = waitStatus;
            this.name = name;
        }


        Node(Thread thread, Node mode) {     // Used by addWaiter
            this.nextWaiter = mode;
            this.thread = thread;
        }

        Node(Thread thread, int waitStatus) { // Used by Condition
            this.waitStatus = waitStatus;
            this.thread = thread;
        }
    }

    /**
     * 这个方法功能：
     * a(正常节点) --> b（取消节点） --> c（正常节点）
     * =》
     * a(正常节点) --> c（正常节点）
     * 就是把取消节点，在单向链表中剔除。
     */
    public void unlinkCancelledWaiters() {
        // first 等待节点
        Node t = firstWaiter;
        // 不是取消状态的节点，用于辅助定位 firstWaiter 和 lastWaiter 节点，
        // 如果 trail 不为空说明，表示上一个节点的 waitStatus == Node.CONDITION
        Node trail = null;
        while (t != null) {
            Node next = t.nextWaiter;
            // 如果节点的状态不是 Node.CONDITION 的话，这个节点就是被取消的
            if (t.waitStatus != Node.CONDITION) {
                t.nextWaiter = null;
                if (trail == null)
                    // 标记等待首节点
                    firstWaiter = next;
                else
                    // trail.nextWaiter 也表示 firstWaiter.nextWaiter
                    trail.nextWaiter = next;
                // 如果是最后一个节点，标记 lastWaiter
                if (next == null)
                    lastWaiter = trail;
            } else
                //  t为firstWaiter 等号赋值 trail
                trail = t;
            t = next;
        }
    }

    public static void main(String[] args) {
        TestConditionObject testConditionObject = new TestConditionObject();
        Node firstWaiter = new Node(Node.CONDITION, "1");
        Node nextWaiter = new Node(Node.CONDITION, "2");
        nextWaiter.nextWaiter = new Node(0, "3");
        firstWaiter.nextWaiter = nextWaiter;
        testConditionObject.firstWaiter = firstWaiter;
        testConditionObject.unlinkCancelledWaiters();
        System.out.println();
    }
}
