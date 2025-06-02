package org.example.menu.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "IVT_MENU")
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @Id
    @Column(name = "MENU_CD")
    private String menuCd;

    @Column(name = "MENU_NM")
    private String menuNm;

    @Column(name = "PRNT_MENU_CD")
    private String prntMenuCd;

    @Column(name = "MENU_URL")
    private String menuUrl;
}
