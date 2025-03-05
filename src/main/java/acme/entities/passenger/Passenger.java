
package acme.entities.passenger;

import java.util.Date;

import javax.persistence.Entity;

import acme.client.components.basis.AbstractRole;
import acme.client.components.mappings.Automapped;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidEmail;
import acme.client.components.validation.ValidString;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Passenger extends AbstractRole {

	private static final long	serialVersionUID	= 1L;

	@Mandatory
	@Automapped
	@ValidString(min = 1, max = 255)
	private String				fullName;

	@Mandatory
	@Automapped
	@ValidEmail
	private String				email;

	@Mandatory
	@Automapped
	@ValidString(pattern = "^[A-Z0-9]{6,9}$")
	private String				passport;

	@Mandatory
	private Date				birth;

	@Optional
	@Automapped
	@ValidString(min = 1, max = 51)
	private String				specialNeeds;

	// HACER CUANDO CREE LA ENTIDAD DE BOOKING
	//@Mandatory
	//@Automapped
	//@ManyToOne
	//private Booking				booking;

}
