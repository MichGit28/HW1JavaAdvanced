package core;
import java.util.ArrayList;
import java.util.Arrays;

import utils.*;

/**
 * @author Hadas Racah AND Michel Hawa
 * This class represents the Team.
 * The class includes team id, team name,
 * teams country, the number of teams fans,
 * the stage qualified, goals scored/ goals against,
 * yellow and red cards, the players in the team,
 * results of all teams matches and the fund.
 */

public class Team implements Comparable<Team>, Popularity{
	private String tId;
	private String tName;
	private Country represents;
	private int fansCount;
	private Qualified finalStage;
	private short goalsScored=0;
	private short goalsAgainst=0;
	private short yellowCards=0;
	private short redCards=0;
	private Player[] players = new Player [Constants.totalPlayers];
	private MatchResult [] results =new MatchResult [Constants.maxPlayedGames];
	private Fund fund;

	public Team(String tId, String tName, String represents, int fansCount, String finalStage) {
		this.tId = tId;
		this.tName = tName;
		this.represents = Country.valueOf(represents);
		this.fansCount = fansCount;
		this.finalStage = Qualified.valueOf(finalStage);
	}

	public Team(String tId) {
		this.tId = tId;
	}

	public String getKey(){
		return this.tId;
	}


	public String gettId() {
		return tId;
	}


	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public Country getRepresents() {
		return represents;
	}

	public void setRepresents(Country represents) {
		this.represents = represents;
	}

	public short getGoalsScored() {
		return goalsScored;
	}

	public void setGoalsScored(short goalsScored) {
		this.goalsScored = goalsScored;
	}

	public short getGoalsAgainst() {
		return goalsAgainst;
	}

	public void setGoalsAgainst(short goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}

	public short getYellowCards() {
		return yellowCards;
	}

	public void setYellowCards(short yellowCards) {
		this.yellowCards = yellowCards;
	}

	public short getRedCards() {
		return redCards;
	}

	public void setRedCards(short redCards) {
		this.redCards = redCards;
	}

	public int getFansCount() {
		return fansCount;
	}

	public void setFansCount(int fansCount) {
		this.fansCount = fansCount;
	}


	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public MatchResult[] getResults() {
		return results;
	}

	public void setResults(MatchResult[] results) {
		this.results = results;
	}

	public Fund getFund() {
		return fund;
	}

	public void setFund(Fund fund) {
		this.fund = fund;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tId == null) ? 0 : tId.hashCode());
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
		Team other = (Team) obj;
		if (tId == null) {
			if (other.tId != null)
				return false;
		} else if (!tId.equals(other.tId))
			return false;
		return true;
	}

	/**
	 * the method adds player to this team IFF the
	 * player does not exist on this team
	 * @param player
	 * @return true if added player to this team players
	 */
	public boolean addPlayer(Player player) {
		for (int i=0; i<this.players.length; i++) {
			if(this.players[i] != null) {
				if(this.players[i].equals(player)) {
					return false;
				}
			}
		}
		for (int i=0; i<this.players.length; i++) {
			if(this.players[i] == null) {
				this.players[i] = player;
				return true;
			}
		}
		return false;
	}


	/**
	 * remove player from the team
	 * @param player
	 * @return true if the player was removed from team
	 */
	public boolean removePlayer(Player player){
		ArrayList<Player> p= new ArrayList<Player>(Arrays.asList(players));
		if(p.contains(player)) {
			p.remove(player);
			players=p.toArray(players);
			return true;
		}
		players=p.toArray(players);
		return false;
	}


	/**
	 * add Fund to this team, IFF the team has no funding yet.
	 * @param fund
	 * @return true if successfully added
	 */
	public boolean addFund(Fund fund){
		if(this.fund==null){
			this.fund = fund;
			return true;
		}
		return false;
	}


	/**
	 * the method remove the funding from this team, IFF it exist
	 *
	 * @return true if the fund was changed to null
	 */
	public boolean removeFund() {
		if(this.fund!=null) {
			this.fund=null;
			return true;
		}
		return false;

	}

	/**
	 * adds the results received to this method IFF it does NOT exist,
	 * and it adds it in the first empty cell
	 * @param result
	 * @return true if added the result successfully to the results array
	 */
	public boolean addMatchResult(MatchResult result) {
		ArrayList<MatchResult> res = new ArrayList<MatchResult>(Arrays.asList(results));
		if(!res.contains(result)) {
			res.add(result);
			results=res.toArray(results);
			return true;
		}
		results=res.toArray(results);
		return false;
	}

	/**
	 * removes a result IFF it exists in the results array of this Team
	 * @param result
	 * @return true if the remove happened successfully
	 */
	public boolean removeMatchResult(MatchResult result) {
		ArrayList<MatchResult> res = new ArrayList<MatchResult>(Arrays.asList(results));
		if(res.contains(result)) {
			res.remove(result);
			results=res.toArray(results);
			return true;
		}
		results=res.toArray(results);
		return false;

	}

	/**
	 * the method calculate the gap between overall goals scored by this team minus overall goals against this team
	 * @return the gap between the goals of this team
	 * @return negative if the team has received goals more then scored
	 */
	public Integer calculateGoalsGap() {
		return goalsScored-goalsAgainst;
	}



	@Override
	public String toString() {
		return   "Team [tId=" + this.tId+", "+"tName="+this.tName
				+", "+"represents="+this.represents+", "+"goalsScored="+this.goalsScored+", "+"goalsAgainst="+this.goalsAgainst+", "
				+"yellowCards="+this.yellowCards+", "+"redCards="+this.redCards+", "+"fansCount="+this.fansCount
				+", finalStage="+this.finalStage.toString()+"]";
	}

	/**
	 * Compares between two teams
	 * @param t
	 * @return
	 */


	@Override
	public int compareTo(Team t) {
		if(this.calculateGoalsGap() - t.calculateGoalsGap() != 0)
			return t.calculateGoalsGap() - this.calculateGoalsGap();
		return this.fansCount - t.fansCount;
	}

	/**
	 * calculates the popularity of the team
	 */
	public double calcPopularity() {
		return getFansCount()*((getGoalsScored()-getGoalsAgainst())/(getRedCards()+getYellowCards()));
	}

	/**
	 * updates the Team properties
	 * @param goalsScored
	 * @param goalsAgainst
	 * @param yellowCards
	 * @param redCards
	 */
	public void updateTeamProperties(int goalsScored, int goalsAgainst, short yellowCards, short redCards){
		this.goalsScored = (short) (this.goalsScored + goalsScored);
		this.goalsAgainst = (short) (this.goalsAgainst + goalsAgainst);
		this.yellowCards = (short) (this.yellowCards + yellowCards);
		this.redCards = (short) (this.redCards + redCards);
	}





}
