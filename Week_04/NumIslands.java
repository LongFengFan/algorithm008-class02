//200. 岛屿数量
//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
//
//岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
//
//此外，你可以假设该网格的四条边均被水包围。
//
//
//
//示例 1:
//
//输入:
//11110
//11010
//11000
//00000
//输出: 1
//示例 2:
//
//输入:
//11000
//11000
//00100
//00011
//输出: 3
//解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
package Week_04;

public class NumIslands {
    public int numIslands(char[][] grid) {
        if(grid.length == 0) return 0;
        int count = 0;
        int width = grid.length;
        int height = grid[0].length;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    convert(grid, i, j, width, height);
                }
            }
        }
        return count;
    }

    private void convert(char[][] grid, int i, int j, int width, int height) {
        if (i < 0 || i > width || j < 0 || j > height) return;
        grid[i][j] = '0';
        int up = j - 1;
        int down = j + 1;
        int left = i - 1;
        int right = i + 1;

        if (up >= 0 && grid[i][up] == '1') convert(grid, i, up, width, height);
        if (down < height && grid[i][down] == '1') convert(grid, i, down, width, height);
        if (left >= 0 && grid[left][j] == '1') convert(grid, left, j, width, height);
        if (right < width && grid[right][j] == '1') convert(grid, right, j, width, height);
    }

    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        System.out.println(new NumIslands().numIslands(grid));
    }

}

