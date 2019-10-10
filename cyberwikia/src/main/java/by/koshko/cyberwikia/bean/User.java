package by.koshko.cyberwikia.bean;

import java.util.Objects;

/**
 * User entity class.
 */
public final class User extends Entity {

    private static final long serialVersionUID = 1948680934600028453L;
    /**
     * User role.
     * 0 - Administrator.
     * 1 - Event moderator.
     * 2 - User.
     */
    private Role role;
    /**
     * Users login.
     */
    private String login;
    /**
     * Users email address.
     */
    private String email;
    /**
     * Users password hash value.
     */
    private String password;

    /**
     * Returns the {@link #role} of the user.
     *
     * @return the role of the user.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the {@link #role} of the user.
     *
     * @param userRole New value to be set to the users {@link #role}.
     */
    public void setRole(final int userRole) {
        role = Role.valueOf(userRole);
    }

    /**
     * Returns the {@link #login} of the user.
     *
     * @return the login of the user.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the login of the user.
     *
     * @param userLogin The new string value to be set to users {@link #login}
     *                  value.
     */
    public void setLogin(final String userLogin) {
        login = userLogin;
    }

    /**
     * Returns the {@link #email} address value of the user.
     *
     * @return the email address of value the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the {@link #email} value.
     *
     * @param userEmail New email address value to be set to the users
     *                  {@link #email} value.
     */
    public void setEmail(final String userEmail) {
        email = userEmail;
    }

    /**
     * Returns the {@link #password} of the user.
     *
     * @return the password value.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the {@link #password} value.
     *
     * @param userPassword New value to be set to users {@link #password} value.
     */
    public void setPassword(final String userPassword) {
        password = userPassword;
    }

    @Override
    public String toString() {
        return "User{"
               + "role=" + role
               + ", login='" + login + '\''
               + ", email='" + email + '\''
               + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(getId(), user.getId())
               && role == user.role
               && login.equals(user.login)
               && email.equals(user.email)
               && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, login, email, password, getId());
    }
}
