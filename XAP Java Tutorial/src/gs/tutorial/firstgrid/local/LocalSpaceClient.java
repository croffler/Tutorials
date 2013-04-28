package gs.tutorial.firstgrid.local;

import java.util.UUID;

import gs.tutorial.domain.po.EPurchaseOrderState;
import gs.tutorial.domain.po.EPurchaseOrderType;
import gs.tutorial.domain.po.PurchaseOrder;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;
import org.openspaces.core.space.cache.LocalCacheSpaceConfigurer;

public class LocalSpaceClient {

	// Remote Space
	static String url = "jini://*/*/mySpace";

	public static void main(String[] args) throws InterruptedException {

		UrlSpaceConfigurer urlConfigurer = new UrlSpaceConfigurer(url);

		// Connect to remote space
		GigaSpace remoteSpace = new GigaSpaceConfigurer(urlConfigurer)
				.gigaSpace();

		// Create local space
		GigaSpace localCache = new GigaSpaceConfigurer(
				new LocalCacheSpaceConfigurer(urlConfigurer)).gigaSpace();

		// Create a PO
		PurchaseOrder po = new PurchaseOrder();
		po.setId(UUID.randomUUID());
		po.setOrderState(EPurchaseOrderState.NEW);
		po.setOrderType(EPurchaseOrderType.BLANKET);
		po.setNumber("X233FF");

		// Write the PO to the Space
		remoteSpace.write(po);

		// Read the PO from Space
		PurchaseOrder p = localCache.readById(PurchaseOrder.class, po.getId());
		System.out.println(p);
	}
}
