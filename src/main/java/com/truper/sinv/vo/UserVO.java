package com.truper.sinv.vo;

import java.util.Collection;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cgarcias Extiende de Serializable para poder almacenar el objeto en
 *         session y no tener problemas al persistirla Extiende de UserDetails
 *         para poder leer las variables desde spring y poder realizar la
 *         autenticaci�n correctamente
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserVO implements java.io.Serializable, UserDetails {

	private static final long serialVersionUID = 1123231449857349875L;

	private String user;

	@JsonIgnore
	private String password;

	private String nombre;

	private String apellidos;

	private String token;

	private Map<String, Boolean> roles;

	private String rol;

	private Collection<? extends GrantedAuthority> aut;

	public UserVO(String user, String password) {
		this.user = user;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return aut;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> aut) {
		this.aut = aut;
	}

	@Override
	public String getUsername() {
		return this.user;
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
