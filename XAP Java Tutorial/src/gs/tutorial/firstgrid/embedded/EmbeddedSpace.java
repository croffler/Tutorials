package gs.tutorial.firstgrid.embedded;

import java.util.UUID;

import gs.tutorial.domain.po.EPurchaseOrderState;
import gs.tutorial.domain.po.PurchaseOrder;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

public class EmbeddedSpace {

	// Embedded Space
	static String url = "/./mySpace";

	public static void main(String[] args) throws InterruptedException {

		// Create the Space
		GigaSpace gigaSpace = new GigaSpaceConfigurer(new UrlSpaceConfigurer(
				url)).gigaSpace();

		System.out.println("Space is embedded :"
				+ gigaSpace.getSpace().isEmbedded());

		// Create a PO
		PurchaseOrder po = new PurchaseOrder();
		po.setId(UUID.randomUUID());
		po.setOrderState(EPurchaseOrderState.NEW);
		po.setNumber("X233FF");

		// Write the PO to the Space
		gigaSpace.write(po);

		// Read the PO from Space
		PurchaseOrder p = gigaSpace.readById(PurchaseOrder.class, po.getId());
		System.out.println(p);

		System.exit(1);
	}
}
