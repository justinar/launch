/*
 * Math stuffs for launch
 */
package launch;

public class mat {

    static double btwn(double[] a, double[] b) {
        double dlat = b[0] - a[0];
        double dlon = b[1] - a[1];
        double x = Math.pow(2,Math.sin(dlat / 2)) + Math.cos(lat1)
                * Math.cos(lat2) * Math.pow(2,Math.sin(dlon / 2));
    }
}
