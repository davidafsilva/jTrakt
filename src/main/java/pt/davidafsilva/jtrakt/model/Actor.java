package pt.davidafsilva.jtrakt.model;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public class Actor extends BaseModel {

	private String name;
	private String caracter;
	private String pictureUrl;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getCaracter() {
		return caracter;
	}

	public void setCaracter(final String caracter) {
		this.caracter = caracter;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(final String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		final Actor actor = (Actor) o;

		if (!caracter.equals(actor.caracter)) {
			return false;
		}
		if (!name.equals(actor.name)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + caracter.hashCode();
		return result;
	}
}
