//just using this class to check out what our test java files really look like
public class Class2 {
	public static void main(String[] args) {		
        if(args[1].equals("hello")) // {
        	System.out.println();
	    }
    }
}
// The last } is read, but the stack is empty.  Thus, nothing is expected.
public class Class3 {
	public static void main(String[] args) {
		/* Note that
		 * { is allowed in comments.
		 */
        if(args[1].equals("hello")) {
        	System.out.println();
	    }
    }
}