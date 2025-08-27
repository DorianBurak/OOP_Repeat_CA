package org.example.DAOS;

import org.example.DTOs.weaponDTO;
import org.example.Exceptions.DaoException;

import java.util.List;

public interface WeaponDAOImpl {
    public List<weaponDTO> getAllEntites() throws DaoException;
}