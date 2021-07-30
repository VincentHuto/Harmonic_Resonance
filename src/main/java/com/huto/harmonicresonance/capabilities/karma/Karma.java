package com.huto.harmonicresonance.capabilities.karma;

public class Karma implements IKarma {
	private int Karma = 0;
	private boolean active = false;

	public void subtractKarma(int points) {
		this.Karma -= points;
	}

	public void addKarma(int points) {
		this.Karma += points;
	}

	public void setKarma(int points) {
		this.Karma = points;
	}

	public int getKarma() {
		return this.Karma;
	}

	public boolean isKarmaGood() {
		if (getKarma() >= 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void setActive(boolean set) {
		this.active = set;
	}

	@Override
	public boolean isActive() {
		return active;
	}

}
