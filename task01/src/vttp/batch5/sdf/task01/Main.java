package vttp.batch5.sdf.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
public class Main {

	public static void main(String[] args) throws IOException {
		//top 5 days with the most cyclists in the following format
		//sort by position from highest to lowest based on the number of cyclists.
		//return the rest of the information
		String currLine;
		String fileDir = "C:\\Users\\Admin\\Desktop\\vttp_b5_assessment_template\\task01\\day.csv";
		File file = new File(fileDir);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		List<Items> unfilteredList = new ArrayList<>();
		while (br.readLine()!=null){//skips the first line
            currLine = br.readLine(); 
			String[] tempList = currLine.split(",");//add the split data into an array.
			//season,mnth,holiday,weekday,weathersit,temp,hum,windspeed,casual,registered
			//total is sum of registered and casual riders
			//position season day month total weathersit holiday
			//if sunday, will have error in utilities. use this to bypass
			String dayofWeek;
			if (Integer.parseInt(tempList[3])==0){
				dayofWeek= "Sunday";
			}
			else{
				dayofWeek= Utilities.toWeekday(Integer.parseInt(tempList[3]));
			}
			unfilteredList.add(new Items(Utilities.toSeason(Integer.parseInt(tempList[0])), dayofWeek, Utilities.toMonth(Integer.parseInt(tempList[1])), (Integer.parseInt(tempList[8])+Integer.parseInt(tempList[9])),getWeatherSit(Integer.parseInt(tempList[4])), Integer.parseInt(tempList[2])));
		}
		br.close();
		//turn list into a stream, filter and sort and collect into a usable output
		List<Items> filteredList = new ArrayList<Items>();
		filteredList = unfilteredList.stream().sorted(Comparator.comparingInt(Items::getTotal).reversed()).limit(5).filter((x -> !x.getWeekday().equals("incorrect day"))).collect(Collectors.toList()); //sort according to criteria. rank them.
        //for each item in the filtered list, append the position based on the position
		for (int i =0; i <filteredList.size(); i++){
			System.out.println(getPosition(i)+filteredList.toArray()[i]);
		}
		//highest, second highest, third highest, fourth highest, fifth highest

		

	}
	public static String getPosition(int count) {
		String[] POSITION = { "The highest", "The second highest", "The third highest", "The fourth highest", "The fifth highest" };
        switch (count) {
			case 0:
			case 1:
			case 2:
			case 3:
            case 4:
                return POSITION[count];
            default:
                return "Invalid position.";
        }           
    }
	public static String getWeatherSit(int weatherSit) {
		String[] SEASON = { "Clear, Few clouds, Partly cloudy, Partly cloudy", "Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist", "Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds", "Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog" };
        switch (weatherSit) {
			case 1:
			case 2:
			case 3:
			case 4:
                return SEASON[weatherSit-1];
            default:
                return "Invalid weather.";
        }           
    }
}
