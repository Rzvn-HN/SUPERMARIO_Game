package media;

import manager.GameEngine;
import model.Map;

public class Font {

    private static String chars = "" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ      " + "0123456789.,:;'\"!?$%()-=+/      ";

    public static void render(String msg, GameEngine game, double x, double y, int colour, int scale) {
        msg = msg.toUpperCase();

        for (int i = 0; i < msg.length(); i++) {
            int charIndex = chars.indexOf(msg.charAt(i));
//            if (charIndex >= 0)
////                game.render(x + (i * 8), y, charIndex + 30 * 32, colour, 0x00, scale);
        }
    }
}
