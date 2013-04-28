package gs.tutorial.domain.po;

public enum EPurchaseOrderType {
	NORMAL(1), DROPSHIPMENT(2), BLANKET(3), RELEASE(4);

	private int state;

	EPurchaseOrderType(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}
}