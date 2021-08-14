package by.tc.task01.main;

import static by.tc.task01.entity.criteria.SearchCriteria.*;

import by.tc.task01.entity.Appliance;
import by.tc.task01.entity.criteria.Criteria;
import by.tc.task01.service.ApplianceService;
import by.tc.task01.service.ServiceFactory;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		Appliance appliance;

		ServiceFactory factory = ServiceFactory.getInstance();
		ApplianceService service = factory.getApplianceService();

		//////////////////////////////////////////////////////////////////


		Criteria<Oven> criteriaOven = new Criteria<Oven>();
		criteriaOven.setApplianceType("Oven");
		criteriaOven.add(Oven.CAPACITY, 33);

		appliance = service.find(criteriaOven);

		PrintApplianceInfo.print(appliance, criteriaOven.getApplianceType());

		//////////////////////////////////////////////////////////////////

		Criteria<Laptop> criteriaLaptop = new Criteria<Laptop>();
		criteriaLaptop.setApplianceType("Laptop");
		criteriaLaptop.add(Laptop.MEMORY_ROM, 8000);
		criteriaLaptop.add(Laptop.DISPLAY_INCHS, 19);

		appliance = service.find(criteriaLaptop);

		PrintApplianceInfo.print(appliance, criteriaLaptop.getApplianceType());

		//////////////////////////////////////////////////////////////////

		Criteria<TabletPC> criteriaTabletPC = new Criteria<TabletPC>();
		criteriaTabletPC.setApplianceType("TabletPC");
		criteriaTabletPC.add(TabletPC.COLOR, "BLUE");
		criteriaTabletPC.add(TabletPC.DISPLAY_INCHES, 14);
		criteriaTabletPC.add(TabletPC.MEMORY_ROM, 8000);

		appliance = service.find(criteriaTabletPC);

		PrintApplianceInfo.print(appliance, criteriaTabletPC.getApplianceType());

		///////////////////////////////////////////////////////////////

		Criteria<Speakers> criteriaSpeakers = new Criteria<Speakers>();
		criteriaSpeakers.setApplianceType("Speakers");
		criteriaSpeakers.add(Speakers.FREQUENCY_RANGE, "2-4");
		criteriaSpeakers.add(Speakers.CORD_LENGTH, "2");

		appliance = service.find(criteriaSpeakers);

		PrintApplianceInfo.print(appliance, criteriaSpeakers.getApplianceType());

		//////////////////////////////////////////////////////////////////

		Criteria<VacuumCleaner> cleanerCriteria = new Criteria<VacuumCleaner>();
		cleanerCriteria.setApplianceType("VacuumCleaner");
		cleanerCriteria.add(VacuumCleaner.BAG_TYPE,"A2");
		cleanerCriteria.add(VacuumCleaner.WAND_TYPE,"all-in-one");
		cleanerCriteria.add(VacuumCleaner.FILTER_TYPE, "B");
		cleanerCriteria.add(VacuumCleaner.POWER_CONSUMPTION,110);
		cleanerCriteria.add(VacuumCleaner.MOTOR_SPEED_REGULATION, 2900);
		cleanerCriteria.add(VacuumCleaner.CLEANING_WIDTH, 25);
		appliance  = service.find(cleanerCriteria);
		PrintApplianceInfo.print(appliance, cleanerCriteria.getApplianceType());
	}

}
