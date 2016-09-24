package util;

/**
 * User: xinyuwan, Date: 11/28/13, Time: 4:07 PM
 */
public interface StackInterface<T>
{
    /** Task: add newEntry to stack top
     *  @param newEntry */
    public void push(T newEntry);

    /** Task: remove and return stack top
     *  @return the object of stack top,
     *  if stack is empty, return null */
    public T pop();

    /** Task: return stack top
     *  @return the object of stack top,
     *  if stack is empty, return null*/
    public T peek();

    /** Task: check if stack is empty
     *  @return if is empty, return true */
    public boolean isEmpty();

    /** Task: remove all entries from stack */
    public void clear();
    public int size();
}

