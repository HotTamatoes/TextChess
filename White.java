import java.util.ArrayList;
/**
 * Builds all the white pieces and adds them to the pieces
 * 
 * @author Cameron Holland
 * @version 28/3/17
 */
public class White
{
    Piece rook1 = new Piece("| WRook |", 5);
    Piece knight1 = new Piece("|WKnight|", 3);
    Piece bishop1 = new Piece("|WBishop|", 3);
    Piece queen = new Piece("|WQueen |", 9);
    Piece king = new Piece("| WKing |", 1);
    Piece bishop2 = new Piece("|WBishop|", 3);
    Piece knight2 = new Piece("|WKnight|", 3);
    Piece rook2 = new Piece("| WRook |", 5);
    Piece pawn1 = new Piece("| WPawn |", 1);
    Piece pawn2 = new Piece("| WPawn |", 1);
    Piece pawn3 = new Piece("| WPawn |", 1);
    Piece pawn4 = new Piece("| WPawn |", 1);
    Piece pawn5 = new Piece("| WPawn |", 1);
    Piece pawn6 = new Piece("| WPawn |", 1);
    Piece pawn7 = new Piece("| WPawn |", 1);
    Piece pawn8 = new Piece("| WPawn |", 1);
    public White(ArrayList<Piece> pieces, ArrayList<Boolean> isWhitePiece)
    {
        addWhitePiecesTo(pieces, isWhitePiece);
    }
    
    public void addPiece(int spot, Piece item, ArrayList<Piece> pieces)
    {
        pieces.set(spot, item);
    }
    public void showisWhitePiece(int spot, boolean troo, ArrayList<Boolean> isWhitePiece)
    {
        isWhitePiece.set(spot, troo);
    }
    
    public void addWhitePiecesTo(ArrayList<Piece> pieces, ArrayList<Boolean> isWhitePiece)
    {
        addPiece(63, rook1, pieces);
        showisWhitePiece(63, true, isWhitePiece);
        addPiece(62, knight1, pieces);
        showisWhitePiece(62, true, isWhitePiece);
        addPiece(61, bishop1, pieces);
        showisWhitePiece(61, true, isWhitePiece);
        addPiece(60, king, pieces);
        showisWhitePiece(60, true, isWhitePiece);
        addPiece(59, queen, pieces);
        showisWhitePiece(59, true, isWhitePiece);
        addPiece(58, bishop2, pieces);
        showisWhitePiece(58, true, isWhitePiece);
        addPiece(57, knight2, pieces);
        showisWhitePiece(57, true, isWhitePiece);
        addPiece(56, rook2, pieces);
        showisWhitePiece(56, true, isWhitePiece);
        addPiece(55, pawn1, pieces);
        showisWhitePiece(55, true, isWhitePiece);
        addPiece(54, pawn2, pieces);
        showisWhitePiece(54, true, isWhitePiece);
        addPiece(53, pawn3, pieces);
        showisWhitePiece(53, true, isWhitePiece);
        addPiece(52, pawn4, pieces);
        showisWhitePiece(52, true, isWhitePiece);
        addPiece(51, pawn5, pieces);
        showisWhitePiece(51, true, isWhitePiece);
        addPiece(50, pawn6, pieces);
        showisWhitePiece(50, true, isWhitePiece);
        addPiece(49, pawn7, pieces);
        showisWhitePiece(49, true, isWhitePiece);
        addPiece(48, pawn8, pieces);
        showisWhitePiece(48, true, isWhitePiece);
        //System.out.println(pawn8.returnValue());
    }
}
