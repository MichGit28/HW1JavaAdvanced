package init;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

import core.Fund;
import core.MatchResult;
import core.Player;
import core.Sponsor;
import core.Team;
import utils.Country;
import utils.MyFileLogWriter;
import utils.Role;


/**
 * The Main Class -The program runner  
 */
public class MainClass {

	/**
	 * The main method of this system, gets input from text file Writes output
	 * to output.txt file
	 * @param args
	 * @throws IOException
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws IOException, ParseException,
			ClassNotFoundException {
		// the command read from the input file 
		String command;

		// to check if the command updated the objects 
		boolean isUpdated;

		// writer buffer creation used after update 
		MyFileLogWriter.initializeMyFileWriter();

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));

		// the JEuroTournament Object	
		init.JEuroTournament jEuroTournament = null;

		// create Scanner for the text file named "input.txt"
		Scanner input = new Scanner(new File("input.txt"));

		// if the file has next - not empty
		if (input.hasNext()) {
			jEuroTournament = new init.JEuroTournament();
		}

		/*
		 *  read the commands. loop while input file [input.hasnext()]
		 * and the jEuroTournament is not null
		 */
		while (input.hasNext() && jEuroTournament != null) {

			/*
			 * read the command, must be first at line because command value
			 * determine which method will be activated in JEuroTournament object.
			 */
			command = input.next();
			isUpdated = false;

			// ================				Command			================
			if (command.equals("addTeam")) {

				//  create and add new add team to jEuroTournament
				String tId 			=	input.next();
				String tName		=	input.next();
				String represents	=	input.next();
				int fansCount		=	input.nextInt();
				String finalStage	=	input.next();

				isUpdated = jEuroTournament.addTeam(tId, tName,  represents, fansCount, finalStage);

				MyFileLogWriter
						.writeToFileInSeparateLine("addTeam returns:");

				if (isUpdated) { // if adding successfully, then true returned,
					// the following message is written to the output file
					MyFileLogWriter
							.writeToFileInSeparateLine("\tSuccessfully added team " + tId
									+ ","+represents+" to jEuroTournament");
				} else {
					MyFileLogWriter
							.writeToFileInSeparateLine("\tFailed to add new team " + tId
									+ " to jEuroTournament");
				}
			}
			// ================				Command			================
			else if (command.equals("addMatch")) {

				String matchID 	= input.next();
				Date date		= df.parse(input.next());
				String stadium	= input.next();
				int soldTickets = input.nextInt();

				isUpdated = jEuroTournament.addMatch(matchID, date, stadium,soldTickets);
				MyFileLogWriter
						.writeToFileInSeparateLine("addMatch returns:");
				if (isUpdated) {
					MyFileLogWriter
							.writeToFileInSeparateLine("\tSuccessfully added match with identifier: "
									+ matchID +" "+date+" to jEuroTournament");
				} else {
					MyFileLogWriter
							.writeToFileInSeparateLine("\tFailed to add new match "
									+ matchID +" to jEuroTournament");
				}

			}
			// ================				Command			================
			else if (command.equals("addPlayer")) {

				String pId 		= input.next();
				String pFullName= input.next()+" "+input.next();
				short age 		= input.nextShort();
				String nation 	= input.next();
				int pNum		= input.nextInt();
				Role skill1 	= getRole(input.next());
				Role skill2 	= getRole(input.next());
				int fansCount	= input.nextInt();



				Role[] skills = {skill1,skill2};
				if( pId !=null && pFullName!=null && nation!=null && skill1!=null
						&& skill2!=null){
					isUpdated = jEuroTournament.addPerson(new Player(pId, pFullName,  age
							,nation, pNum, skills, fansCount));

					MyFileLogWriter
							.writeToFileInSeparateLine("addPlayer returns:");
					if(isUpdated){
						MyFileLogWriter
								.writeToFileInSeparateLine("\tSuccessfully added Player "+
										pId +" , "+pNum+" to jEuroTournament");
					}else
						MyFileLogWriter
								.writeToFileInSeparateLine("\tFailed adding Player "+
										pId +" , "+pNum+" to jEuroTournament");
				}
				else
					MyFileLogWriter
							.writeToFileInSeparateLine("addPlayer returns: invalid input pId , "+pId);
			}
			// ================				Command			================
			else if (command.equals("addPlayerToTeam")) {

				String pId	= input.next();
				int pNum	= input.nextInt();
				String tId	= input.next();

				if(pId!=null && tId!=null){
					isUpdated = jEuroTournament.addPlayerToTeam(pId, pNum, tId);
					MyFileLogWriter
							.writeToFileInSeparateLine("addPlayerToTeam returns:");
					if(isUpdated){
						MyFileLogWriter
								.writeToFileInSeparateLine("\tSuccessfully added Player "+
										pId +" , "+pNum+" to Team "+tId);
					}else
						MyFileLogWriter
								.writeToFileInSeparateLine("\tFailed adding Player "+
										pId +" , "+pNum+" to Team "+tId);
				}
				else
					MyFileLogWriter
							.writeToFileInSeparateLine("addPlayerToTeam returns: invalid input");

			}
			// ================				Command			================
			else if (command.equals("addSponsor")) {

				String pId 		= input.next();
				String pFullName= input.next()+" "+input.next();
				String nation 	= input.next();
				short age 		= input.nextShort();
				String surname	= input.next();



				if(pId!=null && pFullName!=null && nation!=null && surname!=null ){
					Sponsor sponsor = new Sponsor(pId, pFullName, age, nation, surname);
					isUpdated = jEuroTournament.addPerson(sponsor);
					MyFileLogWriter
							.writeToFileInSeparateLine("addSponsor returns:");
					if(isUpdated){
						MyFileLogWriter
								.writeToFileInSeparateLine("\tSuccessfully added sponsor "+
										pId +" , "+surname+" to system");
					}else
						MyFileLogWriter
								.writeToFileInSeparateLine("\tFailed adding Player "+
										pId +" , "+surname+" to system");
				}
				else
					MyFileLogWriter
							.writeToFileInSeparateLine("addSponsor returns: invalid input");
			}
			// ================				Command			================
			else if (command.equals("fundTeamBySponser")) {

				String pId		=	input.next();
				String surname	=	input.next();
				String tId		=	input.next();
				double amount	=	input.nextDouble();
				Date fDate		=	df.parse(input.next());

				if(pId!=null && surname!=null && tId!=null
						&& fDate!=null){
					isUpdated = jEuroTournament.fundTeamBySponser(pId, surname
							, tId, amount, fDate);
					MyFileLogWriter
							.writeToFileInSeparateLine("fundTeamBySponser returns:");

					if(isUpdated){
						MyFileLogWriter
								.writeToFileInSeparateLine("\tSuccessfully funded team " + tId
										+ " by sponsor "+pId+" , "+surname);
					}else
						MyFileLogWriter
								.writeToFileInSeparateLine("\tFailed funding team "+ tId
										+ " by sponsor "+pId+" , "+surname);
				}
				else
					MyFileLogWriter
							.writeToFileInSeparateLine("fundTeamBySponser returns: invalid input");
			}
			// ================				Command			================
			else if (command.equals("addMatchResult")) {

				String tIdA			= input.next();
				String tIdB 		= input.next();
				String mId			= input.next();
				Date date			= df.parse(input.next());
				int totalTime 		= input.nextInt();
				int tAgoals			= input.nextInt();
				int tBgoals			= input.nextInt();
				boolean penaltyEnd	= input.nextBoolean();
				short tAYellowCards = input.nextShort();
				short tBYellowCards = input.nextShort();
				short tARedcards	= input.nextShort();
				short tBRedcards	= input.nextShort();

				if(tIdA!=null && tIdB!=null && mId!=null){
					isUpdated = jEuroTournament.addMatchResult(tIdA, tIdB, mId, date
							, totalTime, tAgoals, tBgoals, penaltyEnd, tAYellowCards, tBYellowCards
							, tARedcards, tBRedcards);
					MyFileLogWriter
							.writeToFileInSeparateLine("addMatchResult returns:");

					if(isUpdated){
						MyFileLogWriter
								.writeToFileInSeparateLine("\tSuccessfully added result of:\n\t" +
										"teamA: " + tIdA +" teamB: " + tIdB
										+ " in match "+mId+" , result: A-"+tAgoals+": B-"+tBgoals);
					}else
						MyFileLogWriter
								.writeToFileInSeparateLine("\tFailed adding result of:\n\t" +
										"teamA: " + tIdA +" teamB: " + tIdB+ " in match "+mId);
				}else{
					MyFileLogWriter
							.writeToFileInSeparateLine("addMatchResult returns: invalid input" +
									", some Object does not exist");
				}
			}
			// ================				Command			================
			else if (command.equals("eliteTeamsArrangedByGoalsGap")) {

				int gap = input.nextInt();

				Team[] eT = jEuroTournament.eliteTeamsArrangedByGoalsGap(gap);
				MyFileLogWriter
						.writeToFileInSeparateLine("eliteTeamsArrangedByGoalsGap "+gap+", returns:");

				if (eT!=null){
					MyFileLogWriter
							.writeToFileInSeparateLine("the following elite Teams where found:");
					int i = 0;
					for(Team t:eT){
						if(t!=null)
							MyFileLogWriter
									.writeToFileInSeparateLine("\t"+ ++i +"-\t"+t);
					}
				}else
					MyFileLogWriter
							.writeToFileInSeparateLine("No elite Teams where found");

			}
			// ================				Command			================
			else if (command.equals("getXDiffirentPlayerWithOneSkill")) {

				int playersPerTeam = input.nextInt();
				if(playersPerTeam>0){

					Player[] oneSP = jEuroTournament.getXDifferentPlayerWithOneSkill(playersPerTeam) ;
					MyFileLogWriter
							.writeToFileInSeparateLine("getXDifferentPlayerWithOneSkill, "+playersPerTeam
									+" player\\s per team, returns:");

					if (oneSP[0]!=null){
						MyFileLogWriter
								.writeToFileInSeparateLine("the following one skill players where found:");
						int i = 0;
						for(Player p:oneSP){
							if(p!=null)
								MyFileLogWriter
										.writeToFileInSeparateLine("\t"+ ++i +"-\t"+p);
						}
					}
					else
						MyFileLogWriter
								.writeToFileInSeparateLine("No one skill players where found");
				}
				else
					MyFileLogWriter
							.writeToFileInSeparateLine("getXDiffirentPlayerWithOneSkill returns:" +
									" invalid input"+playersPerTeam);
			}
			// ================				Command			================
			else if (command.equals("InterestFunding")) {

				int total = input.nextInt();
				if(total>0){

					Fund[] iFunds = jEuroTournament.InterestFunding(total) ;
					MyFileLogWriter
							.writeToFileInSeparateLine("InterestFunding, out of: "+total
									+" returns:");

					if (iFunds[0]!=null){
						MyFileLogWriter
								.writeToFileInSeparateLine("the following Interest Funds where found:");
						int i = 0;
						for(Fund f:iFunds){
							if(f!=null)
								MyFileLogWriter
										.writeToFileInSeparateLine("\t"+ ++i +"-\t"+f);
						}
					}
					else
						MyFileLogWriter
								.writeToFileInSeparateLine("No Interest Funds where found");
				}
				else
					MyFileLogWriter
							.writeToFileInSeparateLine("InterestFunding returns:" +
									" invalid input"+total);
			}
			// ================				Command			================
			else if (command.equals("getTeamsFundAndTotal")) {
				String[] teams = jEuroTournament.getTeamsFundAndTotal();

				MyFileLogWriter
						.writeToFileInSeparateLine("getTeamsFundAndTotal returns:");
				int i=0;
				for(String str:teams){
					MyFileLogWriter
							.writeToFileInSeparateLine("\n\t"+ ++i +"-\t"+str);
				}

			}
			// ================				Command			================
			else if (command.equals("extendedMatcheswithoutPenaltyKicks")) {

				MatchResult[] mtR = jEuroTournament.extendedMatcheswithoutPenaltyKicks();

				if (mtR[0]!=null){
					MyFileLogWriter
							.writeToFileInSeparateLine("the following extended matches where found:");
					int i = 0;
					for(MatchResult mR : mtR){
						if(mR!=null)
							MyFileLogWriter
									.writeToFileInSeparateLine("\t"+ ++i +"-\t"+mR);
					}
				}
				else
					MyFileLogWriter
							.writeToFileInSeparateLine("No extended matches where found");

			}
			// ================				Command			================
			else if (command.equals("getAllTeamsInvolvedInExtendedMatches")) {
				Team[] exTm = jEuroTournament.getAllTeamsInvolvedInExtendedMatches();

				if (exTm[0]!=null){
					MyFileLogWriter
							.writeToFileInSeparateLine("the following teams are involved in " +
									"extended matches where found:");
					int i = 0;
					for(Team tm : exTm){
						if(tm!=null)
							MyFileLogWriter
									.writeToFileInSeparateLine("\t"+ ++i +"-\t"+tm);
					}
				}
				else
					MyFileLogWriter
							.writeToFileInSeparateLine("No teams and no extended matches where found");
			}
			// ================				Command			================
			else if (command.equals("//")) {
				MyFileLogWriter.writeToFileInSeparateLine("\n");
				input.nextLine();
				// ignores line starts by '//' the result is empty lines in the output
			}
			// ================				Command			================
			else if (command.equals("//=")) {
				MyFileLogWriter.writeToFileInSeparateLine
						("=============================================================================================");
				input.nextLine();
			}
			// ================				Command			================
			else
				System.out.printf("Command %s does not exist\n", command);
		} //~ end of clause - while input has next

		MyFileLogWriter.saveLogFile(); // save the output file
		input.close(); // close connection to input file
		System.out.println("[End Of process]");
		System.out.println("\n NOTICE:\n\t\"End of process\" " +
				"does NOT mean that all your methods are correct.\n" +
				"\n==>\t You NEED to check the \"output.txt\" file");
	}


	private static Role getRole(String role){
		switch(role){
			case "QB":
				return Role.QB;
			case "C":
				return Role.C;
			case "RB":
				return Role.RB;
			case "FB":
				return Role.FB;
			case "WR":
				return Role.WR;
			case "TE":
				return Role.TE;
			case "LG":
				return Role.LG;
			case "RG":
				return Role.RG;
			case "LT":
				return Role.LT;
			case "RT":
				return Role.RT;
			case "DT":
				return Role.DT;
			case "DE":
				return Role.DE;
			case "LB":
				return Role.LB;
			case "S":
				return Role.S;
			case "CB":
				return Role.CB;
			case "GK":
				return Role.GK;
			default :
				return Role.UN;
		}
	}
}
