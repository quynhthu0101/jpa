package vn.iotstar.entity;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private int id;

    @Column(name = "email", columnDefinition = "VARCHAR(255) NOT NULL")
    private String email;

    @Column(name = "username", columnDefinition = "VARCHAR(255) NOT NULL")
    private String username;

    @Column(name = "fullname", columnDefinition = "VARCHAR(255) NULL")
    private String fullname;

    @Column(name = "password", columnDefinition = "VARCHAR(255) NOT NULL")
    private String password;

    @Column(name = "avatar", columnDefinition = "VARCHAR(255) NULL")
    private String avatar;

    @Column(name = "phone", columnDefinition = "VARCHAR(15) NULL")
    private String phone;

    @Temporal(TemporalType.DATE)
    @Column(name = "createddate")
    private Date createddate;

    // Khóa ngoại roleid tham chiếu đến bảng roles
    @ManyToOne
    @JoinColumn(name = "roleid")
    private Role role;

    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
