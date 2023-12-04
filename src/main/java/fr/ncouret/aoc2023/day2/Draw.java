package fr.ncouret.aoc2023.day2;

public record Draw(int reds, int greens, int blues) {

    static Draw parse(String drawString) {
        var split = drawString.split(",");
        int blues = 0, reds = 0, greens = 0;
        for (var cubes : split) {
            cubes = cubes.trim();
            var split1 = cubes.split("\\s");
            int number = Integer.parseInt(split1[0]);

            if (split1[1].equals("blue")) {
                blues = number;
            } else if (split1[1].equals("green")) {
                greens = number;
            } else {
                reds = number;
            }
        }
        return new Draw(reds, greens, blues);
    }

    @Override
    public String toString() {
        return "{ r: " + reds + ", g: " + greens + ", b: " + blues + " }";
    }
}
