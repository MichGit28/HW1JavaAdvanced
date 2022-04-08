package init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import utils.Constants;
import utils.Country;
import utils.Qualified;
import utils.Role;
import utils.Stadium;
import core.*;

/**
 * This JEuroTournament object represents the class system
 */
public class JEuroTournament {
	//---------------------the class parameters  -------------------------
	/**
	 * All teams list in the system
	 */
	private ArrayList<Team> teams;

	/**
	 * All Matches listed in the system
	 */
	private ArrayList<Match> matches;
	/**
	 * all players sponsors
	 */
	private ArrayList<Person> persons;

	//--------------------- Constructors -------------------------
	/**
	 * Constructor
	 * Perform initialization for related data structures
	 */
	public JEuroTournament() {
		super();
		teams	= new ArrayList<Team>();
		matches = new ArrayList<Match>();
		persons = new ArrayList<Person>();
	}

	// ---------------------- DB Methods -----------------------------

	/**
	 * @return the teams
	 */
	public ArrayList<Team> getTeams() {
		return teams;
	}


	/**
	 * @return the matches
	 */
	public ArrayList<Match> getMatches() {
		return matches;
	}

	/**
	 * @return all players and sponsors
	 */
	public ArrayList<Person> getPerson(){
		return persons;
	}

	/**
	 * the method creates and add new team to the system
	 * IFF the team does not exist.
	 * @param tId
	 * @param tName
	 * @param represents
	 * @param fansCount
	 * @param finalStage
	 * @return if manage to add
	 * **there is a casting to enum for represents and finalStage**
	 */
	public  boolean addTeam(String tId, String tName, String represents,
							int fansCount, String finalStage){
		Team teamToAdd = new Team(tId);
		if(tId!=null && tName!=null
				&& represents!=null	&& finalStage!=null){
			// checking if the team exist
			if(!teams.contains(teamToAdd)){
				teamToAdd = new Team(tId, tName, represents, fansCount, finalStage);
				teams.add(teamToAdd);
				return true;
			}
		}
		return false;
	} // ~ END OF addTeam

	/**
	 * adds a match to the system IFF it does not exist
	 * @param matchID
	 * @param date
	 * @param stadium
	 * @return true if managed to add the match
	 */
	public  boolean addMatch(String matchID, Date date, String stadium,long soldTickets){
		Match matchToAdd = new Match(matchID, date);
		if(!matches.contains(matchToAdd)) {
			matchToAdd = new Match(matchID, date, stadium, soldTickets);
			matches.add(matchToAdd);
			return true;
		}
		return false;


	} // ~ END OF addMatch

	/**
	 * receive a person and without knowing its exact type
	 * the method adds the Person to the system data base
	 * IFF the person does not exist
	 * @param person
	 * @return true if successfully added
	 */
	public boolean addPerson(Person person){
		if(!persons.contains(person)){
			return persons.add(person);
		}
		return false;
	}

	/**
	 * the method adds a player to a team
	 * IFF the player exist and he does not belong to the team or any other team
	 * @param pId
	 * @param pNum
	 * @param tId
	 * @return true if managed to add the player to the team and vis versa
	 */
	public boolean addPlayerToTeam(String pId, int pNum,String tId){
		Player pl = new Player(pId, pNum);
		Team tm   = new Team(tId);

		if(persons.contains(pl) && teams.contains(tm)){
			pl =(Player) persons.get(persons.indexOf(pl));
			tm = teams.get(teams.indexOf(tm));

			if(pl.addTeam(tm)){
				if(tm.addPlayer(pl)){
					return true;
				}
				else{
					pl.removeTeam();
				}
			}
		}
		return false;
	}

	/**
	 * the method adds funding to the team by the sponsor
	 * @param pId
	 * @param surname
	 * @param tId
	 * @param amount
	 * @param fDate
	 * @return true if managed to add the team to the sponsor and vis versa
	 */
	public boolean fundTeamBySponser(String pId, String surname,String tId
			,double amount,Date fDate){
		Sponsor sp 	= new Sponsor(pId, surname);
		Team 	tm 	= new Team(tId);

		if(persons.contains(sp) && teams.contains(tm)){
			sp = (Sponsor)persons.get(persons.indexOf(sp));
			tm = teams.get(teams.indexOf(tm));

			Fund fund = new Fund(amount, fDate, sp, tm);
			if(tm.addFund(fund)){
				if(sp.addFundedTeam(fund)){
					return true;
				}
				else{
					tm.removeFund();
				}
			}
		}
		return false;
	}

	/**
	 * the method adds a Match result that occurred between two teams,
	 * Team A and Team B. in addition the result details.
	 * first search for the teams and match if they exist, create the match result
	 * object and then add it to the proper objects.
	 * @param tIdA
	 * @param tIdB
	 * @param mId
	 * @param totalTime
	 * @param tAgoals
	 * @param tBgoals
	 * @param penaltyEnd
	 * @param tBRedcards
	 * @param tARedcards
	 * @param tAYellowCards
	 * @param tBYellowCards
	 * @param date
	 * @return if successfully added match result to both match the the involved teams
	 */
	public boolean addMatchResult(String tIdA , String tIdB ,String mId, Date date
			,int totalTime ,int tAgoals,int tBgoals, boolean penaltyEnd,short tAYellowCards,short tBYellowCards
			, short tARedcards, short tBRedcards){

		Team tA 	= new Team(tIdA);
		Team tB 	= new Team(tIdB);
		Match match = new Match(mId,date);

		if(teams.contains(tA) && teams.contains(tB) && matches.contains(match)){

			tA 		= teams.get(teams.indexOf(tA));
			tB 		= teams.get(teams.indexOf(tB));
			match 	= matches.get(matches.indexOf(match));

			MatchResult mResult = new MatchResult(tA, tB, match, totalTime
					, tAgoals, tBgoals, penaltyEnd, tAYellowCards, tBYellowCards
					,  tARedcards, tBRedcards);

			if(tA.addMatchResult(mResult)){
				if(tB.addMatchResult(mResult)){
					if(match.addMatchResult(mResult)){
						return true;
					}else{
						tA.removeMatchResult(mResult);
						tB.removeMatchResult(mResult);
						return false;
					}
				}else{
					tA.removeMatchResult(mResult);
					return false;
				}
			}// else - nothing is changed the method will return false
		}// else - nothing is changed the method will return false
		return false;
	}
	// ===================== Queries ===================================
	/**
	 * elite teams are defined as teams with total goals gap (in-out)
	 * bigger or equals to the parameter gap that is received to this method
	 *  then the method arrange the teams according to this gap from highest to lowest
	 * notice: if two teams have the same goals gap then the are compared
	 * according to the total fans number of each team.
	 * @return Team[] contains the elite teams arrange by goals gap, number of fans
	 * @param gap
	 */
	public Team[] eliteTeamsArrangedByGoalsGap(int gap){
		ArrayList<Team> teamsArrayList= new ArrayList<Team>();
		int cnt=0;
		for (Team t : teams) {
			if (t.calculateGoalsGap() >= gap) {
				teamsArrayList.add(t);
				cnt++;
			}
		}
		Collections.sort(teamsArrayList);  //uses the comparable that Team implements
		Team [] sortedTeams = new Team[cnt];
		sortedTeams = teamsArrayList.toArray(sortedTeams);
		return sortedTeams;
	}




	/**
	 * the method searches the players for total of X player
	 * that has exactly one skill from every team.
	 * @param x
	 * @return Player[] Array of X player from every team sorted by team representation country.
	 */
	public Player[] getXDifferentPlayerWithOneSkill(int x){
		ArrayList<Player> oneSkillPlayers = new ArrayList<Player>();
		for(Team team : teams) {
			int count = 0;
			for (Player player : team.getPlayers()) {
				if(player!= null) {
					if(player.isOneSkillPlayer()) {
						oneSkillPlayers.add(player);
						count++;
						if(count == x) {
							break;
						}
					}
				}
			}
		}
		Collections.sort(oneSkillPlayers);
		Player[] sortedPlayers = new Player [oneSkillPlayers.size()];
		int i=0;
		for(Player p : oneSkillPlayers) {
			sortedPlayers[i] = p;
			i++;
		}
		return sortedPlayers;
	}



	/**
	 *Interest funding is a fund which was give from a sponsor
	 * to a team that represents his(sponsor) country
	 * @param total
	 * @return Fund[] that contains Interest funds
	 */
	public Fund[] InterestFunding(int total){
		Fund [] fundedByTotalNumber = new Fund [total];
		ArrayList<Fund> fundsThatHaveGoodNation = new ArrayList<Fund>();
		int i=0;
		for (Team t : teams) {
			Fund fund= t.getFund();
			if(fund==null)
				continue;
			if(t.getRepresents().equals(t.getFund().getSponsor().getNation())) {
				fundsThatHaveGoodNation.add(t.getFund());
				i++;
				if(i==total)
					break;
			}
		}
		fundedByTotalNumber = fundsThatHaveGoodNation.toArray(fundedByTotalNumber);
		return fundedByTotalNumber;

	}

	/**
	 * the method return an array of strings where each string
	 * is a combination of three raws
	 * first row is the team
	 * second row indicates if the team received fund
	 * third row shows the Amount of received fund
	 * Warning: the use of String.format() is compulsory(must)
	 * NOT USING IT WILL CAUSE LOSING POINT
	 * @return String[] that contains Objects as indicated above
	 */
	public String[] getTeamsFundAndTotal(){
		String [] teamFundAmount = new String [teams.size()];
		for(int i=0; i<teamFundAmount.length;i++) {
			if(teams.get(i).getFund()!=null) {
				teamFundAmount[i] = String.format(" Team: %s", teams.get(i).toString()+"\n");
				if(teams.get(i).getFund().getAmount()!=0) {
					teamFundAmount[i]+=String.format(" Funded: true %s", "\n");   //needs to be boolean
					teamFundAmount[i]+=String.format(" Total Amount of Fund: %s", teams.get(i).getFund().getAmount() + "0");
				}
			}
			else if(teams.get(i).getFund()==null) {
				teamFundAmount[i] =String.format(" Team: %s", teams.get(i).toString()+"\n");
				teamFundAmount[i]+=String.format(" Funded: false %s","\n");
				teamFundAmount[i]+=String.format(" Total Amount of Fund: 0.00");
			}
		}
		return teamFundAmount;
	}

	/**
	 * the method search for all matches where the time was extended
	 * but the game was over before getting to the penalty stage.
	 * @return MatchResult[] that contains objects that satisfy the above.
	 */
	public MatchResult[] extendedMatcheswithoutPenaltyKicks(){
		ArrayList<MatchResult> matchResultTimeExtendedArrayList = new ArrayList<MatchResult>();
		MatchResult [] m =new MatchResult [matches.size()];
		int cnt=0;
		for(int i = 0; i < matches.size(); i++) {
			if(matches.get(i).getResult().getTotalTime()>Constants.gameDuration && !matches.get(i).getResult().isPenaltyEnd()) {
				matchResultTimeExtendedArrayList.add(matches.get(i).getResult());
				cnt++;
			}
		}
		m= matchResultTimeExtendedArrayList.toArray(m);
		return m;
	}

	/**
	 * the method return all teams that participant at least once
	 * in an extended Match that ends before the penalty stage
	 * use the method:
	 * @see JEuroTournament#extendedMatcheswithoutPenaltyKicks()
	 * @return Team[] that participant in the extended matches
	 */
	public Team[] getAllTeamsInvolvedInExtendedMatches(){
		ArrayList<MatchResult> MatchResultsFromTheLastFunction =new ArrayList<MatchResult>(Arrays.asList(extendedMatcheswithoutPenaltyKicks()));
		ArrayList<Team> teamsThatInvolved = new ArrayList<Team>();
		int cnt=0;
		for (int i = 0; i < MatchResultsFromTheLastFunction.size(); i++) {
			if(MatchResultsFromTheLastFunction.get(i)!=null) {
				if(!teamsThatInvolved.contains(MatchResultsFromTheLastFunction.get(i).getTeamA()) )   {
					teamsThatInvolved.add(MatchResultsFromTheLastFunction.get(i).getTeamA());
					cnt++;
				}
			}
			if(MatchResultsFromTheLastFunction.get(i)!=null) {
				if(!teamsThatInvolved.contains(MatchResultsFromTheLastFunction.get(i).getTeamB())&& MatchResultsFromTheLastFunction.get(i)!=null ) {
					teamsThatInvolved.add(MatchResultsFromTheLastFunction.get(i).getTeamB());
					cnt++;
				}
			}
		}
		Team [] t = new Team [teamsThatInvolved.size()];
		t=teamsThatInvolved.toArray(t);
		return t;
	}

	/**
	 * this private method is used to add an element to the array
	 * where no duplicate items are not allowed
	 * @param team
	 * @param tms
	 * @return true if the element was added
	 */
	private boolean addTeam(Team team, Team[] tms) {
		ArrayList<Team> teamss = new ArrayList<Team>(Arrays.asList(tms));
		if(teamss.contains(team)) {
			return false;
		}
		teamss.add(team);
		return true;

	}

}
