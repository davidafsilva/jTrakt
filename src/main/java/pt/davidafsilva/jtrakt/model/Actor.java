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
}
