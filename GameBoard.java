public class GameBoard{
    private void checkKeys(){
        //move tiles left
        moveTiles(Direction.LEFT);
        moveTiles(Direction.RIGHT);
        moveTiles(Direction.UP);
        moveTiles(Direction.DOWN);

    }
    private void moveTiles(Direction dir){
        boolean canMove = false; // initially we assume that tiles cannot move
        int horizontalDirection = 0;
        int verticalDirection = 0;
        if(dir == Direction.LEFT){
            horizontalDirection = -1;
            for(int row = 0;row<ROWS;row++){
                for(int col = 0;col<COLS;col++){
                    if(!canMove){ // once canMove is true, we know we can move all the tiles
                        canMove = move(row, col, horizontalDirection, verticalDirection, dir); 
                    }
                    else{
                        move(row, col, horizontalDirection, verticalDirection, dir);
                    }
                }
            }
        }
        else if(dir == Direction.RIGHT){
            horizontalDirection = 1;
            for(int row = 0;row<ROWS;row++){
                for(int col = COLS-1;col>=0;col--){  // this loop will run backwards
                    if(!canMove){ // once canMove is true, we know we can move all the tiles
                        canMove = move(row, col, horizontalDirection, verticalDirection, dir); 
                    }
                    else{
                        move(row, col, horizontalDirection, verticalDirection, dir);
                    }
                }
            }
        }
        else if(dir == Direction.UP){
            verticalDirection = -1;
            for(int row = 0;row<ROWS;row++){
                for(int col = 0;col<COLS;col++){
                    if(!canMove){ // once canMove is true, we know we can move all the tiles
                        canMove = move(row, col, horizontalDirection, verticalDirection, dir); 
                    }
                    else{
                        move(row, col, horizontalDirection, verticalDirection, dir);
                    }
                }
            }
        }
        else if(dir == Direction.DOWN){
            verticalDirection = 1;
            for(int row = ROWS-1;row>=0;row--){
                for(int col = 0;col<COLS;col++){
                    if(!canMove){ // once canMove is true, we know we can move all the tiles
                        canMove = move(row, col, horizontalDirection, verticalDirection, dir); 
                    }
                    else{
                        move(row, col, horizontalDirection, verticalDirection, dir);
                    }
                }
            }
        }
        else{
            System.out.println("Not Valid");
        }

        for(int row = 0; row<ROWS; row++){
            for(int col= 0; col<COLS;col++){
                Tile current = board[row][col]
                if(canMove){
                    spawnRandom();
                }
            }
        }
    }
    private boolean move(int row, int col, int horizontalDirection,int verticalDirection, Direction dir){
        boolean canMove = false;

        Tile current = board[row][col];
        if(current == null)return false;
        boolean move = true;
        int newCol = col;
        int newRow = row;
        while(move){
            newCol += horizontalDirection;
            newRow += verticalDirection;
            if
        }

        return canMove;
    }
}