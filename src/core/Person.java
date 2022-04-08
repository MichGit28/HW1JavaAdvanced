package core;
import utils.*;

/**
 * @author Hadas Racah AND Michel Hawa
 * This class represents all persons that were included in the system.
 * The class includes person id, full name,
 * age and nation.
 */

public abstract class Person {
	protected String pID;
	protected String pFullName;
	protected short age;
	private Country nation;

	public Person(String pID, String pFullName, short age, String nation) {
		this.pID = pID;
		this.pFullName = pFullName;
		this.age = age;
		this.nation = Country.valueOf(nation);
	}

	public Person(String pID) {
		this.pID = pID;
	}

	public int getAge() {
		return age;
	}

	public void setAge(short age) {
		this.age = age;
	}

	public Country getNation() {
		return nation;
	}

	public void setNation(Country nation) {
		this.nation = nation;
	}

	public String getpFullName() {
		return pFullName;
	}

	public void setpFullName(String pFullName) {
		this.pFullName = pFullName;
	}

	public String getpID() {
		return pID;
	}

	public void setpID(String pID) {
		this.pID = pID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pID == null) ? 0 : pID.hashCode());
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
		Person other = (Person) obj;
		if (pID == null) {
			if (other.pID != null)
				return false;
		} else if (!pID.equals(other.pID))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return " Person ["+ "pId=" + pID + ", pFullName=" + pFullName + ", age=" + age + ", nation=" + nation+"]";
	}






}
