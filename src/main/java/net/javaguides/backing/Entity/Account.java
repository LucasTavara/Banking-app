package net.javaguides.backing.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "accounts")
@Entity
public class  Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accout_id")
    private Long id;

    @Column(name = "account_holder_name")
    private String accountHolderName;
    private double balance;
}
