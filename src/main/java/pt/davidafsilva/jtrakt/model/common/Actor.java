package pt.davidafsilva.jtrakt.model.common;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public class Actor extends BaseModel {

	private String name;
	private String character;
	private String pictureUrl;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(final String character) {
		this.character = character;
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

		if (!character.equals(actor.character)) {
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
		result = 31 * result + character.hashCode();
		return result;
	}
}
