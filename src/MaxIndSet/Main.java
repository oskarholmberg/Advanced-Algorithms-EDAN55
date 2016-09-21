package MaxIndSet;


/**
 * Created by oskar on 9/20/16.
 */
public class Main {
    static int[][] vMatrix;
    public static void main(String[] args){

        Parser p = new Parser();

        System.out.println(compute(p.parse("src/MaxIndSet/data/g4.in")));

    }

    public static int compute(int[][] matrix){
        int[] matrixCheck = checkMatrixForVertex(matrix);
        if (matrixCheck[1] == 0){
            matrix = removeIndex(matrix, matrixCheck[0]);
            return 1 + compute(matrix);
        }
        return Math.max(isIncluded(matrix, matrixCheck[0]), notIncluded(matrix, matrixCheck[0]));
    }

    public static int isIncluded(int[][] matrix, int index){
        for (int i = 0; i < matrix[index].length; i++){
            if (matrix[index][i] == 1){
                matrix = removeIndex(matrix, i);
            }
        }
        matrix = removeIndex(matrix, index);
        return 1 + compute(matrix);

    }

    public static int notIncluded(int[][] matrix, int index){
        matrix = removeIndex(matrix, index);
        return compute(matrix);
    }

    public static int[][] removeIndex(int[][] matrix, int index){
        for (int i = 0; i < matrix[1].length; i++){
            if (i == index){
                for (int j = 0; j < matrix[i].length; j++){
                    matrix[i][j]=-1;
                }
            } else if (matrix[i][index] == 1){
                matrix[i][index] = -1;
            }
        }
        return matrix;
    }

    public static int[] checkMatrixForVertex(int[][] matrix){
        int nbrNeighbours = 0;
        int index = -1;
        for (int i = 0; i < matrix[1].length; i++) {
            int temp = neighbourCheck(matrix, i);
            if(temp == 0){
                return new int[]{i, 0};
            }else if (temp>nbrNeighbours) {
                nbrNeighbours = temp;
                index=i;
            }
        }
        return new int[]{index, nbrNeighbours};
    }

    public static int neighbourCheck(int[][] matrix, int index) {
        int temp = 0;
        for (int i = 0; i < matrix[1].length; i++) {
            if (matrix[index][i] != -1) {
                temp += matrix[index][i];
            }
        }
        return temp;
    }
}
