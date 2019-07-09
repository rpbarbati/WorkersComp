/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workmanscomppre.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Rodney
 */
public class CellPhone {
	private int employeeId;
	private String employeeName;
	private int purchaseDate;
	private String model;
	private List<CellPhoneUsageByMonth> usageByMonth = new ArrayList<>();
			
	public CellPhone(String csvRow) {
		
		String[] fields = csvRow.split(",");
		
		employeeId = Integer.valueOf(fields[0]);
		employeeName = fields[1];
		purchaseDate = Integer.valueOf(fields[2]);
		model = fields[3];
	}
	
	public void addAll(List<CellPhoneUsageByMonth> cpubm) {
		usageByMonth.addAll(cpubm);
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public int getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(int purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	int monthlyMinutes = 0;
	float monthlyData = 0;

	public void getUsageForMonth(int month) {
		
		monthlyMinutes = 0;
		monthlyData = 0.0f;
		
		List<CellPhoneUsageByMonth> list = usageByMonth.stream()
			.filter(u -> u.getMonth() == month)
			.collect(Collectors.toList());
		
		list.forEach( mu -> {
				monthlyMinutes += mu.getTotalMinutes();
				monthlyData += mu.getTotalData();
			}
		);
	}
}
