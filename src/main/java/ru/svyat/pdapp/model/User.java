package ru.svyat.pdapp.model;

import lombok.*;
import ru.svyat.pdapp.dto.enums.UserRole;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@Column(name = "username")
	private String login;
	private String password;
	private Boolean enabled;

	@ElementCollection
	@CollectionTable(name = "authorities", joinColumns = @JoinColumn(name = "username"))
	@Column(name="authority")
	@Enumerated(EnumType.STRING)
	private List<UserRole> roles;
}
