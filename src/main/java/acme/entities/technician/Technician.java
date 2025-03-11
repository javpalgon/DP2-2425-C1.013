
package acme.entities.technician;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import acme.client.components.basis.AbstractRole;
import acme.client.components.mappings.Automapped;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidNumber;
import acme.client.components.validation.ValidString;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Technician extends AbstractRole {

	//TODO: Add validation class

	private static final long	serialVersionUID	= 1L;

	@Mandatory
	@NotBlank
	@ValidString(pattern = "^[A-Z]{2-3}\\d{6}$")
	@Column(unique = true)
	private String				licenseNumber;

	@Mandatory
	@NotBlank
	@ValidString(pattern = "^+?\\d{6,15}$")
	@Column(unique = true)
	private String				phoneNumber;

	@Mandatory
	@NotBlank
	@ValidString(max = 50)
	@Automapped
	private String				specialisation;

	@Mandatory
	@Valid
	@Automapped
	private Boolean				passedHealthTest;

	@Mandatory
	@ValidNumber(min = 0, max = 100)
	@Automapped
	private Integer				yearsOfExperience;

	@Optional
	@ValidString(max = 255)
	@Automapped
	private String				certifications;
}
