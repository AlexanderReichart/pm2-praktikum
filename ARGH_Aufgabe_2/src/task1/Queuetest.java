package task1;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test suite for ring-buffer queue implementation.
 *
 * Group: ARGH
 *
 * @author Alexander Reichart {@literal <alexander.reichart@haw-hamburg.de>}
 * @author Gerriet Hinrichs {@literal <gerriet.hinrichs@web.de>}
 */
public class Queuetest {

	/**
	 * Testing exception for trying to deqeue from empty queue
	 */
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * Testing If capacity levels are reported correctly
	 */
	@Test
	public void capacitySize() {
		final int capacity = 5;
		final Queue<String> queue = new Queue<String>(capacity);
		Assert.assertTrue(capacity == queue.getCapacity());
		Assert.assertFalse(capacity - 1 == queue.getCapacity());
	}

	/**
	 * Testing If size increases and decreases properly with enqueue and dequeue
	 */
	@Test
	public void fillLevel() {
		final int capacity = 3;
		final Queue<String> queue = new Queue<String>(capacity);
		Assert.assertTrue(0 == queue.getSize());
		queue.enqueue("A");
		Assert.assertTrue(1 == queue.getSize());
		queue.enqueue("B");
		Assert.assertTrue(2 == queue.getSize());
		queue.enqueue("C");
		Assert.assertTrue(3 == queue.getSize());
		queue.dequeue();
		Assert.assertTrue(2 == queue.getSize());
		queue.dequeue();
		Assert.assertTrue(1 == queue.getSize());
		queue.dequeue();
		Assert.assertTrue(0 == queue.getSize());
	}

	@Test
	public void outOfBoundEmpty() {
		final Queue<String> queue = new Queue<String>(1);
		this.thrown.expect(IndexOutOfBoundsException.class);
		this.thrown.expectMessage("Queue is empty!");
		queue.dequeue();
	}

	/**
	 * Testing exception for trying to enqueue into a full queue
	 */
	@Test
	public void outOfBoundsFull() {
		final Queue<String> queue = new Queue<String>(1);
		this.thrown.expect(IndexOutOfBoundsException.class);
		this.thrown.expectMessage("Queue is full!");
		queue.enqueue("A");
		queue.enqueue("B");
	}

	/**
	 * Testing If elements are returned in proper order with enqueueing and
	 * dequeueing mixed.
	 */
	@Test
	public void retrievalTest() {
		final int capacity = 3;
		final Queue<String> queue = new Queue<String>(capacity);
		queue.enqueue("A");
		queue.enqueue("B");
		queue.enqueue("C");
		Assert.assertTrue("A" == queue.dequeue());
		Assert.assertTrue("B" == queue.dequeue());
		queue.enqueue("D");
		queue.enqueue("E");
		Assert.assertTrue("C" == queue.dequeue());
		Assert.assertTrue("D" == queue.dequeue());
		Assert.assertTrue("E" == queue.dequeue());
		queue.enqueue("F");
		queue.enqueue("G");
		Assert.assertTrue("F" == queue.dequeue());
		Assert.assertTrue("G" == queue.dequeue());
	}

	/**
	 * testing exception on generation of size 0 queue, suppressing "unused"
	 * warning because this queue isn't going to be used.
	 */
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testCapacityException() {
		final Queue<String> queue = new Queue<String>(0);
	}

}
