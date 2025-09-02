package org.example.Json;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.List;
import org.example.DTOs.weaponDTO;

public class JsonManager {

    public static String weaponListToJson(List<weaponDTO> weapons) {
        JSONArray jsonArray = new JSONArray();

        for (weaponDTO weapon : weapons) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", weapon.getId());
            jsonObject.put("name", weapon.getName());
            jsonObject.put("type", weapon.getType());
            jsonObject.put("weight", weapon.getWeight());
            jsonObject.put("durability", weapon.getDurability());
            jsonObject.put("attack", weapon.getAttack());
            jsonObject.put("motivity", weapon.getMotivity());
            jsonObject.put("technique", weapon.getTechnique());

            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

    public static String weaponToJson(weaponDTO weapon) {
        if (weapon == null) {
            return "{}";
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
        return jsonObject.toString();
    }
}
