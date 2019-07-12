/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workerscomppre.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Rodney
 */
public class WorkersCompPreInterview {

	List<CellPhone> cellPhoneData = new ArrayList<>();
	List<CellPhoneUsageByMonth> cellPhoneUsageData = new ArrayList<>();

	int numberOfPhones;

	int totalMinutes = 0;
	int totalData = 0;

	float averageMinutes;

	float averageData;
	
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		
		WorkersCompPreInterview wcpi = new WorkersCompPreInterview();
		
		wcpi.produceReport();
		
	}
	
	private void readCellPhoneData() {
		try (
				BufferedReader cellPhoneBR = getReaderForFile("CellPhone.csv");
			)	
		{
			boolean readHeader = false;

			while (cellPhoneBR.ready()) {
				String line = cellPhoneBR.readLine();

				if (readHeader)
					cellPhoneData.add(new CellPhone(line));

				readHeader = true;
			}
		}
		catch (IOException e){
			// Silently handle (I would never do this in production code)
		}
	}
	
	private void readCellPhoneUsageData() {
		
		try (
				BufferedReader cellPhoneUsageBR = getReaderForFile("CellPhoneUsageByMonth.csv");
			)	
		{
			// Read Cell Phone Usage

			boolean readHeader = false;

			while (cellPhoneUsageBR.ready()) {
				String line = cellPhoneUsageBR.readLine();

				if (readHeader)
					cellPhoneUsageData.add(new CellPhoneUsageByMonth(line));

				readHeader = true;
			}
		}
		catch (IOException e){
			// Silently handle (I would never do this in production code)
		}
	}

	private BufferedReader getReaderForFile(String name) {
		
		InputStream cellPhoneIS = getClass().getResourceAsStream(name);
		InputStreamReader isr = new InputStreamReader(cellPhoneIS);
		BufferedReader br = new BufferedReader(isr);

		return br;
	}
	
	
	public void readDataSources() {

		readCellPhoneData();
		
		readCellPhoneUsageData();
		
		correlateData();
	}
	
	
	/**
	 * Copy CellPhoneUsageData objects into related CellPhone object
	 */
	private void correlateData() {
		
		cellPhoneData.forEach( c -> {
		
			List<CellPhoneUsageByMonth> list = cellPhoneUsageData.stream()
				.filter( cpu -> cpu.getEmployeeId() == c.getEmployeeId())
				.collect(Collectors.toList());
			
			c.addAll(list);
		});
	}
	
	private void outputHeader() {

		System.out.println();
		System.out.print(new Date());
		
		numberOfPhones = cellPhoneData.size();
		
		// Calculate values
		cellPhoneUsageData.forEach( cpu -> {
			
			totalMinutes += cpu.getTotalMinutes();
			totalData += cpu.getTotalData();
		});

		averageMinutes = totalMinutes / numberOfPhones;
		averageData = totalData / numberOfPhones;
		
		System.out.println(numberOfPhones);
		System.out.println(totalMinutes);
		System.out.println(totalData);
		System.out.println(averageMinutes);
		System.out.println(averageData);
	}
	
	private void outputDetail() {
		
		cellPhoneData.forEach( c -> {

			System.out.print(c.getEmployeeId() + "\t");
			System.out.print(c.getEmployeeName() + "\t");
			System.out.print(c.getModel() + "\t");
			System.out.print(c.getPurchaseDate() + "\t");
			
			for (int i = 0; i < 12; i++) {
				
				c.getUsageForMonth(i + 1);
				
				System.out.print(c.monthlyMinutes + "\t");
				System.out.print(c.monthlyData + "\t");
			}
			System.out.println();
		});
	}
	
	private void produceReport() {
	
		readDataSources();
		
		// I am outputting raw data with no real formatting
		// If I was going to produce a formatted report, I would use 
		// a tool like JasperReports to do so.
		
		// However, Jasperreports has some issues with reading CSV data that
		// I did not have the time to debug

		outputHeader();
		
		outputDetail();
	}

}
