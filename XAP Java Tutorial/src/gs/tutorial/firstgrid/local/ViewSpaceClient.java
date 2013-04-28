package gs.tutorial.firstgrid.local;

import gs.tutorial.domain.po.EPurchaseOrderState;
import gs.tutorial.domain.po.PurchaseOrder;

import java.util.UUID;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;
import org.openspaces.core.space.cache.LocalViewSpaceConfigurer;

import com.j_spaces.core.client.SQLQuery;

public class ViewSpaceClient {

	// Remote Space
	static String url = "jini://*/*/mySpace";

	public static void main(String[] args) throws InterruptedException {

		UrlSpaceConfigurer urlConfigurer = new UrlSpaceConfigurer(url);

		// Connect the Remote Space
		GigaSpace remoteSpace = new GigaSpaceConfigurer(urlConfigurer)
				.gigaSpace();

		// Create the view criteria for the local view
		SQLQuery<PurchaseOrder> sqlQuery = new SQLQuery<PurchaseOrder>(
				PurchaseOrder.class, "orderState = ?");
		sqlQuery.setParameter(1, EPurchaseOrderState.PROCESSED);

		LocalViewSpaceConfigurer localViewConfigurer = new LocalViewSpaceConfigurer(
				urlConfigurer).addViewQuery(sqlQuery);

		// Create local view:
		GigaSpace localView = new GigaSpaceConfigurer(localViewConfigurer)
				.gigaSpace();

		// Create a PO
		PurchaseOrder po = new PurchaseOrder();
		po.setId(UUID.randomUUID());
		po.setOrderState(EPurchaseOrderState.NEW);
		po.setNumber("X233FF");

		// Write the PO to the Space
		remoteSpace.write(po);

		// Create a PO
		PurchaseOrder po1 = new PurchaseOrder();
		po1.setNumber("X233XX");
		po1.setId(UUID.randomUUID());
		po1.setOrderState(EPurchaseOrderState.PROCESSED);

		// Write the PO to the Space
		remoteSpace.write(po1);

		// Let XAP execute the writes
		Thread.sleep(1000);

		// Read by template from the localView
		PurchaseOrder p = new PurchaseOrder();
		PurchaseOrder list[] = localView.readMultiple(p);

		System.out.println(list);
	}
}
