import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/*
 * This is a compilation test, it passes if it compiles. Running
 * the test is not necessary, and may not actually pass when you
 * run it.
 */
public class CompilationTest {

	@SuppressWarnings("unused")
	@Test
	public void test() {
		
		
		BST<String> bst = new BST<>();
		System.out.println("/////////created bst///////////");
		bst.add("");
		System.out.println("////////added element///////////");
		display(bst);
		bst.addAll(new ArrayList<String>());
		System.out.println("////////added all//////////////");
		display(bst);
		bst.clear();
		System.out.println("///////cleared///////////////");
		display(bst);
		boolean b = bst.contains("");
		BST.Node<String> r = bst.getRoot();
		List<String> l = bst.inOrder();
		l = bst.preOrder();
		l = bst.postOrder();
		b = bst.isEmpty();
		bst.reconstruct(l, l);
		System.out.println("////////reconstruct//////////");
		display(bst);
		String s = bst.remove("");
		bst.setRoot(r);
		bst.setSize(0);
		int i = bst.size();

	}
	public void display(BST little)
	{
		
		List<String> stuff = little.inOrder();
		
		for (String uss: stuff){
			
			System.out.print(uss + "\t");
		}
	}
	

}
