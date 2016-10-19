package pojo;

public class TreeVertex {

	private String value;
	private Object Vertex;
	/**
	 * @param value
	 * @param vertex
	 */
	public TreeVertex(String value, Object vertex) {
		super();
		this.value = value;
		Vertex = vertex;
	}

	public Object getVertex() {
		return Vertex;
	}

	public void setVertex(Object vertex) {
		Vertex = vertex;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
