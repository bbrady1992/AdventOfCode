public class TobogganRunner {
    TobogganRunner(final TreeMap treeMap) {
        this.treeMap = treeMap;
    }

    public int RunWithSlope(int xSlope, int ySlope) {
        int treesHit = 0;
        int x = 0;
        int y = 0;
        while (!treeMap.AtBottom(y)) {
            treesHit += (treeMap.HitTreeAt(x, y) ? 1 : 0);
            x += xSlope;
            y += ySlope;
        }
        return treesHit;
    }

    private final TreeMap treeMap;
}
