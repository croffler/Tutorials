package gs.tutorial.firstgrid.executor.task;

import gs.tutorial.domain.po.EPurchaseOrderType;
import gs.tutorial.domain.po.LineItem;
import gs.tutorial.domain.po.PurchaseOrder;

import java.math.BigDecimal;
import java.util.List;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.executor.DistributedTask;
import org.openspaces.core.executor.TaskGigaSpace;

import com.gigaspaces.annotation.pojo.SpaceRouting;
import com.gigaspaces.async.AsyncResult;

public class PODistributedTask implements
		DistributedTask<BigDecimal, BigDecimal> {

	private static final long serialVersionUID = 1L;

	@TaskGigaSpace
	private transient GigaSpace gigaSpace;


	@Override
	public BigDecimal execute() throws Exception {

		PurchaseOrder template = new PurchaseOrder();

		BigDecimal sum = BigDecimal.ZERO;
		
		// Sum up cost of PO Items
		for (PurchaseOrder po : gigaSpace.readMultiple(template)) {
			for (LineItem item : po.getItems()) {
				sum = sum.add(item.getCost());
			}
		}
		return sum;
	}

	@Override
	public BigDecimal reduce(List<AsyncResult<BigDecimal>> results)
			throws Exception {
		
		BigDecimal sum = BigDecimal.ZERO;

		for (AsyncResult<BigDecimal> result : results) {
			if (result.getException() != null) {
				throw result.getException();
			}
			sum = sum.add(result.getResult());
		}
		return sum;
	}
}