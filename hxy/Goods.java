package hxy;

public class Goods {
	private String goodsId;
	private String goodsName;
	private String packStyle;
	private String measuerUnit;
	private String productArea;
	private int keepTime;
	private String description;
	private float price;
	public Goods(){
		
	}
	public Goods(
			String goodsId,
			String goodsName,
			String packStyle,
			String measureUnit,
			String productArea,
			int keepTime,
			String description,
			float price){
		setGoodsId(goodsId);
		setGoodsName(goodsName);
		setPackStyle(packStyle);
		setMeasureUnit(measureUnit);
		setProductArea(productArea);
		setKeepTime(keepTime);
		setDeacription(description);
		setPrice(price);
	}
	
	public void setGoodsId(String goodsId){
		this.goodsId = goodsId;
	}
	public void setGoodsName(String goodsName){
		this.goodsName = goodsName;
	}
	public void setPackStyle(String packStyle){
		this.packStyle = packStyle;
	}
	public void setMeasureUnit(String measureUnit){
		this.measuerUnit = measureUnit;
	}
	public void setProductArea(String productArea){
		this.productArea = productArea;
	}
	public void setKeepTime(int keepTime){
		this.keepTime = keepTime;
	}
	public void setDeacription(String description){
		this.description = description;
	}
	public void setPrice(float price){
		this.price = price;
	}
	public String getGoodsId(){
		return goodsId;
	}
	public String getGoodsName(){
		return goodsName;
	}
	public String getPackStyle(){
		return packStyle;
	}
	public String getMeasureUnit(){
		return measuerUnit;
	}
	public String getProductArea(){
		return productArea;
	}
	public int getKeepTime(){
		return keepTime;
	}
	public String getDeacription(){
		return description;
	}
	public float getPrice(){
		return price;
	}
}
