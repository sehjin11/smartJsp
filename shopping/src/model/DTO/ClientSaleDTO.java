package model.DTO;

import java.util.Date;

public class ClientSaleDTO {
	String memId;
	String memName;
	String memPhone;
	String prodNum;
	String prodName;
	String PurchaseNum;
	String PurchaseAddr;
	Date PurchaseDate;
	String PurchasePrice;
	String PurchaseQty;
	String ReceiverName;
	String ReceiverPhone;
	String deliveryNum;
	
	
	
	
	
	public String getDeliveryNum() {
		return deliveryNum;
	}
	public void setDeliveryNum(String deliveryNum) {
		this.deliveryNum = deliveryNum;
	}
	public String getPurchaseAddr() {
		return PurchaseAddr;
	}
	public void setPurchaseAddr(String purchaseAddr) {
		PurchaseAddr = purchaseAddr;
	}
	public Date getPurchaseDate() {
		return PurchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		PurchaseDate = purchaseDate;
	}
	public String getPurchasePrice() {
		return PurchasePrice;
	}
	public void setPurchasePrice(String purchasePrice) {
		PurchasePrice = purchasePrice;
	}
	public String getPurchaseQty() {
		return PurchaseQty;
	}
	public void setPurchaseQty(String purchaseQty) {
		PurchaseQty = purchaseQty;
	}
	public String getReceiverName() {
		return ReceiverName;
	}
	public void setReceiverName(String receiverName) {
		ReceiverName = receiverName;
	}
	public String getReceiverPhone() {
		return ReceiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		ReceiverPhone = receiverPhone;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public String getProdNum() {
		return prodNum;
	}
	public void setProdNum(String prodNum) {
		this.prodNum = prodNum;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getPurchaseNum() {
		return PurchaseNum;
	}
	public void setPurchaseNum(String purchaseNum) {
		PurchaseNum = purchaseNum;
	}
	
	
}
