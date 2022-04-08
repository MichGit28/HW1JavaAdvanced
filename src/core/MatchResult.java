package core;
import utils.*;

/**
 * @author Hadas Racah AND Michel Hawa
 * This class represents the results of a match.
 * The class includes the teams in a match and the match itself,
 * the total time of the match, if there was penalties at the end,
 * teams goals, yellow cards and red cards.
 */

public class MatchResult {
	private Team teamA;
	private Team teamB;
	private Match match; //the game that both of teams participated in
	private int totalTime; //the length of the game in full minutes
	private boolean penaltyEnd; //true is the game ended with
	private int tAgoals; //total team A goals
	private int tBgoals; //total team B goals
	private short tAYellowCards; //total team A yellow cards
	private short tBYellowCards; //total team B yellow cards
	private short tARedCards; //total team A red cards
	private short tBRedCards; //total team B red cards

	/**
	 * @param teamA
	 * @param teamB
	 * @param match
	 * @param totalTime
	 * @param tAgoals
	 * @param tBgoals
	 * @param penaltyEnd
	 * @param tAYellowCards
	 * @param tBYellowCards
	 * @param tARedCards
	 * @param tBRedCards
	 */
	public MatchResult(Team teamA, Team teamB, Match match, int totalTime, int tAgoals, int tBgoals,boolean penaltyEnd,
					   short tAYellowCards, short tBYellowCards, short tARedCards, short tBRedCards) {
		this.teamA = teamA;
		this.teamB = teamB;
		this.match = match;
		this.totalTime = totalTime;
		this.penaltyEnd = penaltyEnd;
		this.tAgoals = tAgoals;
		this.tBgoals = tBgoals;
		this.tAYellowCards = tAYellowCards;
		this.tBYellowCards = tBYellowCards;
		this.tARedCards = tARedCards;
		this.tBRedCards = tBRedCards;
		this.teamA.updateTeamProperties(tAgoals, tBgoals, tAYellowCards, tARedCards);
		this.teamB.updateTeamProperties(tBgoals, tAgoals, tBYellowCards, tBRedCards);
	}

	/**
	 *
	 * @return teamA
	 */
	public Team getTeamA() {
		return teamA;
	}

	/**
	 *
	 * @param teamA
	 */
	public void setTeamA(Team teamA) {
		this.teamA = teamA;
	}

	/**
	 *
	 * @return teamB
	 */
	public Team getTeamB() {
		return teamB;
	}

	/**
	 * @param teamB
	 */
	public void setTeamB(Team teamB) {
		this.teamB = teamB;
	}

	/**
	 *
	 * @return match
	 */
	public Match getMatch() {
		return match;
	}

	/**
	 *
	 * @param match
	 */
	public void setMatch(Match match) {
		this.match = match;
	}

	/**
	 *
	 * @return totalTime
	 */
	public int getTotalTime() {
		return totalTime;
	}

	/**
	 * @param totalTime
	 */
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	/**
	 *
	 * @return penaltyEnd
	 */
	public boolean isPenaltyEnd() {
		return penaltyEnd;
	}

	/**
	 *
	 * @param penaltyEnd
	 */
	public void setPenaltyEnd(boolean penaltyEnd) {
		this.penaltyEnd = penaltyEnd;
	}

	/**
	 *
	 * @return tAgoals
	 */
	public int gettAgoals() {
		return tAgoals;
	}

	/**
	 *
	 * @param tAgoals
	 */
	public void settAgoals(int tAgoals) {
		this.tAgoals = tAgoals;
	}

	/**
	 * @return tBgoals
	 */
	public int gettBgoals() {
		return tBgoals;
	}

	/**
	 *
	 * @param tBgoals
	 */
	public void settBgoals(int tBgoals) {
		this.tBgoals = tBgoals;
	}

	/**
	 *
	 * @return tAYellowCards
	 */
	public short gettAYellowCards() {
		return tAYellowCards;
	}

	/**
	 *
	 * @param tAYellowCards
	 */
	public void settAYellowCards(short tAYellowCards) {
		this.tAYellowCards = tAYellowCards;
	}

	/**
	 *
	 * @return tBYellowCards
	 */
	public short gettBYellowCards() {
		return tBYellowCards;
	}

	/**
	 *
	 * @param tBYellowCards
	 */
	public void settBYellowCards(short tBYellowCards) {
		this.tBYellowCards = tBYellowCards;
	}

	/**
	 *
	 * @return tARedCards
	 */
	public short gettARedCards() {
		return tARedCards;
	}

	/**
	 *
	 * @param tARedCards
	 */
	public void settARedCards(short tARedCards) {
		this.tARedCards = tARedCards;
	}

	/**
	 *
	 * @return tBRedCards
	 */
	public short gettBRedCards() {
		return tBRedCards;
	}

	/**
	 * @param tBRedCards
	 */
	public void settBRedCards(short tBRedCards) {
		this.tBRedCards = tBRedCards;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((match == null) ? 0 : match.hashCode());
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
		MatchResult other = (MatchResult) obj;
		if (match == null) {
			if (other.match != null)
				return false;
		} else if (!match.equals(other.match))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return match+", Results: \n" + teamA.gettName() +"\t"+ teamB.gettName() + "\n    "+tAgoals +"      "+ tBgoals;
	}






}
