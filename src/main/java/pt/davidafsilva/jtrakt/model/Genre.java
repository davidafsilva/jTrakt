package pt.davidafsilva.jtrakt.model;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public class Genre extends BaseModel {

	private String name;
	private String slung;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlung() {
		return slung;
	}

	public void setSlung(String slung) {
		this.slung = slung;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Genre genre = (Genre) o;

		if (!name.equals(genre.name)) {
			return false;
		}
		if (!slung.equals(genre.slung)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + slung.hashCode();
		return result;
	}
}
