package com.work.szs.user.domain;

import com.work.szs.common.converter.CryptoConverter;
import com.work.szs.common.entity.BaseEntity;
import com.work.szs.common.exception.BusinessInvalidValueException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Convert(converter = CryptoConverter.class)
    @Column(name = "regNo", nullable = false)
    private String regNo;

    // todo : 이미 가입된 사용자여부(RegNO), UserID 중복 체크
    private User(String userId, String password, String name, String regNo) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.regNo = regNo;
    }

    public static User of(String userId, String password, String name, String regNo) {
        return new User(userId, password, name, regNo);
    }

    public void validatePassword(String password, PasswordEncoder encoder) {
        if (!encoder.matches(password, this.password)) {
            throw new BusinessInvalidValueException("비밀번호를 확인해주세요.");
        }
    }
}
