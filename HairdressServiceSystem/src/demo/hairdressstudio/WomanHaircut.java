package demo.hairdressstudio;

public class WomanHaircut implements Service{
	
	private String name;
	private long duration;
	private double price ;
	
	public WomanHaircut(String name, long duration, double price){
		this.name = name;
		this.duration = duration;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
