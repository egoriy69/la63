package com.example.diplom33.models;

import com.example.diplom33.enumeration.ClientStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.internal.util.stereotypes.ThreadSafe;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client_info")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ClientStatus status;

    @Column(name = "password")
    private String password;

    @Column(name = "login")
    private String login;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "client")
    private List<Deal> deals;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Client client = (Client) o;
//
//        if (id != null ? !id.equals(client.id) : client.id != null) return false;
//        if (comment != null ? !comment.equals(client.comment) : client.comment != null) return false;
//        if (status != client.status) return false;
//        if (password != null ? !password.equals(client.password) : client.password != null) return false;
//        if (login != null ? !login.equals(client.login) : client.login != null) return false;
//        if (user != null ? !user.equals(client.user) : client.user != null) return false;
//        return deals != null ? deals.equals(client.deals) : client.deals == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id != null ? id.hashCode() : 0;
//        result = 31 * result + (comment != null ? comment.hashCode() : 0);
//        result = 31 * result + (status != null ? status.hashCode() : 0);
//        result = 31 * result + (password != null ? password.hashCode() : 0);
//        result = 31 * result + (login != null ? login.hashCode() : 0);
//        result = 31 * result + (user != null ? user.hashCode() : 0);
//        result = 31 * result + (deals != null ? deals.hashCode() : 0);
//        return result;
//    }
}
