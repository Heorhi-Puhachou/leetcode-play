package medium;

/*
    Given a linked list, swap every two adjacent nodes and return its head.
    You must solve the problem without modifying the values in the list's nodes
    (i.e., only nodes themselves may be changed.)
 */
public class SwapNodesInPairs {

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

        ListNode first = head;
        ListNode prev;
        // first swap without previous element
        ListNode second = head.next;
        ListNode result = second;
        head.next = second.next;
        second.next = head;

        while (first.next != null && first.next.next != null) {
            prev = first;
            second = first.next.next;
            first = first.next;

            first.next = second.next;
            second.next = first;
            prev.next = second;
        }
        return result;

    }

    public void test() {
        long startTime = System.nanoTime();
        ListNode list = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        SwapNodesInPairs test = new SwapNodesInPairs();
        SwapNodesInPairs.ListNode result = test.swapPairs(list);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);
    }


    public static void main(String[] args) {
        SwapNodesInPairs test = new SwapNodesInPairs();
        test.test();
    }
}
