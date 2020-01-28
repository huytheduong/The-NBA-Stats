//Huy Duong U65372418
import java.io.*;
import java.util.*;
    public class P1 
    {
	/* Define data structures for holding the data here */
    ArrayList<Coach> Coaches;
    ArrayList<Team> Teams;  
    public P1() 
    {
        /* initialize the data structures */
        Coaches = new ArrayList<Coach>();
        Teams = new ArrayList<Team>();
    }
    
    public void run() {
        CommandParser parser = new CommandParser();
        System.out.println("The mini-DB of NBA coaches and teams");
        System.out.println("Please enter a command.  Enter 'help' for a list of commands.");
        System.out.println();
        System.out.print("> "); 
        Command cmd = null;
        while((cmd = parser.fetchCommand()) != null) 
        {
            //System.out.println(cmd);
            boolean finalResult = false;

        if(cmd.getCommand().equals("help")) 
        {
            finalResult = doHelp();
            /* You need to implement all the following commands */
        } 
        else if(cmd.getCommand().equals("add_coach"))//add coaches to the coaches arrayList
        {
            String[] getMain = cmd.getParameters();              
            Coach newcoach;
            newcoach = new Coach(getMain[0], 
            Integer.parseInt(getMain[1]),
            getMain[2],getMain[3], 
            Integer.parseInt(getMain[4]), 
            Integer.parseInt(getMain[5]), 
            Integer.parseInt(getMain[6]), 
            Integer.parseInt(getMain[7]), 
            getMain[8]);
            Coaches.add(newcoach); 
        }
        else if(cmd.getCommand().equals("add_team"))//add team into Team arrayList
        {
            String[] getTemp = cmd.getParameters();           
            Team newTeam = new Team(getTemp[0],getTemp[1],getTemp[2],getTemp[3]);
            Teams.add(newTeam);

        } 
        else if(cmd.getCommand().equals("print_coaches"))//print the coaches from the input
        {
            int i = 0;
            while(i < Coaches.size())
            {
                System.out.println(Coaches.get(i));
                i++;
            } 
        }
        else if(cmd.getCommand().equals("print_teams")) 
        {
            int i = 0;
            while(i < Teams.size())
            {
                System.out.println(Teams.get(i));
                i++;
            }
        } 
        else if(cmd.getCommand().equals("coaches_by_name"))//enter name of the coache and find the coach
        {
            String[] getMain = cmd.getParameters();
            int i = 0;
            while(i < Coaches.size())
            {
                if(Coaches.get(i).getLastName().trim().equalsIgnoreCase(getMain[0].replace("+", " ")))
                {
                    System.out.println(Coaches.get(i));                      
                }
                i++;
            }
        } 
        else if(cmd.getCommand().equals("teams_by_city"))//seach team by city
        {
            String getMain[] = cmd.getParameters();
            int i = 0;
               while(i < Teams.size())
               {
                   if(Teams.get(i).getLocation().trim().equals(getMain[0]))
                   {
                        System.out.println(Teams.get(i).team_ID + " " +
                        Teams.get(i).location.replace('+',' ') + " " +
                        Teams.get(i).name + " " + Teams.get(i).league);
                   }
                i++;
               }
        } 
        else if(cmd.getCommand().equals("load_coaches"))//load the file coaches_season.txt to display all coaches information
        {
            String getMain[] = cmd.getParameters();              
            Scanner readfile;
            try
            {
                readfile = new Scanner(new File(getMain[0]));
                   String getline;
                   String[] StoreValue;
                   if(readfile.hasNextLine())
                   {
                        getline = readfile.nextLine(); 
                   }                  
                   while(readfile.hasNextLine())
                   {
                       getline = readfile.nextLine();
                       StoreValue = getline.split(",");
                       if (StoreValue.length == 9)
                       {
                           Coach newCoach;
                            newCoach =  new Coach(StoreValue[0],
                            Integer.parseInt(StoreValue[1].replace(" ", "")),
                            StoreValue[2],
                            StoreValue[3],
                            Integer.parseInt(StoreValue[4].replace(" ", "")),
                            Integer.parseInt(StoreValue[5].replace(" ", "")),
                            Integer.parseInt(StoreValue[6].replace(" ", "")),
                            Integer.parseInt(StoreValue[7].replace(" ", "")),
                            StoreValue[8]);
                           Coaches.add(newCoach);                          
                       }
                   }
                readfile.close();      
            }
            catch(FileNotFoundException ex)
            {
                System.out.println(getMain[0] + " Not found");
            }  
        } 
        else if(cmd.getCommand().equals("load_teams"))//load all team informaition
        {
            Scanner readfile;
            String getMain[] = cmd.getParameters();
            try
            {
                readfile = new Scanner(new File(getMain[0]));
                String getLine;
                String[] value;
                // read and ignore the first line that contains the column headings
                if(readfile.hasNextLine())
                getLine = readfile.nextLine();               
                while(readfile.hasNextLine())
                {
                    getLine = readfile.nextLine();
                    value = getLine.split(",");
                    if(value.length == 4)
                    {
                        Team newTeam = new Team(value[0], value[1], value[2], value[3]);
                        Teams.add(newTeam);                          
                    }
                }
               readfile.close();  
            }
            catch(FileNotFoundException exception)
            {
                System.out.println(getMain[0] + " No found ");
            }             
        }
        else if(cmd.getCommand().equals("best_coach"))//find out the best coaches bese on the calculation
        {
            String getMain[] = cmd.getParameters();
            int win = 0;
            int whoWin;
            int WhoWin2;
            Coach calCoach;
            int i = 0;
            while(i < Coaches.size())
            {
                calCoach = Coaches.get(i);
                if(calCoach.getYear() == Integer.parseInt(getMain[0]))
                {
                    whoWin = (calCoach.getSeason_win() - calCoach.getSeason_loss());
                    WhoWin2 = (calCoach.getPlayoff_win() - calCoach.getPlayoff_loss());
                    whoWin += WhoWin2;
                    if(whoWin > win)
                    {
                        win = whoWin;
                    }
                }
                i++;
            }
            int j = 0;
            while(j < Coaches.size())
            {
                calCoach = Coaches.get(j);
                if(calCoach.getYear() == Integer.parseInt(getMain[0]))
                {
                    whoWin = (calCoach.getSeason_win() - calCoach.getSeason_loss());
                    WhoWin2 = (calCoach.getPlayoff_win() - calCoach.getPlayoff_loss());
                    whoWin += WhoWin2;
                    if(whoWin == win)
                    {
                        System.out.println(calCoach.getFirstName() +" "+ calCoach.getLastName());
                    }
                }
                j++;
            }
        } 
        else if(cmd.getCommand().equals("search_coaches"))//search coach after load the file
        {
            String[] getMain = cmd.getParameters();
            String[] point = getMain[0].split("=");
            String position = point[0]; String result = point[1];
            for(int i = 0; i < Coaches.size(); i++)
            {
                Coach newCoach = Coaches.get(i);                  
                if(position.equalsIgnoreCase("year"))
                {
                   if(Integer.parseInt(result) == newCoach.getYear())
                   {
                    System.out.println(newCoach.getFirstName() + " " + newCoach.getLastName());
                    break;
                   }
                }
                else if(position.equalsIgnoreCase("first_name"))
                {
                    if(result.equalsIgnoreCase(newCoach.getFirstName()))
                    {
                        System.out.println(newCoach.getCoachID() + " " + newCoach.getYear() + " " + newCoach.getFirstName() + " " + newCoach.getLastName() + " " + newCoach.getSeason_win() + " " + newCoach.getSeason_loss() + " " + newCoach.getPlayoff_win() + " " + newCoach.getSeason_loss() + " " + newCoach.getTeam());
                        break;
                    }
                }
                else if(position.equalsIgnoreCase("last_name"))
                {
                    if(result.equalsIgnoreCase(newCoach.getLastName()))
                    {
                        System.out.println(newCoach.getCoachID() + " " + newCoach.getYear() + " " + newCoach.getFirstName() + " " + newCoach.getLastName() + " " + newCoach.getSeason_win() + " " + newCoach.getSeason_loss() + " " + newCoach.getPlayoff_win() + " " + newCoach.getSeason_loss() + " " + newCoach.getTeam());
                        break;
                    }
                }
                else if(position.equalsIgnoreCase("season_win"))
                {
                    if(Integer.parseInt(result) == newCoach.getSeason_win())
                    {
                        System.out.println(newCoach.getFirstName() + " " + newCoach.getLastName());
                        break;
                    }
                }
                else if(position.equalsIgnoreCase("season_loss"))
                {
                    if(Integer.parseInt(result) == newCoach.getSeason_loss())
                    {
                        System.out.println(newCoach.getFirstName() + " " + newCoach.getLastName());
                        break;
                    }
                }
                else if(position.equalsIgnoreCase("playoff_win"))
                {
                    if(Integer.parseInt(result) == newCoach.getPlayoff_win())
                    {
                        System.out.println(newCoach.getFirstName() + " " + newCoach.getLastName());
                    }
                }
                else if(position.equalsIgnoreCase("playoff_loss"))
                {
                    if(Integer.parseInt(result) == newCoach.getPlayoff_loss())
                    {
                        System.out.println(newCoach.getFirstName() + " " + newCoach.getLastName());
                    }
                }
                else if(position.equalsIgnoreCase("team"))
                {
                    if(result.equalsIgnoreCase(newCoach.getTeam()))
                    {
                        System.out.println(newCoach.getFirstName() + " " + newCoach.getLastName());
                    }
                }
                else if(position.equalsIgnoreCase("coachid"))
                {
                    if(result.equalsIgnoreCase(newCoach.getCoachID()))
                    {
                        System.out.println(newCoach.getFirstName() + " " + newCoach.getLastName());
                    }
                }
            }
        } 
        else if(cmd.getCommand().equals("exit")) 
        {
			System.out.println("Leaving the database, goodbye!");
			break;
        } 
        else if(cmd.getCommand().equals("")) 
        {}
        else 
        {
			System.out.println("Invalid Command, try again!");
        } 
        if(finalResult) {
                // ...
            }
            System.out.print("> "); 
        }        
    }
    private boolean doHelp() {
        System.out.println("add_coach ID SEASON FIRST_NAME LAST_NAME SEASON_WIN "); 
	    System.out.println("SEASON_LOSS PLAYOFF_WIN PLAYOFF_LOSS TEAM - add new coach data");
        System.out.println("add_team ID LOCATION NAME LEAGUE - add a new team");
        System.out.println("print_coaches - print a listing of all coaches");
        System.out.println("print_teams - print a listing of all teams");
        System.out.println("coaches_by_name NAME - list info of coaches with the specified name");
        System.out.println("teams_by_city CITY - list the teams in the specified city");
	    System.out.println("load_coach FILENAME - bulk load of coach info from a file");
        System.out.println("load_team FILENAME - bulk load of team info from a file");
        System.out.println("best_coach SEASON - print the name of the coach with the most netwins in a specified season");
        System.out.println("search_coaches field=VALUE - print the name of the coach satisfying the specified conditions");
		System.out.println("exit - quit the program");        
        return true;
    }
    public static void main(String[] args) {
        new P1().run();
    }
    private class Team //class team contain all the team
    {
        public String team_ID;
        public String location ;
        public String name;
        public String league;
        public String getLocation(){return location;}
        public Team(String teamID, String thelocation, String thename, String theleague)
        {
            team_ID = teamID;
            location = thelocation;
            name = thename;
            league = theleague;
        }
        public String toString()
        {
            return this.team_ID + " " +
            this.location + " " +
            this.name + " " +
            this.league;
        }
    }
    //class coach contain coach information
    private class Coach
    {
        public int season_win;
        public int season_loss;
        public int playoff_win;
        public int playoff_loss;
        public String team;
        public String coach_ID;
        public int year;
        public String first_name;
        public String last_name;
        //all of these are mutators that will return value
        public String getCoachID(){ return coach_ID;}
        public int getYear(){return year;}
        public String getFirstName(){return first_name;}
        public String getLastName(){return last_name;}
        public int getSeason_win(){return season_win;}
        public int getSeason_loss(){ return season_loss;}
        public int getPlayoff_win(){return playoff_win;}
        public int getPlayoff_loss(){return playoff_loss;}
        public String getTeam(){ return team;}
        public String toString()
        {
            return this.coach_ID + " " + 
            this.year + " " + " " + this.first_name + " " +
            this.last_name + " " + this.season_win + " " + 
            this.season_loss + " " + this.playoff_win + " " + 
            this.playoff_loss + " " + this.team;
        }
        public Coach(String coachID,int Year, String firstName, String lastName, int seasonWin, int seasonLoss, int playoffWin, int playoffLoss, String team)
        {
            this.last_name = lastName;
            this.season_win = seasonWin;
            this.season_loss = seasonLoss;
            this.playoff_win = playoffWin;
            this.playoff_loss = playoffLoss;
            this.coach_ID = coachID;
            this.year = Year;
            this.first_name = firstName;
            this.team = team;
        }
    }  
}
