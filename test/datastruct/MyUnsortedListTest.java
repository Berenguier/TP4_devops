package datastruct;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MyUnsortedListTest {
	private int N = 10;
    UnsortedList<Integer> list;

    @Before
    public void initialisation() {
        list = MyUnsortedList.of();
    }
	
    
	@Test
    public void testEmpty() {
		assertTrue(list.isEmpty());
		
		list.prepend(1);
		list.pop();
		assertTrue(list.isEmpty());
    }
	
	@Test
	public void testNotEmpty() {
		list.prepend(1);
		assertFalse(list.isEmpty());
	}
	
	
	@Test
	public void testSizePrependPop() {
		assertEquals(0, list.size());

		for (int i = 1; i <= N; i++) {
			list.prepend(i);
			assertEquals(i, list.size());
		}
		
		for (int i = N; i > 0; i--) {
			assertEquals(i, list.size());
			list.pop();
		}
		assertEquals(0, list.size());
	}
	
	@Test
	public void testSizeAppendPopLast() {
		assertEquals(0, list.size());

		for (int i = 1; i <= N; i++) {
			list.append(i);
			assertEquals(i, list.size());
		}
		
		for (int i = N; i > 0; i--) {
			assertEquals(i, list.size());
			list.popLast();
		}
		assertEquals(0, list.size());
	}
	
	@Test
	public void testSizeInsertRemoveLast() {
		assertEquals(0, list.size());

		for (int i = 1; i <= N; i++) {
			list.insert(i, i-1);
			assertEquals(i, list.size());
		}
		
		for (int i = N; i > 0; i--) {
			assertEquals(i, list.size());
			list.remove(i-1);
		}
		assertEquals(0, list.size());
	}
	
	
	private void assertListPrepend() {
		UnsortedList<Integer> expected = MyUnsortedList.of(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
		assertEquals(expected, list);
	}
	
	@Test
	public void testPrepend() {
		for (int i = 0; i < 10; i++) {
			list.prepend(i);
		}
		assertListPrepend();
	}
	
	@Test
	public void testPrependPopLast() {
		for (int i = 0; i < N; i++) {
			list.prepend(i);
			assertEquals(i, (int) list.popLast());
		}
		
		for (int i = 0; i < N; i++) {
			list.prepend(i);
		}
		
		for (int i = 0; i < N; i++) {
			assertEquals(i, (int) list.popLast());
		}
	}
	
	
	private void assertListAppend() {
		UnsortedList<Integer> expected = MyUnsortedList.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		assertEquals(expected, list);
	}
	
	@Test
	public void testAppend() {
		for (int i = 0; i < 10; i++) {
			list.append(i);
		}
		assertListAppend();
	}
	
	@Test
	public void testAppendPop() {
		for (int i = 0; i < N; i++) {
			list.append(i);
			assertEquals(i, (int) list.pop());
		}
		
		for (int i = 0; i < N; i++) {
			list.append(i);
		}
		
		for (int i = 0; i < N; i++) {
			assertEquals(i, (int) list.pop());
		}
	}
	
	
	@Test
	public void testInsertFirst() {
		for (int i = 0; i < 10; i++) {
			list.insert(i, 0);
		}
		assertListPrepend();
	}
	
	@Test
	public void testInsertFirstPopLast() {
		for (int i = 0; i < N; i++) {
			list.insert(i, 0);
		}
		for (int i = 0; i < N; i++) {
			assertEquals(i, (int) list.popLast());
		}
	}
	
	@Test
	public void testInsertLast() {
		for (int i = 0; i < 10; i++) {
			list.insert(i, list.size());
		}
		assertListAppend();
	}
	
	@Test
	public void testInsertLastPop() {
		for (int i = 0; i < N; i++) {
			list.insert(i, list.size());
		}
		for (int i = 0; i < N; i++) {
			assertEquals(i, (int) list.pop());
		}
	}
	
	@Test
	public void testInsert() {
		for (int i = 0; i < 10; i++) {
			list.insert(0, 0);
		}
		
		list.insert(1, 4);
		UnsortedList<Integer> expected = MyUnsortedList.of(0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0);
		assertEquals(expected, list);
		
		list.insert(2, 9);
		expected = MyUnsortedList.of(0, 0, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0);
		assertEquals(expected, list);
	}
	
	@Test
	public void testInsertPop() {
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j < N; j++) {
				list.insert(0, 0);
			}
			list.insert(1, i);

			for (int j = 0; j < i; j++) {
				assertEquals(0, (int) list.pop());
			}
			assertEquals(1, (int) list.pop());
			
			for (int j = i+1; j < N; j++) {
				assertEquals(0, (int) list.pop());
			}
		}	
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testInsertIndexOutOfBoundsExceptionNegative() {
		list.insert(1, -1);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testInsertIndexOutOfBoundsExceptionBiggerThanSize() {
		list.insert(1, list.size()+1);
	}
	
		
	@Test
	public void testPop() {
		list = MyUnsortedList.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		
		for (int i = 0; i < 5; i++) {
			assertEquals(i, (int) list.pop());
		}
		assertEquals(MyUnsortedList.of(5, 6, 7, 8, 9), list);
		
		for (int i = 5; i < 10; i++) {
			assertEquals(i, (int) list.pop());
		}
		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
	}
	
	@Test(expected=EmptyListException.class)
	public void testPopEmptyListException() {
		list.pop();
	}
	
		
	@Test
	public void testPopLast() {
		list = MyUnsortedList.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		
		for (int i = 9; i > 4; i--) {
			assertEquals(i, (int) list.popLast());
		}
		assertEquals(MyUnsortedList.of(0, 1, 2, 3, 4), list);
		
		for (int i = 4; i >= 0; i--) {
			assertEquals(i, (int) list.popLast());
		}
		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
	}
	
	@Test(expected=EmptyListException.class)
	public void testPopLastEmptyListException() {
		list.popLast();
	}
	
	
	@Test
	public void testRemoveFirst() {
		list = MyUnsortedList.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		
		for (int i = 0; i < 5; i++) {
			assertEquals(i, (int) list.remove(0));
		}
		assertEquals(MyUnsortedList.of(5, 6, 7, 8, 9), list);
		
		for (int i = 5; i < 10; i++) {
			assertEquals(i, (int) list.remove(0));
		}
		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
	}
	
	@Test
	public void testAppendRemoveFirst() {
		for (int i = 0; i < N; i++) {
			list.append(i);
		}
		for (int i = 0; i < N; i++) {
			assertEquals(i, (int) list.remove(0));
		}
	}
	
	@Test
	public void testRemoveLast() {
		list = MyUnsortedList.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		
		for (int i = 9; i > 4; i--) {
			assertEquals(i, (int) list.remove(list.size()-1));
		}
		assertEquals(MyUnsortedList.of(0, 1, 2, 3, 4), list);
		
		for (int i = 4; i >= 0; i--) {
			assertEquals(i, (int) list.remove(list.size()-1));
		}
		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
	}
	
	@Test
	public void testPrependRemoveLast() {
		for (int i = 0; i < N; i++) {
			list.prepend(i);
		}
		for (int i = 0; i < N; i++) {
			assertEquals(i, (int) list.remove(list.size()-1));
		}
	}
	
	@Test
	public void testRemove() {
		list = MyUnsortedList.of(0, 0, 0, 1, 0, 0, 0, 0, 2, 0);
		
		assertEquals(1, (int) list.remove(3));
		UnsortedList<Integer> expected = MyUnsortedList.of(0, 0, 0, 0, 0, 0, 0, 2, 0);
		assertEquals(expected, list);
		
		assertEquals(2, (int) list.remove(7));
		expected = MyUnsortedList.of(0, 0, 0, 0, 0, 0, 0, 0);
		assertEquals(expected, list);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveIndexOutOfBoundsExceptionEmpty() {
		list.remove(0);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveIndexOutOfBoundsExceptionNegative() {
		list.append(0);
		list.remove(-1);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveIndexOutOfBoundsExceptionSize() {
		list.append(0);
		list.remove(list.size());
	}
}
