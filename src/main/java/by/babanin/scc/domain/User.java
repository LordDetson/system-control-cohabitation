package by.babanin.scc.domain;

import by.babanin.scc.repository.converter.CurrencyConverter;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.money.CurrencyUnit;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Table(name = "t_user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Size(min = 2, max = 50, message = "The first name can't be less than 2 or more than 50 characters")
    private String firstName;

    @Column(nullable = false)
    @Size(min = 2, max = 50, message = "The last name can't be less than 2 or more than 50 characters")
    private String lastName;

    @Column(unique = true, nullable = false)
    @Size(min = 2, max = 50, message = "The username can't be less than 2 or more than 50 characters")
    private String username;

    @Column(nullable = false)
    private String password;

    @Transient
    private String passwordConfirm;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(value = EnumType.STRING)
    private Set<Role> roles;

    @Convert(converter = CurrencyConverter.class)
    private CurrencyUnit currency;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getFullName() {
        return new StringJoiner(" ").add(firstName).add(lastName).toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
