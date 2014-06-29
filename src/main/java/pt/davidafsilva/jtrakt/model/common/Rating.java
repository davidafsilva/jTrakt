package pt.davidafsilva.jtrakt.model.common;

import pt.davidafsilva.jtrakt.model.common.BaseModel;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public class Rating extends BaseModel {

	private double percentage;
	private int votes;
	private int loved;
	private int hated;

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public int getLoved() {
		return loved;
	}

	public void setLoved(int loved) {
		this.loved = loved;
	}

	public int getHated() {
		return hated;
	}

	public void setHated(int hated) {
		this.hated = hated;
	}
}

