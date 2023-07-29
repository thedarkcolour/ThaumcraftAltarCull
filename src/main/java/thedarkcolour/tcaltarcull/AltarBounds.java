package thedarkcolour.tcaltarcull;

public class AltarBounds {
    public final int minX, minY, minZ, maxX, maxY, maxZ;
    public boolean shouldRender;

    public AltarBounds(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;

        // true until culled
        this.shouldRender = true;
    }
}
