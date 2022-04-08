package core;
import utils.*;

import java.util.ArrayList;
import java.util.Arrays;

import utils.*;

public class Player extends Person implements Popularity, Comparable<Player> {
	private int pNum;  //the number of the player
	private Role [] skills; // number of player's skills - maximum 2
	private int fansCount; //the number of the player's fans
	private Team team;


	public Player(String pID, String pFullName, short age, String nation, int pNum, Role[] skills, int fansCount) {
		super(pID, pFullName, age, nation);
		this.pNum = pNum;
		this.skills = skills;
		this.fansCount = fansCount;
	}


	public Player(String pID, int pNum) {
		super(pID);
		this.pNum=pNum;
	}


	public String getKey(){
		 return this.pID + " " + this.pFullName;
		}



	public int getpNum() {
		return pNum;
	}


	public Role[] getSkills() {
		return skills;
	}


	public void setSkills(Role[] skills) {
		this.skills = skills;
	}


	public int getFansCount() {
		return fansCount;
	}

	public void setFansCount(int fansCount) {
		this.fansCount = fansCount;
	}



	public Team getTeam() {
		return team;
	}



	public void setTeam(Team team) {
		this.team = team;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + pNum;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (pNum != other.pNum)
			return false;
		return true;
	}


	/**
	* the method set the value of the team IFF the player
	* does not play for any other team
	* @param team
	* @return true if added successfully
	*/
	public boolean addTeam(Team team){
	if(this.team ==null ){
		this.team=team;
		return true;
	}
	return false;
	}


	/**
	* set the team value to null IFF it is not null,
	* it means the player is playing for another team.
	* @return true if remove from the team was changed to null
	*/
	public boolean removeTeam(){
	if(this.team!=null){
		this.team=null;
		return true;
	}
	return false;
	}


	/**
	 * return true if the add went well
	 * @param skill
	 * @return
	 */
	public boolean addSkill(Role skill) {
		ArrayList<Role> s= new ArrayList<Role>(Arrays.asList(skills));
		if(s.contains(skill)) {
			skills=s.toArray(skills);
			return false;
		}
		s.add(skill);
		skills=s.toArray(skills);
		return true;
	}

	/**
	 * return true if the remove went well
	 * @param skill
	 * @return
	 */
	public boolean removeSkill(Role skill) {
		ArrayList<Role> s= new ArrayList<Role>(Arrays.asList(skills));
		if(s.contains(skill)) {
			s.remove(skill);
			skills=s.toArray(skills);
			return true;
		}
		skills=s.toArray(skills);
		return false;
	}

	/**
	 * returns true is the player has this skill
	 * @param role
	 * @return
	 */
	private boolean hasSkill(Role role) {
		ArrayList<Role> s= new ArrayList<Role>(Arrays.asList(skills));
		if(s.contains(role)) {
			skills=s.toArray(skills);
			return true;
		}
		skills=s.toArray(skills);
		return false;
	}


	@Override
	public String toString() {
		return getClass().getSimpleName()+"[pNum= "+pNum+", skills= "+Arrays.toString(skills)+", fansCount= "+this.fansCount+"]" + super.toString();
	}


	/**
	 *
	 * @return true if the player has only one skill
	 */
	public boolean isOneSkillPlayer() {
		Role[] skills = getSkills();
		if((skills[0] != Role.UN && skills[1] == Role.UN) || (skills[0] == Role.UN && skills[1] != Role.UN)) {
			return true;
		}
		return false;
	}

	/** compares by the player nation
	 * returns Zero if they are equal
	 */
	public int compareTo(Player o) {
		int x = this.getNation().compareTo(o.getNation());
		if(x!=0) //if not equal
			return x;
		return 0;
	}

	/**
	 * calculates the popularity of the player
	 * with his team data and his own data
	 */
	public double calcPopularity() {
		return (fansCount/team.getFansCount())+((team.getGoalsScored()-team.getGoalsAgainst())/(team.getYellowCards()+team.getRedCards()));
	}




}