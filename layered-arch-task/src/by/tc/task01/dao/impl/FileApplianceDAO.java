package by.tc.task01.dao.impl;

import by.tc.task01.dao.ApplianceDAO;
import by.tc.task01.entity.*;
import by.tc.task01.entity.criteria.Criteria;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileApplianceDAO implements ApplianceDAO {

	@Override
	public <E> Appliance find(Criteria<E> criteria) throws IOException {
		try {
			BufferedReader br = new BufferedReader(new FileReader("D:/WorkSpace/layered-arch-task/src/resources/appliances_db.txt"));
			String tmp;
			while ((tmp = br.readLine()) != null) {
				if (tmp.startsWith(criteria.getApplianceType())) {
					if (theRightLine(criteria, tmp)) {
						if (criteria.getApplianceType().equals("Laptop")) {
							return createLaptopObject(tmp);
						}
						if (criteria.getApplianceType().equals("Oven")) {
							return createOvenObject(tmp);
						}
						if (criteria.getApplianceType().equals("Refrigerator")) {
							return createRefrigeratorObject(tmp);
						}
						if (criteria.getApplianceType().equals("Speakers")) {
							return createSpeakersObject(tmp);
						}
						if (criteria.getApplianceType().equals("TabletPC")) {
							return createTabletPCObject(tmp);
						}
						if (criteria.getApplianceType().equals("VacuumCleaner")) {
							return createVacuumCleanerObject(tmp);
						}
					}
				}
			}
			return null;
		}
		catch(FileNotFoundException e) {
			System.err.println("File isn't available.");
		}
		catch (IOException e) {
			System.err.println(" I/O exception.");
		}
		return null;
	}

	private List<String> splitTheCriterion(String tmp) {

		List<String> nameAndVal = new ArrayList<String>();
		Pattern criterion = Pattern.compile("\\p{Blank}[A-Z_]+=[\\w+-?]+[,;]");
		Matcher m = criterion.matcher(tmp);
		while(m.find()) {
			nameAndVal.add(tmp.substring(m.start() + 1, m.end() - 1));
		}
		return nameAndVal;
	}

	private <E> boolean theRightLine(Criteria<E> criteria, String tmp) {
		List<String> nameAndVal = splitTheCriterion(tmp);
		boolean flag = false;
		for (E searchCriteria : criteria.getCriteria().keySet()) {
			String s = searchCriteria.toString() + "=" + criteria.getValue(searchCriteria);
			for (String nameVal : nameAndVal) {
				if (nameVal.equals(s)) {
					flag = true;
					break;
				}
				flag = false;
			}
		}
		return flag;
	}

	private List<String> parseValues(String tmp) {
		List<String> values = new ArrayList<String>();
		Pattern parse = Pattern.compile("=(\\S)+");
		Matcher m = parse.matcher(tmp);
		while (m.find()) {
			values.add(tmp.substring(m.start() + 1, m.end() - 1));
		}
		return values;
	}

	private Appliance createOvenObject(String tmp) {
		List<String> values = parseValues(tmp);
		Oven oven = new Oven();
		oven.setPowerConsumption(Integer.parseInt(values.get(0)));
		oven.setWeight(Integer.parseInt(values.get(1)));
		oven.setCapacity(Integer.parseInt(values.get(2)));
		oven.setDepth(Integer.parseInt(values.get(3)));
		oven.setHeight(Double.parseDouble(values.get(4)));
		oven.setWidth(Double.parseDouble(values.get(5)));
		return oven;
	}

	private Appliance createLaptopObject(String tmp) {
		List<String> values = parseValues(tmp);
		Laptop laptop = new Laptop();
		laptop.setBatteryCapacity(Double.parseDouble(values.get(0)));
		laptop.setOs(values.get(1));
		laptop.setMemoryROM(Integer.parseInt(values.get(2)));
		laptop.setSystemMemory(Integer.parseInt(values.get(3)));
		laptop.setCpu(Double.parseDouble(values.get(4)));
		laptop.setDisplayInchs(Integer.parseInt(values.get(5)));
		return laptop;
	}

	private Appliance createRefrigeratorObject(String tmp) {
		List<String> values = parseValues(tmp);
		Refrigerator refrigerator = new Refrigerator();
		refrigerator.setPowerConsumption(Integer.parseInt(values.get(0)));
		refrigerator.setWeight(Integer.parseInt(values.get(1)));
		refrigerator.setFreezerCapacity(Integer.parseInt(values.get(2)));
		refrigerator.setOverallCapacity(Double.parseDouble(values.get(3)));
		refrigerator.setHeight(Integer.parseInt(values.get(4)));
		refrigerator.setWidth(Integer.parseInt(values.get(5)));
		return refrigerator;
	}

	private Appliance createSpeakersObject(String tmp) {
		List<String> values = parseValues(tmp);
		Speakers speakers = new Speakers();
		speakers.setPowerConsumption(Integer.parseInt(values.get(0)));
		speakers.setNumberSpeakers(Integer.parseInt(values.get(1)));
		speakers.setFrequencyRange(values.get(2));
		speakers.setCordLength(Integer.parseInt(values.get(3)));
		return speakers;
	}

	private Appliance createTabletPCObject(String tmp) {
		List<String> values = parseValues(tmp);
		TabletPC tabletPC = new TabletPC();
		tabletPC.setBatteryCapacity(Integer.parseInt(values.get(0)));
		tabletPC.setDisplayInches(Integer.parseInt(values.get(1)));
		tabletPC.setMemoryRom(Integer.parseInt(values.get(2)));
		tabletPC.setFlashMemoryCapacity(Integer.parseInt(values.get(3)));
		tabletPC.setColor(values.get(4));
		return tabletPC;
	}

	private Appliance createVacuumCleanerObject(String tmp) {
		List<String> values = parseValues(tmp);
		VacuumCleaner vacuumCleaner = new VacuumCleaner();
		vacuumCleaner.setPowerConsumption(Integer.parseInt(values.get(0)));
		vacuumCleaner.setFilterType(values.get(1));
		vacuumCleaner.setBagType(values.get(2));
		vacuumCleaner.setWandType(values.get(3));
		vacuumCleaner.setMotorSpeedRegulation(Integer.parseInt(values.get(4)));
		vacuumCleaner.setCleaningWidth(Integer.parseInt(values.get(5)));
		return vacuumCleaner;
	}
}

