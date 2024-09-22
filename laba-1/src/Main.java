public class Main {
    public static void main(String[] args) {
        short[] z = new short[13];

        for (short i = 0; i < 13; i++) {
            z[i] = (short) (6 + i);
        }

        double[] x = new double[19];

        double min = -14.0d, max = 10.0d;
        for (int i = 0; i < x.length; i++) {
            x[i] = Math.random() * (max - min) + min;
        }

        double[][] с1 = new double[13][19];

        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 19; j++) {
                с1[i][j] = switch ((int) z[i]) {
                    case 9, 11, 12, 15, 16, 17:
                        yield Math.cbrt(Math.cbrt(Math.cbrt(x[j])));
                    case 7:
                        yield Math.pow((Math.pow((Math.log(Math.abs(x[j]))), (0.25 / Math.pow(2 * x[j], 2)))), (2 * Math.cos(Math.log(Math.abs(x[j])))));
                    default:
                        yield Math.pow(((Math.asin(0.25 * (1 / Math.pow(Math.E, Math.abs(x[j]))))) / (2 - Math.log(Math.pow(((Math.pow(Math.sin(x[j]), 2)) / 2), 2)))), (Math.cos(Math.cos(Math.tan(x[j])))));
                };
            }
        }

        for (double[] f : с1) {
            for (double e1 : f) {
                System.out.printf("%9.5f", e1);
            }
            System.out.println();
        }
    }
}