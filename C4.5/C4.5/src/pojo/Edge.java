package pojo;


public class Edge {
	private String value;
	private String source;
	private String target;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	@Override
	public String toString() {
		return "Edge [value=" + value + ", source=" + source + ", target="
				+ target + "]";
	}
}
