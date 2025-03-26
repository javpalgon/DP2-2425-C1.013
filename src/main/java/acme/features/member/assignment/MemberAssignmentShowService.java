
package acme.features.member.assignment;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.models.Dataset;
import acme.client.services.AbstractGuiService;
import acme.client.services.GuiService;
import acme.entities.assignment.Assignment;
import acme.realms.Member;

@GuiService
public class MemberAssignmentShowService extends AbstractGuiService<Member, Assignment> {

	@Autowired
	private MemberAssignmentRepository repository;


	@Override
	public void authorise() {
		boolean status = super.getRequest().getPrincipal().hasRealmOfType(Member.class);
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		int assignmentId = super.getRequest().getData("id", int.class);
		super.getBuffer().addData(this.repository.findOneById(assignmentId));
	}

	@Override
	public void unbind(final Assignment object) {
		assert object != null;

		Dataset dataset = super.unbindObject(object, "role", "lastUpdate", "status", "remarks");

		// Leg (Flight) information
		dataset.put("flightNumber", object.getLeg().getFlightNumber());
		dataset.put("departure", object.getLeg().getDeparture());
		dataset.put("arrival", object.getLeg().getArrival());
		dataset.put("legStatus", object.getLeg().getStatus());
		dataset.put("departureAirport", object.getLeg().getDepartureAirport().getCity());
		dataset.put("arrivalAirport", object.getLeg().getArrivalAirport().getCity());
		dataset.put("aircraft", object.getLeg().getAircraft().getModel());

		// Member information
		dataset.put("employeeCode", object.getMember().getEmployeeCode());
		dataset.put("phoneNumber", object.getMember().getPhoneNumber());
		dataset.put("languageSkills", object.getMember().getLanguageSkills());
		dataset.put("yearsOfExperience", object.getMember().getYearsOfExperience());
		dataset.put("salary", object.getMember().getSalary());
		dataset.put("availabilityStatus", object.getMember().getAvailabilityStatus());
		dataset.put("memberName", object.getMember().getUserAccount().getUsername());

		super.getResponse().addData(dataset);
	}
}
