package core;
import java.util.Date;

import utils.*;

/**
 * @author Hadas Racah AND Michel Hawa
 * This class represents the fund of a team that was given by a sponsor.
 * The class includes the amount of the fund,
 * the date that the fund was given, the sponsor details
 * and teams funded.
 */

public class Fund {
	private double amount;
	private Date fDate;
	private Sponsor sponsor; //the sponsor that brings the money
	private Team team; //the funded team


	/**
	 *
	 * @param amount
	 * @param fDate
	 * @param sponsor
	 * @param team
	 */
	public Fund(double amount, Date fDate, Sponsor sponsor, Team team) {
		this.amount = amount;
		this.fDate = fDate;
		this.sponsor = sponsor;
		this.team = team;
	}


	/**
	 *
	 * @return amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 *
	 * @return fDate
	 */
	public Date getfDate() {
		return fDate;
	}

	/**
	 *
	 * @return team
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 *
	 * @return sponsor
	 */
	public Sponsor getSponsor() {
		return sponsor;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((fDate == null) ? 0 : fDate.hashCode());
		result = prime * result + ((sponsor == null) ? 0 : sponsor.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
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
		Fund other = (Fund) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (fDate == null) {
			if (other.fDate != null)
				return false;
		} else if (!fDate.equals(other.fDate))
			return false;
		if (sponsor == null) {
			if (other.sponsor != null)
				return false;
		} else if (!sponsor.equals(other.sponsor))
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return  "Fund [amount= "+amount+", fDate="+fDate+",\n"+getSponsor()+", \nTeam="+getTeam()+"]]";
	}








}
