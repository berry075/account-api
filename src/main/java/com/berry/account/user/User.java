package com.berry.account.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Schema(description = "사용자")
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

    @Schema(description = "고유아이디 - 자동생성", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "이메일", example = "abc@examemail.com")
    @Column(unique = true, length = 100)
    @NotNull
    private String email;

    @Schema(description = "전화번호", example = "01033331234")
    @Column(unique = true, length = 15)
    @NotNull
    private String tel;

    @Schema(description = "이름")
    @NotNull
    private String name;

    @Schema(description = "닉네임")
    @NotNull
    private String nickname;

    @Schema(description = "암호화 된 비밀번호")
    @NotNull
    private String password;

    @Schema(description = "가입날짜")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp
    private Timestamp signUpAt;

    @Schema(description = "정보변경날짜")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @UpdateTimestamp
    private Timestamp lastUpdateAt;

    @Schema(description = "권한")
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
