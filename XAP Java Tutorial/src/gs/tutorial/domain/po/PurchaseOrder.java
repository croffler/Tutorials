package gs.tutorial.domain.po;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;

@SpaceClass
public class PurchaseOrder {

	private UUID id;

	private EPurchaseOrderState orderState;

	private EPurchaseOrderType orderType;

	private String number;

	private BigDecimal totalCost;

	private Date orderDate;

	private Date shipDate;

	private Collection<LineItem> items;

	public PurchaseOrder() {
	}

	@SpaceId(autoGenerate = false)
	public UUID getId() {
		return id;
	}

	public boolean validate() {
		if (number == null || number.length() < 10) {
			return false;
		}
		return true;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public EPurchaseOrderState getOrderState() {
		return orderState;
	}

	public void setOrderState(EPurchaseOrderState orderState) {
		this.orderState = orderState;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public Collection<LineItem> getItems() {
		return items;
	}

	public void setItems(Collection<LineItem> items) {
		this.items = items;
	}

	public void addLineItem(LineItem item) {
		if (items == null)
			items = new ArrayList<LineItem>();

		items.add(item);
	}

	public EPurchaseOrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(EPurchaseOrderType orderType) {
		this.orderType = orderType;
	}

	@Override
	public String toString() {
		return "PurchaseOrder [id=" + id + ", orderState=" + orderState
				+ ", orderType=" + orderType + ", number=" + number
				+ ", totalCost=" + totalCost + ", orderDate=" + orderDate
				+ ", shipDate=" + shipDate + ", items=" + items + "]";
	}
}
