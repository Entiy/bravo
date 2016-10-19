package pojo;

public class Vertex {
	private String value;
	private String parent;
	private int SubCount=0;
	public int getSubcount() {
		return SubCount;
	}
	public void setSubcount(int subcount) {
		this.SubCount = subcount;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	@Override
	public String toString() {
		return "Vertex [value=" + value + ", parent=" + parent
				+ ", SubCount=" + SubCount + "]";
	}
}
