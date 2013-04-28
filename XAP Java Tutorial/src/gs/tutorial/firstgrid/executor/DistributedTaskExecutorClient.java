package gs.tutorial.firstgrid.executor;

import gs.tutorial.domain.po.EPurchaseOrderState;
import gs.tutorial.domain.po.EPurchaseOrderType;
import gs.tutorial.domain.po.LineItem;
import gs.tutorial.domain.po.PurchaseOrder;
import gs.tutorial.firstgrid.executor.task.PODistributedTask;

import java.math.BigDecimal;
import java.util.UUID;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

import com.gigaspaces.async.AsyncFuture;

public class DistributedTaskExecutorClient {

	// Remote Space
	static String url = "jini://*/*/mySpace";

	public static void main(String[] args) {

		// Connect to the remote space
		GigaSpace gigaSpace = new GigaSpaceConfigurer(new UrlSpaceConfigurer(
				url)).gigaSpace();

		try {
			PurchaseOrder po = new PurchaseOrder();
			po.setId(UUID.randomUUID());
			po.setOrderState(EPurchaseOrderState.NEW);
			po.setOrderType(EPurchaseOrderType.BLANKET);
			po.setNumber("X23456787890");
			
			LineItem item = new LineItem();
			item.setCost(new BigDecimal(100.00));
			po.addLineItem(item);
			
			gigaSpace.write(po);
			

			po = new PurchaseOrder();
			po.setId(UUID.randomUUID());
			po.setOrderState(EPurchaseOrderState.PROCESSED);
			po.setOrderType(EPurchaseOrderType.DROPSHIPMENT);
			po.setNumber("X2345");
			
			LineItem item1 = new LineItem();
			item1.setCost(new BigDecimal(400.00));
			po.addLineItem(item1);
			gigaSpace.write(po);

			PODistributedTask task = new PODistributedTask();

			AsyncFuture<BigDecimal> future = gigaSpace.execute(task,10);
			System.out.println(future.get());

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
