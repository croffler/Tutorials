package gs.tutorial.firstgrid.remote;

import java.util.UUID;

import gs.tutorial.domain.po.EPurchaseOrderState;
import gs.tutorial.domain.po.EPurchaseOrderType;
import gs.tutorial.domain.po.PurchaseOrder;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

public class SpaceClient {

	// Remote Space
	static String url = "jini://*/*/mySpace";

	public static void main(String[] args) throws InterruptedException {

		// Connect to the Remote Space
		GigaSpace gigaSpace = new GigaSpaceConfigurer(new UrlSpaceConfigurer(
				url)).gigaSpace();

		System.out.println("Space is embedded :"
				+ gigaSpace.getSpace().isEmbedded());

		// Create a PO
		PurchaseOrder po = new PurchaseOrder();
		po.setId(UUID.randomUUID());
		po.setOrderState(EPurchaseOrderState.NEW);
		po.setOrderType(EPurchaseOrderType.DROPSHIPMENT);
		po.setNumber("X233FF");

		// Write the PO to the Space
		gigaSpace.write(po);

		// Read the PO from Space by its id
		PurchaseOrder p = gigaSpace.readById(PurchaseOrder.class, po.getId());
		System.out.println(p);
	}
}
