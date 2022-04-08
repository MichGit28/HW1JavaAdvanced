package core;

import utils.*;
import java.util.ArrayList;
import java.util.Arrays;

import utils.*;

/**
 * @author Hadas Racah AND Michel Hawa
 * This class represents the Sponsor of a Team
 * that funds Teams
 * A Sponsor can fund only three Teams.
 * The class includes surname of the Sponsor and teams funded
 */

public class Sponsor extends Person implements Popularity{

	private String surName; //the sponsor company
	private Fund[] teamsFunded = new Fund [Constants.sponseredTeams];

	/**
	 *
	 * @param pID
	 * @param pFullName
	 * @param age
	 * @param nation
	 * @param surName
	 */
	public Sponsor(String pID, String pFullName, short age, String nation, String surName) {
		super(pID, pFullName, age, nation);
		this.surName = surName;
	}

	/**
	 *
	 * @param pID
	 * @param surName
	 */
	public Sponsor(String pID, String surName) {
		super(pID);
		this.surName=surName;
	}

	public String getKey(){
		return this.pID;
	}



	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}



	public Fund[] getTeamsFunded() {
		return teamsFunded;
	}

	public void setTeamsFunded(Fund[] teamsFunded) {
		this.teamsFunded = teamsFunded;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((surName == null) ? 0 : surName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Sponsor))
			return false;
		Sponsor other = (Sponsor) obj;
		if (surName == null) {
			if (other.surName != null)
				return false;
		} else if (!surName.equals(other.surName))
			return false;
		return true;
	}



	/**
	 * the method add a funded team to this sponsor
	 * IFF the team does not exist in the array
	 * @param fund
	 * @return true if managed to add
	 */
	public boolean addFundedTeam(Fund fund){
		for (int i=0; i<this.teamsFunded.length; i++) {
			if(this.teamsFunded[i] != null) {
				if(this.teamsFunded[i].equals(fund)) {
					return false;
				}
			}
		}
		for (int i=0; i<this.teamsFunded.length; i++) {
			if(this.teamsFunded[i] == null) {
				this.teamsFunded[i] = fund;
				return true;
			}
		}
		return false;

	}


	/**
	 *  the method removes the funded team from the array of this sponsor IFF it exist
	 * @param fund
	 * @return  true if successfully removed
	 */
	public boolean removeFundedTeam(Fund fund){
		ArrayList<Fund> f = new ArrayList<Fund>(Arrays.asList(teamsFunded));
		if(f.contains(fund)) {
			f.remove(fund);
			teamsFunded=f.toArray(teamsFunded);
			return true;
		}
		teamsFunded=f.toArray(teamsFunded);
		return false;
	}

	@Override
	public String toString() {
		return "sponsor = sponsor [surname=" + surName +"]"+super.toString();
	}

	public double calcPopularity() {
		double totalSum=0;
		for (Fund f : teamsFunded) {
			totalSum+=f.getAmount();
		}
		return totalSum*teamsFunded.length;
	}


}
