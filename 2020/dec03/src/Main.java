public class Main {
    private static class Slope {
        public int x;
        public int y;

        Slope(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return String.format("(%d, %d)", x, y);
        }
    }

    public static void main(String[] args) {
        for (var i = 0; i < args.length; ++i) {
            System.out.printf("Arg %d = '%s'\n", i, args[i]);
        }
        if (args.length != 1) {
            System.out.println("Usage: java Part1 <input_file>");
            return;
        }

        TreeMap map = new TreeMap(args[0]);
        map.Read();
        TobogganRunner tb = new TobogganRunner(map);

        Slope[] slopesToTest = {
                new Slope(1, 1),
                new Slope(3, 1),
                new Slope(5, 1),
                new Slope(7, 1),
                new Slope(1, 2)};

        int treesHit;
        long treeMultiple = 1;
        for (Slope s: slopesToTest) {
            treesHit = tb.RunWithSlope(s.x, s.y);
            treeMultiple *= treesHit;
            System.out.printf("Slope %s:  %d\n", s.toString(), treesHit);
        }

        System.out.printf("Tree multiple: %d\n", treeMultiple);
    }
}
