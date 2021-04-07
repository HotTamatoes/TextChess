import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;
/**
 * Runs everything - all algorithmic thinking
 *  
 * @author Cameron Holland 
 * @version 1
 */
public class LETSPLAY
{
    private int turn = 0, player1Points = 0, player2Points = 0, specialMove = 0;
    private int lastMove, lastSpot;
    public LETSPLAY()
    {
        actuallyPlay();
    }
    
    public static void main(String[] args)
    {
        LETSPLAY a = new LETSPLAY();
    }
    
    public void actuallyPlay()
    {
        boolean isPlaying = true;
        Dictionary hashtableSpots = makeHashtable();
        do
        {
            Board board = new Board();
            displayWelcomeMessage();
            playSomeChess(board, hashtableSpots);
            isPlaying = playAgain();
        }while(isPlaying);
    }
    public boolean playAgain()
    {
        Scanner scanIn = new Scanner(System.in);
        System.out.println("Would you like to play again?");
        String answer = scanIn.nextLine();
        if(answer.equals("y") || answer.equals("yes") || answer.equals("Yes"))
            return true;
        return false;
    }
    
    public Dictionary makeHashtable()
    {
        Dictionary hashtable = new Hashtable<String, Integer>();
        for(int i = 1; i < 9; i++)
            hashtable.put("a" + (9-i), (i-1)*8);
        for(int i = 1; i < 9; i++)
            hashtable.put("b" + (9-i), ((i-1)*8)+1);
        for(int i = 1; i < 9; i++)
            hashtable.put("c" + (9-i), ((i-1)*8)+2);
        for(int i = 1; i < 9; i++)
            hashtable.put("d" + (9-i), ((i-1)*8)+3);
        for(int i = 1; i < 9; i++)
            hashtable.put("e" + (9-i), ((i-1)*8)+4);
        for(int i = 1; i < 9; i++)
            hashtable.put("f" + (9-i), ((i-1)*8)+5);
        for(int i = 1; i < 9; i++)
            hashtable.put("g" + (9-i), ((i-1)*8)+6);
        for(int i = 1; i < 9; i++)
            hashtable.put("h" + (9-i), ((i-1)*8)+7);
        return hashtable;
    }
    public void playSomeChess(Board board, Dictionary hashtable)
    {
        boolean gameGoes = true;
        String winner = "";
        while(gameGoes)
        {
            for(int i = 0; i < 100; i++)
                System.out.print("_");
            System.out.println("_");
            move("White", board, hashtable);
            winner = "WHITE";
            //System.out.println("DOING THE TESTKING WITH BLACK");
            gameGoes = testKing("Black", board);
            //System.out.println("gameGoes" + gameGoes);
            if(gameGoes)
            {
                for(int i = 0; i < 100; i++)
                    System.out.print("_");
                System.out.println("_");
                move("Black", board, hashtable);
                winner = "BLACK";
                //System.out.println("DOING THE TESTKING WITH WHITE");
                gameGoes = testKing("White", board);
                //System.out.println("gameGoes" + gameGoes);
            }
        }
        board.boardPrint();
        System.out.println(winner + " WINS!!!");
    }
    
    public void displayWelcomeMessage()
    {
        System.out.println("Welcome to HotTamatoes' Chess Game!\nMove the king to castle");
    }
    
    public void move(String side, Board board, Dictionary hashtable)
    {
        int spaht = -1, moovie = -1;
        board.boardPrint();
        System.out.println(side + "'s turn");
        spaht = getSpot(side, hashtable, board);
        Scanner input = new Scanner(System.in);
        System.out.println("Enter spot to move to: ");
        String move = input.nextLine();
        try{moovie = (int)hashtable.get(move);} catch(Exception e){}
        if(isValidMove(side, spaht, moovie, board)) //Find the spot, and check isValidMove() if yes, .set and add to points
        {
            if(side.equals("White"))
            {
                if(board.isBlackPiece(moovie))//if taking a black piece
                    board.setIsBlackPiece(moovie, false);
                board.setPiece(moovie, board.getPiece(spaht));
                board.setIsWhitePiece(moovie, true);
                board.setPiece(spaht, null);
                board.setIsWhitePiece(spaht, false);
            }
            else if(side.equals("Black"))
            {
                if(board.isWhitePiece(moovie))//if taking a white piece
                    board.setIsWhitePiece(moovie, false);
                board.setPiece(moovie, board.getPiece(spaht));
                board.setIsBlackPiece(moovie, true);
                board.setPiece(spaht, null);
                board.setIsBlackPiece(spaht, false);
            }
            if(specialMove > 4)
                moveForEnPassant(moovie, spaht, board);
            else if(specialMove != 0)
                moveForCastle(board);
            specialMove = 0;
            lastMove = moovie;//lastMove
            lastSpot = spaht;//lastSpot
        }
        else
        {
            specialMove = 0;
            System.out.println("___                                /\\  /\\    _        __ ");
            System.out.println(" |  |\\ | \\  /  /_\\  |   -|- |\\    /  \\/  \\  / \\ \\  / |__     ");
            System.out.println("_|_ | \\|  \\/  /   \\ |__ _|_ |/   /        \\ \\_/  \\/  |__     ");
            move(side, board, hashtable);
        }
    }
    public void moveForCastle(Board board)
    {
        if(specialMove == 1)
        {
            board.setPiece(61, board.getPiece(63));
            board.setIsWhitePiece(61, true);
            board.setPiece(63, null);
            board.setIsWhitePiece(63, false);
        }
        else if (specialMove == 2)
        {
            board.setPiece(59, board.getPiece(56));
            board.setIsWhitePiece(59, true);
            board.setPiece(56, null);
            board.setIsWhitePiece(56, false);
        }
        else if (specialMove == 3)
        {
            board.setPiece(5, board.getPiece(7));
            board.setIsBlackPiece(5, true);
            board.setPiece(7, null);
            board.setIsBlackPiece(7, false);
        }
        else if (specialMove == 4)
        {
            board.setPiece(3, board.getPiece(0));
            board.setIsBlackPiece(3, true);
            board.setPiece(3, null);
            board.setIsBlackPiece(0, false);
        }
    }
    public void moveForEnPassant(int move, int spot, Board board)
    {
        if (specialMove == 5)
        {
            board.setPiece(spot+1, null);
            board.setIsBlackPiece(spot+1, false);
        }
        else if (specialMove == 6)
        {
            board.setPiece(spot-1, null);
            board.setIsBlackPiece(spot-1, false);
        }
        else if (specialMove == 7)
        {
            board.setPiece(spot+1, null);
            board.setIsBlackPiece(spot+1, false);
        }
        else if (specialMove == 8)
        {
            board.setPiece(spot-1, null);
            board.setIsBlackPiece(spot-1, false);
        }
    }
    
    public int getSpot(String side, Dictionary hashtable, Board board)
    {
        int spaht = -1;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter spot of piece (Ex: a1): ");
        String spot = input.nextLine();
        try{spaht = (int)hashtable.get(spot);} catch(Exception e){}
        Piece daPiece = board.getPiece(spaht);
        if(side.equals("White") && !board.isWhitePiece(spaht))
        {
            System.out.println("ERROR: No white piece found");
            return getSpot(side, hashtable, board);
        }
        else if(side.equals("Black") && !board.isBlackPiece(spaht))
        {
            System.out.println("ERROR: No black piece found");
            return getSpot(side, hashtable, board);
        }
        try{System.out.println("You chose: " + daPiece);}
        catch(Exception e) {System.out.println("Could not find the piece you chose");}
        return spaht;
    }
    
    public boolean isValidMove(String side, int spot, int move , Board board)
    {
        //System.out.print(spot + " --> " + move);
        if(spot == -1 || move == -1){return false;}
        int aInRow = spot-spot%8;
        int[] rowOfSpot = {aInRow, aInRow + 1, aInRow + 2, aInRow + 3, aInRow + 4, aInRow + 5, aInRow + 6, aInRow + 7};
        Piece pieceOnSpot = (Piece)board.getPiece(spot);
        String pieceName = pieceOnSpot.getNameOfPiece(); //stores the piece on the board, puts the name of the piece in a string
        //System.out.println(kingNotInCheck(side, spot, move, board));
        if(kingNotInCheck(side, spot, move, board))
        {
            if(pieceName.equals("|WKnight|")||pieceName.equals("|BKnight|"))//Possible moves - +/-10,+/-17,+/-15,+/-6
            {
                if(knightPossibleMoves(side, rowOfSpot, spot, move, board))
                    return true;
            }
            else if(pieceName.equals("| WRook |")||pieceName.equals("| BRook |"))//Possible moves - +/-8, +/-1
            {
                if(rookPossibleMoves(side, rowOfSpot, spot, move, board))
                    return true;
            }
            else if(pieceName.equals("|WBishop|")||pieceName.equals("|BBishop|"))//Possible moves - +/-9, +/-7
            {
                if(bishopPossibleMoves(side, rowOfSpot, spot, move, board))
                    return true;
            }
            else if(pieceName.equals("| WPawn |"))//Possible moves - -8, -9, -7, -16 AND QUEEN
            {
                if(whitePawnPossibleMoves(aInRow, spot, move, board))
                    return true;
            }
            else if(pieceName.equals("| BPawn |"))//Possible moves - +8, +9, +7, +16 AND QUEEN
            {
                if(blackPawnPossibleMoves(aInRow, spot, move, board))
                    return true;
            }
            else if(pieceName.equals("|WQueen |")||pieceName.equals("|BQueen |"))//Possible moves - +/-9, +/-8, +/-7, +/-1
            {
                if(bishopPossibleMoves(side, rowOfSpot, spot, move, board)||rookPossibleMoves(side, rowOfSpot, spot, move, board))
                    return true; 
            }
            else if(pieceName.equals("| WKing |")||pieceName.equals("| BKing |"))//Possible moves - Same as queen, but only once AND cannot move into check : also checks king isn't on check
            {
                if(kingPossibleMoves(side, rowOfSpot, spot, move, board))
                    return true;
            }
        }
        return false;
    }
    public boolean isValidMoveNoKing(String side, int spot, int move , Board board)
    {
        //System.out.println(side + spot + " --> " + move);
        //System.out.println(spot + " ivmnk ");
        if(spot == -1 || move == -1){return false;}
        int aInRow = spot-spot%8;
        int[] rowOfSpot = {aInRow, aInRow + 1, aInRow + 2, aInRow + 3, aInRow + 4, aInRow + 5, aInRow + 6, aInRow + 7};
        Piece pieceOnSpot = (Piece)board.getPiece(spot);
        //System.out.println(pieceOnSpot);
        //board.boardPrint();
        //try
        //{
        String pieceName = pieceOnSpot.getNameOfPiece(); //stores the piece on the board, puts the name of the piece in a string
        if(pieceName.equals("|WKnight|")||pieceName.equals("|BKnight|"))//Possible moves - +/-10,+/-17,+/-15,+/-6
        {
            if(knightPossibleMoves(side, rowOfSpot, spot, move, board))
                return true;
        }
        else if(pieceName.equals("| WRook |")||pieceName.equals("| BRook |"))//Possible moves - +/-8, +/-1
        {
            if(rookPossibleMoves(side, rowOfSpot, spot, move, board))
                return true;
        }
        else if(pieceName.equals("|WBishop|")||pieceName.equals("|BBishop|"))//Possible moves - +/-9, +/-7
        {
            if(bishopPossibleMoves(side, rowOfSpot, spot, move, board))
                return true;
        }
        else if(pieceName.equals("| WPawn |"))//Possible moves - -8, -9, -7, -16 AND QUEEN
        {
            if(whitePawnPossibleMoves(aInRow, spot, move, board))
                return true;
        }
        else if(pieceName.equals("| BPawn |"))//Possible moves - +8, +9, +7, +16 AND QUEEN
        {
            if(blackPawnPossibleMoves(aInRow, spot, move, board))
                return true;
        }
        else if(pieceName.equals("|WQueen |")||pieceName.equals("|BQueen |"))//Possible moves - +/-9, +/-8, +/-7, +/-1
        {
            //System.out.println("wuz here");
            //System.out.println("q! " + bishopPossibleMoves(side, spot, move, board));
            //System.out.println("q! " + rookPossibleMoves(side, rowOfSpot, spot, move, board));
            if(bishopPossibleMoves(side, rowOfSpot, spot, move, board)||rookPossibleMoves(side, rowOfSpot, spot, move, board))
            {
                return true;
            }
        }
        else if(pieceName.equals("| WKing |")||pieceName.equals("| BKing |"))//Possible moves - Same as queen, but only once AND cannot move into check : also checks king isn't on check
        {
            if(kingPossibleMoves(side, rowOfSpot, spot, move, board))
                return true;
        }
        //}catch(NullPointerException e){}
        return false;
    }
    public int[] addAll(int[] array, int rowNum)
    {
        int[] retuhn = new int[array.length];
        for(int i = 0; i < array.length; i++)
        {
            retuhn[i] = array[i] + 8*rowNum;
        }
        return retuhn;
    }
    
    public boolean knightPossibleMoves(String side, int[] row, int spot, int move, Board board)
    {
        int[] rowAbove = addAll(row, -1), row2Above = addAll(row, -2), rowBelow = addAll(row, 1), row2Below = addAll(row, 2);
        if(side.equals("White"))
        {
            for(int intz : rowAbove)
            {
                if(intz == move && (move == spot-10 || move == spot - 6) && !board.isWhitePiece(move))
                {
                    return true;
                }
            }
            for(int intz : row2Above)
            {
                if(intz == move && (move == spot-17 || move == spot-15) && !board.isWhitePiece(move))
                {
                    return true;
                }
            }
            for(int intz : rowBelow)
            {
                if(intz == move && (move == spot+10 || move == spot+6) && !board.isWhitePiece(move))
                {
                    return true;
                }
            }
            for(int intz : row2Below)
            {
                if(intz == move && (move == spot+15 || move == spot+17) && !board.isWhitePiece(move))
                {
                    return true;
                }
            }
        }
        else if (side.equals("Black"))
        {
            for(int intz : rowAbove)
            {
                if(intz == move && (move == spot-10 || move == spot - 6) && !board.isBlackPiece(move))
                {
                    return true;
                }
            }
            for(int intz : row2Above)
            {
                if(intz == move && (move == spot-17 || move == spot-15) && !board.isBlackPiece(move))
                {
                    return true;
                }
            }
            for(int intz : rowBelow)
            {
                if(intz == move && (move == spot+10 || move == spot+6) && !board.isBlackPiece(move))
                {
                    return true;
                }
            }
            for(int intz : row2Below)
            {
                if(intz == move && (move == spot+15 || move == spot+17) && !board.isBlackPiece(move))
                {
                    return true;
                }
            }            
        }
        return false;
    }
    public boolean rookPossibleMoves(String side, int[] row, int spot, int move, Board board)
    {
        int up = spot, left = spot, right = spot, down = spot;
        if(side.equals("White"))//if the piece is white
        {
            if(move > spot)//+moves (down and right)
            {
                //System.out.println("Checking right");
                do
                {
                    if(move == right && right <= row[7])
                        return true;
                    right++;
                }while(right <= row[7] && !board.isBlackPiece(right-1) && !board.isWhitePiece(right));
                //System.out.println("Checking down");
                do
                {
                    if(move == down)
                        return true;
                    down += 8;
                }while(down <= 63 && !board.isBlackPiece(down-8) && !board.isWhitePiece(down));
            }
            else if(move != spot)//-moves (up and left)
            {
                //System.out.println("Checking left");
                do
                {
                    if(move == left && left >= row[0])
                    {
                        return true;
                    }
                    left--;
                    //System.out.println("isBpice " + (left+1) + " " + !board.isBlackPiece(left+1));
                    //System.out.println("isWpice" + left + " " + !board.isWhitePiece(left));
                }while(left >= row[0] && !board.isBlackPiece(left+1) && !board.isWhitePiece(left));
                //System.out.println("Checking up");
                do
                {
                    if(move == up)
                        return true;
                    up -= 8;
                }while(up >= 0 && !board.isBlackPiece(up+8) && !board.isWhitePiece(up));
            }
        }
        else if(side.equals("Black"))//if the piece is black
        {
            if(move > spot)//+moves (down and right)
            {
                //System.out.println("Checking right");
                do
                {
                    if(move == right && right <= row[7])
                        return true;
                    right++;
                }while(right <= row[7] && !board.isWhitePiece(right-1) && !board.isBlackPiece(right));
                //System.out.println("Checking down");
                do
                {
                    if(move == down)
                        return true;
                    down += 8;
                }while(down <= 63 && !board.isWhitePiece(down-8) && !board.isBlackPiece(down));
            }
            else//-moves (up and left)
            {
                //System.out.println("Checking left");
                do
                {
                    if(move == left && left >= row[0])
                    {
                        return true;
                    }
                    left--;
                    //System.out.println("isBpice " + left + " " + !board.isBlackPiece(left));
                    //System.out.println("isWpice" + (left+1) + " " + !board.isWhitePiece(left+1));
                }while(left >= row[0] && !board.isWhitePiece(left+1) && !board.isBlackPiece(left));
                //System.out.println("Checking up");
                do
                {
                    if(move == up)
                        return true;
                    up -= 8;
                }while(up >= 0 && !board.isWhitePiece(up+8) && !board.isBlackPiece(up));
            }
        }
        return false;
    }
    public boolean bishopPossibleMoves(String side, int[] row, int spot, int move, Board board)//Possible moves - +/-9, +/-7
    {
        int upR = spot, upL = spot, downR = spot, downL = spot;
        int[] downRRow = row, downLRow = row, upRRow = row, upLRow = row;
        if(side.equals("White"))
        {
            if(move > spot)//+moves (down and right)
            {
                do
                {
                    if(move == downR)
                        return true;
                    downR += 9;
                    downRRow = addAll(downRRow, 1);
                }while(downR <= 63 && downR <= downRRow[7] && !board.isBlackPiece(downR-9) && !board.isWhitePiece(downR));
                do
                {
                    if(move == downL)
                        return true;
                    downL += 7;
                    downLRow = addAll(downLRow, 1);
                }while(downL <= 63 && downL >= downLRow[0] && !board.isBlackPiece(downL-7) && !board.isWhitePiece(downL));
            }
            else if(!board.isWhitePiece(move))//-moves (up and left)
            {
                do
                {
                    if(move == upL)
                        return true;
                    upL -= 9;
                    upLRow = addAll(upLRow, -1);
                    //System.out.println("isblakepice " + (upL+9) + " " + !board.isBlackPiece(upL+9));
                    //System.out.println("iswitepice" + upL + " " + !board.isWhitePiece(upL));
                }while(upL >= 0 && upL >= upLRow[0] && !board.isBlackPiece(upL+9) && !board.isWhitePiece(upL));
                do
                {
                    if(move == upR)
                        return true;
                    upR -= 7;
                    upRRow = addAll(upRRow, -1);
                }while(upR >= 0 && upR <= upRRow[7] && !board.isBlackPiece(upR+7) && !board.isWhitePiece(upR));
            }
        }
        else if(side.equals("Black"))
        {
            if(move > spot)//+moves (down and right)
            {
                do
                {
                    if(move == downR)
                        return true;
                    downR += 9;
                    downRRow = addAll(downRRow, 1);
                }while(downR <= 63 && !board.isWhitePiece(downR-9) && !board.isBlackPiece(downR));
                do
                {
                    if(move == downL)
                        return true;
                    downL += 7;
                    downLRow = addAll(downLRow, 1);
                }while(downL <= 63 && !board.isWhitePiece(downL-7) && !board.isBlackPiece(downL));
            }
            else  if(!board.isWhitePiece(move))//-moves (up and left)
            {
                do
                {
                    if(move == upL)
                        return true;
                    upL -= 9;
                    upLRow = addAll(upLRow, -1);
                }while(upL >= 0 && !board.isWhitePiece(upL+9) && !board.isBlackPiece(upL));
                do
                {
                    if(move == upR)
                        return true;
                    upR -= 7;
                    upRRow = addAll(upRRow, -1);
                }while(upR >= 0 && !board.isWhitePiece(upR+7) && !board.isBlackPiece(upR));
            }
        }
        return false;
    }
    public boolean whitePawnPossibleMoves(int aRow, int spot, int move, Board  board)
    {
        if(!board.isWhitePiece(move) && !board.isBlackPiece(move))
        {
            if(move == spot-8)
                return true;
            else if(aRow == 48 && move == spot-16 && !board.isBlackPiece(spot-8) && !board.isWhitePiece(spot-8))
                return true;
            else if(enPassantWhite(aRow, spot, move, board))
                return true;
        }
        else if(board.isBlackPiece(move) && (move == spot-7 || move == spot-9))
            return true;
        return false;
    }
    public boolean blackPawnPossibleMoves(int aRow, int spot, int move, Board  board)
    {
        if(!board.isBlackPiece(move) && !board.isWhitePiece(move))
        {
            if(move == spot+8)
                return true;
            else if(aRow == 8 && move == spot+16 && !board.isBlackPiece(spot+8) && !board.isWhitePiece(spot+8))
                return true;
            else if(enPassantBlack(aRow, spot, move, board))
                return true;
        }
        else if(board.isWhitePiece(move) && (move == spot+7 || move == spot+9))
            return true;
        return false;
    }
    
    public boolean enPassantWhite(int aRow, int spot, int move, Board board)
    {
        //System.out.println(board.getPiece(spot+1) == lastMoved);
        //System.out.println(move == spot - 7);
        //System.out.println(aRow == 24);
        //System.out.println(board.getPiece(spot-1) == lastMoved);
        //System.out.println(move == spot - 9);
        //System.out.println(aRow == 24);
        if(spot+1 == lastMove && move == spot - 7 && aRow == 24 && lastSpot == lastMove - 16)
        {
            specialMove = 5;
            return true;
        }
        else if(spot-1 == lastMove && move == spot - 9 && aRow == 24 && lastSpot == lastMove - 16)
        {
            specialMove = 6;
            return true;
        }
        return false;
    }
    public boolean enPassantBlack(int aRow, int spot, int move, Board board)
    {
        //System.out.println(board.getPiece(spot+1) == lastMoved);
        //System.out.println(move == spot + 9);
        //System.out.println(aRow == 32);
        //System.out.println(board.getPiece(spot-1) == lastMoved);
        //System.out.println(move == spot + 7);
        //System.out.println(aRow == 32);
        if(move == spot + 9 && aRow == 32 && spot+1 == lastMove && lastSpot == lastMove + 16)
        {
            specialMove = 7;
            return true;
        }
        else if(move == spot + 7 && aRow == 32 && spot-1 == lastMove && lastSpot == lastMove + 16)
        {
            specialMove = 8;
            return true;
        }
        return false;
    }
    
    public boolean kingPossibleMoves(String side, int[] row, int spot, int move, Board board)
    {
        int[] rowAbove = addAll(row, -1), rowBelow = addAll(row, 1);
        //System.out.println("Checking moves " + (move-spot) + " " + move + " " + rowBelow[7]);
        if((move == spot-7 && move >= rowAbove[0] && move <= rowAbove[7]) || move == spot-8 || (move == spot-9 && move <= rowAbove[7] && move >= rowAbove[0]) || (move == spot-1 && move >= row[0] && move <= row[7]) || (move == spot+1 && move <= row[7] && move >= row[0]) || (move == spot+7 && move >= rowBelow[0] && move <= rowBelow[7]) || move == spot+8 || (move == spot+9 && move <= rowBelow[7] && move >= rowBelow[0]))
        {
            //System.out.println("one move was good");
            if(side.equals("White"))
            {
                if(!board.isWhitePiece(move) && notInCheck(side, move, board))
                {
                    return true;
                }
            }
            else if(side.equals("Black"))
            {
                if(!board.isBlackPiece(move) && notInCheck(side, move, board))
                {
                    return true;
                }
            }
        }
        //System.out.println("Checking Castle");
        if(castle(side, spot, move, board))
        {
            return true;
        }
        return false;
    }
    /**
     * conditions:
     *      king and rook must be on native spots
     *      cannot be check between king and rook
     *      cannot be pieces between king and rook
     */
    public boolean castle(String side, int spot, int move, Board board)
    {
        Piece wAtEndRow = board.getPiece(63), wAtBegRow = board.getPiece(56), pieceAtMove = board.getPiece(move), bAtEndRow = board.getPiece(7), bAtBegRow = board.getPiece(0);
        if(side.equals("White") && spot == 60)
        {
            if(move == spot + 2)
            {
                try
                {
                    if(wAtEndRow.getNameOfPiece().equals("| WRook |"))
                    {
                        int count = 0;
                        for(int i = spot; i < spot+3; i++)
                        {
                            //System.out.println(side + " cnt " + i + "-----------------------");
                            if(notInCheck(side, i, board) && !board.isBlackPiece(i))
                            {
                                if(i > spot && !board.isWhitePiece(i))
                                {
                                    //System.out.println("count went up !*!*!*");
                                    count++;
                                }
                                else
                                {
                                    //System.out.println("count went up !*!*!*");
                                    count++;
                                }
                            }
                        }
                        //System.out.println("???at END count is " + count);
                        if(count == 3)
                        {
                            specialMove = 1;
                            return true;
                        }
                    }
                }catch(NullPointerException e){}
            }
            else if(move == spot - 2)
            {
                try
                {
                    if(wAtBegRow.getNameOfPiece().equals("| WRook |"))
                    {
                        int count = 0;
                        for(int i = spot; i >= spot-3; i--)
                        {
                            //System.out.println(side + " cnt " + i + "-----------------------");
                            if(notInCheck(side, i, board) && !board.isBlackPiece(i))
                            {
                                if(i < spot && !board.isWhitePiece(i))
                                {
                                    //System.out.println("count went up !*!*!*");
                                    count++;
                                }
                                else
                                {
                                    //System.out.println("count went up !*!*!*");
                                    count++;
                                }
                            }
                        }
                        //System.out.println("???at END count is " + count);
                        if(count == 3)
                        {
                            specialMove = 2;
                            return true;
                        }
                    }
                }catch(NullPointerException e){}
            }
        }
        else if(side.equals("Black") && spot == 4)
        {
            if(move == spot + 2)
            {
                try
                {
                    if(bAtEndRow.getNameOfPiece().equals("| BRook |"))
                    {
                        int count = 0;
                        for(int i = spot; i < spot+3; i++)
                        {
                            if(notInCheck(side, i, board) && !board.isWhitePiece(i))
                            {
                                if(i > spot && !board.isBlackPiece(i))
                                    count++;
                                else
                                    count++;
                            }
                        }
                        if(count == 3)
                        {
                            specialMove = 3;
                            return true;
                        }
                    }
                }catch(NullPointerException e){}
            }
            else if(move == spot - 2)
            {
                try
                {
                    if(bAtBegRow.getNameOfPiece().equals("| BRook |"))
                    {
                        int count = 0;
                        for(int i = spot; i >= spot-3; i--)
                        {
                            if(notInCheck(side, i, board) && !board.isWhitePiece(i))
                            {
                                if(i < spot && !board.isBlackPiece(i))
                                    count++;
                                else
                                    count++;
                            }
                        }
                        if(count == 3)
                        {
                            specialMove = 4;
                            return true;
                        }
                    }
                }catch(NullPointerException e){}
            }
        }
        return false;
    }
    
    /*
     * if king in check:
     * checks piece by piece if it can move to a
     * spot on the board to put the king not in check
     * returns true if there is a move or king is not in check
     * returns false if game is over - checkmate
     */
    public boolean testKing(String side, Board board)//side must be able to move to get kingNotInCheck
    {
        //System.out.println("I AM A PANDA " + side);
        int spotKing = 0;
        if(side.equals("White"))
        {
            assert findKing("| WKing |", board) != -1 : "WKingNotFound";
            spotKing = findKing("| WKing |", board);
            assert spotKing != 0 : "WKingNotMadeSpot";
            //System.out.println("testKing" + !notInCheck(side, spotKing,board));
            if(!notInCheck(side, spotKing, board))//if king is in check
            {
                //Rook
                if(isKingInCheck(side, "| WRook |", board))//returns true if this piece can move to get king out of check
                    return true;
                //Knight
                if(isKingInCheck(side, "|WKnight|", board))
                    return true;
                //Bishop
                if(isKingInCheck(side, "|WBishop|", board))
                    return true;
                //Pawn
                if(isKingInCheckPawn(side, "| WPawn |", board))
                    return true;
                //Queen
                if(isKingInCheck(side, "|WQueen |", board))
                    return true;
                //King
                if(isKingInCheck(side, "| WKing |", board))
                    return true;
                return false;
            }
        }
        else if(side.equals("Black"))
        {
            assert findKing("| BKing |", board) != -1 : "BKingNotFound";
            spotKing = findKing("| BKing |", board);
            assert spotKing != 0 : "BKingNotMadeSpot";
            //System.out.println("testKing " + !notInCheck(side, spotKing,board));
            if(!notInCheck(side, spotKing, board))
            {
                boolean pinted = isKingInCheck(side, "| BRook |", board);
                //System.out.println("TestKing" + pinted);
                //Rook
                if(pinted)
                    return true;
                //Knight
                if(isKingInCheck(side, "|BKnight|", board))
                    return true;
                //Bishop
                if(isKingInCheck(side, "|BBishop|", board))
                    return true;
                //Pawn
                if(isKingInCheckPawn(side, "| BPawn |", board))
                    return true;
                //Queen
                if(isKingInCheck(side, "|BQueen |", board))
                    return true;
                //King
                if(isKingInCheck(side, "| BKing |", board))
                    return true;
                return false;
            }
        }
        return true;
    }
    
    /*
     * return true if there is a possible move
     * returns false if this piece cannot move to get king out of check
     */
    public boolean isKingInCheck(String side, String nameOfPiece, Board board)
    {
        //String oldSide = side;
        //if(side.equals("White"))
        //    side = "Black";
        //else
        //    side = "White";
        int loc1 = -1, loc2 = -1, hasFound = 0;
        Piece checking = new Piece();
        //System.out.println(side + " p " + nameOfPiece);
        for(int i = 0; i < board.lengthOfBoard() && hasFound < 2; i++) //finding spots of pieces
        {
            checking = board.getPiece(i);
            try
            {
                if(checking.getNameOfPiece().equals(nameOfPiece))
                {
                    if(hasFound == 0)
                    {
                        //System.out.print(nameOfPiece + " loc1: " + i);
                        loc1 = i;
                        hasFound++;
                    }
                    else if(loc1 != i)
                    {
                        //System.out.println(nameOfPiece + " loc2: " + i);
                        loc2 = i;
                        hasFound++;
                    }
                }
            }catch(NullPointerException e){}
        }
        
        //if(hasFound > 0)
        //    System.out.println("Found ikic******** " + nameOfPiece + loc1 + loc2);
        
        for(int i = 0; i < board.lengthOfBoard(); i++)//moves found piece to every spot on the board, 
        {
            try
            {
                board.getPiece(loc1).returnValue();
                if(kingNotInCheck(side, loc1, i, board))
                {
                    //side = oldSide;
                    return true;
                }
            }catch(ArrayIndexOutOfBoundsException e){}
            try
            {
                board.getPiece(loc2).returnValue();
                if(kingNotInCheck(side, loc2, i, board))
                {
                    //side = oldSide;
                    return true;
                }
            }catch(ArrayIndexOutOfBoundsException e){}
        }
        //System.out.println("Cannot move" + nameOfPiece + "legally");
        //side = oldSide;//not sure if these are needed
        return false;
    }
    
    public boolean isKingInCheckPawn(String side, String nameOfPiece, Board board)
    {
        //String oldSide = side;
        //if(side.equals("White"))
        //    side = "Black";
        //else
        //    side = "White";
        int loc1 = -1, loc2 = -1, loc3 = -1, loc4 = -1, loc5 = -1, loc6 = -1, loc7 = -1, loc8 = -1, hasFound = 0;
        Piece checking = new Piece();
        //System.out.println(side + " p " + nameOfPiece);
        for(int i = 0; i < board.lengthOfBoard() && hasFound < 8; i++) //finding spots of Knights
        {
            checking = board.getPiece(i);
            //System.out.println(checking.getNameOfPiece());
            try
            {
                if(checking.getNameOfPiece().equals(nameOfPiece))
                {
                    if(hasFound == 0)
                    {
                        //System.out.println(checking.getNameOfPiece());
                        loc1 = i;
                        hasFound++;
                    }
                    else if(hasFound == 1 && loc1 != i)
                    {
                        //System.out.println(checking.getNameOfPiece());
                        loc2 = i;
                        hasFound++;
                    }
                    else if(hasFound == 2 && loc1 != i && loc2 != i)
                    {
                        //System.out.println(checking.getNameOfPiece());
                        loc3 = i;
                        hasFound++;
                    }
                    else if(hasFound == 3 && loc1 != i && loc2 != i && loc3 != i)
                    {
                        //System.out.println(checking.getNameOfPiece());
                        loc4 = i;
                        hasFound++;
                    }
                    else if(hasFound == 4 && loc1 != i && loc2 != i && loc3 != i && loc4 != i)
                    {
                        //System.out.println(checking.getNameOfPiece());
                        loc5 = i;
                        hasFound++;
                    }
                    else if(hasFound == 5 && loc1 != i && loc2 != i && loc3 != i && loc4 != i && loc5 != i)
                    {
                        //System.out.println(checking.getNameOfPiece());
                        loc6 = i;
                        hasFound++;
                    }
                    else if(hasFound == 6 && loc1 != i && loc2 != i && loc3 != i && loc4 != i && loc5 != i && loc6 != i)
                    {
                        //System.out.println(checking.getNameOfPiece());
                        loc7 = i;
                        hasFound++;
                    }
                    else if(hasFound == 7 && loc1 != i && loc2 != i && loc3 != i && loc4 != i && loc5 != i && loc6 != i && loc7 != i)
                    {
                        //System.out.println(checking.getNameOfPiece());
                        loc8 = i;
                        hasFound++;
                    }
                }
            }catch(NullPointerException e){}
        }
        
        //System.out.println("Found ikicp " + nameOfPiece + loc1 + loc2 + loc3 + loc4 + loc5 + loc6 + loc7 + loc8);
        
        for(int i = 0; i < board.lengthOfBoard(); i++)
        {
            //kingNotInCheck(String side, int spot, int move, Board board)
            try{
                if(kingNotInCheck(side, loc1, i, board))
                {
                    //side = oldSide;
                    return true;
                }
            }catch(ArrayIndexOutOfBoundsException e){}
            try{
                board.getPiece(loc2).returnValue();
                if (kingNotInCheck(side, loc2, i, board))
                {
                    //side = oldSide;
                    return true;
                }
            }catch(ArrayIndexOutOfBoundsException e){}
            try{
                board.getPiece(loc3).returnValue();
                if (kingNotInCheck(side, loc3, i, board))
                {
                    //side = oldSide;
                    return true;
                }
            }catch(ArrayIndexOutOfBoundsException e){}
            try{
                board.getPiece(loc4).returnValue();
                if (kingNotInCheck(side, loc4, i, board))
                {
                    //side = oldSide;
                    return true;
                }
            }catch(ArrayIndexOutOfBoundsException e){}
            try{
                board.getPiece(loc5).returnValue();
                if (kingNotInCheck(side, loc5, i, board))
                {
                    //side = oldSide;
                    return true;
                }
            }catch(ArrayIndexOutOfBoundsException e){}
            try{
                board.getPiece(loc6).returnValue();
                if (kingNotInCheck(side, loc6, i, board))
                {
                    //side = oldSide;
                    return true;
                }
            }catch(ArrayIndexOutOfBoundsException e){}
            try{
                board.getPiece(loc7).returnValue();
                if (kingNotInCheck(side, loc7, i, board))
                {
                    //side = oldSide;
                    return true;
                }
            }catch(ArrayIndexOutOfBoundsException e){}
            try{
                board.getPiece(loc8).returnValue();
                if (kingNotInCheck(side, loc8, i, board))
                {
                    //side = oldSide;
                    return true;
                }
            }catch(ArrayIndexOutOfBoundsException e){}
        }
        //System.out.println("Cannot move" + nameOfPiece + "legally");
        //side = oldSide;
        return false;
    }
    
    /*
     * returns true if the king is NOT in check after the piece has moved there
     * returns false if the king is in check or move is illegal
     */
    public boolean kingNotInCheck(String side, int spot, int move, Board board)
    {
        //System.out.println(side + " * " + spot + " " + move);
        //System.out.println("KNIC");
        boolean retuhn = false, twasThere = false, breakForLoop = false;
        Piece wasThere = new Piece(), king = new Piece();
        int i = 0, BKingSpot = -1, WKingSpot = -1;
        if(side.equals("White"))
        {
            //System.out.println("I AM A DUCK " + side);
            //System.out.println(store);
            if(isValidMoveNoKing(side, spot, move, board))
            {
                twasThere = board.isWhitePiece(move); 
                wasThere = board.getPiece(move);
                board.setPiece(move, board.getPiece(spot));
                board.setIsWhitePiece(move, true);
                board.setPiece(spot, null);
                board.setIsWhitePiece(spot, false);
                WKingSpot = findKing("| WKing |", board);
                //System.out.println("notCheckW " + notInCheck(side, WKingSpot, board));
                if(notInCheck(side, WKingSpot, board))
                {
                    //System.out.println("KNIC");
                    retuhn = true;
                }
                board.setPiece(spot, board.getPiece(move));
                board.setIsWhitePiece(spot, true);
                board.setPiece(move, wasThere);
                board.setIsWhitePiece(move, twasThere);
            }
        }
        else if(side.equals("Black"))
        {
            //System.out.println("I AM CHINESE " + side);
            //System.out.println(store);
            if(isValidMoveNoKing(side, spot, move, board))
            {
                twasThere = board.isBlackPiece(move); 
                wasThere = board.getPiece(move);
                board.setPiece(move, board.getPiece(spot));
                board.setIsBlackPiece(move, true);
                board.setPiece(spot, null);
                board.setIsBlackPiece(spot, false);
                BKingSpot = findKing("| BKing |", board);
                //System.out.println("notCheckB " + notInCheck(side, store, board) + " " + board.getPiece(move) + " " + spot + " " + move);
                if(notInCheck(side, BKingSpot, board))
                {
                    //System.out.println("KNIC");
                    retuhn = true;
                }  
                board.setPiece(spot, board.getPiece(move));
                board.setIsBlackPiece(spot, true);
                board.setPiece(move, wasThere);
                board.setIsBlackPiece(move, twasThere);
            }
        }
        //board.boardPrint();
        return retuhn;
    }
    public int findKing(String nameOfKingPiece, Board board)
    {
        for(int i = 0; i < board.lengthOfBoard(); i++)
        {
            Piece king = board.getPiece(i);
            try
            {
                if(king.getNameOfPiece().equals(nameOfKingPiece))
                {
                    return i;
                }
            }catch(NullPointerException err){}
        }
        return -1;
    }
    public boolean notInCheck(String side, int kingMoveTo, Board board)
    {
        //System.out.println(side + " ! " + kingMoveTo);
        //board.boardPrint();
        if(side.equals("White"))
        {
            side = "Black"; //side is opposite
            //rooks
            //System.out.println("rook " + canMoveTo(side, "| BRook |", kingMoveTo, board));
            if(canMoveTo(side, "| BRook |", kingMoveTo, board))
            {
                side = "White";
                return false;
            }
            //bishop
            //System.out.println("bishop " + canMoveTo(side, "|BBishop|", kingMoveTo, board));
            if(canMoveTo(side, "|BBishop|", kingMoveTo, board))
            {
                side = "White";
                return false;
            }
            //knight
            //System.out.println("knight " + canMoveTo(side, "|BKnight|", kingMoveTo, board));
            if(canMoveTo(side, "|BKnight|", kingMoveTo, board))
            {
                side = "White";
                return false;
            }
            //queen
            //System.out.println("queen " + canMoveTo(side, "|BQueen |", kingMoveTo, board));
            if(canMoveTo(side, "|BQueen |", kingMoveTo, board))
            {
                side = "White";
                return false;
            }
            //king
            //System.out.println("king " + canMoveTo(side, "| BKing |", kingMoveTo, board));
            if(canMoveTo(side, "| BKing |", kingMoveTo, board))
            {
                side = "White";
                return false;
            }
            //pawn
            //System.out.println("pawn " + canMoveToPawn(side, "| BPawn |", kingMoveTo, board));
            if(canMoveToPawn(side, "| BPawn |", kingMoveTo, board))
            {
                side = "White";
                return false;
            }
            side = "White";
        }
        else if(side.equals("Black"))
        {
            side = "White";
            //rooks
            if(canMoveTo(side, "| WRook |", kingMoveTo, board))
            {
                side = "Black";
                return false;
            }
            //bishop
            if(canMoveTo(side, "|WBishop|", kingMoveTo, board))
            {
                side = "Black";
                return false;
            }
            //knight
            if(canMoveTo(side, "|WKnight|", kingMoveTo, board))
            {
                side = "Black";
                return false;
            }
            //queen
            if(canMoveTo(side, "|WQueen |", kingMoveTo, board))
            {
                side = "Black";
                return false;
            }
            //king
            if(canMoveTo(side, "| WKing |", kingMoveTo, board))
            {
                side = "Black";
                return false;
            }
            //pawn
            if(canMoveToPawn(side, "| WPawn |", kingMoveTo, board))
            {
                side = "Black";
                return false;
            }
            side = "Black";
        }
        return true;
    }
    /*
     * returns true if can move to spot
     * returns false if cannot move to spot
     */
    public boolean canMoveTo(String side, String nameOfPiece, int kingMoveTo, Board board)
    {
        //System.out.println(side + nameOfPiece + " z " + kingMoveTo);
        //if(side.equals("White"))
        //    side = "Black";
        //else
        //    side = "White";
        int loc1 = -1, loc2 = -1, hasFound = 0;
        Piece checking = new Piece();
        for(int i = 0; i < board.lengthOfBoard() && hasFound < 2; i++) //finding spots of Knights
        {
            checking = board.getPiece(i);
            try
            {
                if(checking.getNameOfPiece().equals(nameOfPiece))
                {
                    if(hasFound == 0)
                    {
                        //System.out.print(checking.getNameOfPiece());
                        loc1 = i;
                        hasFound++;
                    }
                    else if(hasFound != loc1)
                    {
                        //System.out.print(checking.getNameOfPiece());
                        loc2 = i;
                        hasFound++;
                    }
                }
            }catch(NullPointerException e){}
        }
        //System.out.println(nameOfPiece + "1 canMove " + isValidMoveNoKing(side, loc1, kingMoveTo, board));
        //if(hasFound > 1)
        //    System.out.println("Found cm " + nameOfPiece + loc1 + " " + loc2 + " " + kingMoveTo);
        //else if(hasFound > 0)
        //    System.out.println("Found cm " + nameOfPiece + loc1 + " " + kingMoveTo);
        
        try
        {
            board.getPiece(loc1).returnValue();
            //System.out.println(board.getPiece(loc1).returnValue());
            //System.out.println(side);
            if(isValidMoveNoKing(side, loc1, kingMoveTo, board))
                return true;
        }catch(ArrayIndexOutOfBoundsException e){}
        try
        {
            board.getPiece(loc2).returnValue();
            if(isValidMoveNoKing(side, loc2, kingMoveTo, board))
                return true;
        }catch(ArrayIndexOutOfBoundsException e){}
        return false;
    }
    public boolean canMoveToPawn(String side, String nameOfPiece, int kingMoveTo, Board board)
    {
        int loc1 = -1, loc2 = -1, loc3 = -1, loc4 = -1, loc5 = -1, loc6 = -1, loc7 = -1, loc8 = -1, hasFound = -1;
        Piece checking = new Piece();
        for(int i = 0; i < board.lengthOfBoard() && hasFound < 8; i++) //finding spots of Knights
        {
            checking = board.getPiece(i);
            //System.out.println(checking.getNameOfPiece());
            try
            {
                if(checking.getNameOfPiece().equals(nameOfPiece))
                {
                    if(hasFound == 0)
                    {
                        loc1 = i;
                        hasFound++;
                    }
                    else if(hasFound == 1 && loc1 != i)
                    {
                        loc2 = i;
                        hasFound++;
                    }
                    else if(hasFound == 2 && loc1 != i && loc2 != i)
                    {
                        loc3 = i;
                        hasFound++;
                    }
                    else if(hasFound == 3 && loc1 != i && loc2 != i && loc3 != i)
                    {
                        loc4 = i;
                        hasFound++;
                    }
                    else if(hasFound == 4 && loc1 != i && loc2 != i && loc3 != i && loc4 != i)
                    {
                        loc5 = i;
                        hasFound++;
                    }
                    else if(hasFound == 5 && loc1 != i && loc2 != i && loc3 != i && loc4 != i && loc5 != i)
                    {
                        loc6 = i;
                        hasFound++;
                    }
                    else if(hasFound == 6 && loc1 != i && loc2 != i && loc3 != i && loc4 != i && loc5 != i && loc6 != i)
                    {
                        loc7 = i;
                        hasFound++;
                    }
                    else if(hasFound == 7 && loc1 != i && loc2 != i && loc3 != i && loc4 != i && loc5 != i && loc6 != i && loc7 != i)
                    {
                        loc8 = i;
                        hasFound++;
                    }
                }
            }catch(NullPointerException e){}
        }
        
        //System.out.println("Found cmp " + nameOfPiece + loc1 + loc2 + loc3 + loc4 + loc5 + loc6 + loc7 + loc8);
        
        try{
            board.getPiece(loc1).returnValue();
            if(isValidMoveNoKing(side, loc1, kingMoveTo, board))
               return true;
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
            board.getPiece(loc2).returnValue();
            if (isValidMoveNoKing(side, loc2, kingMoveTo, board))
                return true;
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
            board.getPiece(loc3).returnValue();
            if (isValidMoveNoKing(side, loc3, kingMoveTo, board))
                return true;
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
            board.getPiece(loc4).returnValue();
            if (isValidMoveNoKing(side, loc4, kingMoveTo, board))
                return true;
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
            board.getPiece(loc5).returnValue();
            if (isValidMoveNoKing(side, loc5, kingMoveTo, board))
                return true;
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
            board.getPiece(loc6).returnValue();
            if (isValidMoveNoKing(side, loc6, kingMoveTo, board))
                return true;
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
            board.getPiece(loc7).returnValue();
            if (isValidMoveNoKing(side, loc7, kingMoveTo, board))
                return true;
        }catch(ArrayIndexOutOfBoundsException e){}
        try{
            board.getPiece(loc8).returnValue();
            if (isValidMoveNoKing(side, loc8, kingMoveTo, board))
                return true;
        }catch(ArrayIndexOutOfBoundsException e){}
        return false;
    }
}