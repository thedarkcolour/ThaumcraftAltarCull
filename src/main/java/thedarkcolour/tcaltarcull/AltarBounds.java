package thedarkcolour.tcaltarcull;

public class AltarBounds {

    public final int minX, minY, minZ, maxX, maxY, maxZ;
    public final double centerX, centerY, centerZ;
    public boolean shouldRender;

    public AltarBounds(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;

        this.centerX = maxX - minX / (float) 2;
        this.centerY = maxY - minY / (float) 2;
        this.centerZ = maxZ - minZ / (float) 2;

        // true until culled
        this.shouldRender = true;
    }
}
