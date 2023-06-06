package com.example.neolabs.entity;

import com.example.neolabs.entity.base.BaseEntity;
import com.example.neolabs.enums.Role;
import com.example.neolabs.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity implements UserDetails {

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "email")
    @NotNull
    String email;

    @Column(name = "password")
    @NotNull
    String password;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "url_image", length = 5000)
    String urlImage;

    @ManyToOne
    @JoinColumn(columnDefinition = "course_id",
            referencedColumnName = "id")
    Course course;

    @ManyToOne
    @JoinColumn(columnDefinition = "subject_id",
            referencedColumnName = "id")
    Subject subject;

    @Enumerated(EnumType.STRING)
    @NotNull
    Role role;

    @Enumerated(EnumType.STRING)
    Status status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
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
