package util;

/**
 * User: xinyuwan, Date: 11/28/13, Time: 4:53 PM
 */
public interface QueueInterface<T>
{
    /** Task: insert newEntry to the back
     * end of queue
     * @param newEntry object to be inserted */
    public void enqueue(T newEntry);

    /** Task: remove and return the object
     * at the front end
     * @return object that at the front end
     * if queue is empty, return null;
     */
    public T dequeue();

    /** Task: get the object at front end
     * @return object that at the front end
     * if queue is empty, return null;
     */
    public T getFront();

    /** Task: Check if the queue is empty
     *  @return if queue is empty, return true */
    public boolean isEmpty();
    public int size();
    /** Task: remove all elements in queue */
    public void clear();
}
