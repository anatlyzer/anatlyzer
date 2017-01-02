package anatlyzer.experiments.export;


public class Category implements Comparable<Category> {

	protected String name;

	public Category(String categoryName) {
		this.name = categoryName;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return name;
	}
	
	//
	// Hash code & equals
	//
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Category o) {
		return name.compareTo(o.name);
	}


	
}
