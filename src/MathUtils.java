import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class MathUtils {

    public static float randomizeNumber(float toRange) {
        Random random = new Random();
        return round((random.nextFloat() * (toRange * 2)) - toRange, 2);
    }

    public static float round(float value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bigDecimal = new BigDecimal(Double.toString(value));
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.floatValue();
    }
}
