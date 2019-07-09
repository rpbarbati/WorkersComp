/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workmanscomppre.interview;

/**
 *
 * @author Rodney
 */
public class CellPhoneUsageByMonth {
	private int employeeId;
	private String date;
	private int totalMinutes;
	private float totalData;
	
	
	public CellPhoneUsageByMonth(String csvRow) {
		String[] fields = csvRow.split(",");
		
		employeeId = Integer.valueOf(fields[0]);
		date = fields[1];
		totalMinutes = Integer.valueOf(fields[2]);
		totalData = Float.valueOf(fields[3]);
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public int getMonth() {
		return Integer.valueOf(date.substring(date.indexOf("/") + 1, date.lastIndexOf("/")));
	}

	public int getTotalMinutes() {
		return totalMinutes;
	}

	public void setTotalMinutes(int totalMinutes) {
		this.totalMinutes = totalMinutes;
	}

	public float getTotalData() {
		return totalData;
	}

	public void setTotalData(float totalData) {
		this.totalData = totalData;
	}
}
