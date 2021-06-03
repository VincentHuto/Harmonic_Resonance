package com.huto.harmonicresonance.capabilities.karma;

public interface IKarma {

	public boolean isActive();
	
	public void subtractKarma(int points);

	public void addKarma(int points);

	public void setKarma(int points);

	public int getKarma();

	public void setActive(boolean set);
	
	public boolean isKarmaGood();
}
