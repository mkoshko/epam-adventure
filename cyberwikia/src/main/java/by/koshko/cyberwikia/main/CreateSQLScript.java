package by.koshko.cyberwikia.main;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.json.JSONArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringJoiner;

public class CreateSQLScript {
    public static void main(final String[] args) throws IOException {
        createUser();
    }

    private static void createUser() throws IOException {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        StringJoiner joiner = new StringJoiner(";\n");
        for (int i = 0; i < 100; i++) {
            String password = argon2.hash(4, 1024 * 1024, 4, String.format("user%d", i));
            joiner.add(String.format("INSERT INTO user (login, email, password, role) VALUES ('user%d', 'user%d@gmail.com', '%s', 2)", i, i, password));
        }
        Files.writeString(Paths.get("sql/5_fill_user_table.sql"), joiner.toString());
    }

    private static void createCountry() throws IOException {
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
        Files.writeString(Paths.get("sql/4_fill_countries_table.sql"), joiner.toString());
    }
}
