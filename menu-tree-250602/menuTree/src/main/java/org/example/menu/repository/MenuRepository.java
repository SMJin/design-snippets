package org.example.menu.repository;

import org.example.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String> {
    List<Menu> findByPrntMenuCdIsNull();
}
