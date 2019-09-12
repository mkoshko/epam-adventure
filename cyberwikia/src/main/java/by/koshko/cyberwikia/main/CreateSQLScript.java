package by.koshko.cyberwikia.main;

import org.json.JSONArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringJoiner;

public class CreateSQLScript {
    public static void main(final String[] args) throws IOException {
        String str = Files.readString(Paths.get("data/countries.json"));
        JSONArray array = new JSONArray(str);
        StringJoiner joiner = new StringJoiner("\n");
        int len = array.length();
        for (int i = 0; i < len; i++) {
            joiner.add(String.format("INSERT INTO country (name, code, icon_file) VALUES ('%s', '%s', '%s');",
                    array.getJSONObject(i).get("Name"),
                    array.getJSONObject(i).get("Code"),
                    "images/flags/" + array.getJSONObject(i).get("Code").toString().toLowerCase() + ".png"));
        }
        Files.writeString(Paths.get("sql/fill_countries_table.sql"), joiner.toString());
    }
}
