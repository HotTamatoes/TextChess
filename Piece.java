
/**
 * This class defines defines Pieces on the board, what they need and how they are modified
 * 
 * @author Cameron Holland
 * @version 28/3/17
 */
public class Piece
{
    private String nameOfPiece;
    private int pointValue;
    public Piece(String makeDisPiece, int makePointValue)
    {
        nameOfPiece = makeDisPiece;
        pointValue = makePointValue;
    }
    public Piece()
    {
        nameOfPiece = "";
        pointValue = 0;
    }
    
    public void makeName(String make)
    {
        nameOfPiece = make;
    }
    public void makeValue(int val)
    {
        pointValue = val;
    }
    
    public int returnValue()
    {
        //System.out.println(nameOfPiece + " " + pointValue + "   ");
        return pointValue;
    }
    
    public String getNameOfPiece()
    {
        return nameOfPiece;
    }
    
    public boolean equals(Piece other)
    {
        return this.nameOfPiece.equals(other.getNameOfPiece());
    }
    
    public String toString()
    {
        return nameOfPiece;
    }
}
