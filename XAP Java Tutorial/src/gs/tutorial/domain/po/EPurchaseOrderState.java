package gs.tutorial.domain.po;

public enum EPurchaseOrderState {
	NEW(1), ONHOLD(2), SHIPPED(3), DELIVERED(4), PROCESSED(5);

	private int state;

	EPurchaseOrderState(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}
}