package com.vyb.coronatracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.vyb.coronatracker.models.LocationStats;

@Service
public class CoronaVirusService {

	private String CORONA_VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private List<LocationStats> allStats = new ArrayList<>();
	
	public List<LocationStats> getAllStats() {
		return allStats;
	}
	
	@PostConstruct
	@Scheduled(cron = "0 0/30 * * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		
		List<LocationStats> newStats = new ArrayList<>();

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(CORONA_VIRUS_DATA_URL)).build();
		BodyHandler<String> responseBodyHandler = HttpResponse.BodyHandlers.ofString();
		HttpResponse<String> httpResponse = client.send(request, responseBodyHandler);
		
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		System.out.println("record size=>"+records.toString().isEmpty());
		for (CSVRecord csvRecord : records) {
			System.out.println("csv =>"+csvRecord);
			LocationStats location = new LocationStats();
			location.setState(csvRecord.get(0));
			location.setCountry(csvRecord.get(1));
			
			String csvrec1 = csvRecord.get(csvRecord.size()-1).trim();
			System.out.println("csvrec1=>"+csvrec1);
			if ("".equals(csvrec1)) {
				csvrec1 = "0";
				
			}
			
			String csvrec2 = csvRecord.get(csvRecord.size()-2).trim();
			System.out.println("csvrec2=>"+csvrec2);
			if ("".equals(csvrec2)) {
				csvrec2 = "0";
			}
			
			int latestCases = Integer.parseInt(csvrec1);
			int previousCases = Integer.parseInt(csvrec2);
			location.setTotalCases(latestCases);
			location.setDiffFromPrevious(latestCases - previousCases);
			//System.out.println(location);
			if (!csvrec1.equals("0")) {
				newStats.add(location);
			}
			
		}
		this.allStats = newStats;
	}

}
