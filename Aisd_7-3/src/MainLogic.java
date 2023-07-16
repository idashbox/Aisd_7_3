public class MainLogic {
    // Проверка связности графа
    public static boolean checkConnectivity(int[][] matrix) {
        int n = matrix.length;
        boolean[] visited = new boolean[n];
        // Начинаем обход из первой вершины
        dfs(matrix, visited, 0);
        // Проверяем, были ли посещены все вершины
        for (boolean isVisited : visited) {
            if (!isVisited) {
                return false;
            }
        }
        return true;
    }
    private static void dfs(int[][] matrix, boolean[] visited, int vertex) {
        visited[vertex] = true;
        // обходим смежные вершины
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[vertex][i] == 1 && !visited[i]) {
                // переходим к вершине i, чтобы проверить пути из неё
                dfs(matrix, visited, i);
            }
        }
    }
}
