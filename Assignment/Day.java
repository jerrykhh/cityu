
public class Day implements Cloneable {

    private int year;
    private int month;
    private int day;
    private static final String MonthNames = "JanFebMarAprMayJunJulAugSepOctNovDec";

    // Constructor
    public Day(int y, int m, int d) {
        this.year = y;
        this.month = m;
        this.day = d;
    }

    public Day(String sDay) throws ExInvaildDate{
        set(sDay);
    }

    public Day(Day day){
        this.year = day.getYear();
        this.month = day.getMonth();
        this.day = day.getDay();
    }

    // check if a given year is a leap year
    static public boolean isLeapYear(int y) {
        if (y % 400 == 0)
            return true;
        else if (y % 100 == 0)
            return false;
        else if (y % 4 == 0)
            return true;
        else
            return false;
    }

    static public boolean valid(int y, int m, int d)
	{
		if (m<1 || m>12 || d<1) return false;
		switch(m){
			case 1: case 3: case 5: case 7:
			case 8: case 10: case 12:
					 return d<=31; 
			case 4: case 6: case 9: case 11:
					 return d<=30; 
			case 2:
					 if (isLeapYear(y))
						 return d<=29; 
					 else
						 return d<=28; 
		}
		return false;
	}

    @Override
    public Day clone() {
        Day copy = null;
        try{
            copy = (Day) super.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        
        return copy;
    }

    public void set(String sDay) throws ExInvaildDate{ 
        String[] sDayParts = sDay.split("-");
        if(sDayParts.length != 3)
            throw new ExInvaildDate();
        this.day = Integer.parseInt(sDayParts[0]);
        this.year = Integer.parseInt(sDayParts[2]);
        int monthIndex = MonthNames.indexOf(sDayParts[1]);
        if(monthIndex == -1) throw new ExInvaildDate();
        this.month = monthIndex / 3 + 1;
        if(!valid(this.year, this.month, this.day)) throw new ExInvaildDate();
    }

    public static Day plus(Day startDay, int plusDays){
        Day endDay = new Day(startDay);
        for(int i = 0; i < plusDays - 1; i++){
            if(endDay.isEndOfAMonth()){
                if(endDay.getYear() == 12)
                    endDay.setMonth(1);
                else
                    endDay.setMonth(endDay.getMonth() + 1);
                endDay.setDay(1);
            }else{
                endDay.setDay(endDay.getDay() + 1);
            }
        }
        return endDay;
    }

    public void setDay(int day){
        this.day = day;
    }

    public void setMonth(int month){
        this.month = month;
    }

    public void setYear(int year){
        this.year = year;
    }

    public int getDay(){
        return day;
    }

    public int getMonth(){
        return month;
    }

    public int getYear(){
        return year;
    }

    public boolean isEndOfAMonth() 
	{
		if (valid(year,month,day+1)) 
			return false;
		else
			return true;
    }
    
    public static boolean compareProjectStartDate(Day sysDate, Day date) throws ExProjectDateStartEarlist{
        int sysDateInt = sysDate.getYear() + sysDate.getMonth() + sysDate.getDay();
        int dateInt = date.getYear() + date.getMonth() + date.getDay();
        if (sysDateInt != dateInt)
            return true;

        throw new ExProjectDateStartEarlist();
    }

    public static boolean compareProjectAvaiable(Project project, Project chkProject){
        int projectStartDateInt = project.getStartDay().getDateInt();
        int projectEndDateInt = project.getEndDay().getDateInt();
        int chkStartDateInt = chkProject.getStartDay().getDateInt();
        int chkEndDateInt = chkProject.getEndDay().getDateInt();
        if((chkStartDateInt < projectStartDateInt && (chkEndDateInt < projectEndDateInt 
            && chkEndDateInt < projectStartDateInt)) || ((chkStartDateInt > projectStartDateInt && chkStartDateInt > projectEndDateInt )&& chkEndDateInt > projectEndDateInt))
            return true;

        return false;
    }


    public Day next() 
	{
		if (isEndOfAMonth())
			if (month==12)
				return new Day(year+1,1,1); 
			else
				return new Day(year, month+1, 1); 
		else
			return new Day(year, month, day+1); 
    }
    
    public int getDateInt(){
        return day + month * 100 + year;
    }
    
    public String toString() {
        return day + "-" + MonthNames.substring((month - 1) * 3, month * 3) + "-" + year; // (month-1)*3,(month)*3
    }
}