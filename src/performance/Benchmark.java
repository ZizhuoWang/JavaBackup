package performance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;


/**
 * @author wang
 * The benchmark class
 */
public class Benchmark {
	
	/**
	 * The maximum size of ArrayList, LinkedList and Vector
	 */
	private final int MAX_SIZE = 100000;
	private ArrayList<Integer> arrayList;
	private LinkedList<Integer> linkedList;
	private Vector<Integer> vector;
	
	
	/**
	 * @return A random number from 0 to 999
	 */
	public static int random(){
		return (int)(Math.random()*1000);
	}
	
	/**
	 * Initialize
	 */
	private void init(){
		for(int i=0;i<MAX_SIZE;i++){
			arrayList.add(random());
			linkedList.add(random());
			vector.addElement(random());
		}
	}
	/**
	 * Constructor
	 * @see init()
	 */
	public Benchmark() {
		this.arrayList = new ArrayList<Integer>();
		this.linkedList = new LinkedList<Integer>();
		this.vector = new Vector<Integer>();
		init();
	}
	/**
	 * Test the speed of getting from random index
	 * @see random()
	 */
	private void testGet() {
		System.gc();

		long time1 = System.currentTimeMillis();
		for(int i=0;i<MAX_SIZE;i++)
			arrayList.get(random());
		long time2 = System.currentTimeMillis();

		long time3 = System.currentTimeMillis();
		for(int i=0;i<MAX_SIZE;i++)
			linkedList.get(random());
		long time4 = System.currentTimeMillis();

		long time5 = System.currentTimeMillis();
		for(int i=0;i<MAX_SIZE;i++)
			vector.get(random());
		long time6 = System.currentTimeMillis();

		System.out.println("ArrayList Get:"+(time2-time1));
		System.out.println("LinkedList Get:"+(time4-time3));
		System.out.println("Vector Get:"+(time6-time5)+"\n");
	}
	/**
	 * Test the iterator's iteration speed
	 */
	private void testIteration(){
		System.gc();
		Iterator<Integer> iteratorArr = arrayList.listIterator();
		Iterator<Integer> iteratorLin = linkedList.listIterator();
		Iterator<Integer> iteratorVec = vector.listIterator();

		long time1 = System.currentTimeMillis();
		while(iteratorArr.hasNext()){
			iteratorArr.next();
		}
		long time2 = System.currentTimeMillis();

		long time3 = System.currentTimeMillis();
		while(iteratorLin.hasNext()){
			iteratorLin.next();
		}
		long time4 = System.currentTimeMillis();

		long time5 = System.currentTimeMillis();
		while(iteratorVec.hasNext())
			iteratorVec.next();
		long time6 = System.currentTimeMillis();

		System.out.println("ArrayList Iteration:"+(time2-time1));
		System.out.println("LinkedList Iteration:"+(time4-time3));
		System.out.println("Vector Iteration:"+(time6-time5)+"\n");
	}
	
	/**
	 * Insert from last index
	 * Can also change the ".size()" to 0 to insert from the first index
	 */
	private void testInsert(){
		System.gc();

		long time1 = System.currentTimeMillis();
		for(int i=0;i<MAX_SIZE;i++){
			arrayList.add(random(),0);
		}
		long time2 = System.currentTimeMillis();

		long time3 = System.currentTimeMillis();
		for(int i=0;i<MAX_SIZE;i++){
			linkedList.add(random(),0);
		}
		long time4 = System.currentTimeMillis();

		long time5 = System.currentTimeMillis();
		for(int i=0;i<MAX_SIZE;i++){
			vector.add(random(),0);
		}
		long time6 = System.currentTimeMillis();

		System.out.println("ArrayList Insertion:"+(time2-time1));
		System.out.println("LinkedList Insertion:"+(time4-time3));
		System.out.println("Vector Insertion:"+(time6-time5)+"\n");
	}
	/**
	 * Delete from the first element
	 */
	private void testDelete(){
		System.gc();

		long time1 = System.currentTimeMillis();
		for(int i=0;i<arrayList.size();i++){
			arrayList.remove(arrayList.size()-1);
		}
		long time2 = System.currentTimeMillis();

		long time3 = System.currentTimeMillis();
		for(int i=0;i<linkedList.size();i++){
			linkedList.remove(linkedList.size()-1);
		}
		long time4 = System.currentTimeMillis();

		long time5 = System.currentTimeMillis();
		for(int i=0;i<vector.size();i++){
			vector.remove(vector.size()-1);
		}
		long time6 = System.currentTimeMillis();

		System.out.println("ArrayList Delete:"+(time2-time1));
		System.out.println("LinkedList Delete:"+(time4-time3));
		System.out.println("Vector Delete:"+(time6-time5)+"\n");
	}

	/**
	 * @param args
	 * Test Get, Iteration, Insert, Delete
	 */
	public static void main(String[] args) {
		Benchmark benchmark = new Benchmark();
//		benchmark.testGet();
//		benchmark.testIteration();
		benchmark.testInsert();
//		benchmark.testDelete();
	}

}
