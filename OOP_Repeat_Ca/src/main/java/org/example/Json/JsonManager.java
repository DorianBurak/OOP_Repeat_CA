package org.example.Json;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.List;
import org.example.DTOs.weaponDTO;

public class JsonManager {

    public static JSONObject weaponToJson(weaponDTO weapon) {
        if (weapon == null) {
            return null;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", weapon.getId());
        jsonObject.put("name", weapon.getName());
        jsonObject.put("type", weapon.getType());
        jsonObject.put("weight", weapon.getWeight());
        jsonObject.put("durability", weapon.getDurability());
        jsonObject.put("attack", weapon.getAttack());
        jsonObject.put("motivity", weapon.getMotivity());
        jsonObject.put("technique", weapon.getTechnique());
        return jsonObject;
    }

    public static JSONArray weaponListToJson(List<weaponDTO> weapons) {
        if (weapons == null) {
            return new JSONArray();
        }
        JSONArray jsonArray = new JSONArray();
        for (weaponDTO weapon : weapons) {
            jsonArray.put(weaponToJson(weapon));
        }
        return jsonArray;
    }
}
