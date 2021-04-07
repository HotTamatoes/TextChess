import java.util.ArrayList;
/**
 * creates 4 arraylists that help print out the board - mostly for textual interface
 * 
 * @author Cameron Holland 
 * @version 28/3/17
*/
public class Board
{
    private ArrayList<Piece> board = new ArrayList<Piece>();
    private ArrayList<Boolean> isBlackPiece = new ArrayList<Boolean>();
    private ArrayList<Boolean> isWhitePiece = new ArrayList<Boolean>();
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    
    public Board()
    {
        makeBoard();
        Black player2 = new Black(pieces, isBlackPiece);
        addBlankSpace();
        White player1 = new White(pieces, isWhitePiece);
        //printLists(board, isWhitePiece, isBlackPiece, pieces);
    }
    
    public String returnValues(String side)
    {
        int add = 0;
        if(side.equals("White"))
        {
            for(int i = 0; i < board.size(); i++)
            {
                if(isWhitePiece.get(i))
                {
                    try
                    {
                        //System.out.print(i + " ");
                        add += pieces.get(i).returnValue();
                    }catch(NullPointerException e){}
                }
            }
        }
        else if(side.equals("Black"))
        {
            assert add == 0 : "Hi";
            for(int i = 0; i < board.size(); i++)
            {
                if(isBlackPiece.get(i))
                {
                    try
                    {
                        add += pieces.get(i).returnValue();
                    }catch(NullPointerException e){}
                }
            }
        }
        return side + ": " + add + " points";
    }
    
    public boolean isWhitePiece(int spot)
    {
        return isWhitePiece.get(spot);
    }
    public boolean isBlackPiece(int spot)
    {
        return isBlackPiece.get(spot);
    }
    
    public void printLists(ArrayList<Piece> spool, ArrayList<Boolean> spoof, ArrayList<Boolean> spule, ArrayList<Piece> thisNameDontMatter)
    {
        System.out.println(spool);
        System.out.println(spoof);
        System.out.println(spule);
        System.out.println(thisNameDontMatter);
    }
    
    public Piece getPiece(int spot)
    {
        return pieces.get(spot);
    }
    public void setIsWhitePiece(int spot, boolean item)
    {
        isWhitePiece.set(spot, item);
    }
    public void setIsBlackPiece(int spot, boolean item)
    {
        isBlackPiece.set(spot, item);
    }
    public void setPiece(int spot, Piece item)
    {
        pieces.set(spot, item);
    }
    public void addBoard(int spot, Piece item)
    {
        board.add(spot, item);
    }
    
    public int lengthOfBoard()
    {
        return pieces.size();
    }
    
    public void makeBoard()
    {
        int counter = 0;
        Piece blank = new Piece("|_______|", 0);
        Piece black = new Piece("|#######|", 0);
        for(int i = 0; i < 64; i++)
        {
            if(counter%2 == 0)
                addBoard(i, blank);
            else
                addBoard(i, black);
            counter++;
            //7 15 23 31 39 47 55 63
            if((i+1)%8 == 0)
            {
                counter++;
                //addPiece(i, new Piece(Integer.toString(i),0));
            }
        }
        for(int j = 0; j < 64; j++)
            isWhitePiece.add(j, false);
        for(int k = 0; k < 64; k++)
            isBlackPiece.add(k, false);
    }
    public void addBlankSpace()
    {
        Piece blank = new Piece("", 0);
        for(int i = 16; i < 64; i++)
            pieces.add(i, blank);
    }
    
    public void boardPrint() //- GUI-ish
    {
        int boardSize = 8;
        for(int i = 0; i < boardSize; i++)
        {
            //top row
            System.out.print("   ");
            for(int j = 0; j < boardSize-1; j++)
            {
                System.out.print(board.get((i*boardSize) + j) + "   ");
            }
            System.out.println(board.get((i*boardSize) + boardSize-1));
            //middle row
            System.out.print(boardSize - i + "  ");
            for(int j = 0; j < boardSize-1; j++)
            {
                if(isWhitePiece.get((i*boardSize) + j)||isBlackPiece.get((i*boardSize) + j))
                    System.out.print(pieces.get((i*boardSize) + j) + "   ");
                else
                    System.out.print(board.get((i*boardSize) + j) + "   ");
            }
            if(isWhitePiece.get((i*boardSize) + boardSize-1)||isBlackPiece.get((i*boardSize) + boardSize-1))
                System.out.println(pieces.get((i*boardSize) + boardSize-1));
            else
                System.out.println(board.get((i*boardSize) + boardSize-1));
            //bottom row
            System.out.print("   ");
            for(int j = 0; j < boardSize-1; j++)
            {
                System.out.print(board.get((i*boardSize) + j) + "   ");
            }
            System.out.println(board.get((i*boardSize) + boardSize-1));
            System.out.println();
        }
        System.out.println("       A           B           C           D           E           F           G           H");
        //printLists(board, isWhitePiece, isBlackPiece, pieces);
        System.out.println(returnValues("White") + " " + returnValues("Black"));
    }
}