package gs.tutorial.firstgrid.remote;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

import com.j_spaces.core.IJSpace;

public class RemoteSpace {

	static String url = "/./mySpace";

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] arg) throws InterruptedException {

		IJSpace space = new UrlSpaceConfigurer(url).space();
		@SuppressWarnings("unused")
		GigaSpace gigaSpace = new GigaSpaceConfigurer(space).gigaSpace();

		System.out.println("Remote Space : 'mySpace' is running ");

		Thread.sleep(Integer.MAX_VALUE); 
		
	}
}
