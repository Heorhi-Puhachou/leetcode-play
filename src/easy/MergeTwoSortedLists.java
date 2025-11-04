package easy;

public class MergeTwoSortedLists {

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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        }

        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        ListNode result;
        ListNode currentMerged;
        ListNode currentList1 = list1;
        ListNode currentList2 = list2;
        if (list1.val < list2.val) {
            result = new ListNode(list1.val);
            currentList1 = list1.next;
        } else {
            result = new ListNode(list2.val);
            currentList2 = list2.next;
        }

        currentMerged = result;
        while (currentList1 != null || currentList2 != null) {
            if (currentList1 == null) {
                currentMerged.next = new ListNode(currentList2.val);
                currentMerged = currentMerged.next;
                currentList2 = currentList2.next;
            } else if (currentList2 == null) {
                currentMerged.next = new ListNode(currentList1.val);
                currentMerged = currentMerged.next;
                currentList1 = currentList1.next;
            } else {
                if (currentList1.val < currentList2.val) {
                    currentMerged.next = new ListNode(currentList1.val);
                    currentMerged = currentMerged.next;
                    currentList1 = currentList1.next;
                } else {
                    currentMerged.next = new ListNode(currentList2.val);
                    currentMerged = currentMerged.next;
                    currentList2 = currentList2.next;
                }
            }
        }

        return result;
    }

    public void test() {
        long startTime = System.nanoTime();
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        //ListNode list1 = new ListNode();
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        //ListNode list2 = new ListNode();
        MergeTwoSortedLists test = new MergeTwoSortedLists();
        ListNode result = test.mergeTwoLists(list1, list2);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);
    }


    public static void main(String[] args) {
        MergeTwoSortedLists test = new MergeTwoSortedLists();
        test.test();
    }
}
