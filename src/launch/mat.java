/*
 * Math stuffs for launch
 */
package launch;

public class mat {

    static double btwn(double[] a, double[] b) {
        double lat1 = Math.toRadians(a[0]);
        double lat2 = Math.toRadians(b[0]);
        double lon1 = Math.toRadians(a[1]);
        double lon2 = Math.toRadians(b[1]);
        double dlat = Math.toRadians(b[0] - a[0]);
        double dlon = Math.toRadians(b[1] - a[1]);
        double u1 = Math.cos(lat2) * Math.sin(dlon);
        double u2 = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(dlon);
        double u = Math.pow(2, u1) + Math.pow(2, u2);
        double l = Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(dlon);
        double x = Math.pow(1 / 2, u) / l;
        return Math.atan(x);
    }

    static double rounded(int lat) {
        double EQUATOR = 40075.16;
        return EQUATOR * Math.cos(lat);
    }
}
