package task1;

/**
 * Queue class as fifo ringbuffer.
 *
 * Group: ARGH
 *
 * @author Alexander Reichart {@literal <alexander.reichart@haw-hamburg.de>}
 * @author Gerriet Hinrichs {@literal <gerriet.hinrichs@web.de>}
 */
public class Queue<T> {

	/**
	 * the array with the elements
	 */
	private final T[] elements;

	/**
	 * head of the queue, where to add new elements
	 */
	private int head;

	/**
	 * tail of the queue, oldest element
	 */
	private int tail;

	/**
	 * fill level of the buffer
	 */
	private int fillLevel;

	/**
	 * constructor for the queue, suppressing the warnings to make it shut up
	 * about the unchecked type cast for the array
	 *
	 * @param capacity
	 */
	@SuppressWarnings("unchecked")
	public Queue(final int capacity) {

		if (capacity < 1) {
			throw new IllegalArgumentException("Capacity must be positive!");
		}
		this.elements = (T[]) new Object[capacity];
		this.head = 0;
		this.fillLevel = 0;
		this.tail = 0;
	}

	/**
	 * @return T the oldest element in the queue
	 */

	public T dequeue() {
		if (this.getSize() > 0) {
			final int i = this.tail;
			this.tail = (this.tail + 1) % this.elements.length;
			this.fillLevel--;
			return this.elements[i];
		} else {
			throw new IndexOutOfBoundsException("Queue is empty!");
		}
	}

	/**
	 * adds a new Element.
	 *
	 * @param value
	 *            the element to add. .
	 */
	public void enqueue(final T value) {
		if (this.elements.length > 0 & this.head != this.tail | this.fillLevel == 0) {
			this.elements[this.head] = value;

			this.head = (this.head + 1) % this.elements.length;

			this.fillLevel++;
		} else {
			throw new IndexOutOfBoundsException("Queue is full!");
		}

	}

	/**
	 * @return the maximum size of the queue
	 */
	public int getCapacity() {
		return this.elements.length;
	}

	/**
	 * @return the fill level of the queue.
	 */
	public int getSize() {
		return this.fillLevel;
	}

}