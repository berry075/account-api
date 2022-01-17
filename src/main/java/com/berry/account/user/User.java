package com.berry.account.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@NoArgsConstructor(force = true)
@Entity
@Table(
    name = "user",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "tel")
    }
)
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, length = 100)
    @NotNull
    private String email;

    @Column(unique = true, length = 15)
    @NotNull
    private String tel;

    @NotNull
    private String name;

    @NotNull
    private String nickname;

    @NotNull
    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp
    private Timestamp signUpAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @UpdateTimestamp
    private Timestamp lastUpdateAt;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Builder
    public User(String email, String tel, String name, String nickname, String password) {
        this.email = email;
        this.tel = tel;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.role = UserRole.USER;
    }

    @PrePersist
    public void setup() {
        this.role = UserRole.USER;
    }
}
