package leetCode.mayLeetCodingChallenge.week2;

public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];
        if (color != newColor) dfs(image, sr, sc, color, newColor);
        return image;
    }
    public void dfs(int[][] image, int r, int c, int color, int newColor) {
        if (image[r][c] == color) {
            image[r][c] = newColor;
            if (r >= 1) dfs(image, r-1, c, color, newColor);
            if (c >= 1) dfs(image, r, c-1, color, newColor);
            if (r+1 < image.length) dfs(image, r+1, c, color, newColor);
            if (c+1 < image[0].length) dfs(image, r, c+1, color, newColor);
        }
    }

    public static void main(String[] args) {
        int[][][] testImages = {
                {{0, 0, 0}, {0, 1, 1}}
        };

        int[] testXs = {
                1
        };

        int[] testYs = {
                1
        };

        int[] testColors = {
                1
        };

        for (int i = 0; i < testImages.length; i++) {
            FloodFill s = new FloodFill();
            System.out.println(s.floodFill(testImages[i], testXs[i], testYs[i], testColors[i]));
        }
    }
}
