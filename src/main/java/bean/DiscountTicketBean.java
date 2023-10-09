package bean;

import java.util.Date;

public class DiscountTicketBean {
	private int discountTicketId;
	private String srcProductPattern;
	private int desProductId_fk;
	private int addProductid_fk;
	private int discountPrice;
	private int discountPricePercentage;
	private String discountTicketStatus;
	private Date discountTicketExpirationDate;
	public int getDiscountTicketId() {
		return discountTicketId;
	}
	public void setDiscountTicketId(int discountTicketId) {
		this.discountTicketId = discountTicketId;
	}
	public String getSrcProductPattern() {
		return srcProductPattern;
	}
	public void setSrcProductPattern(String srcProductPattern) {
		this.srcProductPattern = srcProductPattern;
	}
	public int getDesProductId_fk() {
		return desProductId_fk;
	}
	public void setDesProductId_fk(int desProductId_fk) {
		this.desProductId_fk = desProductId_fk;
	}
	public int getAddProductid_fk() {
		return addProductid_fk;
	}
	public void setAddProductid_fk(int addProductid_fk) {
		this.addProductid_fk = addProductid_fk;
	}
	public int getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}
	public int getDiscountPricePercentage() {
		return discountPricePercentage;
	}
	public void setDiscountPricePercentage(int discountPricePercentage) {
		this.discountPricePercentage = discountPricePercentage;
	}
	public String getDiscountTicketStatus() {
		return discountTicketStatus;
	}
	public void setDiscountTicketStatus(String discountTicketStatus) {
		this.discountTicketStatus = discountTicketStatus;
	}
	public Date getDiscountTicketExpirationDate() {
		return discountTicketExpirationDate;
	}
	public void setDiscountTicketExpirationDate(Date discountTicketExpirationDate) {
		this.discountTicketExpirationDate = discountTicketExpirationDate;
	}
}
