package core;
import java.util.Date;
import utils.*;

/**
 * @author Hadas Racah AND Michel Hawa
 * This class represents the match.
 * The class includes match id, match date,
 * the stadium that took place of the match,
 * total tickets sold and the match result.
 */

public class Match {
	private String mId; //game id
	private Date mDate;
	private Stadium tookPlace; //the Stadium where the game occurred
	private long totalTickets; //total tickets that sold for the game
	private MatchResult result;


	/**
	 * @param mId
	 * @param mDate
	 * @param tookPlace
	 * @param totalTickets
	 */
	public Match(String mId, Date mDate, String tookPlace, long totalTickets) {
		this.mId = mId;
		this.mDate = mDate;
		this.tookPlace = Stadium.valueOf(tookPlace);
		this.totalTickets = totalTickets;
	}

	/**
	 *
	 * @param mId
	 * @param mDate
	 */
	public Match(String mId, Date mDate) {
		this.mId = mId;
		this.mDate=mDate;
	}

	/**
	 * Method get key
	 * @return mId
	 */
	public String getKey(){
		return this.mId;
	}

	/**
	 *
	 * @return mDate
	 */
	public Date getmDate() {
		return mDate;
	}

	/**
	 *
	 * @param mDate
	 */
	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}

	/**
	 *
	 * @return tookPlace
	 */
	public Stadium getTookPlace() {
		return tookPlace;
	}

	/**
	 *
	 * @param tookPlace
	 */
	public void setTookPlace(Stadium tookPlace) {
		this.tookPlace = tookPlace;
	}

	/**
	 *
	 * @return totalTickets
	 */
	public long getTotalTickets() {
		return totalTickets;
	}

	/**
	 *
	 * @param totalTickets
	 */
	public void setTotalTickets(long totalTickets) {
		this.totalTickets = totalTickets;
	}

	/**
	 *
	 * @return mId
	 */
	public String getmId() {
		return mId;
	}

	/**
	 *
	 * @return result
	 */
	public MatchResult getResult() {
		return result;
	}

	/**
	 *
	 * @param result
	 */
	public void setResult(MatchResult result) {
		this.result = result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mId == null) ? 0 : mId.hashCode());
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
		Match other = (Match) obj;
		if (mId == null) {
			if (other.mId != null)
				return false;
		} else if (!mId.equals(other.mId))
			return false;
		return true;
	}

	/**
	 * add a match result IFF the current result is null
	 * to do changes in result it not allowed
	 * @param result
	 * @return true if added match result to this match
	 */
	public boolean addMatchResult(MatchResult result){
		if(this.result==null){
			this.result=result;
			return true;
		}
		return false;
	}

	/**
	 * remove a result of this match only if it exist, not null
	 * @return true if managed to remove the result from this team
	 */
	public boolean removeMatchResult(){
		if(this.result!=null){
			this.result=null;
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return  "Match [mId="+mId+", mDate="+mDate +"]";
	}





}
