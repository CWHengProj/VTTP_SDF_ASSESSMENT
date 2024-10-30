package vttp.batch5.sdf.task01;
//position season day month total weathersit holiday

public class Items {
    private String season;
    private String weekday;
    private String month;
    private int total;
    private String weatherSit;
    private int isHoliday;
    public Items(String season, String weekday, String month, int total, String weatherSit, int isHoliday) {
        this.season = season;
        this.weekday = weekday;
        this.month = month;
        this.total = total;
        this.weatherSit = weatherSit;
        this.isHoliday = isHoliday;
    }
    public String getSeason() {
        return season;
    }
    public void setSeason(String season) {
        this.season = season;
    }
    public String getWeekday() {
        return weekday;
    }
    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }
    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public String getWeatherSit() {
        return weatherSit;
    }
    public void setWeatherSit(String weatherSit) {
        this.weatherSit = weatherSit;
    }
    public String getIsHoliday() {
        if (isHoliday==0){
            return "not a holiday.\n";
        }
        else
            return "a holiday.\n";
    }
    @Override
    public String toString() {
        return " (position) recorded number of cyclists was in " + getSeason() +" (season), on a "+getWeekday()+ " (day) in the month of "+ getMonth()+" (month) .\nThere were a total of "+total +
        " (total) cyclists. The weather was "+ getWeatherSit()+" (weather) .\n"+getWeekday() + " (day) was "+ getIsHoliday();
        /*The <position> recorded number of cyclists was in
		<season>, on a <day> in the month of <month>. There were a total of <total> cyclists. 
		The weather was <weather>. <day> was <holiday>*/
    }

}
