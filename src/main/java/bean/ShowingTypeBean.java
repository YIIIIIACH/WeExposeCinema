package bean;

public class ShowingTypeBean {
	private int showingTypeId;
	private String showingTypeName;
	private int productId_fk;
	public int getShowingTypeId() {
		return showingTypeId;
	}
	public void setShowingTypeId(int showingTypeId) {
		this.showingTypeId = showingTypeId;
	}
	public String getShowingTypeName() {
		return showingTypeName;
	}
	public void setShowingTypeName(String showingTypeName) {
		this.showingTypeName = showingTypeName;
	}
	public int getProductId_fk() {
		return productId_fk;
	}
	public void setProductId_fk(int productId_fk) {
		this.productId_fk = productId_fk;
	}
}
