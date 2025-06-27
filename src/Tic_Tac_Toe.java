import java.util.Scanner;

public class Tic_Tac_Toe {
    public static void main(String[] args) {
        char[][] space = new char[3][3];
        for(int row = 0;row < space.length;row++){
            for(int col = 0;col < space[row].length;col++){
                space[row][col] = ' ';
            }
        }
        char player = 'X';
        boolean gameOver = false;
        Scanner scan = new Scanner(System.in);

        while(!gameOver){
            printBoard(space);
            System.out.println("Player " + player + " enter: ");
            int row = scan.nextInt();
            int col = scan.nextInt();

            if(space[row][col] == ' '){
                space[row][col] = player;
                gameOver = haveWon(space, player);
                if(gameOver){
                    System.out.println("Player " + player + " has won: ");
                }else {
                    if(player == 'X'){
                        player = '0';
                    }else {
                        player = 'X';
                    }
                    //plqyer = (player == 'X') ? '0' : 'X';
                }
            }
            else {
                System.out.println("Invalid move. Try again!");
            }
        }
    }

    public static boolean haveWon(char[][] space, char player){

    }
    public static void printBoard(char[][] space){

    }
}
