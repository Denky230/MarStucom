
package marstucom;

import input.InputHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import persistence.MarStucomDAO;

public class MarStucom {

    public static void main(String[] args) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            InputHandler in = InputHandler.getInstance();
            MarStucomDAO dao = MarStucomDAO.getInstance();
            dao.connect();

            // Read user input
            String line;
            while ((line = br.readLine()) != null) {

                try {
                    in.processInput(line);

                } catch (RuntimeException | SQLException | IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (RuntimeException | SQLException | IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                MarStucomDAO.getInstance().disconnect();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
