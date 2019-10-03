package doubLinkList;

import java.util.NoSuchElementException;

public class DoubleLinkedList {
	private Node head;
	private Node tail;
	private int size;

	public DoubleLinkedList(Node head, Node tail, int size) {
		super();
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	class Node {
		int num;
		Node next;
		Node prev;

		public Node(int num) {
			this.num = num;
			this.next = null;
			this.prev = null;
		}

	}

	public boolean isEmpty() {
		return this.head == null;
	}

	public void addLast(int num) {
		if (isEmpty()) {
			this.head = new Node(num);
		} else {
			this.addLast(this.head, num);

		}
	}

	private void addLast(Node head2, int num) {
		if (head2.next == null) {
			Node newNode = new Node(num);
			head2.next = newNode;
			newNode.prev = head2;
			return;
		} else {
			addLast(head2.next, num);
		}

	}

	public int size() {
		if (isEmpty()) {
			return 0;

		} else {
			return 1 + size(this.head.next);
		}
	}

	private int size(Node node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + size(node.next);
		}
	}

	public int soma() {
		return soma(this.head);
	}

	private int soma(Node head2) {
		if (head2 == null) {
			return 0;
		} else {
			return head.num + soma(head2.next);
		}
	}

	public boolean isSorted() {
		return isSorted(this.head);

	}

	private boolean isSorted(Node head2) {
		boolean retorno = true;
		if (head2.next != null) {
			if (head2.num > head2.next.num) {
				retorno = false;
			} else {
				retorno = isSorted(head2.next);
			}
		}
		return retorno;
	}

	public void add(int index, int num) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();

		}
		Node newNode = new Node(num);

		if (index == 0) {
			this.addFirst(num);
		} else if (index == size - 1) {
			this.addLast(num);
		} else {
			Node aux = this.head;

			for (int i = 0; i < index - 1; i++) {
				aux = aux.next;

				newNode.next = aux.next;
				aux.next = newNode;
				newNode.next.prev = newNode;
				newNode.prev = aux;

				size += 1;
			}
		}

	}

	public void addFirst(int num) {
		Node newNode = new Node(num);

		if (isEmpty()) {
			this.head = newNode;
			this.tail = newNode;
		} else {
			newNode.next = this.head;
			this.head.prev = newNode;
			this.head = newNode;
		}

		size += 1;
	}

	public int removeFirst() {

		if (isEmpty())
			throw new NoSuchElementException();

		int num = this.head.num;

		if (this.head.next == null) {
			this.head = null;
			this.tail = null;
		} else {
			this.head = this.head.next;
			this.head.prev = null;
		}

		size -= 1;
		return num;
	}

	public int removeLast() {

		if (isEmpty())
			throw new NoSuchElementException();

		int num = this.tail.num;

		if (this.head.next == null) {
			this.head = null;
			this.tail = null;
		} else {
			this.tail = this.tail.prev;
			this.tail.next = null;
		}

		size -= 1;
		return num;
	}

	public int remove(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();

		if (index == 0)
			return removeFirst();
		if (index == size - 1)
			return removeLast();

		Node aux = this.head;
		for (int i = 0; i < index; i++)
			aux = aux.next;

		aux.prev.next = aux.next;
		aux.next.prev = aux.prev;
		size -= 1;
		return aux.num;
	}

}
