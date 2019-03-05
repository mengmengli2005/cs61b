import synthesizer.BoundedQueue;
import synthesizer.GuitarString;

import java.util.HashMap;
import java.util.Map;


public class GuitarHero {
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static final char[] keyboardArray = keyboard.toCharArray();
    private static final Double CONCERT = 440.0;

    public static void main(String[] args) {

        /** method 1 */
        Map<Character, Double> mapKeyBoard = new HashMap<>(37); // key: stores the keyboard characters; value: stores frequency.
        GuitarString[] KeyBoard = new GuitarString[37]; // stores GuitarString objects.
        for (int i = 0; i < keyboard.length(); i ++) {
            Double CONCERTS = CONCERT * Math.pow(2, (i - 24) / 12);
            mapKeyBoard.put(keyboardArray[i], CONCERTS);
            KeyBoard[i] = new GuitarString(CONCERTS);
        }
        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (key == 'q') {
                    KeyBoard[0].pluck();
                } else if (key == '2') {
                    KeyBoard[1].pluck();
                }
            }
            /* compute the superposition of samples */
            double sample = KeyBoard[0].sample() + KeyBoard[1].sample();
            /* play the sample on standard audio */
            StdAudio.play(sample);
            /* advance the simulation of each guitar string by one step */
            KeyBoard[0].tic();
            KeyBoard[1].tic();
        }

        /** method 2 with bugs */
//        Map<Character, GuitarString> mapKeyBoard = new HashMap<>(37); // key: stores the keyboard characters; value: stores frequency.
//        for (int i = 0; i < 37; i ++) {
//            Double CONCERTS = CONCERT * Math.pow(2, (i - 24) / 12);
//            GuitarString GG = new GuitarString(CONCERTS);
//            mapKeyBoard.put(keyboardArray[i], GG);
//        }
//        while(true) {
//            /* check if the user has typed a key; if so, process it */
//            if (StdDraw.hasNextKeyTyped()) {
//                char key = StdDraw.nextKeyTyped();
//                mapKeyBoard.get(key).pluck();
//            }
//            /* compute the superposition of samples */
//
//
//            /* play the sample on standard audio */
//
//            /* advance the simulation of each guitar string by one step */
//            for (Character c : keyboardArray) {
//                mapKeyBoard.get(c).tic();
//            }
//            double sample = mapKeyBoard.get("q").sample();
//            StdAudio.play(sample);
//        }
    }

}
