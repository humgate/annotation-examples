package reverselist;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 */

public class ReverseList {

    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
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

    public static ListNode initList(int... numbers) {
        ListNode node = new ListNode(numbers[0]);
        ListNode first = node;
        for (int i = 1; i < numbers.length; i++) {
            node.next = new ListNode(numbers[i]);
            node = node.next;
        }
        return first;
    }

    public static ListNode reverseList(ListNode head) {
        if (head.next == null) return head;
        ListNode node = reverseList(head.next);
        head.next.next=head;
        head.next=null;
        return node;
    }

    public static void main(String[] args) {
        ListNode head = initList(1, 2, 3,4);
        head = reverseList(head);
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
    }
}
