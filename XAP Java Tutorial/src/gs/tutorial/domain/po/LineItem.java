package gs.tutorial.domain.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class LineItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;

	private int quantity;

	private BigDecimal cost;

	public LineItem() {
		id = UUID.randomUUID();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "LineItem [quantity=" + quantity + ", cost=" + cost + "]";
	}
}
